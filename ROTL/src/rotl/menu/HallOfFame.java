package rotl.menu;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Vector;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import javafx.util.Pair;

import static java.lang.Math.*;

public class HallOfFame implements MenuOption {

    static final String menuSectionImageSrc = "..\\resources\\images\\BGHallOfFlame.jpg";
    private static final Integer contentFontSize = 30;
    private static HallOfFame single_instance = null;
    private static Vector<Pair<String, Integer>> history =
            new Vector<Pair<String, Integer>>();

    private Scanner scanner;

    private HallOfFame() {
    }

    public static HallOfFame getHallOfFame() {
        if (single_instance == null)
            single_instance = new HallOfFame();

        return single_instance;
    }

    public void setHallOfFame(Pane root) {

        try (InputStream is = Files.newInputStream(Paths.get(menuSectionImageSrc))) {
            ImageView img = new ImageView(new Image(is));
            img.setFitWidth(root.getMaxWidth() + 75);
            img.setFitHeight(root.getMaxHeight());
            root.getChildren().add(img);

            Text back = createBackButton(root);

            Text title = createTitle(root);

            Vector<Object> content = createContent(root);

            back.setOnMouseReleased(event -> {
                root.getChildren().removeAll(img, back, title, (Text) content.get(0),
                        (Text) content.get(1));
            });

        } catch (IOException e) {
            System.out.println("Couldn't load image...");
        }
    }

    private void readHistory() {

        String s = null;
        try {
            scanner = new Scanner(new File("..\\resources\\files\\HallOfFames.txt"));
            while (scanner.hasNextLine()) {
                s = scanner.nextLine();

                Pair<String, Integer> aux =
                        new Pair<String, Integer>(s.substring(0, s.indexOf('#') - 1),
                                Integer.parseInt(s.substring(s.indexOf('#') + 1,
                                        s.lastIndexOf('#'))));
                history.add(aux);
            }
        } catch (FileNotFoundException e) {
        	System.out.println("Couldn't load text ...");
            e.printStackTrace();
        }
    }

    private void processingHistory() {

        Comparator<Pair<String, Integer>> comparator = new PairComparator();
        Collections.sort(history, comparator);
    }

    @Override
    public Text createBackButton(Pane root) {

        LinearGradient gradient = new LinearGradient(0, 0, 1, 0,
                true, CycleMethod.NO_CYCLE, new Stop[]{
                new Stop(0, Color.DEEPSKYBLUE),
                new Stop(0.5, Color.BLACK),
                new Stop(0.5, Color.BLACK),
                new Stop(1, Color.DEEPSKYBLUE)

        });


        Text text = new Text("BACK");
        text.setFill(Color.DARKGREY);
        text.setFont(Font.font("Neuropol", FontWeight.SEMI_BOLD, backButtonFontSize));
        text.setTranslateX(100);
        text.setTranslateY(50);

        root.getChildren().add(text);
        text.setOnMouseEntered(event -> {
            text.setFill(gradient);
            text.setFill(Color.WHITE);
        });

        text.setOnMouseExited(event -> {
            text.setFill(Color.BLACK);
            text.setFill(Color.DARKGREY);
        });
        text.setOnMousePressed(event -> {
            text.setFont(Font.font("Neuropol", FontWeight.BOLD, backButtonFontSize * 1.1));
        });

        return text;
    }

    @Override
    public Text createTitle(Pane root) {

        Text text = new Text("H A L L   O f   F L A M E S ");
        text.setFill(Color.DEEPSKYBLUE);
        text.setFont(Font.font("Neuropol", FontWeight.EXTRA_BOLD, titleFontSize));
        text.setTranslateX(300);
        text.setTranslateY(75);
        root.getChildren().add(text);

        return text;
    }

    @Override
    public Vector<Object> createContent(Pane root) {

        readHistory();

        processingHistory();

        String content1 = new String("");
        String content2 = new String("");

        for (Integer i = 0; i < min(history.size(), 10); ++i) {

            Pair<String, Integer> aux = history.get(i);
            content1 += i + 1;
            content1 += ".  ";
            content1 += aux.getKey();
            content1 += '\n';

            content2 += aux.getValue();
            content2 += '\n';
        }


        Text text1 = new Text(content1);
        text1.setFill(Color.LIGHTGOLDENRODYELLOW);
        text1.setFont(Font.font("Neuropol X", FontWeight.NORMAL, contentFontSize * 1.2));
        text1.setTranslateX(150);
        text1.setTranslateY(200);
        text1.setVisible(true);
        root.getChildren().add(text1);

        Text text2 = new Text(content2);
        text2.setFill(Color.LIGHTGOLDENRODYELLOW);
        text2.setFont(Font.font("Neuropol X", FontWeight.NORMAL, contentFontSize * 1.2));
        text2.setTranslateX(750);
        text2.setTranslateY(200);
        text2.setVisible(true);
        root.getChildren().add(text2);

        Vector<Object> objVector = new Vector<Object>(2);
        objVector.add(text1);
        objVector.add(text2);

        return objVector;
    }
}

class PairComparator implements Comparator<Pair<String, Integer>> {
    public int compare(Pair<String, Integer> o1, Pair<String, Integer> o2) {
        return -(o1.getValue().compareTo(o2.getValue()));
    }
}