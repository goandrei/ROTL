package rotl.gfx;

import java.awt.image.BufferedImage;

public class Animation {
	
	private BufferedImage[] frames;
	private Integer[] delays;
	private int index = 0;
	private long last;
	private long now;
	private boolean started = false;
	
	public Animation(BufferedImage[] frames, Integer[] delays) {
		this.frames = frames;
		this.delays = delays;
	}
	
	public void start() {
		started = true;
		last = System.currentTimeMillis() * 10;
	}
	
	public boolean hasStarted() {
		return started;
	}
	
	public BufferedImage getFrame() {

		now = System.currentTimeMillis() * 10;
		if(now - last >= delays[Math.min(index, 18)]) {
			++index;
			last = now;
		}else {
			last = now;
		}
		return frames[Math.min(index, 18)];
	}

}
