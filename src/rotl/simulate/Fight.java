package rotl.simulate;

import rotl.entities.SoldierType;
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

public final class Fight extends JPanel {

	private static final long serialVersionUID = 1L;

	private static Fight instance = null;

	private static int indexFirst = -1;
	private static int indexSecond = -1;
	
	private static final Player player = Player.getInstance();
	private static final Map<SoldierType, String> soldiersSources = new HashMap<>();

	private static int closeImgDimensionsX;
	private static int closeImgDimensionsY;
	private static Point closeImgPosition = new Point();

	private static int soldier1RectDimensionsX;
	private static int soldier1RectDimensionsY;
	private static Point soldier1RectPosition = new Point();
	
	private static int soldier2RectDimensionsX;
	private static int soldier2RectDimensionsY;
	private static Point soldier2RectPosition = new Point();

	private static int soldier1DimensionsX;
	private static int soldier1DimensionsY;
	private static Point soldier1Position = new Point();
	
	private static int soldier2DimensionsX;
	private static int soldier2DimensionsY;
	private static Point soldier2Position = new Point();
	
	private static int rehealSoldier1DimensionsX;
	private static int rehealSoldier1DimensionsY;
	private static Point rehealSoldier1Position = new Point();

	private static int rehealSoldier2DimensionsX;
	private static int rehealSoldier2DimensionsY;
	private static Point rehealSoldier2Position = new Point();
	
	private static int repairArmorSoldier1DimensionsX;
	private static int repairArmorSoldier1DimensionsY;
	private static Point repairArmorSoldier1Position = new Point();

	private static int repairArmorSoldier2DimensionsX;
	private static int repairArmorSoldier2DimensionsY;
	private static Point repairArmorSoldier2Position = new Point();
	
	private static int upgradeSoldier1DimensionsX;
	private static int upgradeSoldier1DimensionsY;
	private static Point upgradeSoldier1Position = new Point();

	private static int upgradeSoldier2DimensionsX;
	private static int upgradeSoldier2DimensionsY;
	private static Point upgradeSoldier2Position = new Point();
	
	private static int HPSoldier1DimensionsX;
	private static int HPSoldier1DimensionsY;
	private static Point HPSoldier1Position = new Point();

	private static int HPSoldier2DimensionsX;
	private static int HPSoldier2DimensionsY;
	private static Point HPSoldier2Position = new Point();
	
	private static int armorSoldier1DimensionsX;
	private static int armorSoldier1DimensionsY;
	private static Point armorSoldier1Position = new Point();

	private static int armorSoldier2DimensionsX;
	private static int armorSoldier2DimensionsY;
	private static Point armorSoldier2Position = new Point();
	
	private static int pauseContinueButtonDimensionsX;
	private static int pauseContinueButtonDimensionsY;
	private static Point pauseContinueButtonPosition = new Point();

	private static Handler handler;
	private static JDialog frame = new JDialog();

	private static int screenWidth, screenHeight;

	private static BufferedImage closeImg;
	private static BufferedImage backgroundImg;
	private static BufferedImage soldier1;
	private static BufferedImage soldier2;

	private static BufferedImage rehealButton1;
	private static BufferedImage repairArmorButton1;
	private static BufferedImage upgradeButton1;
	private static BufferedImage rehealButton2;
	private static BufferedImage repairArmorButton2;
	private static BufferedImage upgradeButton2;
	private static BufferedImage pauseContinueButton;
	private static BufferedImage cashImg;
	
	private static int firstLife;
	private static int firstArmor;
	private static int secondLife;
	private static int secondArmor;
	private static boolean isActive = false;
	
	private void loadFighters() {
		
		if (indexFirst != -1 && indexSecond != -1) {
			
			final SoldierInfoArena sInfo1 = player.getSoldierInfo(indexFirst);
			final SoldierInfoArena sInfo2 = player.getSoldierInfo(indexSecond);
	
			soldier1 = ImageLoader.loadImage("/store/" + soldiersSources.get(sInfo1.getSoldierType()) + ".png");
			soldier2 = ImageLoader.loadImage("/store/" + soldiersSources.get(sInfo2.getSoldierType()) + ".png");
		}
	}
	
	public static Fight getInstance(Handler handler, int firstSelected, int secondSelected) {
		
		indexFirst = firstSelected;
		indexSecond = secondSelected;
		
		if (instance == null) {
			instance = new Fight(handler);
		} else 
			instance.loadFighters();
		
		frame.setVisible(true);
		return instance;
	}
	
