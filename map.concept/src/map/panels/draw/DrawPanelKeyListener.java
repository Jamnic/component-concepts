package map.panels.draw;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import map.panels.message.MessagePanel;

public class DrawPanelKeyListener implements KeyListener {

	/* Components */
	private MessagePanel messagePanel;

	/* Public */
	public DrawPanelKeyListener(MessagePanel messagePanel) {
		this.messagePanel = messagePanel;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();

		if (keyCode == KeyEvent.VK_BACK_SPACE)
			messagePanel.deleteLetterInTextField();
		else if ((keyCode >= 65 && keyCode <= 122) || keyCode == 32 || keyCode > 186)
			messagePanel.putLetterInTextField(e.getKeyChar());
		else if (keyCode == KeyEvent.VK_ENTER) {
			messagePanel.sendMessage();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

}