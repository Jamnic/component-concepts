package map.panels.map;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import map.concept.Component;
import map.concept.Tickable;
import map.concept.Timer;
import map.panels.message.Message;
import text.StringParter;

public class ChatPanel extends Component implements Tickable {

	private static final Color backgroundColor = new Color(150, 150, 150, 200);
	private static final int SHOW_MESSAGE_DELAY = 10;
	private static final int NUMBER_OF_MESSAGES = 10;
	private static final int VERDANA_OFFSET = 4;
	private static final int TEXT_GAP = 15;

	/* ========== PUBLIC ========== */

	/**
	 * Constructs the {@link ChatPanel} component with specified dimensions. Registers this class to the {@link Timer}.
	 * 
	 * @param drawPanel - parent of this panel, used to redraw panel and its components.
	 * @param x - sets the x position on the owning window in pixels.
	 * @param y - sets the y position on the owning window in pixels.
	 * @param width - sets the width of the panel.
	 * @param height - sets the height of the panel
	 */
	public ChatPanel(int x, int y, int width, int height) {
		super(x, y, width, height);
		
		Timer.getInstance().addTickable(this);
	}

	@Override
	public void paint(Graphics g) {

		if (isVisible) {
			createMessageFromAwaitingOnesAndRemoveOldOnes(g);

			int heightOfTextArea = TEXT_GAP * messagePanelSize;
			int yCoordOfTextArea = y + height - heightOfTextArea;

			printBackground(g, heightOfTextArea, yCoordOfTextArea);
			printMessages(g, yCoordOfTextArea);
		}
	}

	@Override
	public void tick() {
		for (Message message : messages) {
			if (message.getCreationTime() + SHOW_MESSAGE_DELAY * Tickable.SECOND < System.currentTimeMillis()) {
				messages.remove(message);
				messagePanelSize -= message.getSize();
				repaint();
			}
		}
	}
	
	public void sendMessage(String message) {
		awaitingMessages.add(message);
	}

	/* ========== PRIVATE ========== */

	private final int MESSAGE_X_OFFSET = x + 2;
	private final Queue<String> awaitingMessages = new LinkedList<String>();
	private final Queue<Message> messages = new LinkedList<Message>();
	private int messagePanelSize = 0;

	private void createMessageFromAwaitingOnesAndRemoveOldOnes(Graphics g) {
		

		while (!awaitingMessages.isEmpty()) {
			List<String> partedMessage = StringParter.partMessage(awaitingMessages.poll(), width, g.getFontMetrics());
			Message newMessage = new Message("Jamnic", partedMessage);

			messages.add(newMessage);
			messagePanelSize += newMessage.getSize();

			while (messagePanelSize > NUMBER_OF_MESSAGES) {
				Message oldMessage = messages.poll();
				messagePanelSize -= oldMessage.getSize();
			}
		}
	}

	private void printBackground(Graphics g, int heightOfTextArea, int yCoordOfTextArea) {
		g.setColor(backgroundColor);
		g.fillRect(x, yCoordOfTextArea, width, heightOfTextArea);
		g.setColor(Color.BLACK);
		g.drawRect(x, yCoordOfTextArea, width, heightOfTextArea);
	}

	private void printMessages(Graphics g, int yCoordOfTextArea) {

		g.setFont(new Font("Verdana", Font.BOLD, 10));

		int counter = 1;
		for (Message message : messages) {

			int internalCounter = 0;
			for (String part : message.getContent())
				g.drawString(part, MESSAGE_X_OFFSET, yCoordOfTextArea + (internalCounter++ + counter) * TEXT_GAP
						- VERDANA_OFFSET);

			counter += message.getSize();
		}
	}

}
