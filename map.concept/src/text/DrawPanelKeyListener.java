package text;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class DrawPanelKeyListener implements KeyListener {

	public DrawPanelKeyListener(TextField textField, JPanel panel) {
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
		}
	}

	public void keyReleased(KeyEvent e) {
	}
	
	private TextField textField;
	private JPanel panel;

}
