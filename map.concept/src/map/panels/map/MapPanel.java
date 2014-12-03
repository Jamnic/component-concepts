package map.panels.map;

import java.awt.Graphics;

import map.concept.Component;
import map.concept.Coords;
import map.concept.FieldGraphics;
import map.concept.GameMap;

/**
 * Draws map on the screen.
 */
public class MapPanel extends Component {

	private static final int SPRITE_SIZE = 32;

	/* ========== PUBLIC ========== */

	/**
	 * Creates MapPanel component with specified dimensions.
	 * 
	 * @param x - sets the x position on the owning window in pixels.
	 * @param y - sets the y position on the owning window in pixels.
	 * @param width - sets the width of the panel.
	 * @param height - sets the height of the panel.
	 */
	public MapPanel(int x, int y, int width, int height) {
		super(x, y, width, height);

		int rows = height / SPRITE_SIZE;
		int columns = width / SPRITE_SIZE;

		createHelpers(columns, rows);
	}

	/**
	 * Paints the map on the screen using given Graphics if <code>isVisible</code> is set to true.
	 * <br>
	 * Gets array of {@link Field} from {@link GameMap} component, and iterates over it, printing its content.
	 */
	@Override
	public void paint(Graphics g) {

		if (isVisible) {
			Field[] fields = gameMapComponent.getFields();

			for (int i = 0; i < fields.length; ++i)
				paintField(g, fields, i);
		}

	}

	/**
	 * Prints out the information about specified {@link Field}.
	 * 
	 * @param x - position in X axis in pixels of requested {@link Field}.
	 * @param y - position in Y axis in pixels of requested {@link Field}.
	 * @return 
	 */
	public Field getField(int x, int y) {
		int column = x / SPRITE_SIZE;
		int row = y / SPRITE_SIZE;

		return getField(new Coords(column, row));
	}

	/**
	 * Prints out the information about specified {@link Field}.
	 * 
	 * @param coords - {@link Coords} pointing requested {@link Field}.
	 */
	public Field getField(Coords coords) {
		int column = coords.getX();
		int row = coords.getY();

		return gameMapComponent.getField(column, row);
	}

	/* ========== PRIVATE ========== */

	private FieldGraphics fieldGraphicsComponent;
	private GameMap gameMapComponent;

	/**
	 * Creates helpers, which are components of MapPanel.
	 * 
	 * @param columns- number of columns of the map.
	 * @param rows - number of rows of the map.
	 */
	private void createHelpers(int columns, int rows) {
		fieldGraphicsComponent = new FieldGraphics();
		gameMapComponent = new GameMap(columns, rows);
	}
	
	/**
	 * Draws {@link Field} with specified index. Gets neighbours of {@link Field}s to determine the Sprite of processed
	 * {@link Field}, and draws it using {@link FieldGraphics} component.
	 * 
	 * @param g - {@link Graphics} context.
	 * @param fields - array of {@link Field} objects, representing map.
	 * @param currentIndex - current index of iterated array.
	 */
	private void paintField(Graphics g, Field[] fields, int currentIndex) {
		Field field = fields[currentIndex];

		Field[] neighbours = gameMapComponent.getNeighbours(field.getCoords());

		fieldGraphicsComponent.draw(g, field, neighbours);
	}
}