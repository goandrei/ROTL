package dev.rofl.managers;

import dev.rofl.states.State;

public class StateManager {
	
	private State actualState;
	
	public StateManager(State actualState) {	
		this.actualState = actualState;
	}
	
	public State getActualState() {
		return actualState;
	}
	
	public void setActualState(State actualState) {
		this.actualState = actualState;
	}
}
