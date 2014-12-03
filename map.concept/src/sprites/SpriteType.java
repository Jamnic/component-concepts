package sprites;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

import map.panels.map.Field;
import map.panels.map.FieldType;

public class SpriteType {

	private Map<TerrainSpritePlacement, BufferedImage> images;

	public SpriteType() {

		try {
			SpritePropertiesLoaderHelper helper = new SpritePropertiesLoaderHelper();
			images = helper.loadProperties();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public BufferedImage resolveTerrain(Field field, Field[] neighbours) {

		field.getType();

		boolean north = false;
		boolean east = false;
		boolean south = false;
		boolean west = false;

		FieldType type = field.getType();
		if (neighbours[0] != null && !type.equals(neighbours[0].getType()))
			north = true;

		if (neighbours[1] != null && !type.equals(neighbours[1].getType()))
			west = true;

		if (neighbours[2] != null && !type.equals(neighbours[2].getType()))
			south = true;

		if (neighbours[3] != null && !type.equals(neighbours[3].getType()))
			east = true;

		return images.get(new TerrainSpritePlacement(type, north, east, south, west));
	}
}