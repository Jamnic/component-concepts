package map.concept;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import sprites.SpriteContainer;
import map.panels.map.Field;
import map.panels.map.FieldType;

public class FieldGraphics {

	public void draw(Graphics g, Field field, int cursorX, int cursorY) {
		g.drawImage(determineImage(field.getType()), cursorX, cursorY, null);

	}

	private BufferedImage determineImage(FieldType type) {
		switch (type) {
		case GRASS:
			return SpriteContainer.getGrassSprite();
		case MOUNTAIN:
			return SpriteContainer.getMountainSprite();
		case OCEAN:
			return SpriteContainer.getOceanSprite();
		default:
			return SpriteContainer.getGrassSprite();
		}
	}

}
