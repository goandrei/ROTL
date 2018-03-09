package dev.rofl.game;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import dev.rofl.display.Display;
import dev.rofl.managers.StateManager;
import dev.rofl.states.IntroState;
import dev.rofl.states.State;

public class Game implements Runnable{
	
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
		introState = new IntroState();
		stateManager = new StateManager(introState);
	}
	
	public synchronized void start(){
		
		//if the game is running,don't start the thread
		if(running)
			return;
		
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	//stop the thread
	public synchronized void stop(){
		
		//if the thread isn't running,don't stop the thread
		if(!running)
			return;
			
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void update() {
		
		if(stateManager.getActualState() != null)
			stateManager.getActualState().update();
	}
	
	private void render() {
			
		bufferStrategy = display.getCanvas().getBufferStrategy();
		if(bufferStrategy == null){
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		
		g = bufferStrategy.getDrawGraphics();
		g.clearRect(0, 0, screenWidth, screenHeight);
		
		if(stateManager.getActualState() != null)
			stateManager.getActualState().render(g);
		
		bufferStrategy.show();
		g.dispose();
	}
	
	@Override
	public void run() {

		int fps = 60;
		long last = System.nanoTime();
		double frameTime = 1000000000 / fps;
		double delta = 0;
		double time = 0;
		int ticks = 0;
		
		while(running){
			
			long now = System.nanoTime();
			delta += (now - last) / frameTime;
			time += now - last;
			last = now;
			
			//if delta >= 1,then the frame exceeded the frameTime and we can update/render another frame
			if(delta >= 1){
				update();
				render();
				--delta;
				++ticks;
			}
			
			//if time >= 1s,then we reset the values
			if(time >= 1000000000){
				System.out.println("FPS: " + ticks);
				ticks = 0;
				time = 0;
			}
		}
		
		stop();
	}
}
