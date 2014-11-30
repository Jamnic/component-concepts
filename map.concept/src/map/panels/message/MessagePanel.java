package map.panels.message;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import map.concept.Component;
import map.concept.TextField;
import map.concept.Tickable;
import map.panels.draw.DrawPanel;
import text.FontLoader;
import text.StringParter;

public class MessagePanel extends Component implements Tickable {

	private static final Color backgroundColor = new Color(150, 150, 150, 200);
	private static final int NUMBER_OF_MESSAGES = 10;
	private static final int SECOND = 1000;
	private static final int SHOW_MESSAGE_DELAY = 10 * SECOND;
	private static final int TEXT_GAP = 15;
	private static final int VERDANA_OFFSET = 4;
	private final int MESSAGE_X_OFFSET = x + 2;

	/* Components */
	private TextField textField;
	private final DrawPanel mainPanel;

	/* Public */
	public MessagePanel(DrawPanel drawPanel, int x, int y, int width, int height) {
		super(x, y, width, height);
		this.mainPanel = drawPanel;

		createComponents();
	}

	public void sendMessage() {

		awaitingMessages.add(textField.getText());
		this.repaint(mainPanel);

		textField.reset();
		textField.repaint(mainPanel);
	}

	public void paint(Graphics g) {

		if (isVisible) {
			createMessageFromAwaitingOnesAndRemoveOldOnes(g);

			int heightOfTextArea = TEXT_GAP * messagePanelSize;
			int yCoordOfTextArea = y + height - heightOfTextArea;

			printBackground(g, heightOfTextArea, yCoordOfTextArea);
			printMessages(g, yCoordOfTextArea);

			textField.paint(g);
		}
	}

	@Override
	public void tick() {
		for (Message message : messages) {
			if (message.getCreationTime() + SHOW_MESSAGE_DELAY < System.currentTimeMillis()) {
				messages.remove(message);
				messagePanelSize -= message.getSize();
				this.repaint(mainPanel);
			}
		}
	}

	public void deleteLetterInTextField() {
		if (textField.deleteLetter())
			textField.repaint(mainPanel);
	}

	public void putLetterInTextField(char keyChar) {
		if (textField.putLetter(keyChar))
			textField.repaint(mainPanel);
	}

	/* Private */
	private final Queue<Message> messages = new LinkedList<Message>();
	private final Queue<String> awaitingMessages = new LinkedList<String>();
	private int messagePanelSize = 0;

	private void createComponents() {
		textField = new TextField(0, y + height, width, TEXT_GAP, FontLoader.getVerdanaFont(), 255);
	}

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