	public static Fight getInstanceNonAbusive() {
		return instance;
	}

	private Fight(Handler handler) {

		Fight.handler = handler;
		
		soldiersSources.put(SoldierType.FIGHTER, "Infantry");
		soldiersSources.put(SoldierType.DEFENDER, "Knight_templar");
		soldiersSources.put(SoldierType.WARRIOR, "Teutonic_knight");

		// get the parent screen size and get the modal's size
		screenWidth = (handler.getGame().getWidth() * 8) / 10;
		screenHeight = (handler.getGame().getHeight() * 77) / 100;

		closeImgDimensionsX = (int) (screenWidth * 5.5 / 100);
		closeImgDimensionsY = (int) (screenHeight * 9.8 / 100);
		closeImgPosition.setLocation(screenWidth - closeImgDimensionsX, 0);
		
		rehealSoldier1DimensionsX = (int) (screenWidth * 20/ 100);
		rehealSoldier1DimensionsY = (int) (screenHeight * 10 / 100);
		rehealSoldier1Position.setLocation((int) (screenWidth * 5 / 100),
				                           (int) (screenHeight * 17 / 100));
		
		repairArmorSoldier1DimensionsX = (int) (screenWidth * 20/ 100);
		repairArmorSoldier1DimensionsY = (int) (screenHeight * 10 / 100);
		repairArmorSoldier1Position.setLocation((int) (screenWidth * 5 / 100),
			(int) (screenHeight * 17 / 100) + (int) (screenHeight * 15 / 100));
		
		upgradeSoldier1DimensionsX = (int) (screenWidth * 20/ 100);
		upgradeSoldier1DimensionsY = (int) (screenHeight * 10 / 100);
		upgradeSoldier1Position.setLocation((int) (screenWidth * 5 / 100),
				(int) (screenHeight * 17 / 100) + 2*  (int) (screenHeight * 15 / 100));
		
		
		soldier1RectDimensionsX = (int) (screenWidth * 24 / 100);
		soldier1RectDimensionsY = (int) (screenHeight * 45 / 100);
		soldier1RectPosition.setLocation((int) (screenWidth * 25 / 100), (int) (screenHeight * 14 / 100));
		
		HPSoldier1DimensionsX = soldier1RectDimensionsX;
		HPSoldier1DimensionsY = (int) (screenHeight * 5 / 100);
		HPSoldier1Position.setLocation(soldier1RectPosition.x, soldier1RectPosition.y + soldier1RectDimensionsY + 
				(int) (screenHeight * 3 / 100));
		
		armorSoldier1DimensionsX = soldier1RectDimensionsX;
		armorSoldier1DimensionsY = (int) (screenHeight * 5 / 100);
		armorSoldier1Position.setLocation(soldier1RectPosition.x, HPSoldier1Position.y + HPSoldier1DimensionsY + 
				(int) (screenHeight * 3 / 100));
		
		soldier2RectDimensionsX = soldier1RectDimensionsX;
		soldier2RectDimensionsY = soldier1RectDimensionsY;
		soldier2RectPosition.setLocation((int) (screenWidth * 52 / 100), (int) (screenHeight * 14 / 100));
		
		HPSoldier2DimensionsX = soldier2RectDimensionsX;
		HPSoldier2DimensionsY = (int) (screenHeight * 5 / 100);
		HPSoldier2Position.setLocation(soldier2RectPosition.x, soldier2RectPosition.y + soldier2RectDimensionsY + 
				(int) (screenHeight * 3 / 100));
		
		armorSoldier2DimensionsX = soldier2RectDimensionsX;
		armorSoldier2DimensionsY = (int) (screenHeight * 5 / 100);
		armorSoldier2Position.setLocation(soldier2RectPosition.x, HPSoldier2Position.y + HPSoldier2DimensionsY + 
				(int) (screenHeight * 3 / 100));
		
		rehealSoldier2DimensionsX = (int) (screenWidth * 20/ 100);
		rehealSoldier2DimensionsY = (int) (screenHeight * 10 / 100);
		rehealSoldier2Position.setLocation((int) (screenWidth * 76 / 100),
				                           (int) (screenHeight * 17 / 100));
	
		repairArmorSoldier2DimensionsX = (int) (screenWidth * 20/ 100);
		repairArmorSoldier2DimensionsY = (int) (screenHeight * 10 / 100);
		repairArmorSoldier2Position.setLocation((int) (screenWidth * 76 / 100),
			(int) (screenHeight * 17 / 100) + (int) (screenHeight * 15 / 100));
		
		upgradeSoldier2DimensionsX = (int) (screenWidth * 20/ 100);
		upgradeSoldier2DimensionsY = (int) (screenHeight * 10 / 100);
		upgradeSoldier2Position.setLocation((int) (screenWidth * 76 / 100),
				(int) (screenHeight * 17 / 100) + 2 *  (int) (screenHeight * 15 / 100));

		soldier1DimensionsX = (int) (soldier1RectDimensionsX * 80 / 100);
		soldier1DimensionsY = (int) (soldier1RectDimensionsY * 80 / 100);
		soldier1Position.setLocation(soldier1RectPosition.x + (int) (soldier1RectDimensionsX * 20 / 100),
				soldier1RectPosition.y + (int) (soldier1RectDimensionsY * 5 / 100));
		
		soldier2DimensionsX = soldier1DimensionsX;
		soldier2DimensionsY = soldier1DimensionsY;
		soldier2Position.setLocation(soldier2RectPosition.x + (int) (soldier2RectDimensionsX * 20 / 100),
				soldier2RectPosition.y + (int) (soldier2RectDimensionsY * 5 / 100));
		
		pauseContinueButtonDimensionsX = (int) (screenWidth * 20/ 100);
		pauseContinueButtonDimensionsY = (int) (screenHeight * 10/ 100);
		pauseContinueButtonPosition.setLocation((int) (screenWidth * 40 / 100),
				(int) (screenHeight * 85 / 100));
		

		setModalSize();

		frame.setUndecorated(true);

		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setContentPane(this);
		
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
						Arena.getInstance(handler).setRunning(false);
					}
				}
				if (rehealButton1 != null) {
					Point me = e.getPoint();
					Rectangle bounds = new Rectangle(rehealSoldier1Position.x, rehealSoldier1Position.y, rehealSoldier1DimensionsX,
							rehealSoldier1DimensionsY);
					if (bounds.contains(me)) {
						reheal1();
					}
				}
				if (rehealButton2 != null) {
					Point me = e.getPoint();
					Rectangle bounds = new Rectangle(rehealSoldier2Position.x, rehealSoldier2Position.y, rehealSoldier2DimensionsX,
							rehealSoldier2DimensionsY);
					if (bounds.contains(me)) {
						reheal2();
					}
				}
				if (repairArmorButton1 != null) {
					Point me = e.getPoint();
					Rectangle bounds = new Rectangle(repairArmorSoldier1Position.x, repairArmorSoldier1Position.y, repairArmorSoldier1DimensionsX,
							repairArmorSoldier1DimensionsY);
					if (bounds.contains(me)) {
						repairArmor1();
					}
				}
				if (repairArmorButton2 != null) {
					Point me = e.getPoint();
					Rectangle bounds = new Rectangle(repairArmorSoldier2Position.x, repairArmorSoldier2Position.y, repairArmorSoldier2DimensionsX,
							repairArmorSoldier2DimensionsY);
					if (bounds.contains(me)) {
						repairArmor2();
					}
				}
				if (upgradeButton1 != null) {
					Point me = e.getPoint();
					Rectangle bounds = new Rectangle(upgradeSoldier1Position.x, upgradeSoldier1Position.y, upgradeSoldier1DimensionsX,
							upgradeSoldier1DimensionsY);
					if (bounds.contains(me)) {
						upgrade1();
					}
				}
				if (upgradeButton2 != null) {
					Point me = e.getPoint();
					Rectangle bounds = new Rectangle(upgradeSoldier2Position.x, upgradeSoldier2Position.y, upgradeSoldier2DimensionsX,
							upgradeSoldier2DimensionsY);
					if (bounds.contains(me)) {
						upgrade2();
					}
				}
				if (pauseContinueButton != null) {
					Point me = e.getPoint();
					Rectangle bounds = new Rectangle(pauseContinueButtonPosition.x, pauseContinueButtonPosition.y, pauseContinueButtonDimensionsX,
							pauseContinueButtonDimensionsY);
					if (bounds.contains(me)) {
						pauseContinue();
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
		g.drawString("Fight", (int) (screenWidth * 40 / 100), 65);
		
		g.drawImage(closeImg, closeImgPosition.x, closeImgPosition.y, closeImgDimensionsX, closeImgDimensionsY, this);
		
		g.setColor(new Color(255, 255, 255, 100));
		
		/** First fighter **/
		g.drawImage(rehealButton1,rehealSoldier1Position.x, rehealSoldier1Position.y,  rehealSoldier1DimensionsX,  rehealSoldier1DimensionsY, this);
		g.drawImage(repairArmorButton1,repairArmorSoldier1Position.x, repairArmorSoldier1Position.y,  repairArmorSoldier1DimensionsX,  repairArmorSoldier1DimensionsY, this);
		g.drawImage(upgradeButton1,upgradeSoldier1Position.x, upgradeSoldier1Position.y,  upgradeSoldier1DimensionsX,  upgradeSoldier1DimensionsY, this);
		
		g.fillRect(soldier1RectPosition.x, soldier1RectPosition.y, soldier1RectDimensionsX, soldier1RectDimensionsY);
		g.drawImage(soldier1, soldier1Position.x, soldier1Position.y,  soldier1DimensionsX,  soldier1DimensionsY, this);
		
		/** Second fighter **/
		g.drawImage(rehealButton2,rehealSoldier2Position.x, rehealSoldier2Position.y,  rehealSoldier2DimensionsX,  rehealSoldier2DimensionsY, this);
		g.drawImage(repairArmorButton2,repairArmorSoldier2Position.x, repairArmorSoldier2Position.y,  repairArmorSoldier2DimensionsX,  repairArmorSoldier2DimensionsY, this);
		g.drawImage(upgradeButton2,upgradeSoldier2Position.x, upgradeSoldier2Position.y,  upgradeSoldier2DimensionsX,  upgradeSoldier2DimensionsY, this);
		
		g.fillRect(soldier2RectPosition.x, soldier2RectPosition.y, soldier2RectDimensionsX, soldier2RectDimensionsY);
		g.drawImage(soldier2, soldier2Position.x, soldier2Position.y,  soldier2DimensionsX,  soldier2DimensionsY, this);
		
		
		g.setFont(new Font("Neuropol X", Font.BOLD, 30));
		g.setColor(Color.WHITE);
		g.drawString("HP         : ", HPSoldier1Position.x - (int) (screenWidth * 15 / 100), HPSoldier1Position.y + (int) (screenHeight * 3 / 100));
		g.drawString("Armor : ", HPSoldier1Position.x - (int) (screenWidth * 15 / 100), HPSoldier1Position.y + (int) (screenHeight * 11 / 100));
		
		/** First HP **/
		g.setColor(new Color(41, 168, 222, 100));
		g.fillRoundRect(HPSoldier1Position.x, HPSoldier1Position.y, 
				HPSoldier1DimensionsX, HPSoldier1DimensionsY, 10, 25);
		g.setColor(new Color(0,255,0, 175));
		g.fillRoundRect(HPSoldier1Position.x + 5, HPSoldier1Position.y + 2, 
				(int) (HPSoldier1DimensionsX * firstLife / 100) - 10, HPSoldier1DimensionsY - 4, 10, 25);
		
		/** Second HP **/
		g.setColor(new Color(41, 168, 222, 100));
		g.fillRoundRect(HPSoldier2Position.x, HPSoldier2Position.y, 
				HPSoldier2DimensionsX, HPSoldier2DimensionsY, 10, 25);
		g.setColor(new Color(255,0,0, 175));
		g.fillRoundRect(HPSoldier2Position.x + 5, HPSoldier2Position.y + 2, 
				(int) (HPSoldier2DimensionsX * secondLife / 100) - 10, HPSoldier2DimensionsY - 4, 10, 25);
		
		
		g.setFont(new Font("Neuropol X", Font.BOLD, 30));
		g.setColor(Color.WHITE);
		g.drawString(": HP", HPSoldier2Position.x + HPSoldier2DimensionsX + (int) (screenWidth * 3 / 100), HPSoldier1Position.y + (int) (screenHeight * 3 / 100));
		g.drawString(": Armor", HPSoldier2Position.x + HPSoldier2DimensionsX + (int) (screenWidth * 3 / 100), HPSoldier1Position.y + (int) (screenHeight * 11 / 100));
		
		
		/** First armor **/
		g.setColor(new Color(41, 168, 222, 100));
		g.fillRoundRect(armorSoldier1Position.x, armorSoldier1Position.y, 
				armorSoldier1DimensionsX, armorSoldier1DimensionsY, 10, 25);
		g.setColor(new Color(0,0,255, 175));
		g.fillRoundRect(armorSoldier1Position.x + 5, armorSoldier1Position.y + 2, 
				(int) (armorSoldier1DimensionsX * firstArmor / 100) - 10, armorSoldier1DimensionsY - 4, 10, 25);
		
		/** Second armor **/
		g.setColor(new Color(41, 168, 222, 100));
		g.fillRoundRect(armorSoldier2Position.x, armorSoldier2Position.y, 
				armorSoldier2DimensionsX, armorSoldier2DimensionsY, 10, 25);
		g.setColor(new Color(255,153,0, 175));
		g.fillRoundRect(armorSoldier2Position.x + 5, armorSoldier2Position.y + 2, 
				(int) (armorSoldier2DimensionsX * secondArmor / 100) - 10, armorSoldier2DimensionsY - 4, 10, 25);
		
		
		g.drawImage(pauseContinueButton, pauseContinueButtonPosition.x, pauseContinueButtonPosition.y,  pauseContinueButtonDimensionsX,  pauseContinueButtonDimensionsY, this);
		
		g.drawImage(cashImg, screenWidth - (int) (screenWidth * 8 / 100),
				screenHeight - (int) (screenHeight * 11 / 100), (int) (screenWidth * 6 / 100),
				(int) (screenHeight * 10 / 100), this);
		
		g.setFont(new Font("Neuropol X", Font.BOLD, 30));
		g.setColor(Color.WHITE);
		g.drawString(player.getGold() + " $", (int) (screenWidth * 70 / 100), (int) (screenHeight * 95 / 100));
	}

	private void setModalSize() {

		frame.setPreferredSize(new Dimension(screenWidth, screenHeight));
		frame.setMaximumSize(new Dimension(screenWidth, screenHeight));
		frame.setMinimumSize(new Dimension(screenWidth, screenHeight));
	}

	private void Init() {
		
		backgroundImg = ImageLoader.loadImage("/images/Age-of-Empires-Fight2.jpg");
		closeImg = ImageLoader.loadImage("/store/closeImg.png"); 
		
		this.loadFighters();
		upgradeButton1 = ImageLoader.loadImage("/store/UpgradeButton.png");
		rehealButton1 = ImageLoader.loadImage("/store/RehealButton.png");
		repairArmorButton1 = ImageLoader.loadImage("/store/RepairArmorButton.png");
		upgradeButton2 = ImageLoader.loadImage("/store/UpgradeButton.png");
		rehealButton2 = ImageLoader.loadImage("/store/RehealButton.png");
		repairArmorButton2 = ImageLoader.loadImage("/store/RepairArmorButton.png");
		pauseContinueButton = ImageLoader.loadImage("/store/PauseContinueButton.png");
		cashImg = ImageLoader.loadImage("/store/cash-icon.png");
	}
	
	private void reheal1() {
			
		if (!isActive) {
			
			System.out.println("Reheal first");
		}
	}
	
	private void repairArmor1() {
		
		if (!isActive) {
			
			System.out.println("Repair first");
		}
	}

	private void upgrade1() {
		
		if (!isActive) {
					
			System.out.println("Upgrade first");		
		}
	}
	
	private void reheal2() {
		
		if (!isActive) {
			
			System.out.println("Reheal second");
		}
	}
	
	private void repairArmor2() {
		
		if (!isActive) {
			
			System.out.println("Repair second");
		}
	}

	private void upgrade2() {
		
		if (!isActive) {
			
			System.out.println("Upgrade second");
		}
	}
	
	private void pauseContinue() {
		
		isActive = !isActive;
	}
	
	public boolean isVisible() {
		return frame.isVisible();
	}
	
	public void changeVisibility(boolean val) {
		frame.setVisible(val);
	}
	
	public void setFirstLife(int life) {
		
		firstLife = life;
		repaint();
	}
	
	public void setFirstArmor(int armor) {
		
		firstArmor = armor;
		repaint();
	}
	
	public void setSecondLife(int life) {
		
		secondLife = life;
		repaint();
	}
	
	public void setSecondArmor(int armor) {
		
		secondArmor = armor;
		repaint();
	}
}