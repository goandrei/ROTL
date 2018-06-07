package rotl.menu;

import static java.lang.Math.min;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javafx.util.Pair;
import rotl.utilities.Handler;
import rotl.utilities.ImageLoader;

public final class HallOfFame extends JPanel implements MenuOption {

	private static final long serialVersionUID = 1L;

	private static int closeImgDimensionsX;
	private static int closeImgDimensionsY;
	private static Point closeImgPosition = new Point();

	private static Handler handler;
	private static JDialog frame = new JDialog();
	private static HallOfFame single_instance = null;
	private static StringBuilder content = new StringBuilder("");

	private static int screenWidth, screenHeight;

	private static BufferedImage backgroundImg;
	private static BufferedImage closeImg;

	private static List<String> history = new ArrayList<>();

	private HallOfFame(Handler handler) {

		HallOfFame.handler = handler;
		screenWidth = (handler.getGame().getWidth() * 2) / 3;
		screenHeight = (handler.getGame().getHeight() * 2) / 3;

		frame.setPreferredSize(new Dimension(screenWidth, screenHeight));
		frame.setMaximumSize(new Dimension(screenWidth, screenHeight));
		frame.setMinimumSize(new Dimension(screenWidth, screenHeight));

		frame.setUndecorated(true);

		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setContentPane(this);
		frame.setVisible(true);

		final Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/cursor_final.png"));
		final Point hotspot = new Point(0, 0);
		final Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(image, hotspot, "pencil");
		frame.setCursor(cursor);

		this.init();
		this.setHallOfFame();

		frame.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void focusLost(FocusEvent arg0) {
				
				frame.setVisible(false);
			}
		});
	}

	public static HallOfFame getHallOfFame(Handler handler) {
		
		if (single_instance == null) {
			single_instance = new HallOfFame(handler);
		}

		frame.setVisible(true);
		
		return single_instance;
	}

	private void drawString(Graphics g, String text, int x, int y) {
		for (String line : text.split("\n"))
			g.drawString(line, x, y += g.getFontMetrics().getHeight());
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawImage(backgroundImg, 0, 0, screenWidth, screenHeight, this);
		g.setFont(new Font("Neuropol X", Font.BOLD, titleFontSize));
		g.setColor(Color.WHITE);
		g.drawString("Hall Of Fame", (int) (screenWidth * 25 / 100), (int) (screenHeight * 15 / 100));
		g.drawImage(closeImg, closeImgPosition.x, closeImgPosition.y, closeImgDimensionsX, closeImgDimensionsY, this);
		g.setFont(new Font("Neuropol X", Font.BOLD, (int) (fontSize * 1.5)));
		
		drawString(g, content.toString(), (int) (screenWidth * 15 / 100), (int) (screenHeight * 25 / 100));
	}

	private void setHallOfFame() {

		closeImgDimensionsX = (int) (screenWidth * 5.5 / 100);
		closeImgDimensionsY = (int) (screenHeight * 9.8 / 100);
		closeImgPosition.setLocation(screenWidth - closeImgDimensionsX, 0);

		addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (closeImg != null) {
					Point me = e.getPoint();
					Rectangle bounds = new Rectangle(closeImgPosition.x, closeImgPosition.y, closeImgDimensionsX,
							closeImgDimensionsY);
					if (bounds.contains(me)) {
						frame.setVisible(false);
					}
				}
			}
		});

	}

	@Override
	public void init() {

		backgroundImg = ImageLoader.loadImage("/images/BGHallOfFame.jpg");
		closeImg = ImageLoader.loadImage("/images/closeImg.png");

		readHistory();
		
		history.stream()
				.map(str -> str + "\n")
				.forEach(content::append);
	}

	private void readHistory() {

		String buffer = null;
		final Scanner scanner;
		
		try {
			
			scanner = new Scanner(new File("./resources/files/HallOfFames.txt"));
			
			while (scanner.hasNextLine()) {
				
				buffer = scanner.nextLine();
				history.add(buffer);
			}
			
		} catch (FileNotFoundException e) {
			System.err.println("Couldn't load text ...");
			e.printStackTrace();
		}
	}
}