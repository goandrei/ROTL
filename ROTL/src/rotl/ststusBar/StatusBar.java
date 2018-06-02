package rotl.ststusBar;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JPanel;

import rotl.store.Store;
import rotl.utilities.Handler;

public class StatusBar extends JPanel {

	private static final long serialVersionUID = 1L;

	private static StatusBar instance = null;

	private static int closeImgDimensionsX;
	private static int closeImgDimensionsY;
	private static Point closeImgPosition = new Point();
	
	private static int positionX;
	private static int positionY;
	private static Point position = new Point();
	
	private static int storeButtonX;
	private static int storeButtonY;
	private static int storeButtonXDimenssion;
	private static int storeButtonYDimenssion;
	private static Point storeButtonPosition = new Point();

	private static Handler handler;
	private static JDialog frame = new JDialog();

	private static int screenWidth, screenHeight;

	private static BufferedImage closeImg;
	private static BufferedImage backgroundImg;
	private static BufferedImage storeButton;
	
	
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

		closeImgDimensionsX = (int) (screenWidth * 5.5 / 100);
		closeImgDimensionsY = (int) (screenHeight * 9.8 / 100);
		closeImgPosition.setLocation(screenWidth - closeImgDimensionsX, 0);
		
		positionX = (handler.getGame().getWidth() - screenWidth) / 2;
		positionY = handler.getGame().getHeight() - screenHeight - 10;
		position.setLocation(positionX, positionY);
		
		storeButtonX = (int) (screenWidth * 80 / 100);
		storeButtonY = (int) (screenHeight * 15 / 100);
		storeButtonXDimenssion = (int) (screenWidth * 20 / 100);
		storeButtonYDimenssion = (int) (screenHeight * 70 / 100);
		storeButtonPosition.setLocation(storeButtonX, storeButtonY);

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
			}
		});
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawImage(backgroundImg, 0, 0, screenWidth, screenHeight, this);
		g.setFont(new Font("Neuropol X", Font.BOLD, 100));
		g.setColor(Color.WHITE);
		g.drawImage(storeButton, storeButtonX, storeButtonY, storeButtonXDimenssion, storeButtonYDimenssion, this);
	}

	private void setModalSize() {

		frame.setPreferredSize(new Dimension(screenWidth, screenHeight));
		frame.setMaximumSize(new Dimension(screenWidth, screenHeight));
		frame.setMinimumSize(new Dimension(screenWidth, screenHeight));
	}

	private void Init() {

		URL resourceBKImg = getClass().getResource("/images/pexels-photo-370799.jpeg");
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
		URL resourcestoreButton = getClass().getResource("/images/storeButton.png");
		try {
			storeButton = ImageIO.read(resourcestoreButton);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void upgrade() {

	}

	private void buy() {

	}
}
