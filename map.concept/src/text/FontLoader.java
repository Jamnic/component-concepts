package text;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FontLoader {

	public static Font getDiplomaFont() {
		if (diplomaFont == null)
			throw new IllegalStateException("Create at least one instance of FontLoader");

		return diplomaFont;
	}

	public static Font getVerdanaFont() {
		if (verdanaFont == null)
			throw new IllegalStateException("Create at least one instance of FontLoader");

		return verdanaFont;
	}

	public FontLoader() throws FontFormatException, IOException {

		InputStream myStream = new BufferedInputStream(new FileInputStream("diploma.ttf"));
		diplomaFont = Font.createFont(Font.TRUETYPE_FONT, myStream).deriveFont(Font.PLAIN, 24);

		verdanaFont = new Font("Verdana", Font.PLAIN, 10);
	}

	/* Private */
	private static Font diplomaFont;
	private static Font verdanaFont;
}
