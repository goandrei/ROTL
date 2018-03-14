package rotl.states;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import rotl.display.Display;

public class IntroState extends State {
	
	private int screenWidth, screenHeight;
	private Display display;
	private Graphics g;
	private BufferStrategy bufferStrategy;
	private Image image;
	
	
	public IntroState(Display display, Graphics graphics, BufferStrategy bufferStrategy ) {
		this.display = display;
		this.g = graphics;
		this.bufferStrategy = bufferStrategy;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		screenWidth  =(int) screenSize.getWidth();
		screenHeight =(int) screenSize.getHeight();
		image = Toolkit.getDefaultToolkit().createImage("/ROTL/resources/images/punchline_intro_final.gif");
	}

	@Override
	public void update() {

	}

	@Override
	public void render(Graphics g) {
		
		
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				System.out.println("in IntroState render");
				display.getFrame().add(image);

				display.getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				display.getFrame().setSize(screenWidth, screenHeight);
				display.getFrame().setVisible(true);
				
				

			}
		});

		
	}

}