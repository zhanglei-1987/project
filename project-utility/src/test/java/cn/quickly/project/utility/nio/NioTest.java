package cn.quickly.project.utility.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

import org.junit.Test;

public class NioTest {

	private InetSocketAddress address = new InetSocketAddress("0.0.0.0", 56);

	@Test
	public void testServer() throws Exception {

		Selector selector = Selector.open();

		// 1.创建服务端监听
		ServerSocketChannel channel = ServerSocketChannel.open();
		channel.bind(address);// 绑定地址端口
		channel.configureBlocking(false);// 非阻塞模式

		// 2.注册SelectionKey.OP_ACCEPT，其它SelectionKey不可用
		channel.register(selector, SelectionKey.OP_ACCEPT);

		// 3.轮询Selector，等待客户端连接
		for (;;) {

			System.out.println("***等待客户端连接***");

			selector.select();// 阻塞直到有需要处理的SelectKey

			Set<SelectionKey> selectionKeys = selector.selectedKeys();

			for (Iterator<SelectionKey> it = selectionKeys.iterator(); it.hasNext(); it.remove()) {

				SelectionKey key = it.next();

				if (key.isValid()) {

					try {

						if (key.isAcceptable()) {

							System.out.println("-----服务端接受成功-----");

							SocketChannel clientChannel = channel.accept();
							clientChannel.configureBlocking(false);
							clientChannel.register(selector, SelectionKey.OP_READ);

						} else if (key.isReadable()) {

							System.out.println("-----服务端读取成功-----");

							SocketChannel clientChannel = (SocketChannel) key.channel();

							ByteBuffer buffer = ByteBuffer.allocateDirect(128);

							clientChannel.read(buffer);

							System.out.println(new String(Buffers.bytes(buffer)));

							clientChannel.register(selector, SelectionKey.OP_WRITE);

						} else if (key.isWritable()) {

							System.out.println("-----服务端写入成功-----");

							SocketChannel clientChannel = (SocketChannel) key.channel();

							clientChannel.write(ByteBuffer.wrap("pong".getBytes()));

							clientChannel.close();

						}

					} catch (Exception e) {

						e.printStackTrace();

						// 处理异常时忽略改SelectKey
						key.cancel();

					}

				}

			}

		}

	}

	@Test
	public void testClient() throws Exception {

		Selector selector = Selector.open();

		SocketChannel channel = SocketChannel.open();
		channel.configureBlocking(false);
		channel.connect(address);

		channel.register(selector, SelectionKey.OP_CONNECT);

		for (; channel.isOpen() && selector.select() > 0;) {

			System.out.println("***等待服务端响应***");

			Set<SelectionKey> selectionKeys = selector.selectedKeys();

			for (Iterator<SelectionKey> it = selectionKeys.iterator(); it.hasNext(); it.remove()) {

				SelectionKey key = it.next();

				if (key.isValid()) {

					if (key.isConnectable()) {

						System.out.println("-----客户端连接成功-----");

						channel.finishConnect();

						channel.register(selector, SelectionKey.OP_WRITE);

					} else if (key.isWritable()) {

						channel.write(ByteBuffer.wrap("ping".getBytes()));

						System.out.println("-----客户端写入成功-----");

						channel.register(selector, SelectionKey.OP_READ);

					} else if (key.isReadable()) {

						ByteBuffer buffer = ByteBuffer.allocateDirect(128);

						channel.read(buffer);

						System.out.println("-----客户端读取成功-----");

						System.out.println(new String(Buffers.bytes(buffer)));

						channel.close();

					}

				}

			}

		}

	}

}
