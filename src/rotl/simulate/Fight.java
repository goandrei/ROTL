package rotl.simulate;

import rotl.store.Store;
import rotl.utilities.Handler;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

public class Fight extends JPanel {

	private static final long serialVersionUID = 1L;

	private static Fight instance = null;

	private static int indexPhotoSoldair1 = 0;
	private static int indexPhotoSoldair2 = 1;
	private static int HPSoldair1 = 90;       // trebuie sa fie procente intregi 
	private static int HPSoldair2 = 100;       // (ca sa desenez bara de life si de armor)
	private static int ArmourSoldair1 = 75;
	private static int ArmourSoldair2 = 100;
	
	private static final ArrayList<String> soldiersSources = new ArrayList<>(
			Arrays.asList("Infantry", "Knight_templar", "Teutonic_knight"));
	private static final ArrayList<String> soldiersName = new ArrayList<>(
			Arrays.asList("Fighter", "Defender", "Warrior"));

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
	
	private static int repairArmourSoldier1DimensionsX;
	private static int repairArmourSoldier1DimensionsY;
	private static Point repairArmourSoldier1Position = new Point();

	private static int repairArmourSoldier2DimensionsX;
	private static int repairArmourSoldier2DimensionsY;
	private static Point repairArmourSoldier2Position = new Point();
	
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
	
	private static int armourSoldier1DimensionsX;
	private static int armourSoldier1DimensionsY;
	private static Point armourSoldier1Position = new Point();

	private static int armourSoldier2DimensionsX;
	private static int armourSoldier2DimensionsY;
	private static Point armourSoldier2Position = new Point();
	
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

	public static Fight getInstance(Handler handler) {

		if (instance == null) {
			instance = new Fight(handler);
		}
		frame.setVisible(true);
		return instance;
	}
	
	public static Fight getInstanceNonAbusive() {
		return instance;
	}

