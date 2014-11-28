package map.concept;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import text.TextField;

public class MessagePanel extends Component {

	private static final Color backgroundColor = new Color(150, 100, 150, 200);
	private static final int NUMBER_OF_MESSAGES = 10;
	private static final int SHOW_MESSAGE_DELAY = 5;
	private static final int TEXT_GAP = 15;

	/* Components */
	private Button button;
	private TextField textField;
	private final DrawPanel mainPanel;

	/* Public */
	public MessagePanel(DrawPanel mainPanel, int x, int y, int width, int height) {
		super(x, y, width, height);

		this.mainPanel = mainPanel;

		createComponents();
		timer();
	}

	public void createMessage() {

		messageCountdownTimer += SHOW_MESSAGE_DELAY;

		if (messages.size() == NUMBER_OF_MESSAGES)
			messages.poll();

		awaitingMessages.add(textField.getText());

		this.repaint(mainPanel);

		textField.reset();
		textField.repaint(mainPanel);
	}

	public void paint(Graphics g) {
		
		while (!awaitingMessages.isEmpty()) {
			messages.add(new Message("", partMessage(awaitingMessages.poll(), width, g.getFontMetrics())));
		}
		
		int size = 0;
		for (Message message : messages) {
			size += message.getSize();
		}

		int heightOfMessages = TEXT_GAP * (size > NUMBER_OF_MESSAGES ? NUMBER_OF_MESSAGES : size);
		int dynamicY = y + height - heightOfMessages;

		g.setColor(backgroundColor);
		g.fillRect(x, dynamicY, width, heightOfMessages);
		g.setColor(Color.BLACK);
		g.drawRect(x, dynamicY, width, heightOfMessages);

		int counter = 1;
		g.setFont(new Font("Verdana", Font.BOLD, 10));

		for (Message message : messages) {

			if (message.getContent().size() == 1) {
				g.drawString(message.getContent().get(0), x + 2, dynamicY + 15 * counter++ - 4);
			} else {
				
				int internalCounter = 0;

				for (String part : message.getContent()) {
					g.drawString(part, x + 2, dynamicY + message.getSize() * 15 - internalCounter++ * 15 - 4);
				}

				counter += message.getSize();
			}

		}

		textField.paint(g);

	}

	private List<String> partMessage(String message, int width, FontMetrics fontMetrics) {

		List<String> strings = new LinkedList<String>();

		String[] tokens = message.split("\\s+");

		StringBuilder builder = new StringBuilder();
		for (String token : tokens) {
			if (fontMetrics.stringWidth(builder.toString() + token) > width) {
				strings.add(builder.toString());
				builder = new StringBuilder();
			} else {
				builder.append(token);
				builder.append(" ");
			}
		}

		strings.add(builder.toString());

		System.out.println(strings.size());

		return strings;
	}

	/* Private */
	private final Queue<Message> messages = new LinkedList<Message>();
	private int messageCountdownTimer = 0;
	private Queue<String> awaitingMessages = new LinkedList<String>();

	private void createComponents() {
		Font font = new Font("Verdana", Font.PLAIN, 10);
		textField = new TextField(0, y + height, width, TEXT_GAP, font, 255);
	}

	private void timer() {
		new Thread(new Runnable() {

			@Override
			public synchronized void run() {
				long previous = System.currentTimeMillis();

				while (true) {
					long current = System.currentTimeMillis();
					if (current - 1000 > previous) {
						try {
							this.wait(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						previous = current;
						tick();
					}
				}
			}
		}).start();

	}

	protected void tick() {
		if (messageCountdownTimer > 0) {
			if ((messageCountdownTimer % SHOW_MESSAGE_DELAY) == 1 || messageCountdownTimer == 1) {
				messages.poll();
				this.repaint(mainPanel);
			}
			messageCountdownTimer--;
		}

	}

	public boolean deleteLetterInTextField() {
		return textField.deleteLetter();
	}

	public boolean putLetterInTextField(char keyChar) {
		return textField.putLetter(keyChar);
	}

	public void repaintTextField() {
		textField.repaint(mainPanel);
	}

	public boolean isButtonClicked(MouseEvent e) {
		return button.isClicked(e);
	}

	public void pushButton() {
		button.push();
	}

	public void repaintButton() {
		button.repaint(mainPanel);
	}

	public void unpushButton() {
		button.unpush();
	}

}
