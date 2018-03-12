import java.util.Vector;

import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public interface MenuOption {

    static final String menuSectionImageSrc = "img\\menuBG.jpg";
    static final Integer backButtonFontSize = 25;
    static final Integer titleFontSize = 35;

    public Text createBackButton(Pane root);

    public Text createTitle(Pane root);

    public Vector<Object> createContent(Pane root);
}
