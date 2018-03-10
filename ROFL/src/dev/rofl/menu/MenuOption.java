import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public interface MenuOption {
	
    static final String menuImageSrc = "img\\TheBrogrammersLogo.png";
    static final Integer backButtonFontSize = 25;
    
    public void createBackButton(Pane root, ImageView img);
}
