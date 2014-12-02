package sprites;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import javax.imageio.ImageIO;

import map.panels.map.FieldType;

public class SpritePropertiesLoaderHelper {

	private static final int SPRITE_SIZE = 32;

	public Map<TerrainSpritePlacement, BufferedImage> loadProperties() throws IOException {
		Properties properties = new Properties();
		System.out.println("loading");
		InputStream inputStream = getClass().getResourceAsStream("/terrain.properties");
		System.out.println(inputStream);
		properties.load(inputStream);
		inputStream.close();

		Map<TerrainSpritePlacement, BufferedImage> result = new HashMap<TerrainSpritePlacement, BufferedImage>();

		for (Entry<Object, Object> entry : properties.entrySet()) {
			TerrainSpritePlacement key = parseKey((String) entry.getKey());
			BufferedImage value = parseValue((String) entry.getValue());
			result.put(key, value);
		}

		return result;
	}

	/* Private */
	private BufferedImage spriteSheet = ImageIO.read(new File("res/Terrain.bmp"));

	public SpritePropertiesLoaderHelper() throws IOException {
	}

	private TerrainSpritePlacement parseKey(String property) {
		String[] split = property.split("_");

		FieldType fieldType;
		String terrain = split[0];
		if ("OCEAN".equals(terrain)) {
			fieldType = FieldType.OCEAN;
		}

		// TODO pozosta³e typy terenu

		else {
			// B³¹d parsowania
			return null;
		}

		boolean north = false;
		boolean east = false;
		boolean south = false;
		boolean west = false;

		for (String token : Arrays.copyOfRange(split, 1, split.length)) {

			if ("WEST".equals(token)) {
				west = true;
				break;
			}

			if ("NORTH".equals(token))
				north = true;

			if ("EAST".equals(token))
				east = true;

			if ("SOUTH".equals(token))
				south = true;
		}

		return new TerrainSpritePlacement(fieldType, north, east, south, west);
	}

	private BufferedImage parseValue(String value) {

		String[] valueSplit = value.split("_");

		int cols = Integer.valueOf(valueSplit[0]);
		int rows = Integer.valueOf(valueSplit[1]);

		return spriteSheet.getSubimage(cols * SPRITE_SIZE, rows * SPRITE_SIZE, SPRITE_SIZE, SPRITE_SIZE);
	}
}