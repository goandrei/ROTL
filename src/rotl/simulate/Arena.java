package rotl.simulate;

import rotl.entities.SoldierType;
import rotl.player.Player;
import rotl.utilities.Handler;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Arena extends JPanel {

	private static final long serialVersionUID = 1L;

	private static Arena instance = null;

	static int currentSoldier = -1;
	
	static int index = 0;
	static int index1 = 0;
	static int index2 = 0;
	
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

	private static BufferedImage NextButton = null;
	private static BufferedImage PrevButton = null;

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
	
	private Arena(Handler handler) {

		this.handler = handler;
		
		soldiersSources.put(SoldierType.FIGHTER, "Infantry");
		soldiersSources.put(SoldierType.DEFENDER, "Knight_templar");
		soldiersSources.put(SoldierType.WARRIOR, "Teutonic_knight");

		// get the parent screen size and get the modal's size
		screenWidth = (handler.getGame().getWidth() * 8) / 10;
		screenHeight = (handler.getGame().getHeight() * 8) / 10;

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
		frame.setVisible(true);

		Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/cursor_final.png"));
		Point hotspot = new Point(0, 0);
		Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(image, hotspot, "pencil");
		frame.setCursor(cursor);
		frame.setAlwaysOnTop(true);

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
						soldier1 = null;
						soldier2 = null;
						currentSoldier = -1;
					}
				}
				
				if (PrevButton != null) {
					
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
				
				if (NextButton != null) {
					
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
		
		if (soldier1 != null) {
			
			g.drawImage(soldier1, resctSoldier1Position.x + (int) (resctSoldier1DimensionsX * 30/100), 
					resctSoldier1Position.y + (int) (resctSoldier1Position.y * 5 /100), 
					(int) (resctSoldier1DimensionsY * 80 /100),
					(int) (resctSoldier1DimensionsY * 90 /100), this);
		}
		
		g.fillRect(resctSoldier2Position.x, resctSoldier2Position.y, resctSoldier2DimensionsX, resctSoldier2DimensionsY);
		
		if (soldier2 != null) {
			
			g.drawImage(soldier2, resctSoldier2Position.x + (int) (resctSoldier2DimensionsX * 30/100), 
					resctSoldier2Position.y + (int) (resctSoldier2Position.y * 5 /100), 
					(int) (resctSoldier2DimensionsY * 80 /100),
					(int) (resctSoldier2DimensionsY * 90 /100), this);
		}
		
		/** Soldier background **/
		g.fillRect(soldierRectPosition.x, soldierRectPosition.y, soldierRectDimensionsX, soldierRectDimensionsY);
		
		g.drawImage(PrevButton, prevButtonPosition.x, prevButtonPosition.y, prevAndNextButtonDimensionsX,
				prevAndNextButtonDimensionsY, this);
		
		g.drawImage(NextButton, prevButtonPosition.x + prevAndNextButtonDimensionsX + 10, prevButtonPosition.y,
				prevAndNextButtonDimensionsX, prevAndNextButtonDimensionsY, this);
		g.setColor(new Color(255, 255, 255, 100));
		
		g.fillRect(infoRectPosition.x, infoRectPosition.y, infoRectDimensionsX, infoRectDimensionsY);
		
		/** Upgrade current soldier info **/
		
		if (player.getNumberOfSoldiers() > 0) {
			
			//** Get soldier info for arena **//*
			
			if (currentSoldier == -1)
				currentSoldier = 0;
				
			final SoldierInfoArena sInfo = player.getSoldierInfo(currentSoldier);

			URL resourceSoldiersBK = getClass().getResource("/store/" + soldiersSources.get(sInfo.getSoldierType()) + ".png");
			try {
				SoldiersBKIMG = ImageIO.read(resourceSoldiersBK);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
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
		}	
		
		/** **/
		
		g.drawImage(cashImg, screenWidth - (int) (screenWidth * 8 / 100),
				screenHeight - (int) (screenHeight * 11 / 100), (int) (screenWidth * 6 / 100),
				(int) (screenHeight * 10 / 100), this);
		
		g.setFont(new Font("Neuropol X", Font.BOLD, 30));
		g.drawString("100000000 $ : ", (int) (screenWidth * 70 / 100), (int) (screenHeight * 95 / 100));
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

		URL resourceBKImg = getClass().getResource("/images/Age-of-Empires-Arena.jpg");
		try {
			backgroundImg = ImageIO.read(resourceBKImg);
		} catch (IOException e) {
			e.printStackTrace();
		}

		URL resourceCloseImg = getClass().getResource("/store/closeImg.png");
		try {
			closeImg = ImageIO.read(resourceCloseImg);
		} catch (IOException e) {
			e.printStackTrace();
		}

		URL resourcePrevButton = getClass().getResource("/store/TriangleButtonL.png");
		try {
			PrevButton = ImageIO.read(resourcePrevButton);
		} catch (IOException e) {
			e.printStackTrace();
		}

		URL resourceNextButton = getClass().getResource("/store/TriangleButtonR.png");
		try {
			NextButton = ImageIO.read(resourceNextButton);
		} catch (IOException e) {
			e.printStackTrace();
		}

		URL resourcelife = getClass().getResource("/store/heart.png");
		try {
			lifeImg = ImageIO.read(resourcelife);
		} catch (IOException e) {
			e.printStackTrace();
		}

		URL resourceArmor = getClass().getResource("/store/Armor.png");
		try {
			armorImg = ImageIO.read(resourceArmor);
		} catch (IOException e) {
			e.printStackTrace();
		}

		URL resourceattack = getClass().getResource("/store/Attack.png");
		try {
			attackImg = ImageIO.read(resourceattack);
		} catch (IOException e) {
			e.printStackTrace();
		}

		URL resourceupgrade = getClass().getResource("/store/Apple.png");
		try {
			upgradeImg = ImageIO.read(resourceupgrade);
		} catch (IOException e) {
			e.printStackTrace();
		}

		URL resourceupgradeButton = getClass().getResource("/store/Upgrade-Now-Button.png");
		try {
			upgradeButton = ImageIO.read(resourceupgradeButton);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		URL resourcesellButton = getClass().getResource("/store/SellButton.png");
		try {
			sellButton = ImageIO.read(resourcesellButton);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		URL resourceUseFFightButton = getClass().getResource("/store/UseforFightButton.png");
		try {
			useForFightButton = ImageIO.read(resourceUseFFightButton);
		} catch (IOException e) {
			e.printStackTrace();
		}

		URL resourceMyCash = getClass().getResource("/store/cash-icon.png");
		try {
			cashImg = ImageIO.read(resourceMyCash);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		URL resourceVS = getClass().getResource("/images/vs.png");
		try {
			vs = ImageIO.read(resourceVS);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		URL resourceFight = getClass().getResource("/images/fight.png");
		try {
			fight = ImageIO.read(resourceFight);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void useForFight() {
		/*if(index % 2 == 0) {
			URL resourceSoldier1 = getClass()
					.getResource("/store/" + soldiersSources.get(currentSoldier) + ".png");
			try {
				soldier1 = ImageIO.read(resourceSoldier1);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			repaint();
			index1 = currentSoldier;
		}
		else {
			URL resourceSoldier2 = getClass()
					.getResource("/store/" + soldiersSources.get(currentSoldier) + ".png");
			try {
				soldier2 = ImageIO.read(resourceSoldier2);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			repaint();
			index2 = currentSoldier;
		}
		index++;*/
	}

	private void upgrade() {
		
		if (player.getNumberOfSoldiers() > 0) {
			
			player.upgradeSoldier(currentSoldier);
			repaint();
		}
	}

	private void sell() {
		
		if (player.getNumberOfSoldiers() > 0) {
			
			player.removeSoldier(currentSoldier);
			
			if (player.getNumberOfSoldiers() == 0) {
				currentSoldier = -1;
			} else currentSoldier = currentSoldier % player.getNumberOfSoldiers();
			
			repaint();
		}
	}
	
	private void fight() {
		
		frame.setVisible(false);
		Fight.getInstance(handler);
	}
}