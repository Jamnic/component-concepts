package map.concept;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DrawPanelMouseListener extends MouseAdapter {

	/* Components */
	private DrawPanel mainPanel;
	private MessagePanel messagePanel;

	/* Public */
	public DrawPanelMouseListener(DrawPanel panel, MessagePanel messagePanel) {
		this.mainPanel = panel;
		this.messagePanel = messagePanel;
	}

	public void mousePressed(MouseEvent e) {
		if (messagePanel.isButtonClicked(e)) {
			isButtonClicked = true;
			messagePanel.pushButton();
			messagePanel.repaintButton();
			messagePanel.sendMessage();
		}
	}

	public void mouseReleased(MouseEvent e) {
		if (isButtonClicked) {
			messagePanel.unpushButton();
			messagePanel.repaintButton();
			isButtonClicked = false;
		}
	}

	/* Private */
	private boolean isButtonClicked;

}
