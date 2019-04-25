package cn.quickly.project.utility.security;

import java.io.File;

import cn.quickly.project.utility.codec.Hex;

public class Hashs {

	public static String upper(String algorithm, byte[] data) {
		return Hex.upper(MessageDigests.bytes(algorithm, data));
	}

	public static String upper(String algorithm, File file) {
		return Hex.upper(MessageDigests.file(algorithm, file));
	}

	public static String lower(String algorithm, byte[] data) {
		return Hex.lower(MessageDigests.bytes(algorithm, data));
	}

	public static String lower(String algorithm, File file) {
		return Hex.lower(MessageDigests.file(algorithm, file));
	}

}
