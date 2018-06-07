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
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JPanel;
import rotl.utilities.Handler;
import rotl.utilities.ImageLoader;

public final class Options extends JPanel implements MenuOption {

	private static final long serialVersionUID = 1L;

	private static int closeImgDimensionsX;
	private static int closeImgDimensionsY;
	private static int volumeIconDimensionsX;
	private static int volumeIconDimensionsY;
	
	private static ArrayList<Integer> volumeIconPosX = new ArrayList<Integer>() ;
	private static ArrayList<Integer> volumeIconPosY = new ArrayList<Integer>();
	
	private static Point closeImgPosition = new Point();
	private static Handler handler;
	private static JDialog frame = new JDialog();
	private static Options single_instance = null;
	private static String content = "";

	private static int screenWidth, screenHeight;

	private static BufferedImage backgroundImg;
	private static BufferedImage closeImg;
	private static BufferedImage volumeIcon;
	private static BufferedImage muteIcon;
	
	private static int [] state = {1, 1, 1};
	
	private Options(Handler handler) {

		Options.handler = handler;
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

	public static Options getOptions(Handler handler) {
		if (single_instance == null) {
			single_instance = new Options(handler);
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
		g.drawString("Options", (int) (screenWidth * 35 / 100), (int) (screenHeight * 10 / 100));
		
		g.setFont(new Font("Neuropol X", Font.BOLD, 55));
		
		g.drawString("Menu Music : ", (int) (screenWidth * 10 / 100), (int) (screenHeight * 20 / 100));
		g.drawString("Game Music : ", (int) (screenWidth * 10 / 100), (int) (screenHeight * 40 / 100));
		g.drawString("Arena/Store Music : ", (int) (screenWidth * 10 / 100), (int) (screenHeight * 60 / 100));
		
		//Draw Options button
		g.drawImage(closeImg, closeImgPosition.x, closeImgPosition.y, closeImgDimensionsX, closeImgDimensionsY, this);
		
		//Draw 3 Volume / Mute Buttons
		if(state[0] == 1) {
			g.drawImage(volumeIcon, volumeIconPosX.get(0), volumeIconPosY.get(0), volumeIconDimensionsX, volumeIconDimensionsY, this);
		}else {
			g.drawImage(muteIcon, volumeIconPosX.get(0), volumeIconPosY.get(0), volumeIconDimensionsX, volumeIconDimensionsY, this);
		}
		
		if(state[1] == 1) {
			g.drawImage(volumeIcon, volumeIconPosX.get(1), volumeIconPosY.get(1), volumeIconDimensionsX, volumeIconDimensionsY, this);
		}else {
			g.drawImage(muteIcon, volumeIconPosX.get(1), volumeIconPosY.get(1), volumeIconDimensionsX, volumeIconDimensionsY, this);
		}
		
		if(state[2] == 1) {
			g.drawImage(volumeIcon, volumeIconPosX.get(2), volumeIconPosY.get(2), volumeIconDimensionsX, volumeIconDimensionsY, this);
		}else {
			g.drawImage(muteIcon, volumeIconPosX.get(2), volumeIconPosY.get(2), volumeIconDimensionsX, volumeIconDimensionsY, this);
		}
		
	}

	private void setOptions() {

		closeImgDimensionsX = (int) (screenWidth * 5.5 / 100);
		closeImgDimensionsY = (int) (screenHeight * 9.8 / 100);
		closeImgPosition.setLocation(screenWidth - closeImgDimensionsX, 0);
		
		volumeIconDimensionsX = (int) (screenWidth * 7 / 100);
		volumeIconDimensionsY = (int) (screenHeight * 11 / 100);
		//X's for 3 Volume Icons
		volumeIconPosX.add((int) (screenWidth * 45 / 100));
		volumeIconPosX.add((int) (screenWidth * 45 / 100));
		volumeIconPosX.add((int) (screenWidth * 63 / 100));
		
		//Y's for 3 Volume Icons
		volumeIconPosY.add((int) (screenHeight * 14 / 100) - 5);
		volumeIconPosY.add((int) (screenHeight * 34 / 100) - 5);
		volumeIconPosY.add((int) (screenHeight * 54 / 100) - 5);
		
		
		
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
				
				//Unmute -> Mute ; Mute -> Unmute
				if(volumeIcon != null) {
					Point me = e.getPoint();
					Rectangle bounds0 = new Rectangle(volumeIconPosX.get(0), volumeIconPosY.get(0), volumeIconDimensionsX,
							volumeIconDimensionsY);
					
					Rectangle bounds1 = new Rectangle(volumeIconPosX.get(1), volumeIconPosY.get(1), volumeIconDimensionsX,
							volumeIconDimensionsY);
					
					Rectangle bounds2 = new Rectangle(volumeIconPosX.get(2), volumeIconPosY.get(2), volumeIconDimensionsX,
							volumeIconDimensionsY);
					if(bounds0.contains(me)) {
						state[0] = 1 - state[0];
						repaint();
					}
					
					if(bounds1.contains(me)) {
						state[1] = 1 - state[1];
						repaint();
					}
					
					if(bounds2.contains(me)) {
						state[2] = 1 - state[2];
						repaint();
					}
				}
			}
		});

	}

	public static int [] getStateArray() {
		return state;
	}
	@Override
	public void init() {

		backgroundImg = ImageLoader.loadImage("/images/BGoption.jpg");
		closeImg = ImageLoader.loadImage("/images/closeImg.png");
		volumeIcon = ImageLoader.loadImage("/images/volume_icon.png");
		muteIcon = ImageLoader.loadImage("/images/mute_icon.png");
	}
}