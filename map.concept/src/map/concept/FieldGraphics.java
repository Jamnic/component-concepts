package map.concept;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import map.panels.map.Field;
import map.panels.map.FieldType;
import sprites.SpriteContainer;
import sprites.SpriteType;

public class FieldGraphics {

	private SpriteType spriteType = new SpriteType();

	public void draw(Graphics g, Field field, Field[] neighbours, int columnOffset, int rowOffset) {
		if (field != null)
			g.drawImage(determineImage(field, neighbours), (field.getCoords().getX() - columnOffset) * 32, (field
					.getCoords().getY() - rowOffset) * 32, null);
	}

	private BufferedImage determineImage(Field field, Field[] neighbours) {
		FieldType type = field.getType();
		switch (type) {
		case GRASS:
			return SpriteContainer.getGrassSprite();
		case MOUNTAIN:
			return SpriteContainer.getMountainSprite();
		case OCEAN:
			return spriteType.resolveTerrain(field, neighbours);
		default:
			return null;
		}
	}
}
