package rotl.utilities;

import rotl.game.Game;
import rotl.gfx.GameCamera;
import rotl.managers.MouseManager;
import rotl.managers.StateManager;

public class Handler {

	private StateManager stateManager;
	private Game game;

	public Handler(StateManager stateManager, Game game) {
		this.stateManager = stateManager;
		this.game = game;
	}
<<<<<<< HEAD
	
	public GameCamera getGameCamera() {
		return game.getGameCamera();
	}
	
	public int getWidth() {
		return game.getWidth();
	}
	
	public int getHeight() {
		return game.getHeight();
	}
	
	
=======

>>>>>>> refs/remotes/origin/master
	public StateManager getStateManager() {
		return stateManager;
	}
<<<<<<< HEAD
	
	public MouseManager getMouseManager() {
		return game.getMouseManager();
	}
	
=======

>>>>>>> refs/remotes/origin/master
	public Game getGame() {
		return game;
	}
}
