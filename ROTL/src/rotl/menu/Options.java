package rotl.menu;

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

public class Options implements MenuOption {

    static final String menuSectionImageSrc = "img\\BGoption.jpg";
    private static final Integer contentFontSize = 30;
    private static Options single_instance = null;
    private static String content1 = "";
    private static String content2 = "";
    private static Integer step = 1;

    private Options() {
    }

    public static Options getOptions() {
        if (single_instance == null)
            single_instance = new Options();

        return single_instance;
    }

    public void setOptions(Pane root) {

        step = 1;
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

        Text text = new Text("O P T I O N S ");
        text.setFill(Color.DEEPSKYBLUE);
        text.setFont(Font.font("Neuropol", FontWeight.EXTRA_BOLD, titleFontSize));
        text.setTranslateX(350);
        text.setTranslateY(50);
        root.getChildren().add(text);

        return text;
    }

    @Override
    public Vector<Object> createContent(Pane root) {

        Text text1 = new Text(content1);
        Text text2 = new Text(content2);
        text1.setFill(Color.LIGHTGOLDENRODYELLOW);
        text1.setFont(Font.font("Neuropol X", FontWeight.NORMAL, contentFontSize));
        text1.setTranslateX(100);
        text1.setTranslateY(100);
        text1.setVisible(true);
        root.getChildren().add(text1);

        text2.setFill(Color.LIGHTGOLDENRODYELLOW);
        text2.setFont(Font.font("Neuropol X", FontWeight.NORMAL, contentFontSize));
        text2.setTranslateX(100);
        text2.setTranslateY(100);
        text2.setVisible(false);
        root.getChildren().add(text2);
        step++;

        Vector<Object> objVector = new Vector<Object>(2);
        objVector.add(text1);
        objVector.add(text2);

        return objVector;
    }
}