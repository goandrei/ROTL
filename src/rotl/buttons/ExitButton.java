package rotl.buttons;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import rotl.states.GameState;
import rotl.states.MenuState;
import rotl.statusBar.StatusBar;
import rotl.utilities.Handler;
import rotl.utilities.ImageLoader;

public class ExitButton implements Button {

	private BufferedImage icon;
	private Rectangle position;
	private final int POS_X = 5, POS_Y = 5;
	private final int INIT_WIDTH = 64, INIT_HEIGHT = 32;
	private final int HOVER_WIDTH = 160, HOVER_HEIGHT = 64;
	private int width, height;
	
	private Handler handler;
	
	private GameState gameState;
	
	public ExitButton(Handler handler, GameState gameState) {
		
		this.handler = handler;
		this.gameState = gameState;
		
		icon = ImageLoader.loadImage("/images/goBack.png");
		
		width = INIT_WIDTH;
		height = INIT_HEIGHT;
		
		buildListenerRectangle();
		addMouseListener();
	}

	private void addMouseListener() {

		handler.getGame().getDisplay().getCanvas().addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent event) {
				Point mousePosition = event.getPoint();

				if(position.contains(mousePosition)){
					MenuState menuState = new MenuState(handler, gameState);
					handler.getStateManager().setActualState(menuState);
					StatusBar.changeVisibility(false);
				}
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});

		handler.getGame().getDisplay().getCanvas().addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseDragged(MouseEvent e) {

			}

			@Override
			public void mouseMoved(MouseEvent e) {
				Point mousePosition = e.getPoint();
				if (position.contains(mousePosition)){
					height = HOVER_HEIGHT;
					width = HOVER_WIDTH;
				} else {
					height = INIT_HEIGHT;
					width = INIT_WIDTH;
				}
			}
		});
	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage(icon, POS_X, POS_Y, width, height, null);
	}

	@Override
	public void update() {

	}

	@Override
	public void buildListenerRectangle() {
		position = new Rectangle(POS_X, POS_Y, width, height);
	}
}
