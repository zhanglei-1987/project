package cn.quickly.project.utility.security;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.Adler32;
import java.util.zip.CRC32;

import cn.quickly.project.utility.codec.Bits;
import cn.quickly.project.utility.codec.Hex;
import cn.quickly.project.utility.lang.Exceptions;
import cn.quickly.project.utility.lang.Quiet;

public final class Checksums {

	private Checksums() {
		throw new UnsupportedOperationException();
	}

	public static String crc32(byte[] value) {

		CRC32 crc32 = new CRC32();
		crc32.update(value);

		int crc = (int) crc32.getValue();

		return Hex.upper(Bits.getBytes(crc));

	}

	public static String crc32(File value) {

		try {

			CRC32 crc32 = new CRC32();

			FileInputStream in = new FileInputStream(value);

			byte[] buffer = new byte[10240];

			for (int count = -1; (count = in.read(buffer)) > 0;) {

				crc32.update(buffer, 0, count);

			}

			Quiet.close(in);

			int crc = (int) crc32.getValue();

			return Hex.upper(Bits.getBytes(crc));

		} catch (IOException e) {
			throw Exceptions.argument(e);
		}

	}

	public static String adler32(byte[] value) {

		Adler32 adler32 = new Adler32();
		adler32.update(value);

		int adler = (int) adler32.getValue();

		return Hex.upper(Bits.getBytes(adler));

	}

	public static String adler32(File value) {

		try {

			Adler32 adler32 = new Adler32();

			FileInputStream in = new FileInputStream(value);

			byte[] buffer = new byte[10240];

			for (int count = -1; (count = in.read(buffer)) > 0;) {

				adler32.update(buffer, 0, count);

			}

			Quiet.close(in);

			int adler = (int) adler32.getValue();

			return Hex.upper(Bits.getBytes(adler));

		} catch (IOException e) {
			throw Exceptions.argument(e);
		}
	}

	public static String md5(File file) {
		return Hex.upper(MessageDigests.file("MD5", file));
	}

	public static String md5(byte[] data) {
		return Hex.upper(MessageDigests.bytes("MD5", data));
	}

	public static String sha1(File file) {
		return Hex.upper(MessageDigests.file("SHA1", file));
	}

	public static String sha1(byte[] data) {
		return Hex.upper(MessageDigests.bytes("SHA1", data));
	}

}
