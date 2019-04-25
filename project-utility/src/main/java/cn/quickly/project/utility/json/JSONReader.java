package cn.quickly.project.utility.json;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PushbackReader;
import java.io.Reader;
import java.io.StringReader;

public class JSONReader {
	
	private PushbackReader reader;

	public JSONReader(File file) throws FileNotFoundException {
		this(new FileInputStream(file));
	}

	public JSONReader(InputStream input) {
		this(new InputStreamReader(input));
	}

	public JSONReader(Reader reader) {
		this.reader = new PushbackReader(reader);
	}

	public Object read() throws IOException {
		return JSONParser.parse(reader);
	}

	public void close() throws IOException {
		reader.close();
	}

	public static Object read(String text) throws IOException {
		JSONReader reader = new JSONReader(new StringReader(text));
		try {
			return reader.read();
		} finally {
			reader.close();
		}
	}
}
