package rotl.managers;

import java.awt.Graphics;
import rotl.gfx.Assets;

public final class TileManager {

	private static TileManager instance = null;
	private TileManager() {}
	
	public static TileManager getInstance() {
		
		if (instance == null)
			instance = new TileManager();
		
		return instance;
	}
	
	public void render(Graphics g, int x, int y, int index, int layer) {

		switch (layer) {
		case 0:
			g.drawImage(Assets.getInstance().getOutsideTiles()[index], x, y, 64, 32, null);
			break;
		case 1:
			g.drawImage(Assets.getInstance().getOutsideTiles()[index], x, y, 64, 32, null);
			break;
		case 2:
			g.drawImage(Assets.getInstance().getBuildingTiles()[Math.max(0, index - 320)], x, y, 64, 32, null);
			break;
		case 3:
			g.drawImage(Assets.getInstance().getSoldierFrames()[Math.max(0, index - 1232)], x, y, 32, 64, null);
		}
	}
}
