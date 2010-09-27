/*
 * utils.json - JsonArray.java - Copyright © 2010 David Roden
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

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Defines a JSON array. A JSON array can contain an arbitrary amount of other
 * objects. Those objects can be {@link String}s, {@link Number}s,
 * {@code boolean}s, {@code null}, or other {@link JsonObject JSON objects} or
 * {@link JsonArray JSON arrays}.
 *
 * @author <a href="mailto:bombe@pterodactylus.net">David ‘Bombe’ Roden</a>
 */
public class JsonArray {

	/** The values in this list. */
	private final List<Object> values = new ArrayList<Object>();

	/**
	 * Adds a {@link String} value to this array.
	 *
	 * @param value
	 *            The value to add
	 * @return The array (for method chaining)
	 */
	public JsonArray add(String value) {
		values.add(value);
		return this;
	}

	/**
	 * Adds a {@link Number} value to this array.
	 *
	 * @param value
	 *            The value to add
	 * @return This array (for method chaining)
	 */
	public JsonArray add(Number value) {
		values.add(value);
		return this;
	}

	/**
	 * Adds a {@code boolean} value to this array.
	 *
	 * @param value
	 *            The value to add
	 * @return This array (for method chaining)
	 */
	public JsonArray add(boolean value) {
		values.add(value);
		return this;
	}

	/**
	 * Adds a {@link JsonArray JSON array} value to this array. While this
	 * method allows an array to be added to itself, the
	 * {@link JsonUtils#format(Object) formatter} can not handle it and will
	 * crash!
	 *
	 * @param value
	 *            The value to add
	 * @return This array (for method chaining)
	 */
	public JsonArray add(JsonArray value) {
		values.add(value);
		return this;
	}

	/**
	 * Adds a {@link JsonObject JSON object} value to this array.
	 *
	 * @param value
	 *            The value to add
	 * @return This array (for method chaining)
	 */
	public JsonArray add(JsonObject value) {
		values.add(value);
		return this;
	}

	/**
	 * Writes this array to the given writer.
	 *
	 * @param writer
	 *            The writer to write this array to
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	public void write(Writer writer) throws IOException {
		writer.write("[");
		boolean first = true;
		for (Object value : values) {
			if (!first) {
				writer.write(',');
			} else {
				first = false;
			}
			writer.write(JsonUtils.format(value));
		}
		writer.write("]");
	}

	/**
	 * Returns a textual representation of this array. This method is
	 * equivalent to calling {@link #write(Writer)} with a newly created
	 * {@link StringWriter}.
	 *
	 * @return A textual representation of this array
	 */
	@Override
	public String toString() {
		StringWriter stringWriter = new StringWriter();
		try {
			write(stringWriter);
		} catch (IOException ioe1) {
			/* ignore, StringWriter never throws. */
		}
		return stringWriter.toString();
	}

}
