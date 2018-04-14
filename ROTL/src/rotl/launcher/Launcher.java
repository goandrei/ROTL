package rotl.launcher;

import java.awt.Color;
import java.awt.Graphics;

import rotl.display.Display;
import rotl.game.Game;

public class Launcher {

	public static void main(String args[]) {
		Game game = new Game("ROTL");
		game.start();

	}
}
