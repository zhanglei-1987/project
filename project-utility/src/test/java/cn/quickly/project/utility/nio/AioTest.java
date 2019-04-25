package cn.quickly.project.utility.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;

import org.junit.Test;

import cn.quickly.project.utility.lang.Quiet;

public class AioTest {

	private InetSocketAddress address = new InetSocketAddress("127.0.0.1", 57);

	@Test
	public void testServer() throws Exception {

		CountDownLatch latch = new CountDownLatch(1);

		AsynchronousServerSocketChannel channel = AsynchronousServerSocketChannel.open();
		channel.bind(address);

		channel.accept("pong", new CompletionHandler<AsynchronousSocketChannel, String>() {

			public void completed(final AsynchronousSocketChannel clientChannel, String attachment) {

				System.out.println("-----服务端接受成功-----");

				final ByteBuffer buffer = ByteBuffer.allocateDirect(128);

				try {

					clientChannel.read(buffer, null, new CompletionHandler<Integer, Object>() {

						public void completed(Integer result, Object attachment) {

							System.out.println("-----服务端读取成功-----");

							System.out.println(new String(Buffers.bytes(buffer)));

							clientChannel.write(ByteBuffer.wrap("pong".getBytes()), null, new CompletionHandler<Integer, Object>() {

								public void completed(Integer result, Object attachment) {

									System.out.println("-----服务端写入成功-----");

									Quiet.close(clientChannel);

								}

								public void failed(Throwable e, Object attachment) {

									e.printStackTrace();

								}
							});

						}

						public void failed(Throwable e, Object attachment) {

							e.printStackTrace();

						}
					});

				} catch (Exception e) {

					e.printStackTrace();

				}

			}

			public void failed(Throwable e, String attachment) {

				e.printStackTrace();

			}

		});

		latch.await();

	}

	@Test
	public void testClient() throws Exception {

		final CountDownLatch latch = new CountDownLatch(1);

		final AsynchronousSocketChannel channel = AsynchronousSocketChannel.open();

		channel.connect(address, null, new CompletionHandler<Void, Object>() {

			public void completed(Void result, Object attachment) {

				System.out.println("-----客户端连接成功-----");

				channel.write(ByteBuffer.wrap("ping".getBytes()), null, new CompletionHandler<Integer, Object>() {

					public void completed(Integer result, Object attachment) {

						System.out.println("-----客户端写入成功-----");

						final ByteBuffer buffer = ByteBuffer.allocateDirect(128);

						channel.read(buffer, null, new CompletionHandler<Integer, Object>() {

							public void completed(Integer result, Object attachment) {

								System.out.println("-----客户端读取成功-----");

								System.out.println(new String(Buffers.bytes(buffer)));

								Quiet.close(channel);

								latch.countDown();

							}

							public void failed(Throwable e, Object attachment) {

								e.printStackTrace();

								latch.countDown();

							}

						});

					}

					public void failed(Throwable e, Object attachment) {

						e.printStackTrace();

						latch.countDown();

					}

				});

			}

			public void failed(Throwable e, Object attachment) {

				e.printStackTrace();

				latch.countDown();

			}
		});

		latch.await();

	}

}
