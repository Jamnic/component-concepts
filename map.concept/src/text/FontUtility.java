package text;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Utility class providing prepared and loaded fonts to the game.
 */
public class FontUtility {

	/* ========== STATIC ========== */

	/**
	 * Returns the only instance of {@link FontUtility} singleton class. It contains initialzed fonts, ready to use.
	 * 
	 * @return the only instance of {@link FontUtility} singleton class.
	 */
	public static FontUtility getInstance() {
		if (instance == null)
			try {
				instance = new FontUtility();
			} catch (FontFormatException e) {
				// TODO exception
			} catch (IOException e) {
				// TODO exception
			}

		return instance;
	}

	/* ========== PUBLIC ========== */

	/**
	 * Returns the <code>diplomaFont</code> for drawing purposes.
	 * 
	 * @return the <code>diplomaFont</code>.
	 */
	public Font getDiplomaFont() {
		return diplomaFont;
	}

	/**
	 * Returns the <code>verdanaFont</code> for drawing purposes.
	 * 
	 * @return the <code>verdanaFont</code>.
	 */
	public Font getVerdanaFont() {
		return verdanaFont;
	}

	/* ========== PRIVATE ========== */

	/**
	 * Singleton instance container.
	 */
	private static FontUtility instance;
	
	private Font diplomaFont;
	private Font verdanaFont;

	/**
	 * Constructs the {@Link FontUtility} singleton, loads font configurations from files, and saves to the
	 * proper fields.
	 * 
	 * @throws FontFormatException - when given file with font has wrong format.
	 * @throws IOException - when there is IO problems during obtaining the file.
	 */
	private FontUtility() throws FontFormatException, IOException {

		diplomaFont = loadDiplomaFont();

		verdanaFont = loadVerdanaFont();
	}

	/**
	 * Loads diploma font from given file.
	 */
	private Font loadDiplomaFont() throws FileNotFoundException, FontFormatException, IOException {
		InputStream myStream = new BufferedInputStream(new FileInputStream("diploma.ttf"));

		return Font.createFont(Font.TRUETYPE_FONT, myStream).deriveFont(Font.PLAIN, 12);
	}
	
	/** 
	 * Loads default Verdana font.
	 */
	private Font loadVerdanaFont() {
		return new Font("Verdana", Font.PLAIN, 10);
	}
}