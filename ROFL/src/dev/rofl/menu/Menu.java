import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.application.*;
import javafx.stage.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.*;
import javafx.util.Pair;

public class Menu extends Application {

    private static final String menuImageSrc = "img\\BG2.png";
    private static final Pair<Integer, Integer> paneDimensions =
            new Pair<Integer, Integer>(1080, 620);
    private static final String titleName = "Return of the\nLegends";
    private static final String panelName = "Return of the Legends - Game";
    private static final Pair<Integer, Integer> titlePosition =
            new Pair<Integer, Integer>(50, 150);
    private static final Integer titleFontSize = 50;
    //	private static final Pair <Pair <Integer, Integer>, Integer> titleBoxDimensions =
//			new Pair <Pair <Integer, Integer>, Integer>
//			(new Pair<Integer, Integer> (475, 150), 2);
    private static final Pair<Integer, Integer> menuPosition =
            new Pair<Integer, Integer>(100, 300);
    private static final Pair<Integer, Integer> menuItemDimensions =
            new Pair<Integer, Integer>(250, 35);
    private static final Integer menuItemFontSize = 20;

    public static void main(String[] args) {

        launch(args);
    }

    private Parent createContent() {
        Pane root = new Pane();

        root.setPrefSize(paneDimensions.getKey(), paneDimensions.getValue());

        try (InputStream is = Files.newInputStream(Paths.get(menuImageSrc))) {
            ImageView img = new ImageView(new Image(is));
            img.setFitWidth(root.getLayoutX());
            img.setFitHeight(root.getLayoutY());
            root.getChildren().add(img);
        } catch (IOException e) {
            System.out.println("Couldn't load image...");
        }

        Title title = new Title(titleName);
        title.setTranslateX(titlePosition.getKey());
        title.setTranslateY(titlePosition.getValue());

        MenuBox vbox = new MenuBox(
                new MenuItem("PLAY", root),
                new MenuItem("HALL OF FLAME", root),
                new MenuItem("INSTRUCTIONS", root),
                new MenuItem("OPTIONS", root),
                new MenuItem("CREDITS", root),
                new MenuItem("EXIT", root));
        vbox.setTranslateX(menuPosition.getKey());
        vbox.setTranslateY(menuPosition.getValue());

        root.getChildren().addAll(title, vbox);
        root.setMaxSize(paneDimensions.getKey() * 1.2, paneDimensions.getValue() * 1.2);
        root.setMinSize(paneDimensions.getKey() * 0.8, paneDimensions.getValue() * 0.8);

        return root;

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(createContent());
        primaryStage.setTitle(panelName);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private static class Title extends StackPane {
        public Title(String name) {
//			Rectangle bg = new Rectangle(titleBoxDimensions.getKey().getKey(),
//					titleBoxDimensions.getKey().getValue());
//			bg.setStroke(Color.WHITE);
//			bg.setStrokeWidth(titleBoxDimensions.getValue());
//			bg.setFill(null);

            Text text = new Text(name);
            text.setFill(Color.LIGHTGOLDENRODYELLOW);
            text.setFont(Font.font("Neuropol", FontWeight.BOLD, titleFontSize));
            text.setTranslateX(titlePosition.getKey());

            setAlignment(Pos.CENTER);
//			getChildren().addAll(bg,text);
            getChildren().add(text);
        }
    }

    private static class MenuBox extends VBox {
        public MenuBox(MenuItem... items) {
            getChildren().add(createSeperator());

            for (MenuItem item : items) {
                getChildren().addAll(item, createSeperator());
            }
        }

        private Line createSeperator() {
            Line sep = new Line();
            sep.setEndX(menuItemDimensions.getKey());
            sep.setStroke(Color.DARKGREY);
            return sep;
        }

    }

    private static class MenuItem extends StackPane {
        public MenuItem(String name, Pane root) {
            LinearGradient gradient = new LinearGradient(0, 0, 1, 0,
                    true, CycleMethod.NO_CYCLE, new Stop[]{
                    new Stop(0, Color.DEEPSKYBLUE),
                    new Stop(0.5, Color.BLACK),
                    new Stop(0.5, Color.BLACK),
                    new Stop(1, Color.DEEPSKYBLUE)

            });

            Rectangle bg = new Rectangle(menuItemDimensions.getKey(),
                    menuItemDimensions.getValue());
            bg.setOpacity(0.25);

            Text text = new Text(name);
            text.setFill(Color.DARKGREY);
            text.setFont(Font.font("Neuropol", FontWeight.SEMI_BOLD, menuItemFontSize));
            setAlignment(Pos.CENTER);
            getChildren().addAll(bg, text);

            setOnMouseEntered(event -> {
                bg.setFill(gradient);
                text.setFill(Color.WHITE);
            });

            setOnMouseExited(event -> {
                bg.setFill(Color.BLACK);
                text.setFill(Color.DARKGREY);
            });

            setOnMousePressed(event -> {
                bg.setFill(Color.DARKBLUE);
            });

            setOnMouseReleased(event -> {
                bg.setFill(gradient);

                if (name == "EXIT") {
                    setVisible(false);
                    // finally, call this to really exit.
                    // i/o libraries such as WiiRemoteJ need this.
                    // also, this is what swing does for JFrame.EXIT_ON_CLOSE
                    System.exit(0);
                }
                if (name == "HALL OF FLAME") {
                    HallOfFlame hof = HallOfFlame.getHallOfFlame();
                    hof.setHallOfFlame(root);
                }
                if (name == "INSTRUCTIONS") {
                    Instructions instr = Instructions.getInstructions();
                    instr.setInstructions(root);
                }
                if (name == "OPTIONS") {
                    Options op = Options.getOptions();
                    op.setOptions(root);
                }
                if (name == "CREDITS") {
                    Credits credits = Credits.getCredits();
                    credits.setCredits(root);
                }
            });
        }
    }
}