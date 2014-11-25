package map.concept;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

class DrawPanel extends JPanel {

	RedSquare redSquare = new RedSquare();
	TextField textField = new TextField(20);

	private String message = "Testmessage";

	public DrawPanel() {

		setBorder(BorderFactory.createLineBorder(Color.black));

		getInputMap().put(KeyStroke.getKeyStroke('a'), "a");
		getActionMap().put("a", new Action() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}

			public void setEnabled(boolean b) {
				// TODO Auto-generated method stub

			}

			public void removePropertyChangeListener(PropertyChangeListener listener) {
				// TODO Auto-generated method stub

			}

			public void putValue(String key, Object value) {
				// TODO Auto-generated method stub

			}

			public boolean isEnabled() {
				// TODO Auto-generated method stub
				return false;
			}

			public Object getValue(String key) {
				// TODO Auto-generated method stub
				return null;
			}

			public void addPropertyChangeListener(PropertyChangeListener listener) {
				// TODO Auto-generated method stub

			}
		});

		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				moveSquare(e.getX(), e.getY());
			}
		});

		addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent e) {
				moveSquare(e.getX(), e.getY());
			}
		});

		addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent e) {
				if (textField.putLetter(e.getKeyChar())) {
					System.out.println("Gottt it! " + e.getKeyChar());
					repaint(textField.getX(), textField.getY(), textField.getWidth(), textField.getHeight());
				}
			}

			public void keyPressed(KeyEvent e) {
			}

			public void keyReleased(KeyEvent e) {
			}

		});

	}

	private void moveSquare(int x, int y) {

		// Current square state, stored as final variables
		// to avoid repeat invocations of the same methods.
		final int CURR_X = redSquare.getX();
		final int CURR_Y = redSquare.getY();
		final int CURR_W = redSquare.getWidth();
		final int CURR_H = redSquare.getHeight();
		final int OFFSET = 1;

		if ((CURR_X != x) || (CURR_Y != y)) {

			// The square is moving, repaint background
			// over the old square location.
			repaint(CURR_X, CURR_Y, CURR_W + OFFSET, CURR_H + OFFSET);

			// Update coordinates.
			redSquare.setX(x);
			redSquare.setY(y);

			// Repaint the square at the new location.
			repaint(redSquare.getX(), redSquare.getY(), redSquare.getWidth() + OFFSET, redSquare.getHeight() + OFFSET);
		}
	}

	public Dimension getPreferredSize() {
		return new Dimension(250, 200);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		textField.paint(g);

		redSquare.paintSquare(g);
	}
}
