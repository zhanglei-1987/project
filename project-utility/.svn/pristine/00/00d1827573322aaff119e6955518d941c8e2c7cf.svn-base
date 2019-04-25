package cn.quickly.project.utility.net;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import cn.quickly.project.utility.io.Streams;

public class Sockets {

	public static String telnet(String host, int port, String cmd, String charset) throws Exception {

		return new String(telnet(host, port, cmd.getBytes(charset)), charset);

	}

	public static byte[] telnet(String host, int port, byte[] cmd) throws Exception {

		try (Socket socket = new Socket(host, port)) {

			try (OutputStream out = socket.getOutputStream()) {

				out.write(cmd);
				out.flush();

				try (InputStream in = socket.getInputStream()) {

					return Streams.getBytes(in);

				}

			}

		}

	}

}
