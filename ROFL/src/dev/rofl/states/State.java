package dev.rofl.states;

import java.awt.Graphics;

public abstract class State {
	
	public abstract void update();
	
	public abstract void render(Graphics g);
}
