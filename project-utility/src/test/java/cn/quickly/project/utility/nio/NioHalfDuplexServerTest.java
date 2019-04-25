package cn.quickly.project.utility.nio;

import java.net.InetSocketAddress;

import org.junit.Test;

public class NioHalfDuplexServerTest {

	private InetSocketAddress address = new InetSocketAddress("0.0.0.0", 56);

	@Test
	public void testServer() throws Exception {

		NioHalfDuplexServer server = new NioHalfDuplexServer();

		server.setAddress(address);

		server.setHandler((data) -> {

			System.out.println(new String(data));

			return "pong".getBytes();

		});

		server.startup();

	}

}
