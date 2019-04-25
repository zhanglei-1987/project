package cn.quickly.project.utility.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import cn.quickly.project.utility.lang.Exceptions;
import cn.quickly.project.utility.lang.Quiet;

public final class Serialization {

	private Serialization() {
		throw new UnsupportedOperationException();
	}

	public static byte[] serialize(Object value) {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(os);
			out.writeObject(value);
		} catch (IOException e) {
			throw Exceptions.runtime(e);
		} finally {
			Quiet.close(out);
		}
		return os.toByteArray();
	}

	public static Object deserialize(byte[] bytes) {
		ByteArrayInputStream is = new ByteArrayInputStream(bytes);
		ObjectInputStream in = null;
		Object value = null;
		try {
			in = new ObjectInputStream(is);
			value = in.readObject();
		} catch (Exception e) {
			throw Exceptions.runtime(e);
		} finally {
			Quiet.close(in);
		}
		return value;
	}

}
