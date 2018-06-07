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
	private static String content1 = "", content2 = "";

	private static int screenWidth, screenHeight;

	private static BufferedImage backgroundImg;
	private static BufferedImage closeImg;

	private static List<Pair<String, Integer>> history = new ArrayList<Pair<String, Integer>>();

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

		Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/cursor_final.png"));
		Point hotspot = new Point(0, 0);
		Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(image, hotspot, "pencil");
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
		g.drawString("HallOfFame", (int) (screenWidth * 30 / 100), (int) (screenHeight * 15 / 100));
		g.drawImage(closeImg, closeImgPosition.x, closeImgPosition.y, closeImgDimensionsX, closeImgDimensionsY, this);
		g.setFont(new Font("Neuropol X", Font.BOLD, (int) (fontSize * 1.5)));
		drawString(g, content1, (int) (screenWidth * 5 / 100), (int) (screenHeight * 25 / 100));
		drawString(g, content2, (int) (screenWidth * 65 / 100), (int) (screenHeight * 25 / 100));
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

		processingHistory();

		for (Integer i = 0; i < min(history.size(), 10); ++i) {

			Pair<String, Integer> aux = history.get(i);
			content1 += i + 1;
			content1 += ".  ";
			content1 += aux.getKey();
			content1 += '\n';

			content2 += aux.getValue();
			content2 += '\n';
		}
	}

	private void readHistory() {

		String s = null;
		Scanner scanner;
		try {
			scanner = new Scanner(new File("./resources/files/HallOfFames.txt"));
			while (scanner.hasNextLine()) {
				s = scanner.nextLine();

				Pair<String, Integer> aux = new Pair<String, Integer>(s.substring(0, s.indexOf('#') - 1),
						Integer.parseInt(s.substring(s.indexOf('#') + 1, s.lastIndexOf('#'))));
				history.add(aux);
			}
		} catch (FileNotFoundException e) {
			System.err.println("Couldn't load text ...");
			e.printStackTrace();
		}
	}

	private void processingHistory() {

		final Comparator<Pair<String, Integer>> comparator = (o1, o2) -> {
			return -(o1.getValue().compareTo(o2.getValue()));
		};
		
		Collections.sort(history, comparator);
	}
}