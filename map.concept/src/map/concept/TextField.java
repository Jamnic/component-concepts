package map.concept;

import java.awt.Color;
import java.awt.Graphics;

public class TextField {
	
	private int xPos = 0;
	private int yPos = 0;
	private int width = 20;
	private int height = 20;
	
	private int max;
	
	private StringBuilder mutableText = new StringBuilder("");

	public TextField(int max) {
		this.max = max;
		width = max * 10;
	}
	
	public boolean putLetter(char letter) {
		if (mutableText.length() > max) 
			return false;
		
		mutableText.append(letter);
		return true;
	}
	
	public void paint(Graphics g) {
		
		g.setColor(Color.WHITE);
		g.fillRect(xPos, yPos, width, height);
		g.setColor(Color.BLACK);
		g.drawRect(xPos, yPos, width, height);
		
		g.drawString(mutableText.toString(), xPos, yPos + 10);
		
	}

	public int getX() {
		return xPos;
	}

	public int getY() {
		return yPos;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}
