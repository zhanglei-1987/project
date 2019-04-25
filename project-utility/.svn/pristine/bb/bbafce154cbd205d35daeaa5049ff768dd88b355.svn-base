package cn.quickly.project.utility.nio;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

import cn.quickly.project.utility.lang.Exceptions;

public class NioHalfDuplexServer {

	private SocketAddress address;

	private HalfDuplexHandler handler;

	private int bufferSize = 1024;

	private AtomicBoolean runnable = new AtomicBoolean(true);

	public void setAddress(int port) {
		setAddress("0.0.0.0", port);
	}

	public void setAddress(String host, int port) {
		setAddress(new InetSocketAddress(host, port));
	}

	public void setAddress(SocketAddress address) {
		this.address = address;
	}

	public void setHandler(HalfDuplexHandler handler) {
		this.handler = handler;
	}

	public void setBufferSize(int bufferSize) {
		this.bufferSize = bufferSize;
	}

	public void setRunnable(AtomicBoolean runnable) {
		this.runnable = runnable;
	}

	public void startup() {

		try {

			Selector selector = Selector.open();

			// 1.创建服务端监听
			ServerSocketChannel serverChannel = ServerSocketChannel.open();
			serverChannel.bind(address);// 绑定地址端口
			serverChannel.configureBlocking(false);// 非阻塞模式

			// 2.注册SelectionKey.OP_ACCEPT，其它SelectionKey不可用
			serverChannel.register(selector, SelectionKey.OP_ACCEPT);

			// 3.轮询Selector，等待客户端连接
			for (; runnable.get();) {

				selector.select();// 阻塞直到有需要处理的SelectKey

				Set<SelectionKey> selectionKeys = selector.selectedKeys();

				for (Iterator<SelectionKey> it = selectionKeys.iterator(); it.hasNext(); it.remove()) {

					SelectionKey key = it.next();

					if (key.isValid()) {

						try {

							if (key.isAcceptable()) {

								SocketChannel clientChannel = serverChannel.accept();
								clientChannel.configureBlocking(false);
								clientChannel.register(selector, SelectionKey.OP_READ);

							} else if (key.isReadable()) {

								SocketChannel clientChannel = (SocketChannel) key.channel();

								ByteBuffer buffer = ByteBuffer.allocateDirect(bufferSize);

								clientChannel.read(buffer);

								clientChannel.register(selector, SelectionKey.OP_WRITE, Buffers.bytes(buffer));

							} else if (key.isWritable()) {

								SocketChannel clientChannel = (SocketChannel) key.channel();

								byte[] data = (byte[]) key.attachment();

								clientChannel.write(ByteBuffer.wrap(handler.execute(data)));

								clientChannel.close();

							}

						} catch (Exception e) {

							key.cancel();

						}

					}

				}

			}

		} catch (Exception e) {

			throw Exceptions.argument(e);

		}

	}

	public void shutdown() {

		runnable.set(false);

	}

}
