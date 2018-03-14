package rotl.states;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;

import javax.swing.JPanel;

public class ImagePanel extends JPanel {

	Image image;
	Graphics g;
	private BufferStrategy bufferStrategy;
	public ImagePanel(Graphics graphics, BufferStrategy bufferStrategy) {
		image = Toolkit.getDefaultToolkit().createImage("/ROTL/resources/images/punchline_intro_final.gif");
		this.g = graphics;
		this.bufferStrategy = bufferStrategy;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (image != null) {
			System.out.println("image not null");
			g.drawImage(image, 0, 0, this);
			bufferStrategy.show();
			g.dispose();
		}else {
			System.out.println("null");
		}
	}

} 
