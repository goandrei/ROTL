package rotl.game;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import rotl.display.Display;
import rotl.managers.StateManager;
import rotl.states.IntroState;
import rotl.states.State;

public class Game implements Runnable {

	private String title;
	private int screenHeight;
	private int screenWidth;

	private boolean running = false;

	private Thread thread;

	private StateManager stateManager;

	private State introState;

	private Display display;

	private BufferStrategy bufferStrategy;

	private Graphics g;

	public Game(String title){
		
		
		
		this.title = title;
		display = new Display(title);
		introState = new IntroState(display, g, bufferStrategy);
		stateManager = new StateManager(introState);
		
		bufferStrategy = display.getCanvas().getBufferStrategy();
		if (bufferStrategy == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		
		this.screenHeight = display.getHeight();
		this.screenWidth  = display.getWidth();
		
		g = bufferStrategy.getDrawGraphics();
		g.clearRect(0, 0, screenWidth, screenHeight);
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
	public synchronized void stop() {

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
		g.clearRect(0, 0, screenWidth, screenHeight);

		if (stateManager.getActualState() != null)
			stateManager.getActualState().render(g);

		bufferStrategy.show();
		g.dispose();
	}

	@Override
	public void run() {
		
		introState.render(g);
		//here we set the state to gameState
	
		/*
		int fps = 60;
		long last = System.nanoTime();
		double frameTime = 1000000000 / fps;
		double delta = 0;
		double time = 0;
		int ticks = 0;

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
				++ticks;
			}

			// if time >= 1s,then we reset the values
			if (time >= 1000000000) {
				// System.out.println("FPS: " + ticks);
				ticks = 0;
				time = 0;
			}
		}

		stop();
		*/
	}
}