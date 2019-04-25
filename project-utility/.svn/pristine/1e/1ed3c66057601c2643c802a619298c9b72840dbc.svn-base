package cn.quickly.project.utility.json;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;

import cn.quickly.project.utility.lang.Objects;
import cn.quickly.project.utility.lang.Strings;
import cn.quickly.project.utility.map.Maps;

public class JSONWriter {

	private PrintWriter writer;
	private char quoteChar = '"';

	public JSONWriter(File file) throws IOException {
		writer = new PrintWriter(file);
	}

	public JSONWriter(OutputStream output) {
		writer = new PrintWriter(output);
	}

	public JSONWriter(PrintWriter writer) {
		this.writer = writer;
	}

	public void setDoubleQuote(boolean flag) {
		if (flag) {
			quoteChar = '"';
		} else {
			quoteChar = '\'';
		}
	}

	public void write(Object value) throws IOException {
		_write(value, writer);
	}

	private void _write(Object object, PrintWriter writer) throws IOException {
		if (Objects.isBasic(object)) {
			if (object instanceof String) {
				writer.write(quoteChar);
				writer.write(Strings.escape(object + ""));
				writer.write(quoteChar);
			} else if (object instanceof Date) {
				writer.write("Date(");
				writer.write(String.valueOf(((Date) object).getTime()));
				writer.write(')');
			} else {
				writer.write(String.valueOf(object));
			}
		} else if (object instanceof JSONObject || object instanceof JSONArray) {
			writer.write(String.valueOf(object));
			flush();
		} else if (object instanceof Map<?, ?>) {
			writer.write('{');
			Map<?, ?> map = (Map<?, ?>) object;
			Object key = null;
			for (Iterator<?> it = map.keySet().iterator(); it.hasNext();) {
				key = it.next();
				writer.write(quoteChar);
				writer.write(Strings.escape(key + ""));
				writer.write(quoteChar);
				writer.write(":");
				_write(map.get(key), writer);
				if (it.hasNext())
					writer.write(',');
			}
			writer.write('}');
			flush();
		} else if (object instanceof Iterator<?>) {
			writer.write('[');
			for (Iterator<?> it = (Iterator<?>) object; it.hasNext();) {
				_write(it.next(), writer);
				if (it.hasNext())
					writer.write(',');
			}
			writer.write(']');
			flush();
		} else if (object instanceof Iterable<?>) {
			_write(((Iterable<?>) object).iterator(), writer);
		} else if (object.getClass().isArray()) {
			writer.write('[');
			for (int i = 0, len = Array.getLength(object), end = len - 1; i < len; i++) {
				_write(Array.get(object, i), writer);
				if (i < end)
					writer.write(',');
			}
			writer.write(']');
			flush();
		} else if (object instanceof Enumeration<?>) {
			writer.write('[');
			Enumeration<?> eum = (Enumeration<?>) object;
			for (; eum.hasMoreElements();) {
				_write(eum.nextElement(), writer);
				if (eum.hasMoreElements())
					writer.write(',');
			}
			writer.write(']');
			flush();
		} else {
			_write(Maps.getMap(object), writer);
		}
	}

	public void flush() throws IOException {
		writer.flush();
	}

	public void close() throws IOException {
		writer.close();
	}

}
