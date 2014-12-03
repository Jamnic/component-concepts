package map.panels.draw;

import java.awt.Color;
import java.awt.Graphics;

import text.FontLoader;
import map.concept.Component;
import map.panels.map.Field;

public class FieldDetailsPopup extends Component {

	/**
	 * Creates {@link FieldDetailsPopup} component with specified dimensions. Instantly, visibility is set to false,
	 * using <code>hidden()</code> method.
	 * 
	 * @param x
	 *            - sets the x position on the owning window in pixels.
	 * @param y
	 *            - sets the y position on the owning window in pixels.
	 * @param width
	 *            - sets the width of the panel.
	 * @param height
	 *            - sets the height of the panel.
	 */
	public FieldDetailsPopup(int x, int y, int width, int height) {
		super(x, y, width, height);

		hide();
	}

	@Override
	public void paint(Graphics g) {

		if (isVisible) {

			if (displayedField != null) {
				System.out.println(displayedField);

				g.setColor(Color.LIGHT_GRAY);
				g.fillRect(0, 0, width, height);

				g.setColor(Color.BLACK);
				g.drawRect(0, 0, width, height);
				
				g.drawString("Coordinates: ", 10 + x, 10 + y);
				g.drawString("Terrain type: ", 10 + x, 20 + y);

				g.drawString(displayedField.getType() + " " + displayedField.getCoords(), 10 + x, 30 + y);

			}
		}

	}

	public void displayField(Field displayedField) {
		this.displayedField = displayedField;
	}

	/* Private */
	private Field displayedField;
}
