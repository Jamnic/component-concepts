package map.panels.map;

import java.awt.Graphics;

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

			for (Field field : fields) {
				if (++counter == 15) {
					cursorX += FIELD_WIDTH;
					cursorY = 0;
					counter = 0;
				}

				fieldGraphics.draw(g, field, cursorX, cursorY);

				cursorY += FIELD_HEIGHT;
			}
		}

	}

	/* Private */
	private Field[] fields;

}
