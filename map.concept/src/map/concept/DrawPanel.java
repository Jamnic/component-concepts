package map.concept;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import text.TextField;
import text.DrawPanelKeyListener;

class DrawPanel extends JPanel {

	RedSquare redSquare = new RedSquare();
	TextField textField = new TextField(40, 10, 20);
	Button button = new Button(60, 60, 30, 30, "Insert A");

	public DrawPanel() {

		setBorder(BorderFactory.createLineBorder(Color.black));

		addKeyListener(new DrawPanelKeyListener(textField, this));

		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				moveSquare(e.getX(), e.getY());
				
				if (button.isClicked(e)) {
					System.out.println("Wlaz³");
					button.push();
					repaint(button.getX(), button.getY(), button.getWidth(), button.getHeight());
					textField.putLetter('a');
					repaint(textField.getX(), textField.getY(), textField.getWidth(), textField.getHeight());
				}
			}
			
			public void mouseReleased(MouseEvent e) {
				moveSquare(e.getX(), e.getY());
				
				if (button.isClicked(e)) {
					button.unpush();
					repaint(button.getX(), button.getY(), button.getWidth(), button.getHeight());
				}
			}
		});

		addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent e) {
				moveSquare(e.getX(), e.getY());
			}
		});

	}

	private void moveSquare(int x, int y) {

		final int CURR_X = redSquare.getX();
		final int CURR_Y = redSquare.getY();
		final int CURR_W = redSquare.getWidth();
		final int CURR_H = redSquare.getHeight();
		final int OFFSET = 1;

		if ((CURR_X != x) || (CURR_Y != y)) {

			repaint(CURR_X, CURR_Y, CURR_W + OFFSET, CURR_H + OFFSET);

			redSquare.setX(x);
			redSquare.setY(y);
			
			repaint(redSquare);
		}
	}

	public Dimension getPreferredSize() {
		return new Dimension(250, 200);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		button.paint(g);

		textField.paint(g);

		redSquare.paint(g);
	}
	
	public void repaint(Component component) {
		int x = component.getX();
		int y = component.getY();
		
		repaint(x, y, component.getWidth() + x, component.getHeight() + y);
	}
}
