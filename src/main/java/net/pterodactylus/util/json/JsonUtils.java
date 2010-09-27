/*
 * utils.json - JsonUtils.java - Copyright © 2010 David Roden
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.pterodactylus.util.json;

import net.pterodactylus.util.number.Hex;

/**
 * A collection of JSON utility methods.
 *
 * @author <a href="mailto:bombe@pterodactylus.net">David ‘Bombe’ Roden</a>
 */
public class JsonUtils {

	/**
	 * Escapes the given text.
	 *
	 * @param text
	 *            The text to escape
	 * @return The escaped text
	 */
	public static String escape(String text) {
		StringBuilder escapedString = new StringBuilder();
		for (char c : text.toCharArray()) {
			if ((c == 0x20) || (c == 0x21) || ((c >= 0x23) && (c <= 0x5b)) || (c >= 0x5d)) {
				escapedString.append(c);
			} else if (c == 0x0a) {
				escapedString.append("\\n");
			} else if (c == 0x0d) {
				escapedString.append("\\r");
				/* TODO - add more 2-character escapes. */
			} else {
				escapedString.append("\\u").append(Hex.toHex(c, 4));
			}
		}
		return escapedString.toString();
	}

	/**
	 * Formats the given value in a form suitable for use with JSON text
	 * output. The value must be {@code null}, or of one of the types
	 * {@link String}, {@link Number}, {@link Boolean}, {@link JsonArray}, or
	 * {@link JsonObject}.
	 *
	 * @param value
	 *            The value to format
	 * @return The formatted value
	 */
	public static String format(Object value) {
		if (value == null) {
			return "null";
		}
		if (value instanceof String) {
			return "\"" + escape((String) value) + "\"";
		}
		if ((value instanceof Number) || (value instanceof Boolean) || (value instanceof JsonObject) || (value instanceof JsonArray)) {
			return String.valueOf(value);
		}
		/* TODO - signal error. */
		return "";
	}

}
