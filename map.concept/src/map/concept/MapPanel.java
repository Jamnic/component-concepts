package map.concept;

import java.awt.Color;
import java.awt.Graphics;

public class MapPanel extends Component {

	public MapPanel(int x, int y, int width, int height) {
		super(x, y, width, height);

		initializeFields();
	}

	public void paint(Graphics g) {

		int drawX = 0;
		int drawY = 0;
		int drawWidth = 25;
		int drawHeight = 25;

		int counter = 0;
		for (Field field : fields) {
			if (counter++ == 19) {
				drawX++;
				drawY = 0;
				counter = 0;
			}
			

			g.setColor(determineColor(field.getType()));
			g.fillRect(x + (drawX * drawWidth), y + (drawY * drawHeight), drawWidth, drawHeight);

			g.setColor(Color.BLACK);
			g.drawRect(x + (drawX * drawWidth), y + (drawY * drawHeight), drawWidth, drawHeight);
			
			drawY++;
		}

	}

	private Color determineColor(FieldType type) {

		switch (type) {
		case GRASS:
			return new Color(200, 255, 200);
		case MOUNTAIN:
			return new Color(255, 200, 230);
		case OCEAN:
			return new Color(200, 200, 255);
		default:
			return Color.BLACK;
		}
	}

	/* Private */
	private Field[] fields = new Field[400];

	private void initializeFields() {

		for (int i = 0; i < 400; ++i) {
			fields[i] = new Field();
			if (i % 10 == 0) {
				fields[i].setType(FieldType.MOUNTAIN);
			} else if (i % 3 == 0) {
				fields[i].setType(FieldType.OCEAN);
			} else {
				fields[i].setType(FieldType.GRASS);
			}
		}
		
		System.out.println("Initialized");

	}

}
