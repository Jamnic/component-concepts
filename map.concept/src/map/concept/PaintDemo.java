package map.concept;

import java.awt.FontFormatException;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import text.FontLoader;

public class PaintDemo {

	public static void main(String[] args) throws FontFormatException, IOException {
		
		new FontLoader();
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}

	private static void createAndShowGUI() {
		JFrame f = new JFrame("Swing Paint Demo");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(800, 600);
		DrawPanel panel = new DrawPanel();
		f.add(panel);
        f.pack();
		f.setVisible(true);

		panel.requestFocusInWindow();
	}
	
}
