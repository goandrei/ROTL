package rotl.store;

import java.awt.Dimension;

import javax.swing.JDialog;

import rotl.display.Display;
import rotl.utilities.Handler;

public class Store extends JDialog{

	private Display display;
	private Handler handler;
	
	private int screenWidth, screenHeight;
	
	public Store(Handler handler) {
		
		this.handler = handler;
		
		//get the parent screen size and get the modal's size
		screenWidth = handler.getGame().getWidth() / 2;
		screenHeight = (handler.getGame().getHeight() * 2) / 3;
		
		setModalSize();
		
		this.setUndecorated(true);
		
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	private void setModalSize() {
		
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setMaximumSize(  new Dimension(screenWidth, screenHeight));
		this.setMinimumSize(  new Dimension(screenWidth, screenHeight));
	}
	
}
