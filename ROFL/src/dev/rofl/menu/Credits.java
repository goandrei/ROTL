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

public class Credits implements MenuOption {

    private static final String menuSectionImageSrc = "img\\BGcredits.jpg",
            nextImageSrc = "img\\Next.png", logoSrc = "img\\tb_logo.png";
    private static final Integer contentFontSize = 30;
    private static Credits single_instance = null;
    private static String content = "Team : - Bogdan Anghelache\n"
            + "              - Andrei Gorneanu\n"
            + "              - Marian Lupascu\n"
            + "              - Stefan Stevoaca\n"
            + "              - Stefan Nita\n";
    private static Integer step = 1;

    private Credits() {
    }

    public static Credits getCredits() {
        if (single_instance == null)
            single_instance = new Credits();

        return single_instance;
    }

    public void setCredits(Pane root) {

        step = 1;
        try (InputStream is = Files.newInputStream(Paths.get(menuSectionImageSrc))) {
            ImageView img = new ImageView(new Image(is));
            img.setFitWidth(root.getMaxWidth() + 75);
            img.setFitHeight(root.getMaxHeight());
            root.getChildren().add(img);

            Text back = createBackButton(root);

            Text title = createTitle(root);

            Vector<Object> content = createContent(root);

            ImageView next = createNextButton(root);

            Vector<Object> logo = createLogo(root);

            back.setOnMouseReleased(event -> {
                root.getChildren().removeAll(img, back, title, (Text) content.get(0), next,
                        (ImageView) logo.get(0), (Text) logo.get(1));
            });

            next.setOnMouseReleased(event -> {

            });

        } catch (IOException e) {
            System.out.println("Couldn't load image...");
        }
    }

    public ImageView createNextButton(Pane root) {

        try (InputStream is = Files.newInputStream(Paths.get(nextImageSrc))) {
            ImageView img = new ImageView(new Image(is));
            img.setFitWidth(75);
            img.setFitHeight(75);
            img.setTranslateX(975);
            img.setTranslateY(525);
            img.setOpacity(0.9);
            root.getChildren().add(img);

            img.setOnMouseEntered(event -> {
                img.setOpacity(1);
                img.setFitWidth(85);
                img.setFitHeight(85);
                img.setTranslateX(970);
                img.setTranslateY(520);
            });

            img.setOnMouseExited(event -> {
                img.setFitWidth(75);
                img.setFitHeight(75);
                img.setTranslateX(975);
                img.setTranslateY(525);
                img.setOpacity(0.9);
            });

            return img;

        } catch (IOException e) {
            System.out.println("Couldn't load image...");
            return null;
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

        Text text = new Text("C R E D I T S ");
        text.setFill(Color.DEEPSKYBLUE);
        text.setFont(Font.font("Neuropol", FontWeight.EXTRA_BOLD, titleFontSize));
        text.setTranslateX(350);
        text.setTranslateY(50);
        root.getChildren().add(text);

        return text;
    }

    @Override
    public Vector<Object> createContent(Pane root) {

        Text text = new Text(content);
        text.setFill(Color.LIGHTGOLDENRODYELLOW);
        text.setFont(Font.font("Neuropol X", FontWeight.NORMAL, contentFontSize));
        text.setTranslateX(100);
        text.setTranslateY(300);
        root.getChildren().add(text);

        step++;

        Vector<Object> objVector = new Vector<Object>(2);
        objVector.add(text);

        return objVector;
    }

    public Vector<Object> createLogo(Pane root) {

        try (InputStream is = Files.newInputStream(Paths.get(logoSrc))) {
            ImageView img = new ImageView(new Image(is));
            img.setFitWidth(200);
            img.setFitHeight(100);
            img.setTranslateX(400);
            img.setTranslateY(100);
            img.setOpacity(0.75);
            root.getChildren().add(img);

            img.setOnMouseEntered(event -> {
                img.setFitWidth(250);
                img.setFitHeight(150);
                img.setTranslateX(375);
                img.setTranslateY(75);
                img.setOpacity(1);
            });

            img.setOnMouseExited(event -> {
                img.setFitWidth(200);
                img.setFitHeight(100);
                img.setTranslateX(400);
                img.setTranslateY(100);
                img.setOpacity(0.75);
            });

            Text text = new Text("The Brogramers LTD");
            text.setFill(Color.BLACK);
            text.setFont(Font.font("Neuropol", FontWeight.EXTRA_BOLD, contentFontSize));
            text.setTranslateX(300);
            text.setTranslateY(250);
            root.getChildren().add(text);

            Vector<Object> objVector = new Vector<Object>(2);
            objVector.add(img);
            objVector.add(text);

            return objVector;

        } catch (IOException e) {
            System.out.println("Couldn't load image...");
            return null;
        }
    }
}