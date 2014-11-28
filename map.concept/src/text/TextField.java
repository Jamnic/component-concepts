package text;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class TextField extends Text {

	private static final Color backgroundColor = new Color(150, 100, 150, 200);

	private int max;

	public TextField(int x, int y, int width, int height, Font font, int columns) {
		super(x, y, width, height, font, "");
		this.max = columns;
	}

	public boolean deleteLetter() {
		int length = text.length();
		if (length <= 0)
			return false;

		text.deleteCharAt(length - 1);
		return true;
	}

	public boolean putLetter(char letter) {
		if (text.length() > max)
			return false;

		text.append(letter);
		return true;
	}

	public void paint(Graphics g) {
		
		System.out.println(x + " " + y + " " + width + " " + height);

		g.setColor(backgroundColor);
		g.fillRect(x, y, width, height);
		g.setColor(Color.BLACK);
		g.drawRect(x, y, width, height);

		super.paint(g);

	}

	public void reset() {
		text.delete(0, text.length());
	}
}
