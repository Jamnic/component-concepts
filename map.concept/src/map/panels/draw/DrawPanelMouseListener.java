package map.panels.draw;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import map.concept.Button;
import map.concept.KingdomDetailsPopup;

public class DrawPanelMouseListener extends MouseAdapter {

	/* Public */
	public DrawPanelMouseListener(KingdomDetailsPopup kingdomDetailsPopup, Button kingdomDetailsButton) {
		this.kingdomDetailsPopup = kingdomDetailsPopup;
		this.kingdomDetailsButton = kingdomDetailsButton;
	}
	
	public void mousePressed(MouseEvent e) {
		if (kingdomDetailsButton.isClicked(e)) {
			isKingdomDetailsButtonClicked = true;
			kingdomDetailsButton.push();
			kingdomDetailsPopup.show();
		}
	}

	public void mouseReleased(MouseEvent e) {
		if (isKingdomDetailsButtonClicked) {
			isKingdomDetailsButtonClicked = false;
			kingdomDetailsButton.unpush();
		}
	}
	
	/* Private */
	private KingdomDetailsPopup kingdomDetailsPopup;
	private Button kingdomDetailsButton;
	private boolean isKingdomDetailsButtonClicked;
	

}
