package cn.quickly.project.utility.thirdparty;

import java.util.Properties;

import cn.quickly.project.utility.lang.Exceptions;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.ProxyHTTP;
import com.jcraft.jsch.ProxySOCKS4;
import com.jcraft.jsch.ProxySOCKS5;
import com.jcraft.jsch.Session;

public final class JSchs {

	static {
		JSch.setConfig("StrictHostKeyChecking", "no");
	}

	public static Session session(Properties setting) {

		String host = setting.getProperty("host");

		int port = Integer.parseInt(setting.getProperty("port", "22"));

		String username = setting.getProperty("username");

		String password = setting.getProperty("password");

		String proxyType = setting.getProperty("proxy.type");

		String proxyHost = setting.getProperty("proxy.host");

		int proxyPort = Integer.parseInt(setting.getProperty("proxy.port", "-1"));

		String proxyUsername = setting.getProperty("proxy.username");

		String proxyPassword = setting.getProperty("proxy.password");

		try {

			JSch jsch = new JSch();

			Session session = jsch.getSession(username, host);
			session.setPort(port);
			session.setPassword(password);

			if ("HTTP".equalsIgnoreCase(proxyType)) {

				ProxyHTTP httpProxy = new ProxyHTTP(proxyHost, proxyPort);
				httpProxy.setUserPasswd(proxyUsername, proxyPassword);
				session.setProxy(httpProxy);

			} else if ("SOCKS4".equalsIgnoreCase(proxyType)) {

				ProxySOCKS4 socks4Proxy = new ProxySOCKS4(proxyHost, proxyPort);
				socks4Proxy.setUserPasswd(proxyUsername, proxyPassword);
				session.setProxy(socks4Proxy);

			} else if ("SOCKS5".equalsIgnoreCase(proxyType)) {

				ProxySOCKS5 socks5Proxy = new ProxySOCKS5(proxyHost, proxyPort);
				socks5Proxy.setUserPasswd(proxyUsername, proxyPassword);
				session.setProxy(socks5Proxy);

			}

			session.connect();

			return session;

		} catch (Exception e) {
			throw Exceptions.argument(e);
		}

	}

	public static Channel channel(Properties setting, String type) {

		Session session = session(setting);

		try {

			Channel channel = session.openChannel(type);
			channel.connect();

			return channel;

		} catch (Exception e) {
			throw Exceptions.argument(e);
		}

	}

}
