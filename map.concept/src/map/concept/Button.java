package map.concept;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

public class Button extends Text {

	public Button(int x, int y, int width, int height, String text) {
		super(x, y, width, height, text);
	}

	public void paint(Graphics g) {

		if (push) {
			g.setColor(Color.RED);
			g.fillRect(x, y, width, height);
			g.setColor(Color.BLACK);
			g.drawRect(x, y, width, height);
		} else {
			g.setColor(Color.WHITE);
			g.fillRect(x, y, width, height);
			g.setColor(Color.BLACK);
			g.drawRect(x, y, width, height);
		}

		super.paint(g);

	}

	public boolean isClicked(MouseEvent e) {
		return e.getX() >= x && e.getX() <= x + width && e.getY() >= y && e.getY() <= y + height;
	}

	public void push() {
		this.push = true;
	}

	public void unpush() {
		this.push = false;
	}

	/* Private */
	private boolean push;

}
