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

	public FontLoader() throws FontFormatException, IOException {

		InputStream myStream = new BufferedInputStream(new FileInputStream("diploma.ttf"));

		diplomaFont = Font.createFont(Font.TRUETYPE_FONT, myStream).deriveFont(Font.PLAIN, 24);

	}

	private static Font diplomaFont;

}
