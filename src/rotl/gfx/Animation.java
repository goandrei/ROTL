package rotl.gfx;

import java.awt.image.BufferedImage;

public final class Animation {

	private final BufferedImage[] frames;
	private final Integer[] delays;
	private int index = 1;
	private final int limit;
	private long last;
	private long now;
	private boolean started = false;

	private Animable animator = null;
	
	public Animation(BufferedImage[] frames, Integer[] delays) {
		
		this.frames = frames;
		this.delays = delays;
		this.limit = delays.length;
	}
	
	public Animation(BufferedImage[] frames, Integer[] delays, Animable animator) {
		
		this.frames = frames;
		this.delays = delays;
		this.limit = delays.length;
		this.animator = animator;
	}

	public void start() {
		
		started = true;
		last = System.currentTimeMillis();
	}

	public boolean hasStarted() {
		return started;
	}
	
	public void stop() {
		index = 0;
		started = false;
	}

	public BufferedImage getFrame() {

		now = System.currentTimeMillis();
			
		if ((now - last) >= delays[index]) {
			++index;
			last = now;
			if(animator != null) {
				animator.updatePosition();
			}
		}

		if (index == limit) {
			return null;
		}

		return frames[index];
	}
	
	public BufferedImage getPeriodFrame() {

		now = System.currentTimeMillis();
			
		if ((now - last) >= delays[index]) {
			index = (index + 1) % 3;
			last = now;
			if(animator != null) {
				animator.updatePosition();
			}
		}

		return frames[index];
	}
}
