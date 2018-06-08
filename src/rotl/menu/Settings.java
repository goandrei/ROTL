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
import java.util.List;
import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JPanel;
import rotl.gfx.Sounds;
import rotl.utilities.Handler;
import rotl.utilities.ImageLoader;

public final class Settings extends JPanel implements MenuOption {

	private static final long serialVersionUID = 1L;

	private static int closeImgDimensionsX;
	private static int closeImgDimensionsY;
	
	private static int volumeIconDimensionsX;
	private static int volumeIconDimensionsY;
	
	private static List<Integer> volumeIconPosX = new ArrayList<>();
	private static List<Integer> volumeIconPosY = new ArrayList<>();
	
	private static Point closeImgPosition = new Point();

	private static Handler handler;
	private static JDialog frame = new JDialog();
	private static Settings single_instance = null;

	private static int screenWidth, screenHeight;

	private static BufferedImage backgroundImg;
	private static BufferedImage closeImg;

	private static BufferedImage volumeIcon;
	private static BufferedImage muteIcon;
	
	private Settings(Handler handler) {

		Settings.handler = handler;
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
		this.setOptions();

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

	public static Settings getOptions(Handler handler) {
		if (single_instance == null) {
			single_instance = new Settings(handler);
		}

		frame.setVisible(true);

		return single_instance;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawImage(backgroundImg, 0, 0, screenWidth, screenHeight, this);
		
		g.setFont(new Font("Neuropol X", Font.BOLD, titleFontSize));
		g.setColor(Color.WHITE);
		g.drawString("Settings", (int) (screenWidth * 35 / 100), (int) (screenHeight * 15 / 100));
		
		g.setFont(new Font("Neuropol X", Font.BOLD, 55));		
							
		g.drawString("Menu Music : ", (int) (screenWidth * 10 / 100), (int) (screenHeight * 40 / 100));		
		g.drawString("Game Music : ", (int) (screenWidth * 10 / 100), (int) (screenHeight * 60 / 100));		
		g.drawString("Arena/Store Music : ", (int) (screenWidth * 10 / 100), (int) (screenHeight * 80 / 100));
		
		final Sounds sounds = Sounds.getInstance();
		
		if (!sounds.isMenuMusicMuted())	
			g.drawImage(volumeIcon, volumeIconPosX.get(0), volumeIconPosY.get(0), volumeIconDimensionsX, volumeIconDimensionsY, this);
		else
			g.drawImage(muteIcon, volumeIconPosX.get(0), volumeIconPosY.get(0), volumeIconDimensionsX, volumeIconDimensionsY, this);
		
		if (!sounds.isGameMusicMuted())
			g.drawImage(volumeIcon, volumeIconPosX.get(1), volumeIconPosY.get(1), volumeIconDimensionsX, volumeIconDimensionsY, this);
		else
			g.drawImage(muteIcon, volumeIconPosX.get(1), volumeIconPosY.get(1), volumeIconDimensionsX, volumeIconDimensionsY, this);
		
		if (!sounds.isBattleMusicMuted())
			g.drawImage(volumeIcon, volumeIconPosX.get(2) + 10, volumeIconPosY.get(2), volumeIconDimensionsX, volumeIconDimensionsY, this);
		else
			g.drawImage(muteIcon, volumeIconPosX.get(2) + 10, volumeIconPosY.get(2), volumeIconDimensionsX, volumeIconDimensionsY, this);
		
		g.drawImage(closeImg, closeImgPosition.x, closeImgPosition.y, closeImgDimensionsX, closeImgDimensionsY, this);
	}

	private void setOptions() {

		closeImgDimensionsX = (int) (screenWidth * 5.5 / 100);
		closeImgDimensionsY = (int) (screenHeight * 9.8 / 100);
		closeImgPosition.setLocation(screenWidth - closeImgDimensionsX, 0);
		
		volumeIconDimensionsX = (int) (screenWidth * 7 / 100);		
		volumeIconDimensionsY = (int) (screenHeight * 11 / 100);		
		
		//X's for 3 Volume Icons
		volumeIconPosX.add((int) (screenWidth * 43 / 100));		
		volumeIconPosX.add((int) (screenWidth * 43 / 100));		
		volumeIconPosX.add((int) (screenWidth * 59 / 100));
		
		//Y's for 3 Volume Icons		
		volumeIconPosY.add((int) (screenHeight * 34 / 100) - 5);		
		volumeIconPosY.add((int) (screenHeight * 54 / 100) - 5);		
		volumeIconPosY.add((int) (screenHeight * 74 / 100) - 5);
		
		addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				
				if (closeImg != null) {
					
					final Point me = e.getPoint();
					final Rectangle bounds = new Rectangle(closeImgPosition.x, closeImgPosition.y, closeImgDimensionsX,
							closeImgDimensionsY);
					if (bounds.contains(me)) {
						frame.setVisible(false);
					}
				}
			
				if(volumeIcon != null && muteIcon != null) {
					
					final Point me = e.getPoint();
					
					final Rectangle bounds0 = new Rectangle(volumeIconPosX.get(0), volumeIconPosY.get(0), volumeIconDimensionsX,
							volumeIconDimensionsY);
					
					final Rectangle bounds1 = new Rectangle(volumeIconPosX.get(1), volumeIconPosY.get(1), volumeIconDimensionsX,
							volumeIconDimensionsY);
					
					final Rectangle bounds2 = new Rectangle(volumeIconPosX.get(2), volumeIconPosY.get(2), volumeIconDimensionsX,
							volumeIconDimensionsY);
					
					final Sounds sounds = Sounds.getInstance();
					boolean mustRepaint = false;
					
					if(bounds0.contains(me)) {
						
						if (!sounds.isMenuMusicMuted())
							sounds.stopMenuMusic();
						else
							sounds.loopMenuMusic();
						
						sounds.setMenuMusicMute(!sounds.isMenuMusicMuted());			
						mustRepaint = true;
					}
					
					if(bounds1.contains(me)) {
						
						sounds.setGameMusicMute(!sounds.isGameMusicMuted());
						mustRepaint = true;
					}
					
					if(bounds2.contains(me)) {
						
						sounds.setBattleMusicMute(!sounds.isBattleMusicMuted());
						mustRepaint = true;
					}
					
					if (mustRepaint)
						repaint();
				}
			}
		});
	}

	@Override
	public void init() {

		backgroundImg = ImageLoader.loadImage("/images/BGoption.jpg");
		closeImg = ImageLoader.loadImage("/images/closeImg.png");
		volumeIcon = ImageLoader.loadImage("/images/volume_icon.png");		
		muteIcon = ImageLoader.loadImage("/images/mute_icon.png");
	}
}