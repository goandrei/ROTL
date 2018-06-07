package rotl.gfx;

import java.awt.image.BufferedImage;
import rotl.utilities.ImageLoader;

public final class Assets {

	private static BufferedImage[] outsideTiles;
	private static BufferedImage[] buildingTiles;
	private static BufferedImage[] introFrames;
	
	private static BufferedImage[] soldiersFrames;
	
	private static final int TILE_WIDTH = 64;
	private static final int TILE_HEIGHT = 32;
	
	private static Assets instance = null;
	
	private Assets() {

		// load the outside tiles
		int lines = 32;
		int columns = 10;

		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/iso-64x64-outside.png"));
		outsideTiles = new BufferedImage[lines * columns + 1];

		for (int i = 0; i < lines; ++i) {
			for (int j = 0; j < columns; ++j) {
				outsideTiles[i * columns + j + 1] = sheet.crop(j * TILE_WIDTH, i * TILE_HEIGHT, TILE_WIDTH,
						TILE_HEIGHT);
			}
		}

		lines = 19;

		introFrames = new BufferedImage[lines + 1];

		for (int i = 1; i <= lines; ++i) {
			introFrames[i - 1] = ImageLoader.loadImage("/images/frame" + i + ".png");
		}

		lines = 57;
		columns = 16;

		buildingTiles = new BufferedImage[lines * columns + 1];
		sheet = new SpriteSheet(ImageLoader.loadImage("/textures/buildings.png"));

		// offset adaugat - TODO

		for (int i = 0; i < lines; ++i) {
			for (int j = 0; j < columns; ++j) {
				buildingTiles[i * columns + j + 1] = sheet.crop(j * TILE_WIDTH, i * TILE_HEIGHT, TILE_WIDTH,
						TILE_HEIGHT);
			}
		}
		
		lines = 12;
		columns = 3;
		
		soldiersFrames = new BufferedImage[lines * columns + 1];
		sheet = new SpriteSheet(ImageLoader.loadImage("/textures/soldiers.png"));
			
		for(int i = 0;i < lines; ++i) {
			for(int j = 0;j < columns; ++j) {
				soldiersFrames[i * columns + j + 1] = sheet.crop(j * TILE_HEIGHT, i * TILE_WIDTH, TILE_HEIGHT, 
						TILE_WIDTH);
			}
		}
		
	}
	
	public static Assets getInstance() {
		
		if (instance == null)
			instance = new Assets();
		
		return instance;
	}
	
	public BufferedImage[] getOutsideTiles() {
		return Assets.outsideTiles;
	}
	
	public BufferedImage[] getBuildingTiles() {
		return Assets.buildingTiles;
	}
	
	public BufferedImage[] getIntroFrames() {
		return Assets.introFrames;
	}
	
	public BufferedImage[] getSoldierFrames() {
		return Assets.soldiersFrames;
	}
}
