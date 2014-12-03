package map.concept;

import java.util.Random;

import map.panels.map.Field;
import map.panels.map.FieldType;

public class GameMap {
	private Field[] fields;
	private int width;
	private int height;
	private int length;

	public GameMap(int width, int height) {
		this.width = width;
		this.height = height;
		this.length = width * height;

		System.out.println("Szerokosc " + width + " wysokosc " + height);

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

	public Field getField(int x, int y) {
		int index = y * width + x;

		if (index < length && index >= 0)
			return fields[index];

		return null;
	}

	public Field[] getNeighbours(Coords coords) {

		int x = coords.getX();
		int y = coords.getY();
		
		Field[] neighbours = new Field[4];

		neighbours[0] = getField(x, y - 1);
		neighbours[1] = getField(x - 1, y);
		neighbours[2] = getField(x, y + 1);
		neighbours[3] = getField(x + 1, y);

		return neighbours;

	}

	private void initializeMap() {
		Random random = new Random();

		for (int i = 0; i < width * height; ++i) {
			Field field = new Field();
			fields[i] = field;

			int modulo = 0;
			if (i != 0) {
				modulo = i / width;
			}

			Coords coords = new Coords(i - width * modulo, modulo);
			field.setCoords(coords);
			int randomNumber = random.nextInt(5);

			if (randomNumber == 0) {
				field.setType(FieldType.MOUNTAIN);
			} else if (randomNumber == 1 || randomNumber == 2) {
				field.setType(FieldType.OCEAN);
			} else {
				field.setType(FieldType.GRASS);
			}

			System.out.println("Pole " + field.getType() + " " + field.getCoords());
		}
	}

}
