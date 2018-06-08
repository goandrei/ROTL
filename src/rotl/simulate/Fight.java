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
	
	/** On fight **/
	private static BufferedImage rehealButton1;
	private static BufferedImage repairArmorButton1;
	private static BufferedImage upgradeButton1;
	private static BufferedImage rehealButton2;
	private static BufferedImage repairArmorButton2;
	private static BufferedImage upgradeButton2;
	private static BufferedImage pauseContinueButton;
	
	/** On pause **/
	private static BufferedImage rehealButton1Pause;
	private static BufferedImage repairArmorButton1Pause;
	private static BufferedImage upgradeButton1Pause;
	private static BufferedImage rehealButton2Pause;
	private static BufferedImage repairArmorButton2Pause;
	private static BufferedImage upgradeButton2Pause;
	private static BufferedImage pauseContinueButtonPause;
	private static BufferedImage cashImg;
	
	private static int firstMaxLife;
	private static int firstLife;
	private static int firstMaxArmor;
	private static int firstArmor;
	private static int secondMaxLife;
	private static int secondLife;
	private static int secondMaxArmor;
	private static int secondArmor;
	private static boolean isActive = false;
	private static Thread fight = null;
	
	private void loadFighters() {
		
		if (indexFirst != -1 && indexSecond != -1) {
			
			final SoldierInfoArena sInfo1 = player.getSoldierInfo(indexFirst);
			final SoldierInfoArena sInfo2 = player.getSoldierInfo(indexSecond);
	
			soldier1 = ImageLoader.loadImage("/store/" + soldiersSources.get(sInfo1.getSoldierType()) + ".png");
			soldier2 = ImageLoader.loadImage("/store/" + soldiersSources.get(sInfo2.getSoldierType()) + ".png");
			
			firstMaxLife = firstLife = sInfo1.getLife();
			firstMaxArmor = firstArmor = sInfo1.getArmor();
			secondMaxLife = secondLife = sInfo2.getLife();
			secondMaxArmor = secondArmor = sInfo2.getArmor();
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
					
					final Point me = e.getPoint();
					final Rectangle bounds = new Rectangle(closeImgPosition.x, closeImgPosition.y, closeImgDimensionsX,
							closeImgDimensionsY);
				
					if (bounds.contains(me)) {
						
						frame.setVisible(false);
						
						indexFirst = -1;
						indexSecond = -1;
						
						/** Select for fight info **/
						soldier1 = null;
						soldier2 = null;
						endFight();
					}
				}
				
				if (rehealButton1 != null) {
					
					final Point me = e.getPoint();
					final Rectangle bounds = new Rectangle(rehealSoldier1Position.x, rehealSoldier1Position.y, rehealSoldier1DimensionsX,
							rehealSoldier1DimensionsY);
					
					if (bounds.contains(me)) {
						healFirst();
					}
				}
				
				if (rehealButton2 != null) {
					
					final Point me = e.getPoint();
					final Rectangle bounds = new Rectangle(rehealSoldier2Position.x, rehealSoldier2Position.y, rehealSoldier2DimensionsX,
							rehealSoldier2DimensionsY);
					
					if (bounds.contains(me)) {
						healSecond();
					}
				}
				
				if (repairArmorButton1 != null) {
					
					final Point me = e.getPoint();
					final Rectangle bounds = new Rectangle(repairArmorSoldier1Position.x, repairArmorSoldier1Position.y, repairArmorSoldier1DimensionsX,
							repairArmorSoldier1DimensionsY);
					
					if (bounds.contains(me)) {
						repairArmorFirst();
					}
				}
				
				if (repairArmorButton2 != null) {
					
					final Point me = e.getPoint();
					final Rectangle bounds = new Rectangle(repairArmorSoldier2Position.x, repairArmorSoldier2Position.y, repairArmorSoldier2DimensionsX,
							repairArmorSoldier2DimensionsY);
					
					if (bounds.contains(me)) {
						repairArmorSecond();
					}
				}
				
				if (upgradeButton1 != null) {
					
					final Point me = e.getPoint();
					final Rectangle bounds = new Rectangle(upgradeSoldier1Position.x, upgradeSoldier1Position.y, upgradeSoldier1DimensionsX,
							upgradeSoldier1DimensionsY);
					
					if (bounds.contains(me)) {
						upgradeFirst();
					}
				}
				
				if (upgradeButton2 != null) {
					
					final Point me = e.getPoint();
					final Rectangle bounds = new Rectangle(upgradeSoldier2Position.x, upgradeSoldier2Position.y, upgradeSoldier2DimensionsX,
							upgradeSoldier2DimensionsY);
					
					if (bounds.contains(me)) {
						upgradeSecond();
					}
				}
				
				if (pauseContinueButton != null && pauseContinueButtonPause != null) {
					
					final Point me = e.getPoint();
					final Rectangle bounds = new Rectangle(pauseContinueButtonPosition.x, pauseContinueButtonPosition.y, pauseContinueButtonDimensionsX,
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
		if(isActive) {
			
			g.drawImage(rehealButton1,rehealSoldier1Position.x, rehealSoldier1Position.y,  rehealSoldier1DimensionsX,  rehealSoldier1DimensionsY, this);
			g.drawImage(repairArmorButton1,repairArmorSoldier1Position.x, repairArmorSoldier1Position.y,  repairArmorSoldier1DimensionsX,  repairArmorSoldier1DimensionsY, this);
			g.drawImage(upgradeButton1,upgradeSoldier1Position.x, upgradeSoldier1Position.y,  upgradeSoldier1DimensionsX,  upgradeSoldier1DimensionsY, this);
		
		} else {
			g.drawImage(rehealButton1Pause,rehealSoldier1Position.x, rehealSoldier1Position.y,  rehealSoldier1DimensionsX,  rehealSoldier1DimensionsY, this);
			g.drawImage(repairArmorButton1Pause,repairArmorSoldier1Position.x, repairArmorSoldier1Position.y,  repairArmorSoldier1DimensionsX,  repairArmorSoldier1DimensionsY, this);
			g.drawImage(upgradeButton1Pause,upgradeSoldier1Position.x, upgradeSoldier1Position.y,  upgradeSoldier1DimensionsX,  upgradeSoldier1DimensionsY, this);
		}
		
		g.fillRect(soldier1RectPosition.x, soldier1RectPosition.y, soldier1RectDimensionsX, soldier1RectDimensionsY);
		g.drawImage(soldier1, soldier1Position.x, soldier1Position.y,  soldier1DimensionsX,  soldier1DimensionsY, this);
		
		/** Second fighter **/
		if(isActive) {
			
			g.drawImage(rehealButton2,rehealSoldier2Position.x, rehealSoldier2Position.y,  rehealSoldier2DimensionsX,  rehealSoldier2DimensionsY, this);
			g.drawImage(repairArmorButton2,repairArmorSoldier2Position.x, repairArmorSoldier2Position.y,  repairArmorSoldier2DimensionsX,  repairArmorSoldier2DimensionsY, this);
			g.drawImage(upgradeButton2,upgradeSoldier2Position.x, upgradeSoldier2Position.y,  upgradeSoldier2DimensionsX,  upgradeSoldier2DimensionsY, this);
		
		} else {
			g.drawImage(rehealButton2Pause,rehealSoldier2Position.x, rehealSoldier2Position.y,  rehealSoldier2DimensionsX,  rehealSoldier2DimensionsY, this);
			g.drawImage(repairArmorButton2Pause,repairArmorSoldier2Position.x, repairArmorSoldier2Position.y,  repairArmorSoldier2DimensionsX,  repairArmorSoldier2DimensionsY, this);
			g.drawImage(upgradeButton2Pause,upgradeSoldier2Position.x, upgradeSoldier2Position.y,  upgradeSoldier2DimensionsX,  upgradeSoldier2DimensionsY, this);
		}
		
		g.fillRect(soldier2RectPosition.x, soldier2RectPosition.y, soldier2RectDimensionsX, soldier2RectDimensionsY);
		g.drawImage(soldier2, soldier2Position.x, soldier2Position.y,  soldier2DimensionsX,  soldier2DimensionsY, this);
		
		
		g.setFont(new Font("Neuropol X", Font.BOLD, 30));
		g.setColor(Color.WHITE);
		g.drawString("HP      : ", HPSoldier1Position.x - (int) (screenWidth * 10 / 100), HPSoldier1Position.y + (int) (screenHeight * 3 / 100));
		g.drawString("Armor : ", HPSoldier1Position.x - (int) (screenWidth * 10 / 100), HPSoldier1Position.y + (int) (screenHeight * 11 / 100));
		
		/** First HP **/
		g.setColor(new Color(41, 168, 222, 100));
		g.fillRoundRect(HPSoldier1Position.x, HPSoldier1Position.y, 
				HPSoldier1DimensionsX, HPSoldier1DimensionsY, 10, 25);
		g.setColor(new Color(0,255,0, 175));
		
		double proc = ((firstMaxLife == 0) ? 0.0 : (firstLife * 1.0) / firstMaxLife);
		g.fillRoundRect(HPSoldier1Position.x + 5, HPSoldier1Position.y + 2, 
				(int) (HPSoldier1DimensionsX * proc) - 10, HPSoldier1DimensionsY - 4, 10, 25);
		
		g.setFont(new Font("Neuropol X", Font.BOLD, 25));
		g.setColor(Color.WHITE);
		
		String info = String.format("%s/%s", firstLife, firstMaxLife);
		g.drawString(info, HPSoldier1Position.x + (int)(HPSoldier1DimensionsX / 2) - 10 - 6 * info.length(), HPSoldier1Position.y + 20);
		
		/** Second HP **/
		g.setColor(new Color(41, 168, 222, 100));
		g.fillRoundRect(HPSoldier2Position.x, HPSoldier2Position.y, 
				HPSoldier2DimensionsX, HPSoldier2DimensionsY, 10, 25);
		g.setColor(new Color(255,0,0, 175));
		
		proc = ((secondMaxLife == 0) ? 0.0 : (secondLife * 1.0) / secondMaxLife);
		g.fillRoundRect(HPSoldier2Position.x + 5, HPSoldier2Position.y + 2, 
				(int) (HPSoldier2DimensionsX * proc) - 10, HPSoldier2DimensionsY - 4, 10, 25);
		
		g.setFont(new Font("Neuropol X", Font.BOLD, 25));
		g.setColor(Color.WHITE);
		
		info = String.format("%s/%s", secondLife, secondMaxLife);
		g.drawString(info, HPSoldier2Position.x + (int)(HPSoldier2DimensionsX / 2) - 10 - 6 * info.length(), HPSoldier2Position.y + 20);
		
		g.setFont(new Font("Neuropol X", Font.BOLD, 30));
		g.setColor(Color.WHITE);
		g.drawString(": HP", HPSoldier2Position.x + HPSoldier2DimensionsX + (int) (screenWidth * 3 / 100), HPSoldier1Position.y + (int) (screenHeight * 3 / 100));
		g.drawString(": Armor", HPSoldier2Position.x + HPSoldier2DimensionsX + (int) (screenWidth * 3 / 100), HPSoldier1Position.y + (int) (screenHeight * 11 / 100));
		
		
		/** First armor **/
		g.setColor(new Color(41, 168, 222, 100));
		g.fillRoundRect(armorSoldier1Position.x, armorSoldier1Position.y, 
				armorSoldier1DimensionsX, armorSoldier1DimensionsY, 10, 25);
		g.setColor(new Color(0,0,255, 175));
		
		proc = ((firstMaxArmor == 0) ? 0.0 : (firstArmor * 1.0) / firstMaxArmor);
		g.fillRoundRect(armorSoldier1Position.x + 5, armorSoldier1Position.y + 2, 
				(int) (armorSoldier1DimensionsX * proc) - 10, armorSoldier1DimensionsY - 4, 10, 25);
		
		g.setFont(new Font("Neuropol X", Font.BOLD, 25));
		g.setColor(Color.WHITE);
		
		info = String.format("%s/%s", firstArmor, firstMaxArmor);
		g.drawString(info, armorSoldier1Position.x + (int)(armorSoldier1DimensionsX / 2) - 10 - 6 * info.length(), armorSoldier1Position.y + 20);
		
		/** Second armor **/
		g.setColor(new Color(41, 168, 222, 100));
		g.fillRoundRect(armorSoldier2Position.x, armorSoldier2Position.y, 
				armorSoldier2DimensionsX, armorSoldier2DimensionsY, 10, 25);
		g.setColor(new Color(255,153,0, 175));
		
		proc = ((secondMaxArmor == 0) ? 0.0 : (secondArmor * 1.0) / secondMaxArmor);
		g.fillRoundRect(armorSoldier2Position.x + 5, armorSoldier2Position.y + 2, 
				(int) (armorSoldier2DimensionsX * proc) - 10, armorSoldier2DimensionsY - 4, 10, 25);
		
		g.setFont(new Font("Neuropol X", Font.BOLD, 25));
		g.setColor(Color.WHITE);
		
		info = String.format("%s/%s", secondArmor, secondMaxArmor);
		g.drawString(info, armorSoldier2Position.x + (int)(armorSoldier2DimensionsX / 2) - 10 - 6 * info.length(), armorSoldier2Position.y + 20);
		
		if (isActive)
			g.drawImage(pauseContinueButton, pauseContinueButtonPosition.x, pauseContinueButtonPosition.y,  pauseContinueButtonDimensionsX,  pauseContinueButtonDimensionsY, this);
		else
			g.drawImage(pauseContinueButtonPause, pauseContinueButtonPosition.x, pauseContinueButtonPosition.y,  pauseContinueButtonDimensionsX,  pauseContinueButtonDimensionsY, this);
		
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
		
		upgradeButton1Pause = ImageLoader.loadImage("/store/UpgradeButtonPause.png");
		rehealButton1Pause = ImageLoader.loadImage("/store/RehealButtonPause.png");
		repairArmorButton1Pause = ImageLoader.loadImage("/store/RepairArmorButtonPause.png");
		upgradeButton2Pause = ImageLoader.loadImage("/store/UpgradeButtonPause.png");
		rehealButton2Pause = ImageLoader.loadImage("/store/RehealButtonPause.png");
		repairArmorButton2Pause = ImageLoader.loadImage("/store/RepairArmorButtonPause.png");
		pauseContinueButtonPause = ImageLoader.loadImage("/store/PauseContinueButtonPause.png");
		cashImg = ImageLoader.loadImage("/store/cash-icon.png");
	}
	
	private void healFirst() {
			
		if (!isActive) {
			
			player.healSoldier(indexFirst);
			updateFirstFighter();
			refreshArenaAndFight();
		}
	}
	
	private void repairArmorFirst() {
		
		if (!isActive) {
			
			player.repairArmorSoldier(indexFirst);
			updateFirstFighter();
			refreshArenaAndFight();
		}
	}

	private void upgradeFirst() {
		
		if (!isActive) {
					
			player.upgradeSoldier(indexFirst);
			updateFirstFighter();
			refreshArenaAndFight();
		}
	}
	
	private void healSecond() {
		
		if (!isActive) {
			
			player.healSoldier(indexSecond);
			updateSecondFighter();
			refreshArenaAndFight();
		}
	}
	
	private void repairArmorSecond() {
		
		if (!isActive) {
			
			player.repairArmorSoldier(indexSecond);
			updateSecondFighter();
			refreshArenaAndFight();
		}
	}

	private void upgradeSecond() {
		
		if (!isActive) {
			
			player.upgradeSoldier(indexSecond);
			updateSecondFighter();
			refreshArenaAndFight();
		}
	}
	
	private void pauseContinue() {
		
		isActive = !isActive;
		
		if (isActive) {
			
			fight = new Thread(new CombatUnit(indexFirst, indexSecond));
			fight.start();
			
			final Runnable collector = () -> {
				
				try {
				
					fight.join();
				
				} catch (InterruptedException ex) {}
				
				endFight();
			};
			
			new Thread(collector).start();
			
		} else {
			
			endFight();
		}
		
		repaint();
	}
	
	public boolean isVisible() {
		return frame.isVisible();
	}
	
	public void changeVisibility(boolean val) {
		frame.setVisible(val);
	}
	
	private void refreshArenaAndFight() {
		
		if (Arena.isBuilt()) {
			Arena.getInstanceNonAbusive().repaint();
		}
		repaint();
	}
	
	private void updateFirstFighter() {
		
		final SoldierInfoArena infoFirst = player.getSoldierInfo(indexFirst);
		firstLife = infoFirst.getLife();
		
		if (firstLife > firstMaxLife)
			firstMaxLife = firstLife;
		
		firstArmor = infoFirst.getArmor();
		
		if (firstArmor > firstMaxArmor)
			firstMaxArmor = firstArmor;
	}
	
	private void updateSecondFighter() {
		
		final SoldierInfoArena infoSecond = player.getSoldierInfo(indexSecond);
		secondLife = infoSecond.getLife();
		
		if (secondLife > secondMaxLife)
			secondMaxLife = secondLife;
		
		secondArmor = infoSecond.getArmor();
		
		if (secondArmor > secondMaxArmor)
			secondMaxArmor = secondArmor;
	}
	
	public void updateFightersInfo() {
		
		updateFirstFighter();
		updateSecondFighter();
		refreshArenaAndFight();
	}
	
	private void endFight() {
		
		isActive = false;
		
		if (fight != null && fight.isAlive()) {
			fight.interrupt();
		}	
		
		fight = null;
	}
}