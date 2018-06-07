package rotl.buttons;

import java.awt.Graphics;

interface Button {

	public void render(Graphics g);
	public void update();
	public void buildListenerRectangle();
}