	private Fight(Handler handler) {

		this.handler = handler;

		// get the parent screen size and get the modal's size
		screenWidth = (handler.getGame().getWidth() * 8) / 10;
		screenHeight = (handler.getGame().getHeight() * 8) / 10;

		closeImgDimensionsX = (int) (screenWidth * 5.5 / 100);
		closeImgDimensionsY = (int) (screenHeight * 9.8 / 100);
		closeImgPosition.setLocation(screenWidth - closeImgDimensionsX, 0);
		
		rehealSoldier1DimensionsX = (int) (screenWidth * 20/ 100);
		rehealSoldier1DimensionsY = (int) (screenHeight * 10 / 100);
		rehealSoldier1Position.setLocation((int) (screenWidth * 5 / 100),
				                           (int) (screenHeight * 17 / 100));
		
		repairArmourSoldier1DimensionsX = (int) (screenWidth * 20/ 100);
		repairArmourSoldier1DimensionsY = (int) (screenHeight * 10 / 100);
		repairArmourSoldier1Position.setLocation((int) (screenWidth * 5 / 100),
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
		
		armourSoldier1DimensionsX = soldier1RectDimensionsX;
		armourSoldier1DimensionsY = (int) (screenHeight * 5 / 100);
		armourSoldier1Position.setLocation(soldier1RectPosition.x, HPSoldier1Position.y + HPSoldier1DimensionsY + 
				(int) (screenHeight * 3 / 100));
		
		soldier2RectDimensionsX = soldier1RectDimensionsX;
		soldier2RectDimensionsY = soldier1RectDimensionsY;
		soldier2RectPosition.setLocation((int) (screenWidth * 52 / 100), (int) (screenHeight * 14 / 100));
		
		HPSoldier2DimensionsX = soldier2RectDimensionsX;
		HPSoldier2DimensionsY = (int) (screenHeight * 5 / 100);
		HPSoldier2Position.setLocation(soldier2RectPosition.x, soldier2RectPosition.y + soldier2RectDimensionsY + 
				(int) (screenHeight * 3 / 100));
		
		armourSoldier2DimensionsX = soldier2RectDimensionsX;
		armourSoldier2DimensionsY = (int) (screenHeight * 5 / 100);
		armourSoldier2Position.setLocation(soldier2RectPosition.x, HPSoldier2Position.y + HPSoldier2DimensionsY + 
				(int) (screenHeight * 3 / 100));
		
		rehealSoldier2DimensionsX = (int) (screenWidth * 20/ 100);
		rehealSoldier2DimensionsY = (int) (screenHeight * 10 / 100);
		rehealSoldier2Position.setLocation((int) (screenWidth * 76 / 100),
				                           (int) (screenHeight * 17 / 100));
	
		repairArmourSoldier2DimensionsX = (int) (screenWidth * 20/ 100);
		repairArmourSoldier2DimensionsY = (int) (screenHeight * 10 / 100);
		repairArmourSoldier2Position.setLocation((int) (screenWidth * 76 / 100),
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
					Rectangle bounds = new Rectangle(repairArmourSoldier1Position.x, repairArmourSoldier1Position.y, repairArmourSoldier1DimensionsX,
							repairArmourSoldier1DimensionsY);
					if (bounds.contains(me)) {
						repairArmour1();
					}
				}
				if (repairArmorButton2 != null) {
					Point me = e.getPoint();
					Rectangle bounds = new Rectangle(repairArmourSoldier2Position.x, repairArmourSoldier2Position.y, repairArmourSoldier2DimensionsX,
							repairArmourSoldier2DimensionsY);
					if (bounds.contains(me)) {
						repairArmour2();
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
		
		g.drawImage(rehealButton1,rehealSoldier1Position.x, rehealSoldier1Position.y,  rehealSoldier1DimensionsX,  rehealSoldier1DimensionsY, this);
		g.drawImage(repairArmorButton1,repairArmourSoldier1Position.x, repairArmourSoldier1Position.y,  repairArmourSoldier1DimensionsX,  repairArmourSoldier1DimensionsY, this);
		g.drawImage(upgradeButton1,upgradeSoldier1Position.x, upgradeSoldier1Position.y,  upgradeSoldier1DimensionsX,  upgradeSoldier1DimensionsY, this);
		
		g.fillRect(soldier1RectPosition.x, soldier1RectPosition.y, soldier1RectDimensionsX, soldier1RectDimensionsY);
		g.drawImage(soldier1, soldier1Position.x, soldier1Position.y,  soldier1DimensionsX,  soldier1DimensionsY, this);
		
		g.drawImage(rehealButton2,rehealSoldier2Position.x, rehealSoldier2Position.y,  rehealSoldier2DimensionsX,  rehealSoldier2DimensionsY, this);
		g.drawImage(repairArmorButton2,repairArmourSoldier2Position.x, repairArmourSoldier2Position.y,  repairArmourSoldier2DimensionsX,  repairArmourSoldier2DimensionsY, this);
		g.drawImage(upgradeButton2,upgradeSoldier2Position.x, upgradeSoldier2Position.y,  upgradeSoldier2DimensionsX,  upgradeSoldier2DimensionsY, this);
		
		g.fillRect(soldier2RectPosition.x, soldier2RectPosition.y, soldier2RectDimensionsX, soldier2RectDimensionsY);
		g.drawImage(soldier2, soldier2Position.x, soldier2Position.y,  soldier2DimensionsX,  soldier2DimensionsY, this);
		
		
		g.setFont(new Font("Neuropol X", Font.BOLD, 30));
		g.setColor(Color.WHITE);
		g.drawString("HP         : ", HPSoldier1Position.x - (int) (screenWidth * 15 / 100), HPSoldier1Position.y + (int) (screenHeight * 3 / 100));
		g.drawString("Armour : ", HPSoldier1Position.x - (int) (screenWidth * 15 / 100), HPSoldier1Position.y + (int) (screenHeight * 11 / 100));
		g.setColor(new Color(41, 168, 222, 100));
		g.fillRoundRect(HPSoldier1Position.x, HPSoldier1Position.y, 
				HPSoldier1DimensionsX, HPSoldier1DimensionsY, 10, 25);
		g.setColor(new Color(0,255,0, 175));
		g.fillRoundRect(HPSoldier1Position.x + 5, HPSoldier1Position.y + 2, 
				(int) (HPSoldier1DimensionsX * HPSoldair1 / 100) - 10, HPSoldier1DimensionsY - 4, 10, 25);
		g.setColor(new Color(41, 168, 222, 100));
		g.fillRoundRect(HPSoldier2Position.x, HPSoldier2Position.y, 
				HPSoldier2DimensionsX, HPSoldier2DimensionsY, 10, 25);
		g.setColor(new Color(255,0,0, 175));
		g.fillRoundRect(HPSoldier2Position.x + 5, HPSoldier2Position.y + 2, 
				(int) (HPSoldier2DimensionsX * HPSoldair2 / 100) - 10, HPSoldier2DimensionsY - 4, 10, 25);
		
		
		g.setFont(new Font("Neuropol X", Font.BOLD, 30));
		g.setColor(Color.WHITE);
		g.drawString(": HP", HPSoldier2Position.x + HPSoldier2DimensionsX + (int) (screenWidth * 3 / 100), HPSoldier1Position.y + (int) (screenHeight * 3 / 100));
		g.drawString(": Armour", HPSoldier2Position.x + HPSoldier2DimensionsX + (int) (screenWidth * 3 / 100), HPSoldier1Position.y + (int) (screenHeight * 11 / 100));
		g.setColor(new Color(41, 168, 222, 100));
		g.fillRoundRect(armourSoldier1Position.x, armourSoldier1Position.y, 
				armourSoldier1DimensionsX, armourSoldier1DimensionsY, 10, 25);
		g.setColor(new Color(0,0,255, 175));
		g.fillRoundRect(armourSoldier1Position.x + 5, armourSoldier1Position.y + 2, 
				(int) (armourSoldier1DimensionsX * ArmourSoldair1 / 100) - 10, armourSoldier1DimensionsY - 4, 10, 25);
		g.setColor(new Color(41, 168, 222, 100));
		g.fillRoundRect(armourSoldier2Position.x, armourSoldier2Position.y, 
				armourSoldier2DimensionsX, armourSoldier2DimensionsY, 10, 25);
		g.setColor(new Color(255,153,0, 175));
		g.fillRoundRect(armourSoldier2Position.x + 5, armourSoldier2Position.y + 2, 
				(int) (armourSoldier2DimensionsX * ArmourSoldair2 / 100) - 10, armourSoldier2DimensionsY - 4, 10, 25);
		
		
		g.drawImage(pauseContinueButton, pauseContinueButtonPosition.x, pauseContinueButtonPosition.y,  pauseContinueButtonDimensionsX,  pauseContinueButtonDimensionsY, this);
		
		g.drawImage(cashImg, screenWidth - (int) (screenWidth * 8 / 100),
				screenHeight - (int) (screenHeight * 11 / 100), (int) (screenWidth * 6 / 100),
				(int) (screenHeight * 10 / 100), this);
		g.setFont(new Font("Neuropol X", Font.BOLD, 30));
		g.setColor(Color.WHITE);
		g.drawString("100000000 $ : ", (int) (screenWidth * 70 / 100), (int) (screenHeight * 95 / 100));
	}

	private void setModalSize() {

		frame.setPreferredSize(new Dimension(screenWidth, screenHeight));
		frame.setMaximumSize(new Dimension(screenWidth, screenHeight));
		frame.setMinimumSize(new Dimension(screenWidth, screenHeight));
	}

	private void Init() {

		URL resourceBKImg = getClass().getResource("/images/Age-of-Empires-Fight2.jpg");
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

		URL resourceSoldier1 = getClass().getResource("/store/" + soldiersSources.get(indexPhotoSoldair1) + ".png");
		try {
			soldier1 = ImageIO.read(resourceSoldier1);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		URL resourceSoldier2 = getClass().getResource("/store/" + soldiersSources.get(indexPhotoSoldair2) + ".png");
		try {
			soldier2 = ImageIO.read(resourceSoldier2);
		} catch (IOException e) {
			e.printStackTrace();
		}

		URL resourceupgradeButton1 = getClass().getResource("/store/UpgradeButton.png");
		try {
			upgradeButton1 = ImageIO.read(resourceupgradeButton1);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		URL resourcerehealButton1 = getClass().getResource("/store/RehealButton.png");
		try {
			rehealButton1 = ImageIO.read(resourcerehealButton1);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		URL resourcerepairArmourButton1 = getClass().getResource("/store/RepairArmorButton.png");
		try {
			repairArmorButton1 = ImageIO.read(resourcerepairArmourButton1);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		URL resourceupgradeButton2 = getClass().getResource("/store/UpgradeButton.png");
		try {
			upgradeButton2 = ImageIO.read(resourceupgradeButton2);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		URL resourcerehealButton2 = getClass().getResource("/store/RehealButton.png");
		try {
			rehealButton2 = ImageIO.read(resourcerehealButton2);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		URL resourcerepairArmourButton2 = getClass().getResource("/store/RepairArmorButton.png");
		try {
			repairArmorButton2 = ImageIO.read(resourcerepairArmourButton2);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		URL resourcepauseContinueButton = getClass().getResource("/store/PauseContinueButton.png");
		try {
			pauseContinueButton = ImageIO.read(resourcepauseContinueButton);
		} catch (IOException e) {
			e.printStackTrace();
		}

		URL resourceMyCash = getClass().getResource("/store/cash-icon.png");
		try {
			cashImg = ImageIO.read(resourceMyCash);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void reheal1() {
			
	}
	
	private void repairArmour1() {
		
	}

	private void upgrade1() {
		
	}
	
	private void reheal2() {
		
	}
	
	private void repairArmour2() {
		
	}

	private void upgrade2() {
		
	}
	
	private void pauseContinue() {
			
	}
	
	public boolean isVisible() {
		return frame.isVisible();
	}
	
	public void changeVisibility(boolean val) {
		frame.setVisible(val);
	}

}