package text;

import java.awt.FontMetrics;
import java.util.LinkedList;
import java.util.List;

public class StringParter {

	public static List<String> partMessage(String message, int width, FontMetrics fontMetrics) {

		List<String> strings = new LinkedList<String>();

		StringBuilder builder = new StringBuilder();

		for (String token : message.split("\\s+")) {
			String buildedString = builder.toString();

			if (fontMetrics.stringWidth(buildedString + token) >= width) {
				strings.add(buildedString);
				builder = new StringBuilder();
			} else {
				builder.append(token);
				builder.append(" ");
			}
		}

		strings.add(builder.toString());

		return strings;
	}

}
