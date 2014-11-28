package text;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import map.concept.Component;

public abstract class Text extends Component {

	public Text(int x, int y, int width, int height, Font font, String text) {
		super(x, y, width, height);
		this.font = font;
		this.text = new StringBuilder(text);
	}

	public Text(int x, int y, int width, int height, String text) {
		super(x, y, width, height);
		this.font = new Font("Verdana", Font.PLAIN, 10);
		this.text = new StringBuilder(text);
	}

	public void paint(Graphics g) {
		g.setColor(Color.BLACK);
		g.setFont(font);
		g.drawString(text.toString(), x + 2, y + 12);
	}

	public String getText() {
		return text.toString();
	}

	/* Private */
	protected StringBuilder text;
	private Font font;

}
