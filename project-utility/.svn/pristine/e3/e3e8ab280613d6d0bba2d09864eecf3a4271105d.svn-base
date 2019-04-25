package cn.quickly.project.utility.thirdparty;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import cn.quickly.project.utility.compress.Zips;
import cn.quickly.project.utility.io.Streams;
import cn.quickly.project.utility.lang.Enums;
import cn.quickly.project.utility.lang.Exceptions;
import cn.quickly.project.utility.lang.Quiet;
import cn.quickly.project.utility.lang.Strings;

/**
 * Ftp上传与下载
 * 
 * 配置
 * 
 * host ： 地址
 * 
 * port ：端口号，默认22
 * 
 * username：用户名
 * 
 * password：密码
 * 
 * encoding：ZIP文件名编码
 * 
 * remoteUri：服务端资源路径
 * 
 * 
 */
public final class Ftps {

	public static FTPClient client(Properties setting) {

		String host = setting.getProperty("host");

		int port = Integer.parseInt(setting.getProperty("port", "21"));

		String username = setting.getProperty("username");

		String password = setting.getProperty("password");

		String proxyType = setting.getProperty("proxy.type");

		String proxyHost = setting.getProperty("proxy.host");

		int proxyPort = Integer.parseInt(setting.getProperty("proxy.port", "-1"));

		// String proxyUsername = setting.getProperty("proxy.username");

		// String proxyPassword = setting.getProperty("proxy.password");

		String encoding = setting.getProperty("encoding", "UTF-8");

		try {

			FTPClient client = new FTPClient();
			client.connect(host, port);

			if (!Strings.isEmpty(proxyType)) {

				client.setProxy(new Proxy(Enums.valueOf(Type.class, proxyType), new InetSocketAddress(proxyHost, proxyPort)));

			}

			client.setControlEncoding(encoding);

			if (!client.login(username, password)) {

				client.disconnect();

				throw Exceptions.argument("FTP登录失败");

			}

			return client;

		} catch (Exception e) {

			throw Exceptions.argument(e);

		}

	}

	public static Set<String> list(FTPClient ftp, String remoteUri, boolean recursion) {

		Set<String> paths = new LinkedHashSet<String>();

		try {

			for (FTPFile file : ftp.listFiles(remoteUri)) {

				String fileName = file.getName();

				if (".".equals(fileName) || "..".equals(fileName)) {
					continue;
				}

				String path = Strings.concat(remoteUri, "/", fileName).replace("//", "/");

				if (file.isDirectory()) {

					paths.add(Strings.concat(path, "/"));

					paths.addAll(list(ftp, path, recursion));

				} else {
					paths.add(path);
				}

			}

		} catch (Exception e) {
			throw Exceptions.argument(e);
		}

		return paths;
	}

	public static void upload(Properties setting, InputStream in, String remoteUri) {

		FTPClient ftp = client(setting);

		try {

			OutputStream out = ftp.appendFileStream(remoteUri);

			Streams.copy(in, out);

			Quiet.close(out);
			Quiet.close(in);

			ftp.disconnect();

		} catch (Exception e) {
			throw Exceptions.argument(e);
		}

	}

	public static void upload(Properties setting, byte[] data, String remoteUri) {
		upload(setting, Streams.input(data), remoteUri);
	}

	public static void upload(Properties setting, File file, String remoteUri) {
		upload(setting, Streams.input(file), remoteUri);
	}

	public static byte[] download(Properties setting, String remoteUri) {
		ByteArrayOutputStream out = Streams.output();
		download(setting, remoteUri, out);
		return out.toByteArray();
	}

	public static void download(Properties setting, String remoteUri, File file) {
		download(setting, remoteUri, Streams.output(file));
	}

	public static void download(Properties setting, String remoteUri, OutputStream out) {

		FTPClient ftp = client(setting);

		try {

			if (remoteUri.endsWith("/")) {

				Map<String, InputStream> files = new LinkedHashMap<String, InputStream>();

				for (String path : list(ftp, remoteUri, true)) {

					if (!path.endsWith("/")) {
						files.put(Strings.suffix(path, remoteUri), ftp.retrieveFileStream(path));
					}

				}

				Zips.zip(out, files, setting.getProperty("encoding", "GBK"));

			} else {

				Streams.copy(ftp.retrieveFileStream(remoteUri), out, true);

			}

			ftp.disconnect();

		} catch (Exception e) {
			throw Exceptions.argument(e);
		} finally {
			Quiet.close(out);
		}

	}
}
