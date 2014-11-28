package map.concept;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import text.DrawPanelKeyListener;

public class DrawPanel extends JPanel {

	/* Components */
	private MessagePanel messagePanel;
	private MapPanel mapPanel;

	/* Public */
	public DrawPanel() {
		createComponents();

		addKeyListener(new DrawPanelKeyListener(this, messagePanel));
		addMouseListener(new DrawPanelMouseListener(this, messagePanel));
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(800, 600);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		mapPanel.paint(g);
		messagePanel.paint(g);
	}

	/* Private */
	private static final long serialVersionUID = 8212047943722390372L;

	private void createComponents() {
		messagePanel = new MessagePanel(this, 0, 320, 150, 165);
		mapPanel = new MapPanel(0, 0, 800, 600);
	}
}
