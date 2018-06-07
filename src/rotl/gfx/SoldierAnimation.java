package rotl.gfx;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

public final class SoldierAnimation implements Animable {

	private final Animation defenderAnimation, fighterAnimation, warriorAnimation;
	private Animation actualAnimation = null;
	private Integer[] delays = {200, 200, 200};
	
	private int soldierInitialX = 52, soldierInitialY = 144;
	//private int soldierFinalX = 41, soldierFinalY = 144;
	
	private int soldierActualX, soldierActualY;
	
	private float offset = 0;
	
	private boolean running;
	
	private final Random rand = new Random();
	
	public SoldierAnimation() {
		
		Integer[] indexes = {10, 11, 12};
		defenderAnimation = new Animation(getSoldierFrames(indexes), delays, this);
		
		Integer[] indexes1 = {22, 23, 24};
		fighterAnimation = new Animation(getSoldierFrames(indexes1), delays, this);
		
		Integer[] indexes2 = {34, 35, 36};
		warriorAnimation = new Animation(getSoldierFrames(indexes2), delays, this);
		
		init();
	}
	
	private BufferedImage[] getSoldierFrames(Integer[] removeIndexes) {
		
		final BufferedImage[] soldiersFrames = Assets.getInstance().getSoldierFrames();
		final BufferedImage[] aux = new BufferedImage[3];
	
		for(int j = 0;j < 3; ++j) {
			aux[j] = soldiersFrames[removeIndexes[j]];
		}

		return aux;
	}
	
	private void init() {
		
		soldierActualX = soldierInitialX;
		soldierActualY = soldierInitialY;
		running = true;
	}
	
	private void stopAnimation() {
		
		running = false;
		offset = 0;
		actualAnimation.stop();
	}
	
	public void render(Graphics g, int startHeight, int finalHeight, int startWidth, int finalWidth) {
		
		if(offset / 32 > 21) {
			
			stopAnimation();
			return;
		}
		
		if ((actualAnimation != null && !actualAnimation.hasStarted()) || (actualAnimation == null)) {
			
			final int generator = rand.nextInt(3);
			
			switch(generator) {
				case 0:
					actualAnimation = defenderAnimation;
					break;
				case 1:
					actualAnimation = fighterAnimation;
					break;
				case 2:
					actualAnimation = warriorAnimation;
					break;
			}
			
			init();
			actualAnimation.start();
		}

		final BufferedImage frame = actualAnimation.getPeriodFrame();

		if (frame != null) {
			
			if (isBetween(startHeight, finalHeight, soldierActualY) && isBetween(startWidth, finalWidth, soldierActualX)) {
				offset += 1.5;
				g.drawImage(actualAnimation.getPeriodFrame(), (soldierActualX - startWidth) * 64 - (int)(offset), (soldierActualY - startHeight - 3) * 16, null);
			}
		}else {
			stopAnimation();
		}
	}
	
	public void updatePosition() {
		//soldierActualX -= 0.0000001;
	}
	
	private boolean isBetween(int left, int right, int middle) {
		return ((left <= middle) && (middle <= right));
	}
	
	public boolean isRunning() {
		return running;
	}
}
