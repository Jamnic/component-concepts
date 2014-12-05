package map.panels.draw;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import map.panels.map.MapPanel;
import map.panels.message.MessagePanel;

public class DrawPanelKeyListener implements KeyListener {

	/* Components */
	private MessagePanel messagePanel;
	private MapPanel mapPanel;

	/* Public */
	public DrawPanelKeyListener(MapPanel mapPanel, MessagePanel messagePanel) {
		this.mapPanel = mapPanel;
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
		} else if (keyCode == KeyEvent.VK_LEFT) {
			mapPanel.toLeft();
		} else if (keyCode == KeyEvent.VK_RIGHT) {
			mapPanel.toRight();
		} else if (keyCode == KeyEvent.VK_UP) {
			mapPanel.toUp();
		} else if (keyCode == KeyEvent.VK_DOWN) {
			mapPanel.toDown();
		} 
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

}