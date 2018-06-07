package rotl.utilities;

import rotl.game.Game;
import rotl.managers.StateManager;

public class Handler {

	private final StateManager stateManager;
	private final Game game;

	public Handler(StateManager stateManager, Game game) {
		this.stateManager = stateManager;
		this.game = game;
	}

	public StateManager getStateManager() {
		return stateManager;
	}

	public Game getGame() {
		return game;
	}
}