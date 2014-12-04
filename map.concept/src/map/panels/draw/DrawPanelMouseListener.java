package map.panels.draw;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import map.concept.Button;
import map.concept.KingdomDetailsPopup;
import map.panels.map.Field;
import map.panels.map.MapPanel;

public class DrawPanelMouseListener extends MouseAdapter {

	/* Public */
	public DrawPanelMouseListener(KingdomDetailsPopup kingdomDetailsPopup, Button kingdomDetailsButton,
			MapPanel mapPanel, FieldDetailsPopup fieldDetailsPopup) {
		this.kingdomDetailsPopup = kingdomDetailsPopup;
		this.kingdomDetailsButton = kingdomDetailsButton;
		this.mapPanel = mapPanel;
		this.fieldDetailsPopup = fieldDetailsPopup;
	}

	public void mousePressed(MouseEvent e) {

		if (fieldDetailsPopup.isClicked(e)) {
			fieldDetailsPopup.hide();
			fieldDetailsPopup.repaint();
		} else if (kingdomDetailsPopup.isClicked(e)) {
			kingdomDetailsPopup.hide();
			kingdomDetailsPopup.repaint();
		} else if (kingdomDetailsButton.isClicked(e)) {
			isKingdomDetailsButtonClicked = true;
			kingdomDetailsButton.push();
			kingdomDetailsButton.repaint();
			kingdomDetailsPopup.show();
			kingdomDetailsPopup.repaint();
		} else if (mapPanel.isClicked(e)) {
			Field field = mapPanel.getField(e.getX(), e.getY());
			fieldDetailsPopup.displayField(field);
			fieldDetailsPopup.show();
			fieldDetailsPopup.repaint();
		}
	}

	public void mouseReleased(MouseEvent e) {
		if (isKingdomDetailsButtonClicked) {
			isKingdomDetailsButtonClicked = false;
			kingdomDetailsButton.unpush();
			kingdomDetailsButton.repaint();
		}
	}

	/* Private */
	private KingdomDetailsPopup kingdomDetailsPopup;
	private Button kingdomDetailsButton;
	private MapPanel mapPanel;
	private FieldDetailsPopup fieldDetailsPopup;

	private boolean isKingdomDetailsButtonClicked;

}
