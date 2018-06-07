package rotl.game;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import rotl.display.Display;
import rotl.gfx.Assets;
import rotl.gfx.GameCamera;
import rotl.managers.StateManager;
import rotl.states.IntroState;
import rotl.states.State;
import rotl.utilities.Handler;

public class Game implements Runnable {
	
	private int screenHeight;
	private int screenWidth;

	private boolean running = false;

	private Thread thread;

	private Handler handler;

	private StateManager stateManager;

	private State introState;

	private Display display;
	
	private BufferStrategy bufferStrategy;
	
	private Graphics g;
	
	private GameCamera gameCamera;
	
	public Game(String title) {

		gameCamera = new GameCamera(0, 0);

		Assets.getInstance();

		display = new Display(title);

		stateManager = new StateManager();

		handler = new Handler(stateManager, this);

		screenHeight = display.getHeight();
		screenWidth = display.getWidth();

		introState = new IntroState(handler);
		stateManager.setActualState(introState);
	}

	public synchronized void start() {

		// if the game is running,don't start the thread
		if (running)
			return;

		thread = new Thread(this);
		thread.start();
		running = true;
	}

	// stop the thread
	private synchronized void stop() {

		// if the thread isn't running,don't stop the thread
		if (!running)
			return;

		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void update() {
		
		if (stateManager.getActualState() != null)
			stateManager.getActualState().update();
	}

	private void render() {
		
		bufferStrategy = display.getCanvas().getBufferStrategy();
		if (bufferStrategy == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}

		g = bufferStrategy.getDrawGraphics();
		if (stateManager.getActualState() != null)
			stateManager.getActualState().render(g);

		bufferStrategy.show();
		g.dispose();
	}

	@Override
	public void run() {

		final int fps = 60;
		long last = System.nanoTime();
		final double frameTime = 1000000000 / fps;
		double delta = 0;
		double time = 0;

		while (running) {
			
			long now = System.nanoTime();
			delta += (now - last) / frameTime;
			time += now - last;
			last = now;

			// if delta >= 1,then the frame exceeded the frameTime and we can update/render
			// another frame
			if (delta >= 1) {
				
				update();
				render();
				--delta;
			}

			// if time >= 1s,then we reset the values
			if (time >= 1000000000) {
				time = 0;
			}
		}

		stop();
	}

	public Display getDisplay() {
		return display;
	}

	public int getWidth() {
		return screenWidth;
	}

	public int getHeight() {
		return screenHeight;
	}

	public GameCamera getGameCamera() {
		return gameCamera;
	}
}