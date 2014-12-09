package map.panels.draw;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import map.concept.Button;
import map.concept.KingdomDetailsPopup;
import map.panels.map.MapPanel;
import map.panels.message.MessagePanel;

public class DrawPanel extends JPanel {

	/* ========== PUBLIC ========== */

	public DrawPanel() {
		createComponents();

		addKeyListener(new DrawPanelKeyListener(mapPanel, messagePanel));
		addMouseListener(new DrawPanelMouseListener(kingdomDetailsPopup, kingdomDetailsButton, mapPanel,
				fieldDetailsPopup));

		instance = this;
	}

	public static void repaintComponent(int x, int y, int width, int height) {
		instance.repaint(x, y, width + x, height + y);
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
		fieldDetailsPopup.paint(g);
	}

	/* ========== PRIVATE ========== */
	
	private MessagePanel messagePanel;
	private MapPanel mapPanel;
	
	private Button kingdomDetailsButton;
	private KingdomDetailsPopup kingdomDetailsPopup;
	private FieldDetailsPopup fieldDetailsPopup;
	private static DrawPanel instance;
	private static final long serialVersionUID = 8212047943722390372L;

	private void createComponents() {
		messagePanel = new MessagePanel(0, 320, 150, 165);
		mapPanel = new MapPanel(0, 0, 800, 480);
		kingdomDetailsButton = new Button(0, 500, 100, 100, "");
		kingdomDetailsPopup = new KingdomDetailsPopup(100, 100, 500, 500);
		fieldDetailsPopup = new FieldDetailsPopup(100, 100, 300, 300);
	}
}
