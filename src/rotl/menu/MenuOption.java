package rotl.menu;

import java.awt.Graphics;

interface MenuOption {

	int fontSize = 25;
	int titleFontSize = 100;

	void init();
	void paintComponent(Graphics g);
}
