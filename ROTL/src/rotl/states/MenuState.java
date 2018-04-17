package rotl.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import rotl.store.Store;
import rotl.utilities.Handler;

public class MenuState extends State{
	
	private boolean start, options, instructions, highScores, exit;
	private int startSize, optionsSize , instructionsSize, highScoresSize, exitSize;
	private Rectangle startRect, optionsRect, instructionsRect, highScoresRect, exitRect;
	private int xMenu, yMenu;
	private final int textSize = 48;
	private Font font;
	
	Rectangle shopTest;
	boolean shop = false;
	
	public MenuState(Handler handler) {
		super(handler);
		
		start = true;
		options = instructions = highScores = exit = false;
		
		font = new Font("Garamond",Font.BOLD,textSize);
		
		xMenu = handler.getGame().getWidth() / 12;
		yMenu = handler.getGame().getHeight() / 4;
		
		startRect = new Rectangle(xMenu, yMenu - textSize, 116, textSize);
		optionsRect = new Rectangle(xMenu, yMenu + 20, textSize * 3 + 30, textSize);
		instructionsRect = new Rectangle(xMenu, yMenu + 80, textSize * 5 + 30, textSize);
		highScoresRect = new Rectangle(xMenu, yMenu + 140, textSize * 6, textSize);
		exitRect = new Rectangle(xMenu, yMenu + 200, textSize * 2, textSize);
		
		//shop test
		shopTest = new Rectangle(xMenu,yMenu + 260, textSize * 2, textSize);
	
		addHandlers();
	}
	
	public void addHandlers() {
		
		handler.getGame().getDisplay().getCanvas().addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseDragged(MouseEvent event) {
			}

			@Override
			public void mouseMoved(MouseEvent event) {
				Point mousePosition = event.getPoint();
				
				if(startRect.contains(mousePosition)) {
					start = true;
				}else {
					start = false;
				}
				if(optionsRect.contains(mousePosition)) {
					options = true;
				}else {
					options = false;
				}
				if(instructionsRect.contains(mousePosition)) {
					instructions = true;
				}else {
					instructions = false;
				}
				if(highScoresRect.contains(mousePosition)) {
					highScores = true;
				}else {
					highScores = false;
				}
				if(exitRect.contains(mousePosition)) {
					exit = true;
				}else {
					exit = false;
				}
				
				if(shopTest.contains(mousePosition)) {
					shop = true;
				}else {
					shop = false;
				}
			}
		});

		handler.getGame().getDisplay().getCanvas().addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent event) {
				
				if(start)
					handler.getStateManager().setActualState(new GameState(handler.getGame().getWidth(), handler.getGame().getHeight(), handler));
				
				if(shop) {
					Store store = new Store(handler);
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				
			}
			
		});
	}
	
	@Override
	public void update() {
		System.out.println(handler.getMouseManager().getMouseX() + "   " + handler.getMouseManager().getMouseY());
	}

	@Override
	public void render(Graphics g) {
		
		g.setColor(Color.black);
		g.fillRect(0, 0, handler.getGame().getWidth(), handler.getGame().getHeight());
		g.setColor(Color.WHITE);

		g.setFont(font);
		g.setColor(Color.red);
		
		if(start) {
			g.setColor(Color.white);
		}else {
			g.setColor(Color.red);
		}
		g.drawString("Start", xMenu, yMenu);

		if(options) {
			g.setColor(Color.white);
		}else {
			g.setColor(Color.red);
		}
		g.drawString("Options", xMenu, yMenu + 60);
		
		if(instructions) {
			g.setColor(Color.white);
		}else {
			g.setColor(Color.red);
		}
		g.drawString("Instructions", xMenu, yMenu + 120);

		if(highScores) {
			g.setColor(Color.white);
		}else {
			g.setColor(Color.red);
		}
		g.drawString("High Scores", xMenu, yMenu + 180);
		
		if(exit) {
			g.setColor(Color.white);
		}else {
			g.setColor(Color.red);
		}
		g.drawString("Exit", xMenu, yMenu + 240);
		
		if(shop) {
			g.setColor(Color.white);
		}else {
			g.setColor(Color.red);
		}
		g.drawString("Shop - test", xMenu, yMenu + 300);
		
		
	}

}
