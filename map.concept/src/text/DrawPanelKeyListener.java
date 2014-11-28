package text;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import map.concept.DrawPanel;
import map.concept.MessagePanel;

public class DrawPanelKeyListener implements KeyListener {

	/* Components */
	private MessagePanel messagePanel;
	private DrawPanel mainPanel;

	/* Public */
	public DrawPanelKeyListener(DrawPanel mainPanel, MessagePanel messagePanel) {
		this.messagePanel = messagePanel;
		this.mainPanel = mainPanel;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();

		if (keyCode == KeyEvent.VK_BACK_SPACE && messagePanel.deleteLetterInTextField())
			messagePanel.repaintTextField();
		else if (keyCode >= 65 && keyCode <= 122 && messagePanel.putLetterInTextField(e.getKeyChar()))
			messagePanel.repaintTextField();
		else if ((keyCode == 32 || keyCode > 186) && messagePanel.putLetterInTextField(e.getKeyChar())) {
			messagePanel.repaintTextField();
		} else if (keyCode == KeyEvent.VK_ENTER) {
			messagePanel.createMessage();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

}