package rotl.states;

import java.awt.Graphics;

import rotl.menu.Menu;
import rotl.utilities.Handler;

public class MenuState extends State{
	
	public MenuState(Handler handler) {
		super(handler);
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		Menu menu = Menu.getInstance(handler);
	}

}
