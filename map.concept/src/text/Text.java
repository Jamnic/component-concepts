package text;

import java.awt.Font;
import java.awt.Graphics;

public abstract class Text {

	public Text(int x, int y, Font font, String text) {
		this.x = x;
		this.y = y;
		this.font = font;
		this.text = new StringBuilder(text);
	}

	public Text(int x, int y) {
		this(x, y, FontLoader.getDiplomaFont(), "");
	}

	public Text(int x, int y, String text) {
		this(x, y, FontLoader.getDiplomaFont(), "");
	}
	
	public Text(int x, int y, Font font) {
		this(x, y, font, "");
	}

	public void paint(Graphics g) {
		g.setFont(font);
		g.drawString(text.toString(), x + 2, y + 20);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public String getText() {
		return text.toString();
	}

	/* Private */
	protected StringBuilder text;
	protected int x;
	protected int y;
	private Font font;

}
