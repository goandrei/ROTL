import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

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
    
	@Override
	public void createBackButton(Pane root, ImageView img) {
    	
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
        	text.setFont(Font.font("Neuropol", FontWeight.BOLD, backButtonFontSize*1.1));
        });

        text.setOnMouseReleased(event -> {
        	text.setFill(gradient);
            root.getChildren().removeAll(text, img);
        });
    }

	public Credits(Pane root) {
		
        try (InputStream is = Files.newInputStream(Paths.get(menuImageSrc))) {
            ImageView img = new ImageView(new Image(is));
            img.setFitWidth(root.getMaxWidth() + 75);
            img.setFitHeight(root.getMaxHeight());
            root.getChildren().add(img);
            
            createBackButton(root, img);
            
        } catch (IOException e) {
            System.out.println("Couldn't load image...");
        }
	}
}