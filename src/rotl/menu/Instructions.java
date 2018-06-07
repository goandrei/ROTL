package rotl.menu;

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
import java.util.Scanner;

import javax.swing.JDialog;
import javax.swing.JPanel;
import rotl.utilities.Handler;
import rotl.utilities.ImageLoader;

public final class Instructions extends JPanel implements MenuOption {

	private static final long serialVersionUID = 1L;

	private static int state;

	private static int closeImgDimensionsX;
	private static int closeImgDimensionsY;
	private static Point closeImgPosition = new Point();

	private static int nextButtonDimensionsX;
	private static int nextButtonDimensionsY;
	private static Point nextButtonPosition = new Point();

	private static Handler handler;
	private static JDialog frame = new JDialog();

	private static Instructions single_instance = null;

	private static String content1 = "";
	private static String content2 = "";
	private static String content3 = "";

	private static int screenWidth, screenHeight;

	private static BufferedImage backgroundImg;
	private static BufferedImage closeImg;
	private static BufferedImage nextButton;
	
	private void readInstructions(String string) {
		
		Scanner scanner;
		if(string.equals("content1")) {
			
			try {
				scanner = new Scanner(new File("./resources/files/"+string + "Instructions.txt"));
				while (scanner.hasNextLine()) {
					content1 = content1 + scanner.nextLine() + '\n';
				}
			} catch (FileNotFoundException e) {
				System.err.println("Couldn't load text ...");
				e.printStackTrace();
			}
			return;
		}
		if(string.equals("content2")) {
			
			try {
				scanner = new Scanner(new File("./resources/files/"+string + "Instructions.txt"));
				while (scanner.hasNextLine()) {
					content2 = content2 + scanner.nextLine() + '\n';
				}
			} catch (FileNotFoundException e) {
				System.err.println("Couldn't load text ...");
				e.printStackTrace();
			}
			return;
		}
		if(string.equals("content3")) {
			
			try {
				scanner = new Scanner(new File("./resources/files/"+string + "Instructions.txt"));
				while (scanner.hasNextLine()) {
					content3 = content3 + scanner.nextLine() + '\n';
				}
			} catch (FileNotFoundException e) {
				System.err.println("Couldn't load text ...");
				e.printStackTrace();
			}
			return;
		}
	}

	private Instructions(Handler handler) {
		
		readInstructions("content1");
		readInstructions("content2");
		readInstructions("content3");

		Instructions.handler = handler;
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
		this.setInstructions();

		frame.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void focusLost(FocusEvent e) {
				
				frame.setVisible(false);
			}
		});

	}

	public static Instructions getInstructions(Handler handler) {
		if (single_instance == null) {
			single_instance = new Instructions(handler);
		}

		frame.setVisible(true);
		state = 1;

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
		g.drawString("Instructions", (int) (screenWidth * 30 / 100), (int) (screenHeight * 15 / 100));
		g.drawImage(closeImg, closeImgPosition.x, closeImgPosition.y, closeImgDimensionsX, closeImgDimensionsY, this);
		g.setFont(new Font("Neuropol X", Font.BOLD, fontSize));
		if (state == 1)
			drawString(g, content1, (int) (screenWidth * 5 / 100), (int) (screenHeight * 25 / 100));
		if (state == 2)
			drawString(g, content2, (int) (screenWidth * 5 / 100), (int) (screenHeight * 25 / 100));
		if (state == 3)
			drawString(g, content3, (int) (screenWidth * 5 / 100), (int) (screenHeight * 25 / 100));
		g.drawImage(nextButton, nextButtonPosition.x, nextButtonPosition.y, nextButtonDimensionsX,
				nextButtonDimensionsY, this);
	}

	private void setInstructions() {

		closeImgDimensionsX = (int) (screenWidth * 5.5 / 100);
		closeImgDimensionsY = (int) (screenHeight * 9.8 / 100);
		closeImgPosition.setLocation(screenWidth - closeImgDimensionsX, 0);

		nextButtonDimensionsX = (int) (screenWidth * 5.5 / 100);
		nextButtonDimensionsY = (int) (screenHeight * 9.8 / 100);
		nextButtonPosition.setLocation(screenWidth - nextButtonDimensionsX - (int) (screenWidth * 1 / 100),
				screenHeight - nextButtonDimensionsY - (int) (screenHeight * 2 / 100));

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
				if (nextButton != null) {
					Point me = e.getPoint();
					Rectangle bounds = new Rectangle(nextButtonPosition.x, nextButtonPosition.y, nextButtonDimensionsX,
							nextButtonDimensionsY);
					if (bounds.contains(me)) {
						if (state != 3) {
							state++;
							repaint();
						}
					}
				}
			}
		});

	}

	@Override
	public void init() {
		backgroundImg = ImageLoader.loadImage("/images/BGinstruction.jpg");
		closeImg = ImageLoader.loadImage("/images/closeImg.png");
		nextButton = ImageLoader.loadImage("/images/Next.png");
	}
}