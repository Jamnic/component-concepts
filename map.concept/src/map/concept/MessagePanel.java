package map.concept;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
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

	public void sendMessage() {

		messageCountdownTimer += SHOW_MESSAGE_DELAY;

		if (messages.size() == NUMBER_OF_MESSAGES)
			messages.poll();

		messages.add(textField.getText());

		this.repaint(mainPanel);

		textField.reset();
		textField.repaint(mainPanel);
	}

	public void paint(Graphics g) {
		
		int heightOfMessages = TEXT_GAP * messages.size();
		int dynamicY = y + height - heightOfMessages;

		g.setColor(backgroundColor);
		g.fillRect(x, dynamicY, width, heightOfMessages);
		g.setColor(Color.BLACK);
		g.drawRect(x, dynamicY, width, heightOfMessages);

		int counter = 1;
		g.setFont(new Font("Verdana", Font.BOLD, 10));

		for (String message : messages) {
			g.drawString(message, x + 2, dynamicY + 15 * counter++ - 4);
		}

		textField.paint(g);

	}

	/* Private */
	private final Queue<String> messages = new LinkedList<String>();
	private int messageCountdownTimer = 0;

	private void createComponents() {
		Font font = new Font("Verdana", Font.PLAIN, 10);
		textField = new TextField(0, y + height, width, TEXT_GAP, font, TEXT_GAP);
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
