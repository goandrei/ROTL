package rotl.statusBar;

import rotl.player.Player;
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
	private static int gold = 0;

	private static Handler handler;
	private static JDialog frame = new JDialog();

	private static int screenWidth, screenHeight;

	private static BufferedImage backgroundImg;
	private static BufferedImage storeButton;
	private static BufferedImage arena;
	private static BufferedImage playerLogo;
	private static BufferedImage goldImg;
	
	private static int numberFighters;
	private static int numberDefenders;
	private static int numberWarriors;
	
	private static BufferedImage Fighter;
	private static BufferedImage Defender;
	private static BufferedImage Warrior;

	private Arena arenaInstance;
	private Store storeInstance;

	public static StatusBar getInstance(Handler handler) {

		if (instance == null) {
			instance = new StatusBar(handler);
		}
		frame.setVisible(true);
		return instance;
	}
	
	public static StatusBar getInstanceNonAbusive() {
		return instance;
	}

	public Store getStoreInstance() {
		return storeInstance;
	}

	public Arena getArenaInstance() {
		return arenaInstance;
	}
	
	private StatusBar(Handler handler) {

		StatusBar.handler = handler;

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

				if (storeButton != null) {
					Point me = e.getPoint();
					Rectangle bounds = new Rectangle(storeButtonPosition.x, storeButtonPosition.y,
							storeButtonXDimenssion, storeButtonYDimenssion);
					if (bounds.contains(me)) {
						
						storeInstance = Store.getInstance(handler);
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
		g.drawString(userName, infoRectPosition.x + (int) (screenWidth * 8 / 100),
				infoRectPosition.y + (int) (screenHeight * 20 / 100));
		g.drawImage(playerLogo, infoRectPosition.x + (int) (screenWidth * 9 / 100),
				infoRectPosition.y + (int) (screenHeight * 30 / 100),
				50, 50, this);

		g.setColor(new Color(255, 255, 255, 100));
		g.fillRect(gameinfoRectPosition.x, gameinfoRectPosition.y, gameinfoRectDimensionsX, gameinfoRectDimensionsY);
		g.setColor(Color.WHITE);
		g.drawImage(goldImg, gameinfoRectPosition.x + (int) (screenWidth * 9 / 100),
				gameinfoRectPosition.y + (int) (screenHeight * 5 / 100),
				45, 45, this);
		
		g.drawString(gold + "", gameinfoRectPosition.x + (int) (screenWidth * 6 / 100),
				infoRectPosition.y + (int) (screenHeight * 10 / 100) + 60);

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

		gold = Player.getInstance().getGold();
		backgroundImg = ImageLoader.loadImage("/images/pexels-photo-370799.jpeg");
		storeButton = ImageLoader.loadImage("/images/storeButton.png");
		arena = ImageLoader.loadImage("/images/Arena.png");
		
		Fighter = ImageLoader.loadImage("/store/Infantry.png");
		Defender = ImageLoader.loadImage("/store/Knight_templar.png");
		Warrior = ImageLoader.loadImage("/store/Teutonic_knight.png");

		playerLogo = ImageLoader.loadImage("/images/playerLogo.png");
		goldImg = ImageLoader.loadImage("/store/Gold_pile.png");
	}

	public boolean isVisible() {
		return frame.isVisible();
	}
	
	public void changeVisibility(boolean val) {
		frame.setVisible(val);
	}
	
	public void setNumberFighters(int number) {
		
		numberFighters = number;
		repaint();
	}
	
	public void setNumberDefenders(int number) {
		
		numberDefenders = number;
		repaint();
	}
	
	public void setNumberWarriors(int number) {
		
		numberWarriors = number;
		repaint();
	}
	
	public int getNumberFighters() {
		
		return numberFighters;
	}
	
	public int getNumberDefenders() {
		
		return numberDefenders;
	}
	
	public int getNumberWarriors() {
		
		return numberWarriors;
	}
	
	public void setGold(int gold) {
		
		StatusBar.gold = gold;
		repaint();
	}
}