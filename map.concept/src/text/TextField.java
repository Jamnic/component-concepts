package text;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class TextField extends Text {

	private int width;
	private final int height = 22;

	private int max;

	public TextField(int x, int y, int columns) {
		super(x, y);
		this.max = columns;
		this.width = columns * 10;
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

		g.setColor(Color.WHITE);
		g.fillRect(x, y, width, height);
		g.setColor(Color.BLACK);
		g.drawRect(x, y, width, height);

		super.paint(g);

	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}
