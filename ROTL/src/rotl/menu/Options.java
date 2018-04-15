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

	static final String menuSectionImageSrc = "..\\resources\\images\\BGoption.jpg";
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

	public void setOptions() {

		
	}
}