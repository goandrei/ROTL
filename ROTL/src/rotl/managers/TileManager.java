package rotl.managers;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import rotl.gfx.Assets;

public class TileManager {
	
	private Assets assets;
	
	public TileManager() {
		
		assets = new Assets();
		assets.init();
	}
	
	public void render(Graphics g, int x, int y, int index) {
		g.drawImage(assets.outsideTiles[index], x, y, 64, 32, null);
	}
}
