package rotl.display;

import java.awt.Canvas;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Display {	
	
	private JFrame frame;
	private Canvas canvas;
	
	private String title;
	private int screenWidth, screenHeight;
	
	public Display(String title){
		this.title = title;
		createDisplay();
	}
	
	private void createDisplay(){
		
		//get the screen's maxi resolution
		Dimension screenMaxResolution = Toolkit.getDefaultToolkit().getScreenSize();
		screenWidth  = (int)screenMaxResolution.getWidth();
		screenHeight = (int)screenMaxResolution.getHeight();

		frame = new JFrame(title);
		Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/cursor_final.png"));
	    Point hotspot = new Point(0,0);
	    Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(image, hotspot, "pencil");
	    frame.setCursor(cursor);

		frame.setSize(screenWidth,screenHeight);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setUndecorated(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(screenWidth,screenHeight));
		canvas.setMinimumSize(  new Dimension(screenWidth,screenHeight));
		canvas.setMaximumSize(  new Dimension(screenWidth,screenHeight));
		canvas.setFocusable(false);
		
		frame.add(canvas);
		frame.pack();
		
	}
		
	//GETers
	public int getWidth() {
		return screenWidth;
	}
	
	public int getHeight() {
		return screenHeight;
	}
	
	public Canvas getCanvas(){
		return canvas;
	}
	
	public JFrame getFrame(){
		return frame;
	}
}