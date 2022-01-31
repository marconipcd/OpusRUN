package util.boletos.transform;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

class BufferedImageGeneratorUtil {

	static BufferedImage generateBufferedImageFor(Image image, int type) {
		int w = image.getWidth(null);
		int h = image.getHeight(null);
		BufferedImage result = new BufferedImage(w, h, type);
		Graphics2D g = result.createGraphics();
		g.drawImage(image, 0, 0, null);
		g.dispose();
		return result;
	}
}
