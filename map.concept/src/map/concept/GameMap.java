package map.concept;

import java.util.Random;

import map.panels.map.Field;
import map.panels.map.FieldType;

public class GameMap {

	private Field[] fields;
	private int width;
	private int height;

	public GameMap(int width, int height) {
		this.width = width;
		this.height = height;

		fields = new Field[width * height];

		initializeMap();
	}

	public Field[] getFields() {
		return fields;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	private void initializeMap() {
		Random random = new Random();

		for (int i = 1; i < width * height; ++i) {
			Field field = new Field();
			fields[i] = field;
			int modulo = width % i;

			System.out.println(modulo + " " + (i - modulo));
			field.setCoords(new Coords(modulo, i - modulo));
			int randomNumber = random.nextInt(5);

			if (randomNumber == 0) {
				fields[i].setType(FieldType.MOUNTAIN);
			} else if (randomNumber == 1 || randomNumber == 2) {
				fields[i].setType(FieldType.OCEAN);
			} else {
				fields[i].setType(FieldType.GRASS);
			}
		}
	}

}
