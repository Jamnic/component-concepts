package map.panels.message;

import java.awt.Graphics;

import map.concept.Component;
import map.concept.TextField;
import map.panels.map.ChatPanel;
import text.FontUtility;

/**
 * Draws inter-player quick chat on the screen.
 * 
 * It is composed with two elements - {@link TextField} in which player can type short messages, and {@link ChatPanel}
 * where messages are displayed.
 */
public class MessagePanel extends Component {

	private static final int TEXT_GAP = 15;

	/* ========== PUBLIC ========== */

	/**
	 * Creates {@link MessagePanel} and sets it into given place on the game screen. Creates its components.
	 * 
	 * @param drawPanel - parent of this panel, used to redraw panel and its components.
	 * @param x - sets the x position on the owning window in pixels.
	 * @param y - sets the y position on the owning window in pixels.
	 * @param width - sets the width of the panel.
	 * @param height - sets the height of the panel.
	 */
	public MessagePanel(int x, int y, int width, int height) {
		super(x, y, width, height);
		
		createComponents();
	}

	/**
	 * Sends message from {@link TextField} to the {@link ChatPanel}, and calls repaints of these components.
	 */
	public void sendMessage() {

		chatPanelComponent.sendMessage(textFieldComponent.getText());
		textFieldComponent.reset();

		chatPanelComponent.repaint();
		textFieldComponent.repaint();
	}

	/**
	 * Paints the panel on the screen using given Graphics if <code>isVisible</code> is set to true. <br>
	 * Creates {@link Message} objects from awaiting String texts from {@link TextField}, and prints the
	 * {@link ChatPanel}.
	 */
	public void paint(Graphics g) {
		
		if (isVisible) {
			chatPanelComponent.paint(g);
			textFieldComponent.paint(g);
		}
	}

	/**
	 * If it is possible, deletes the youngest letter in {@link TextField}.
	 */
	public void deleteLetterInTextField() {
		if (textFieldComponent.deleteLetter())
			textFieldComponent.repaint();
	}

	/**
	 * If it is possible, puts given letter in {@link TextField}.
	 */
	public void putLetterInTextField(char keyChar) {
		if (textFieldComponent.putLetter(keyChar))
			textFieldComponent.repaint();
	}

	/* ========== PRIVATE ========== */

	private TextField textFieldComponent;
	private ChatPanel chatPanelComponent;

	/**
	 * Creates components of {@link MessagePanel}.
	 */
	private void createComponents() {
		// TODO moze verdana powinna miec stala TEXT_GAP
		textFieldComponent = new TextField(x, y + height, width, TEXT_GAP, FontUtility.getInstance().getVerdanaFont(),
				255);
		chatPanelComponent = new ChatPanel(x, y, width, height);
	}
}