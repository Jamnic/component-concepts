package text;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import map.concept.DrawPanel;

public class DrawPanelKeyListener implements KeyListener {

	public DrawPanelKeyListener(TextField textField, DrawPanel panel) {
		this.textField = textField;
		this.panel = panel;
	}
	
	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();

		if (keyCode == KeyEvent.VK_BACK_SPACE && textField.deleteLetter())
			panel.repaint(textField.getX(), textField.getY(), textField.getWidth(), textField.getHeight());
		else if (keyCode >= 65 && keyCode <= 122 && textField.putLetter(e.getKeyChar()))
			panel.repaint(textField.getX(), textField.getY(), textField.getWidth(), textField.getHeight());
		else if ((keyCode == 32 || keyCode > 186) && textField.putLetter(e.getKeyChar())) {
			panel.repaint(textField.getX(), textField.getY(), textField.getWidth(), textField.getHeight());
		} else if (keyCode == KeyEvent.VK_ENTER) {
			panel.sendMessage(textField.getText());
			textField.reset();
			panel.repaint(textField.getX(), textField.getY(), textField.getWidth(), textField.getHeight());
		}
	}

	public void keyReleased(KeyEvent e) {
	}
	
	private TextField textField;
	private DrawPanel panel;

}
