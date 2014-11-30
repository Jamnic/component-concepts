package map.panels.draw;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import map.concept.Button;
import map.concept.KingdomDetailsPopup;
import map.concept.Timer;
import map.panels.map.MapPanel;
import map.panels.message.MessagePanel;

public class DrawPanel extends JPanel {

	/* Components */
	private MessagePanel messagePanel;
	private MapPanel mapPanel;
	private Button kingdomDetailsButton;
	private KingdomDetailsPopup kingdomDetailsPopup;

	/* Public */
	public DrawPanel() {
		createComponents();

		addKeyListener(new DrawPanelKeyListener(messagePanel));
		addMouseListener(new DrawPanelMouseListener(kingdomDetailsPopup, kingdomDetailsButton));
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
		kingdomDetailsButton.paint(g);
		kingdomDetailsPopup.paint(g);
	}

	/* Private */
	private static final long serialVersionUID = 8212047943722390372L;

	private void createComponents() {
		messagePanel = new MessagePanel(this, 0, 320, 150, 165);
		mapPanel = new MapPanel(0, 0, 800, 480);
		kingdomDetailsButton = new Button(0, 500, 100, 100, "");
		kingdomDetailsPopup = new KingdomDetailsPopup(100, 100, 500, 500);

		Timer.getInstance().addTickable(messagePanel);
	}
}
