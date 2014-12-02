package map.panels.map;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import map.concept.Component;
import map.concept.FieldGraphics;
import map.concept.GameMap;

public class MapPanel extends Component {

	private static final int FIELD_WIDTH = 32;
	private static final int FIELD_HEIGHT = 32;
	private FieldGraphics fieldGraphics;

	/* Public */
	public MapPanel(int x, int y, int width, int height) {
		super(x, y, width, height);

		int rows = height / FIELD_HEIGHT;
		int cols = width / FIELD_WIDTH;

		fieldGraphics = new FieldGraphics();

		GameMap gameMap = new GameMap(cols, rows);
		fields = gameMap.getFields();
	}

	public void paint(Graphics g) {

		if (isVisible) {
			int cursorX = 0;
			int cursorY = 0;
			int counter = 0;

			for (int i = 0; i < fields.length; ++i) {
				Field field = fields[i];

				if (++counter == 15) {
					cursorX += FIELD_WIDTH;
					cursorY = 0;
					counter = 0;
				}

				Field[] neighbours = new Field[4];

				int fieldWidth = width / FIELD_WIDTH;
				int fieldHeight = height / FIELD_HEIGHT;

				int firstNeighbourIndex = i - fieldWidth;
				if (firstNeighbourIndex >= 0 && firstNeighbourIndex < fieldWidth * fieldHeight)
					neighbours[0] = fields[firstNeighbourIndex];

				int secondNeighbourIndex = i - 1;
				if (secondNeighbourIndex >= 0 && secondNeighbourIndex < fieldWidth * fieldHeight)
					neighbours[1] = fields[secondNeighbourIndex];

				int thirdNeighbourIndex = i + 1;
				if (thirdNeighbourIndex >= 0 && thirdNeighbourIndex < fieldWidth * fieldHeight)
					neighbours[2] = fields[thirdNeighbourIndex];

				int fourthNeighbourIndex = i + fieldWidth;
				if (fourthNeighbourIndex >= 0 && fourthNeighbourIndex < fieldWidth * fieldHeight)
					neighbours[3] = fields[fourthNeighbourIndex];

				if (field != null) {

					System.out.print("Field " + field.getType() + " " + field.getCoords() + " ma s¹siadów: ");
					for (Field neig : neighbours) {
						if (neig == null)
							System.out.print("(-, -)");
						else
							System.out.print("[" + neig.getType() + " " + neig.getCoords() + "]");
					}
					System.out.println();
				}

				fieldGraphics.draw(g, field, neighbours, cursorX, cursorY);

				cursorY += FIELD_HEIGHT;
			}
		}

	}

	/* Private */
	private Field[] fields;

}
