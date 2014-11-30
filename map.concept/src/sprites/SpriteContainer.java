package sprites;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteContainer {

	public SpriteContainer() throws IOException {
		spriteSheet = ImageIO.read(new File("res/Terrain.bmp"));

		grassSprite = spriteSheet.getSubimage(0 * 32, 0 * 32, 32, 32);
		mountainSprite = spriteSheet.getSubimage(18 * 32, 4 * 32, 32, 32);
		oceanSprite = spriteSheet.getSubimage(2 * 32, 0 * 32, 32, 32);
	}

	private BufferedImage spriteSheet;

	private static BufferedImage grassSprite;
	private static BufferedImage mountainSprite;
	private static BufferedImage oceanSprite;

	public static BufferedImage getGrassSprite() {
		return grassSprite;
	}
	
	public static BufferedImage getOceanSprite() {
		return oceanSprite;
	}
	
	public static BufferedImage getMountainSprite() {
		return mountainSprite;
	}

}
