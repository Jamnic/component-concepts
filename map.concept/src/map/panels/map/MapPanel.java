package map.panels.map;

import java.awt.Graphics;

import map.concept.Component;
import map.concept.Coords;
import map.concept.FieldGraphics;
import map.concept.GameMap;

/**
 * Draws map on the screen.
 * 
 * Responsible for drawing the current situation of map on screen.
 */
public class MapPanel extends Component {

	private static final int SPRITE_SIZE = 32;

	/* ========== PUBLIC ========== */

	/**
	 * Creates {@link MapPanel} component with specified dimensions.
	 * 
	 * @param x - sets the x position on the owning window in pixels.
	 * @param y - sets the y position on the owning window in pixels.
	 * @param width - sets the width of the panel.
	 * @param height - sets the height of the panel.
	 */
	public MapPanel(int x, int y, int width, int height) {
		super(x, y, width, height);

		visibleColumns = 24; //width / SPRITE_SIZE;
		visibleRows = 14; //height / SPRITE_SIZE;
		
		currentColumn = 0;
		currentRow = 0;
		
		createComponents();
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

			for (int i = currentColumn; i < visibleColumns + currentColumn; ++i)
				for (int j = currentRow; j < visibleRows + currentRow; ++j)
					paintField(g, gameMapComponent.getField(i, j), fields);
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
	
	public void toLeft() {
		if (currentColumn > 0) {
			--currentColumn;
			repaint();
		}
	}

	public void toRight() {
		if (gameMapComponent.getWidth() - visibleColumns > currentColumn) {
			++currentColumn;
			repaint();
		}
	}

	public void toUp() {
		if (currentRow > 0) {
			--currentRow;
			repaint();
		}
	}

	public void toDown() {
		if (gameMapComponent.getHeight() - visibleRows > currentRow) {
			++currentRow;
			repaint();
		}
	}

	/* ========== PRIVATE ========== */

	private FieldGraphics fieldGraphicsComponent;
	private GameMap gameMapComponent;
	
	private final int visibleColumns;
	private final int visibleRows;
	
	private int currentColumn;
	private int currentRow;

	/**
	 * Creates components of {@link MapPanel}.
	 */
	private void createComponents() {
		fieldGraphicsComponent = new FieldGraphics();
		gameMapComponent = new GameMap(64, 32);
	}
	
	/**
	 * Draws {@link Field} with specified index. Gets neighbours of {@link Field}s to determine the Sprite of processed
	 * {@link Field}, and draws it using {@link FieldGraphics} component.
	 * 
	 * @param g - {@link Graphics} context.
	 * @param field - current {@link Field}.
	 * @param fields - array of {@link Field} objects, representing map.
	 */
	private void paintField(Graphics g, Field field, Field[] fields) {
		Field[] neighbours = gameMapComponent.getNeighbours(field.getCoords());

		fieldGraphicsComponent.draw(g, field, neighbours, currentColumn, currentRow);
	}

}