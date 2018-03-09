package dev.rofl.display;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Toolkit;

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
		frame.setSize(screenWidth,screenHeight);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setUndecorated(true);
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
	
	public Canvas getCanvas(){
		return canvas;
	}
	
	public JFrame getFrame(){
		return frame;
	}
}
