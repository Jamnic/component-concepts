package map.concept;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

import map.panels.draw.DrawPanel;

public abstract class Component {

	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected boolean isVisible;

	public Component(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		isVisible = true;
	}

	public void paint(Graphics g) {
		if (isVisible) {
			g.setColor(Color.RED);
			g.fillRect(x, y, width, height);
			g.setColor(Color.BLACK);
			g.drawRect(x, y, width, height);
		}
	}

	public boolean isClicked(MouseEvent e) {
		return isVisible && e.getX() >= x && e.getX() <= x + width && e.getY() >= y && e.getY() <= y + height;
	}

	public void repaint() {
		DrawPanel.repaintComponent(x, y, width + x, height + y);
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

	public void show() {
		this.isVisible = true;
	}

	public void hide() {
		this.isVisible = false;
	}

}
