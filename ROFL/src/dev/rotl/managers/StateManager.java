package dev.rotl.managers;

import dev.rotl.states.State;

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
