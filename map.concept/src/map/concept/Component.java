package map.concept;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Component {

	protected int x;
	protected int y;
	protected int width;
	protected int height;

	public Component(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		System.out.println(width);
		this.width = width;
		this.height = height;
	}

	public void paint(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(x, y, width, height);
		g.setColor(Color.BLACK);
		g.drawRect(x, y, width, height);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public void repaint(DrawPanel drawPanel) {
		drawPanel.repaint(x, y, width + x, height + y);
	}

}
