package map.concept;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import text.DrawPanelKeyListener;
import text.TextField;

public class DrawPanel extends JPanel {

	TextField textField = new TextField(2, 503, new Font("Verdana", Font.PLAIN, 10), 15);
	Button button = new Button(280, 320, 22, 22, "Insert A");
	MessagePanel messagePanel = new MessagePanel(this, 2, 402, 150, 100);
	MapPanel mapPanel = new MapPanel(2, 2, 600, 600);

	public DrawPanel() {

		setBorder(BorderFactory.createLineBorder(Color.black));

		addKeyListener(new DrawPanelKeyListener(textField, this));

		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (button.isClicked(e)) {
					button.push();
					repaint(button.getX(), button.getY(), button.getWidth(), button.getHeight());
					sendMessage(textField.getText());
					textField.reset();
					repaint(textField.getX(), textField.getY(), textField.getWidth(), textField.getHeight());
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (button.isClicked(e)) {
					button.unpush();
					repaint(button.getX(), button.getY(), button.getWidth(), button.getHeight());
				}
			}

		});

	}

	public Dimension getPreferredSize() {
		return new Dimension(800, 600);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		button.paint(g);
		textField.paint(g);
		mapPanel.paint(g);
		messagePanel.paint(g);
	}

	public void repaint(Component component) {
		int x = component.getX();
		int y = component.getY();

		int widthPlusX = component.getWidth() + x;
		int heightPlusY = component.getHeight() + y;

		repaint(x, y, widthPlusX, heightPlusY);
	}

	public void sendMessage(String text) {
		messagePanel.sendMessage(text);
		repaint(messagePanel);
	}
}
