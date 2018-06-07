package rotl.states;

import java.awt.Graphics;
import java.awt.Toolkit;
import rotl.gfx.Animation;
import rotl.gfx.Assets;
import rotl.gfx.Sound;
import rotl.utilities.Handler;

public class IntroState extends State {

	private int screenWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	private int screenHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	private Integer[] delays = { 0, 500, 500, 500, 100, 50, 100, 100, 100, 100, 50, 50, 50, 50, 50, 50, 50, 50, 50,
			50 };

	private Animation animation;

	public IntroState(Handler handler) {
		super(handler);

		animation = new Animation(Assets.getInstance().getIntroFrames(), delays);
	}

	@Override
	public void update() {

	}

	@Override
	public void render(Graphics g) {

		if (!animation.hasStarted()) {
			animation.start();
		}

		if (animation.getFrame() == null) {
			
			State menuState = new MenuState(handler, null);
			handler.getStateManager().setActualState(menuState);
		}

		g.drawImage(animation.getFrame(), 0, 0, screenWidth, screenHeight, null);
	}


}