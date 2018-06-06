package rotl.statusBar;

import rotl.simulate.Arena;
import rotl.store.Store;
import rotl.utilities.Handler;
import rotl.utilities.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class StatusBar extends JPanel {

	private static final long serialVersionUID = 1L;

	private static StatusBar instance = null;

	private static int positionX;
	private static int positionY;
	private static Point position = new Point();

	private static int storeButtonX;
	private static int storeButtonY;
	private static int storeButtonXDimenssion;
	private static int storeButtonYDimenssion;
	private static Point storeButtonPosition = new Point();

	private static int infoRectDimensionsX;
	private static int infoRectDimensionsY;
	private static Point infoRectPosition = new Point();

	private static int gameinfoRectDimensionsX;
	private static int gameinfoRectDimensionsY;
	private static Point gameinfoRectPosition = new Point();

	private static int stockinfoRectDimensionsX;
	private static int stockinfoRectDimensionsY;
	private static Point stockinfoRectPosition = new Point();

	private static int arenaDimensionsX;
	private static int arenaDimensionsY;
	private static Point arenaPosition = new Point();
	
	private static int soldairDimensionsX;
	private static int soldairDimensionsY;
	private static Point soldairPosition = new Point();

	private static String userName = "Player 1";
	private static int gold = 1235;
	private static int score = 1234567;
	private static int waveNumber = 2;
	private static int enamyScore = 12345;

	private static Handler handler;
	private static JDialog frame = new JDialog();

	private static int screenWidth, screenHeight;

	private static BufferedImage closeImg;
	private static BufferedImage backgroundImg;
	private static BufferedImage storeButton;
	private static BufferedImage arena;
	
	public static int numberFighters = 100;
	public static int numberDefenders = 150;
	public static int numberWarriors = 77;
	
	private static BufferedImage Fighter;
	private static BufferedImage Defender;
	private static BufferedImage Warrior;


	private Arena arenaInstance;

	public static StatusBar getInstance(Handler handler) {

		if (instance == null) {
			instance = new StatusBar(handler);
		}
		frame.setVisible(true);
		return instance;
	}

	private StatusBar(Handler handler) {

		this.handler = handler;

		// get the parent screen size and get the modal's size
		screenWidth = (handler.getGame().getWidth() * 3) / 4;
		screenHeight = (handler.getGame().getHeight()) / 8;

		positionX = (handler.getGame().getWidth() - screenWidth) / 2;
		positionY = handler.getGame().getHeight() - screenHeight - 10;
		position.setLocation(positionX, positionY);

		storeButtonX = (int) (screenWidth * 80 / 100);
		storeButtonY = (int) (screenHeight * 15 / 100);
		storeButtonXDimenssion = (int) (screenWidth * 20 / 100);
		storeButtonYDimenssion = (int) (screenHeight * 70 / 100);
		storeButtonPosition.setLocation(storeButtonX, storeButtonY);

		infoRectPosition.setLocation((int) (screenWidth * 0.5 / 100), (int) (screenHeight * 5 / 100));
		infoRectDimensionsX = (int) (screenWidth * 22.5 / 100);
		infoRectDimensionsY = (int) (screenHeight * 90 / 100);

		gameinfoRectPosition.setLocation((int) (screenWidth * 23.5 / 100), (int) (screenHeight * 5 / 100));
		gameinfoRectDimensionsX = (int) (screenWidth * 22.5 / 100);
		gameinfoRectDimensionsY = (int) (screenHeight * 90 / 100);

		stockinfoRectPosition.setLocation((int) (screenWidth * 46.5 / 100), (int) (screenHeight * 5 / 100));
		stockinfoRectDimensionsX = (int) (screenWidth * 20 / 100);
		stockinfoRectDimensionsY = (int) (screenHeight * 90 / 100);
		
		soldairDimensionsX = 30;
		soldairDimensionsY = 30;
		soldairPosition.setLocation(stockinfoRectPosition.x + 15, stockinfoRectPosition.y - 2);

		arenaPosition.setLocation((int) (screenWidth * 68 / 100), (int) (screenHeight * 5 / 100));
		arenaDimensionsX = (int) (screenWidth * 10 / 100);
		arenaDimensionsY = (int) (screenHeight * 90 / 100);

		setModalSize();

		frame.setUndecorated(true);

		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setContentPane(this);
		frame.setVisible(true);
		frame.setLocation(position);

		Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/cursor_final.png"));
		Point hotspot = new Point(0, 0);
		Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(image, hotspot, "pencil");
		frame.setCursor(cursor);

		frame.setFocusable(true);
		frame.setAlwaysOnTop(true);

		Init();

		addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

				if (closeImg != null) {
					Point me = e.getPoint();
					Rectangle bounds = new Rectangle(storeButtonPosition.x, storeButtonPosition.y,
							storeButtonXDimenssion, storeButtonYDimenssion);
					if (bounds.contains(me)) {
						Store.getInstance(handler);
					}
				}

				if (arena != null) {
					Point me = e.getPoint();
					Rectangle bounds = new Rectangle(arenaPosition.x, arenaPosition.y,
							arenaDimensionsX, arenaDimensionsY);
					if (bounds.contains(me)) {
						arenaInstance = Arena.getInstance(handler);
					}
				}
			}
		});
	}

	public Arena getArenaInstance() {
		return arenaInstance;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawImage(backgroundImg, 0, 0, screenWidth, screenHeight, this);
		g.setFont(new Font("Neuropol X", Font.BOLD, 100));
		g.setColor(Color.WHITE);
		g.drawImage(storeButton, storeButtonX, storeButtonY, storeButtonXDimenssion, storeButtonYDimenssion, this);

		g.setColor(new Color(255, 255, 255, 100));
		g.fillRect(infoRectPosition.x, infoRectPosition.y, infoRectDimensionsX, infoRectDimensionsY);
		g.setFont(new Font("Neuropol X", Font.BOLD, 25));
		g.setColor(Color.WHITE);
		g.drawString("Player: " + userName, infoRectPosition.x + (int) (screenWidth * 1 / 100),
				infoRectPosition.y + (int) (screenHeight * 20 / 100));
		g.drawString("Gold: " + gold, infoRectPosition.x + (int) (screenWidth * 1 / 100),
				infoRectPosition.y + (int) (screenHeight * 50 / 100));
		g.drawString("Score: " + score, infoRectPosition.x + (int) (screenWidth * 1 / 100),
				infoRectPosition.y + (int) (screenHeight * 80 / 100));

		g.setColor(new Color(255, 255, 255, 100));
		g.fillRect(gameinfoRectPosition.x, gameinfoRectPosition.y, gameinfoRectDimensionsX, gameinfoRectDimensionsY);
		g.setColor(Color.WHITE);
		g.drawString("Wave no: " + waveNumber, gameinfoRectPosition.x + (int) (screenWidth * 1 / 100),
				infoRectPosition.y + (int) (screenHeight * 20 / 100));
		g.drawString("Enamy score: " + enamyScore, gameinfoRectPosition.x + (int) (screenWidth * 1 / 100),
				infoRectPosition.y + (int) (screenHeight * 50 / 100));
		g.drawString("Diverse: ", gameinfoRectPosition.x + (int) (screenWidth * 1 / 100),
				infoRectPosition.y + (int) (screenHeight * 80 / 100));

		g.setColor(new Color(255, 255, 255, 100));
		g.fillRect(stockinfoRectPosition.x, stockinfoRectPosition.y, stockinfoRectDimensionsX,
				stockinfoRectDimensionsY);

		g.setColor(Color.WHITE);
		g.drawImage(Fighter, soldairPosition.x, soldairPosition.y, soldairDimensionsX, soldairDimensionsY, this);
		g.drawString(" : " + numberFighters, soldairPosition.x + soldairDimensionsX,
				soldairPosition.y + (int) (screenHeight * 21 / 100));
		g.drawImage(Defender, soldairPosition.x, soldairPosition.y + soldairDimensionsY,
				soldairDimensionsX, soldairDimensionsY, this);
		g.drawString(" : " + numberDefenders, soldairPosition.x + soldairDimensionsX,
				soldairPosition.y + (int) (screenHeight * 21 / 100) + soldairDimensionsY);
		g.drawImage(Warrior, soldairPosition.x, soldairPosition.y + 2* soldairDimensionsY,
				soldairDimensionsX, soldairDimensionsY, this);
		g.drawString(" : " + numberWarriors, soldairPosition.x + soldairDimensionsX,
				soldairPosition.y + (int) (screenHeight * 21 / 100) + 2* soldairDimensionsY);

		g.drawImage(arena, arenaPosition.x, arenaPosition.y, arenaDimensionsX, arenaDimensionsY, this);
	}

	private void setModalSize() {

		frame.setPreferredSize(new Dimension(screenWidth, screenHeight));
		frame.setMaximumSize(new Dimension(screenWidth, screenHeight));
		frame.setMinimumSize(new Dimension(screenWidth, screenHeight));
	}

	private void Init() {

		backgroundImg = ImageLoader.loadImage("/images/pexels-photo-370799.jpeg");
		closeImg = ImageLoader.loadImage("/store/closeImg.png");
		storeButton = ImageLoader.loadImage("/images/storeButton.png");
		arena = ImageLoader.loadImage("/images/Arena.png");
		
		Fighter = ImageLoader.loadImage("/store/Infantry.png");
		Defender = ImageLoader.loadImage("/store/Knight_templar.png");
		Warrior = ImageLoader.loadImage("/store/Teutonic_knight.png");

	}

	public static void changeVisibility(boolean val) {
		frame.setVisible(val);
	}
}