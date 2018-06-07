package rotl.gfx;

import java.awt.image.BufferedImage;

final class SpriteSheet {

	private final BufferedImage sheet;

	SpriteSheet(BufferedImage sheet) {
		this.sheet = sheet;
	}

	BufferedImage crop(int x, int y, int width, int height) {
		return sheet.getSubimage(x, y, width, height);
	}
}
