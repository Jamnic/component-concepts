package map.concept;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Queue;

public class MessagePanel extends Component {

	public MessagePanel(DrawPanel drawPanel, int x, int y, int width, int height) {
		super(x, y, width, height);
		this.drawPanel = drawPanel;
		
		timer();
	}

	public void sendMessage(String text) {
		
		tick += TICK_DELAY;

		if (messages.size() == NUMBER_OF_MESSAGES)
			messages.poll();

		messages.add(text);

	}

	public void paint(Graphics g) {

		g.setColor(backgroundColor);
		g.fillRect(x, y, width, height);
		g.setColor(Color.GRAY);
		g.drawRect(x, y, width, height);

		int counter = 1;
		g.setColor(Color.GRAY);
		g.setFont(new Font("Verdana", Font.BOLD, 10));

		for (String message : messages) {
			System.out.println((x + 2) + " " + (y + height - (20 * messages.size() + 20 * counter)));
			g.drawString(message, x + 2, y + height - 2 - (20 * messages.size()) + 20 * counter++);
		}

	}
	
	/* Private */
	private static final Color backgroundColor = new Color(100, 100, 100, 100);
	private static final int NUMBER_OF_MESSAGES = 10;
	private final Queue<String> messages = new LinkedList<String>();
	private final DrawPanel drawPanel;
	private static final int TICK_DELAY = 5;
	private int tick = 0;

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
		if (tick > 0) {
			if ((tick % TICK_DELAY) == 1 || tick == 1) {
				messages.poll();
				drawPanel.repaint(this);
			}
			tick--;
		}

	}

}
