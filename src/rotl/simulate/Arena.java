package rotl.simulate;

import rotl.entities.SoldierType;
import rotl.gfx.Sounds;
import rotl.menu.Options;
import rotl.player.Player;
import rotl.utilities.Handler;
import rotl.utilities.ImageLoader;
import javax.swing.JPanel;
import javax.swing.JDialog;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public final class Arena extends JPanel {

	private static final long serialVersionUID = 1L;

	private static Arena instance = null;

	private static int currentSoldier = -1;
	
	private static int currentSelected = 0;
	private static int firstSelected = -1;
	private static int secondSelected = -1;
	
	private static final Player player = Player.getInstance();
	private static final Map<SoldierType, String> soldiersSources = new HashMap<>();

	private static int closeImgDimensionsX;
	private static int closeImgDimensionsY;
	private static Point closeImgPosition = new Point();
	
	private static int vsDimensionsX;
	private static int vsDimensionsY;
	private static Point vsPosition = new Point();
	
	private static int fightDimensionsX;
	private static int fightDimensionsY;
	private static Point fightPosition = new Point();

	private static int soldierRectDimensionsX;
	private static int soldierRectDimensionsY;
	private static Point soldierRectPosition = new Point();

	private static int soldierDimensionsX;
	private static int soldierDimensionsY;
	private static Point soldierPosition = new Point();

	private static int prevAndNextButtonDimensionsX;
	private static int prevAndNextButtonDimensionsY;
	private static Point prevButtonPosition = new Point();

	private static int infoRectDimensionsX;
	private static int infoRectDimensionsY;
	private static Point infoRectPosition = new Point();
	
	private static int buttonsSectionDimensionsX;
	private static int buttonsSectionDimensionsY;
	private static Point buttonsSectionPosition = new Point();

	private static int upgradeButtonDimensionsX;
	private static int upgradeButtonDimensionsY;
	private static Point upgradeButtonPosition = new Point();
	
	private static int sellButtonDimensionsX;
	private static int sellButtonDimensionsY;
	private static Point sellButtonPosition = new Point();
	
	private static Point informationsAboutSell = new Point();
	
	private static int useForFightButtonDimensionsX;
	private static int useForFightButtonDimensionsY;
	private static Point useForFightButtonPosition = new Point();

	private static int otherDimensionsX;
	private static int otherDimensionsY;
	private static Point otherPosition = new Point();
	
	private static int resctSoldier1DimensionsX;
	private static int resctSoldier1DimensionsY;
	private static Point resctSoldier1Position = new Point();

	private static int resctSoldier2DimensionsX;
	private static int resctSoldier2DimensionsY;
	private static Point resctSoldier2Position = new Point();

	private static Handler handler;
	private static JDialog frame = new JDialog();

	private static int screenWidth, screenHeight;

	private static BufferedImage closeImg = null;
	private static BufferedImage backgroundImg = null;
	private static BufferedImage SoldiersBKIMG = null;

	private static BufferedImage nextButton = null;
	private static BufferedImage prevButton = null;

	private static BufferedImage lifeImg = null;
	private static BufferedImage armorImg = null;
	private static BufferedImage attackImg = null;
	private static BufferedImage upgradeImg = null;
	private static BufferedImage upgradeButton = null;
	private static BufferedImage sellButton = null;
	private static BufferedImage useForFightButton = null;
	private static BufferedImage cashImg = null;
	private static BufferedImage vs = null;
	private static BufferedImage fight = null;
	
	private static BufferedImage soldier1 = null;
	private static BufferedImage soldier2 = null;
	private boolean running;
	
	public static boolean isBuilt() {
		return (instance != null);
	}
	
	public static Arena getInstance(Handler handler) {

		if (instance == null) {
			instance = new Arena(handler);
		}
		frame.setVisible(true);
		instance.running = true;
		return instance;
	}
	
	public static Arena getInstanceNonAbusive() {
		return instance;
	}
	
	private Arena(Handler handler) {

		Arena.handler = handler;
		
		soldiersSources.put(SoldierType.FIGHTER, "Infantry");
		soldiersSources.put(SoldierType.DEFENDER, "Knight_templar");
		soldiersSources.put(SoldierType.WARRIOR, "Teutonic_knight");

		// get the parent screen size and get the modal's size
		screenWidth = (handler.getGame().getWidth() * 8) / 10;
		screenHeight = (handler.getGame().getHeight() * 77) / 100;

		closeImgDimensionsX = (int) (screenWidth * 5.5 / 100);
		closeImgDimensionsY = (int) (screenHeight * 9.8 / 100);
		closeImgPosition.setLocation(screenWidth - closeImgDimensionsX, 0);
		
		resctSoldier1DimensionsX = (int) (screenWidth * 35 / 100);
		resctSoldier1DimensionsY = (int) (screenHeight * 35 / 100);
		resctSoldier1Position.setLocation((int) (screenWidth * 5.5 / 100), (int) (screenWidth * 7.5 / 100));

		vsDimensionsX = (int) (screenWidth * 11 / 100);
		vsDimensionsY = (int) (screenHeight * 19 / 100);
		vsPosition.setLocation(resctSoldier1Position.x + (int) (resctSoldier1DimensionsX * 1.1), resctSoldier1Position.y + 10);
		
		fightDimensionsX = (int) (screenWidth * 19 / 100);
		fightDimensionsY = (int) (screenHeight * 19 / 100);
		fightPosition.setLocation(resctSoldier1Position.x + (int) (resctSoldier1DimensionsX * 1), vsPosition.y + vsDimensionsY + 10);
		
		resctSoldier2DimensionsX = (int) (screenWidth * 35 / 100);
		resctSoldier2DimensionsY = (int) (screenHeight * 35 / 100);
		resctSoldier2Position.setLocation((int) (screenWidth * 59.5 / 100), (int) (screenWidth * 7.5 / 100));
		
		soldierRectDimensionsX = (int) (screenWidth * 89 / 100);
		soldierRectDimensionsY = (int) (screenHeight * 40 / 100);
		soldierRectPosition.setLocation((int) (screenWidth * 5.5 / 100), (int) (screenHeight * 50 / 100));

		soldierDimensionsX = (int) (soldierRectDimensionsX * 25 / 100);
		soldierDimensionsY = (int) (soldierRectDimensionsY * 90 / 100);
		soldierPosition.setLocation((int) (soldierRectPosition.x * 1.4), 
				(int) (soldierRectPosition.y * 1.025));

		prevAndNextButtonDimensionsX = (int) (screenWidth * 7.5 / 100);
		prevAndNextButtonDimensionsY = (int) (screenHeight * 10.5 / 100);
		prevButtonPosition.setLocation((int) (screenWidth * 10 / 100), soldierRectDimensionsY + soldierRectPosition.y);

		infoRectDimensionsX = (int) (screenWidth * 35 / 100);
		infoRectDimensionsY = soldierRectDimensionsY;
		infoRectPosition.setLocation(soldierRectPosition.x + soldierDimensionsX + (int) (screenWidth * 1 / 100),
				soldierRectPosition.y);
		
		buttonsSectionDimensionsX = soldierRectDimensionsX - infoRectPosition.x - infoRectDimensionsX;
		buttonsSectionDimensionsY = infoRectDimensionsY;
		buttonsSectionPosition.setLocation(infoRectPosition.x + infoRectDimensionsX, infoRectPosition.y);

		upgradeButtonDimensionsX = (int) (buttonsSectionDimensionsX * 95 / 100);
		upgradeButtonDimensionsY = (int) (buttonsSectionDimensionsY * 20 / 100);
		upgradeButtonPosition.setLocation(buttonsSectionPosition.x + (int) (buttonsSectionPosition.x * 5 / 100),
				buttonsSectionPosition.y + (buttonsSectionPosition.y * 5 / 100));
		
		sellButtonDimensionsX = (int) (buttonsSectionDimensionsX * 95 / 100);
		sellButtonDimensionsY = (int) (buttonsSectionDimensionsY * 20 / 100);
		sellButtonPosition.setLocation(buttonsSectionPosition.x + (int) (buttonsSectionPosition.x * 5 / 100),
				buttonsSectionPosition.y + 2 * (buttonsSectionPosition.y * 5 / 100) + sellButtonDimensionsY);
		
		useForFightButtonDimensionsX = (int) (buttonsSectionDimensionsX * 95 / 100);
		useForFightButtonDimensionsY = (int) (buttonsSectionDimensionsY * 20 / 100);
		useForFightButtonPosition.setLocation(buttonsSectionPosition.x + (int) (buttonsSectionPosition.x * 5 / 100),
				buttonsSectionPosition.y + 2 * (buttonsSectionPosition.y * 5 / 100) + 3 * sellButtonDimensionsY);

		informationsAboutSell.setLocation(buttonsSectionPosition.x + (int) (buttonsSectionPosition.x * 7 / 100),
				buttonsSectionPosition.y + 4 * (buttonsSectionPosition.y * 5 / 100) + 2 * sellButtonDimensionsY);	
	
		otherDimensionsX = (int) (screenWidth * 3 / 100);
		otherDimensionsY = (int) (screenHeight * 5.2 / 100);
		otherPosition.setLocation(infoRectPosition.x + (int) (infoRectDimensionsX * 5 / 100),
				infoRectPosition.y + (int) (screenHeight * 12 / 100));

		setModalSize();

		frame.setUndecorated(true);

		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setContentPane(this);

		Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/cursor_final.png"));
		Point hotspot = new Point(0, 0);
		Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(image, hotspot, "pencil");
		frame.setCursor(cursor);

		Init();

		addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				
				if (closeImg != null) {
					
					Point me = e.getPoint();
					Rectangle bounds = new Rectangle(closeImgPosition.x, closeImgPosition.y, closeImgDimensionsX,
							closeImgDimensionsY);
					
					if (bounds.contains(me)) {
						
						frame.setVisible(false);
						running = false;
						
						/** reset some properties **/
						currentSoldier = -1;
						
						/** Select for fight info **/
						soldier1 = null;
						soldier2 = null;
						firstSelected = -1;
						secondSelected = -1;
						currentSelected = 0;
						
						Sounds.getInstance().stopBattleMusic();
						if(Options.getStateArray()[1] == 1) {
							Sounds.getInstance().loopGameMusic();
						}
					}
				}
				
				if (prevButton != null) {
					
					Point me = e.getPoint();
					Rectangle bounds = new Rectangle(prevButtonPosition.x, prevButtonPosition.y,
							prevAndNextButtonDimensionsX, prevAndNextButtonDimensionsY);
					
					if (bounds.contains(me)) {
						
						if (player.getNumberOfSoldiers() > 1) {
							
							final int numberOfSoldiers = player.getNumberOfSoldiers();
							currentSoldier = (currentSoldier + numberOfSoldiers - 1) % numberOfSoldiers;
							repaint();	
						}
					}
				}
				
				if (nextButton != null) {
					
					Point me = e.getPoint();
					Rectangle bounds = new Rectangle(prevButtonPosition.x + prevAndNextButtonDimensionsX + 10,
							prevButtonPosition.y, prevAndNextButtonDimensionsX, prevAndNextButtonDimensionsY);
					
					if (bounds.contains(me)) {
						
						if (player.getNumberOfSoldiers() > 1) {
							
							
							currentSoldier = (currentSoldier + 1) % player.getNumberOfSoldiers();
							repaint();
							
						}
					}
				}
				
				if (upgradeButton != null) {
					
					Point me = e.getPoint();
					Rectangle bounds = new Rectangle(upgradeButtonPosition.x, upgradeButtonPosition.y,
							upgradeButtonDimensionsX, upgradeButtonDimensionsY);
					
					if (bounds.contains(me)) {
						upgrade();
					}
				}
				
				if (sellButton != null) {
					
					Point me = e.getPoint();
					Rectangle bounds = new Rectangle(sellButtonPosition.x, sellButtonPosition.y,
							sellButtonDimensionsX, sellButtonDimensionsY);
					
					if (bounds.contains(me)) {
						sell();
					}
				}
				
				if (useForFightButton != null) {
					
					Point me = e.getPoint();
					Rectangle bounds = new Rectangle(useForFightButtonPosition.x, useForFightButtonPosition.y,
							useForFightButtonDimensionsX, useForFightButtonDimensionsY);
					
					if (bounds.contains(me)) {
						useForFight();
					}
				}
				
				if (fight != null) {
					Point me = e.getPoint();
					Rectangle bounds = new Rectangle(fightPosition.x, fightPosition.y,
							fightDimensionsX, fightDimensionsY);
					if (bounds.contains(me)) {
						fight();
					}
				}
			}
		});
	}
	
	public boolean isRunning() {
		return running;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawImage(backgroundImg, 0, 0, screenWidth, screenHeight, this);
		g.drawImage(vs, vsPosition.x, vsPosition.y, vsDimensionsX, vsDimensionsY, this);
		
		g.setFont(new Font("Neuropol X", Font.BOLD, 100));
		g.setColor(Color.WHITE);
		g.drawString("Arena", (int) (screenWidth * 40 / 100), 65);
		
		g.drawImage(closeImg, closeImgPosition.x, closeImgPosition.y, closeImgDimensionsX, closeImgDimensionsY, this);
		g.setColor(new Color(255, 255, 255, 100));
		
		g.fillRect(resctSoldier1Position.x, resctSoldier1Position.y, resctSoldier1DimensionsX, resctSoldier1DimensionsY);
		g.fillRect(resctSoldier2Position.x, resctSoldier2Position.y, resctSoldier2DimensionsX, resctSoldier2DimensionsY);
		
		g.setColor(Color.RED);
		
		switch (currentSelected) {
			
			case 0:
			
				g.drawRect(resctSoldier1Position.x, resctSoldier1Position.y, resctSoldier1DimensionsX, resctSoldier1DimensionsY);
				break;
			case 1:
				g.drawRect(resctSoldier2Position.x, resctSoldier2Position.y, resctSoldier2DimensionsX, resctSoldier2DimensionsY);
				break;
		}
		
		g.setColor(new Color(255, 255, 255, 100));
		
		if (soldier1 != null) {
			
			g.drawImage(soldier1, resctSoldier1Position.x + (int) (resctSoldier1DimensionsX * 30/100), 
					resctSoldier1Position.y + (int) (resctSoldier1Position.y * 5 /100), 
					(int) (resctSoldier1DimensionsY * 80 /100),
					(int) (resctSoldier1DimensionsY * 90 /100), this);
			
			g.drawString(String.valueOf(firstSelected + 1), resctSoldier1Position.x + 10, resctSoldier1Position.y + resctSoldier1DimensionsY - 15);
		}
		
		if (soldier2 != null) {
			
			g.drawImage(soldier2, resctSoldier2Position.x + (int) (resctSoldier2DimensionsX * 30/100), 
					resctSoldier2Position.y + (int) (resctSoldier2Position.y * 5 /100), 
					(int) (resctSoldier2DimensionsY * 80 /100),
					(int) (resctSoldier2DimensionsY * 90 /100), this);
			g.drawString(String.valueOf(secondSelected + 1), resctSoldier2Position.x + 10, resctSoldier2Position.y + resctSoldier2DimensionsY - 15);
		}
	
		/** Soldier background **/
		g.fillRect(soldierRectPosition.x, soldierRectPosition.y, soldierRectDimensionsX, soldierRectDimensionsY);
		
		g.drawImage(prevButton, prevButtonPosition.x, prevButtonPosition.y, prevAndNextButtonDimensionsX,
				prevAndNextButtonDimensionsY, this);
		
		g.drawImage(nextButton, prevButtonPosition.x + prevAndNextButtonDimensionsX + 10, prevButtonPosition.y,
				prevAndNextButtonDimensionsX, prevAndNextButtonDimensionsY, this);
		g.setColor(new Color(255, 255, 255, 100));
		
		g.fillRect(infoRectPosition.x, infoRectPosition.y, infoRectDimensionsX, infoRectDimensionsY);
		
		/** Upgrade current soldier info **/
		
		if (player.getNumberOfSoldiers() > 0) {
			
			//** Get soldier info for arena **//*
			
			if (currentSoldier == -1)
				currentSoldier = 0;
				
			final SoldierInfoArena sInfo = player.getSoldierInfo(currentSoldier);
			SoldiersBKIMG = ImageLoader.loadImage("/store/" + soldiersSources.get(sInfo.getSoldierType()) + ".png");
			
			if (SoldiersBKIMG != null)
				g.drawImage(SoldiersBKIMG, soldierPosition.x, soldierPosition.y, soldierDimensionsX, soldierDimensionsY, this);
			
			g.drawImage(upgradeButton, upgradeButtonPosition.x, upgradeButtonPosition.y,
					upgradeButtonDimensionsX, upgradeButtonDimensionsY, this);
			
			g.drawImage(sellButton, sellButtonPosition.x, sellButtonPosition.y,
					sellButtonDimensionsX, sellButtonDimensionsY, this);
			
			g.setColor(new Color(255, 255, 255));
			g.setFont(new Font("Neuropol", Font.BOLD, 15));
			g.drawString("For this gold: " + 0, informationsAboutSell.x, informationsAboutSell.y);
			
			g.drawImage(useForFightButton, useForFightButtonPosition.x, useForFightButtonPosition.y,
					useForFightButtonDimensionsX, useForFightButtonDimensionsY, this);
			
			g.setColor(new Color(255, 255, 255));
			g.setFont(new Font("Neuropol", Font.BOLD, 30));
			g.drawString(sInfo.getSoldierType().getSoldierName(), infoRectPosition.x + (int) (infoRectDimensionsX * 5 / 100),
					infoRectPosition.y + (int) (infoRectDimensionsY * 20 / 100));
			
			/** Life **/
			g.drawImage(lifeImg, otherPosition.x, otherPosition.y, otherDimensionsX, otherDimensionsY, this);
			g.setFont(new Font("Neuropol X", Font.BOLD, 30));
			g.drawString("Life: " + sInfo.getLife(),
					otherPosition.x + otherDimensionsX + (int) (infoRectDimensionsX * 5 / 100),
					otherPosition.y + (int) (infoRectDimensionsY * 7 / 100));
			
			/** Armor **/
			g.drawImage(armorImg, otherPosition.x,
					otherPosition.y + otherDimensionsY + (int) (infoRectDimensionsY * 1 / 100), otherDimensionsX,
					otherDimensionsY, this);
			g.drawString("Armor: " + sInfo.getArmor(),
					otherPosition.x + otherDimensionsX + (int) (infoRectDimensionsX * 5 / 100),
					otherPosition.y + (int) (infoRectDimensionsY * 7 / 100) + otherDimensionsY
							+ (int) (infoRectDimensionsY * 1 / 100));
			
			/** Attack **/
			g.drawImage(attackImg, otherPosition.x,
					otherPosition.y + 2 * otherDimensionsY + 2 * (int) (infoRectDimensionsY * 1 / 100), otherDimensionsX,
					otherDimensionsY, this);
			g.drawString("Attack: " + sInfo.getAttack(),
					otherPosition.x + otherDimensionsX + (int) (infoRectDimensionsX * 5 / 100),
					otherPosition.y + (int) (infoRectDimensionsY * 7 / 100) + 2 * otherDimensionsY
							+ 2 * (int) (infoRectDimensionsY * 1 / 100));
			
			/** Upgrade **/
			g.drawImage(upgradeImg, otherPosition.x,
					otherPosition.y + 3 * otherDimensionsY + 3 * (int) (infoRectDimensionsY * 1 / 100), otherDimensionsX,
					otherDimensionsY, this);
			g.drawString("Upgrade cost: " + sInfo.getGold() + " $",
					otherPosition.x + otherDimensionsX + (int) (infoRectDimensionsX * 5 / 100),
					otherPosition.y + (int) (infoRectDimensionsY * 7 / 100) + 3 * otherDimensionsY
							+ 3 * (int) (infoRectDimensionsY * 1 / 100));
			
			g.setFont(new Font("Neuropol X", Font.BOLD, 40));
			g.setColor(Color.WHITE);
			g.drawString(String.valueOf(currentSoldier + 1), prevButtonPosition.x + prevAndNextButtonDimensionsX - 10, 
					prevButtonPosition.y + (int) (prevAndNextButtonDimensionsY * 5 / 8));
		}	
		
		/** **/
		
		g.drawImage(cashImg, screenWidth - (int) (screenWidth * 8 / 100),
				screenHeight - (int) (screenHeight * 11 / 100), (int) (screenWidth * 6 / 100),
				(int) (screenHeight * 10 / 100), this);
		
		g.setFont(new Font("Neuropol X", Font.BOLD, 30));
		g.drawString(Player.getInstance().getGold() + " $", (int) (screenWidth * 70 / 100), (int) (screenHeight * 95 / 100));
		g.drawImage(fight, fightPosition.x, fightPosition.y, fightDimensionsX, fightDimensionsY, this);
	}

	private void setModalSize() {

		frame.setPreferredSize(new Dimension(screenWidth, screenHeight));
		frame.setMaximumSize(new Dimension(screenWidth, screenHeight));
		frame.setMinimumSize(new Dimension(screenWidth, screenHeight));
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	private void Init() {

		backgroundImg = ImageLoader.loadImage("/images/Age-of-Empires-Arena.jpg");
		closeImg = ImageLoader.loadImage("/store/closeImg.png");
		prevButton = ImageLoader.loadImage("/store/TriangleButtonL.png");
		nextButton = ImageLoader.loadImage("/store/TriangleButtonR.png");
		lifeImg = ImageLoader.loadImage("/store/heart.png");
		armorImg = ImageLoader.loadImage("/store/Armor.png");
		attackImg = ImageLoader.loadImage("/store/Attack.png");
		upgradeImg = ImageLoader.loadImage("/store/Apple.png");
		upgradeButton = ImageLoader.loadImage("/store/Upgrade-Now-Button.png");
		sellButton = ImageLoader.loadImage("/store/SellButton.png");
		useForFightButton = ImageLoader.loadImage("/store/UseforFightButton.png");
		cashImg = ImageLoader.loadImage("/store/cash-icon.png");
		vs = ImageLoader.loadImage("/images/vs.png");
		fight = ImageLoader.loadImage("/images/fight.png");
	}
	
	private void useForFight() {
		
		if (currentSoldier != firstSelected && currentSoldier != secondSelected) {
			
			//** Get soldier info for arena **//*	
			final SoldierInfoArena sInfo = player.getSoldierInfo(currentSoldier);
			
			switch (currentSelected) {
			
				case 0:
					firstSelected = currentSoldier;
					soldier1 = ImageLoader.loadImage("/store/" + soldiersSources.get(sInfo.getSoldierType()) + ".png");
					repaint();
					break;
				case 1:
					secondSelected = currentSoldier;
					soldier2 = ImageLoader.loadImage("/store/" + soldiersSources.get(sInfo.getSoldierType()) + ".png");					
					repaint();
					break;
			}
			
			currentSelected = (currentSelected + 1) % 2;
		}
	}

	private void upgrade() {
		
		if (player.getNumberOfSoldiers() > 0) {
			
			player.upgradeSoldier(currentSoldier);
			repaint();
		}
	}

	private void sell() {
		
		if (player.getNumberOfSoldiers() > 0) {
			
			if (currentSoldier == firstSelected) {
				
				if (secondSelected != -1) {
					
					soldier1 = soldier2;
					firstSelected = ((secondSelected < currentSoldier) ? secondSelected : secondSelected - 1);
					soldier2 = null;
					secondSelected = -1;
					
					currentSelected = 1;
					
				} else {
					
					soldier1 = null;
					firstSelected = -1;
					
					currentSelected = 0;
				}
				
			} else if (currentSoldier == secondSelected) {
				
				firstSelected = ((firstSelected < currentSoldier) ? firstSelected : firstSelected - 1);
				soldier2 = null;
				secondSelected = -1;
				
				currentSelected = 1;
				
			} else {
				
				if (firstSelected != -1)
					firstSelected = ((firstSelected < currentSoldier) ? firstSelected : firstSelected - 1);
					
				if (secondSelected != -1)
					secondSelected = ((secondSelected < currentSoldier) ? secondSelected : secondSelected - 1);
			}
			
			player.removeSoldier(currentSoldier);
			
			if (player.getNumberOfSoldiers() == 0) {
				currentSoldier = -1;
			} else currentSoldier = currentSoldier % player.getNumberOfSoldiers();
			
			repaint();
		}
	}
	
	private void fight() {
		
		if (firstSelected != -1 && secondSelected != -1)
			Fight.getInstance(handler, firstSelected, secondSelected);
	}
	
	public boolean isVisible() {
		return frame.isVisible();
	}
	
	public void changeVisibility(boolean val) {
		frame.setVisible(val);
	}
}