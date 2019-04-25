package cn.quickly.project.utility.net;

import java.io.Serializable;

import cn.quickly.project.utility.reflect.Compat;

public class HostAndPort implements Serializable {

	private static final long serialVersionUID = 1L;

	private String host;

	private int port = -1;

	public HostAndPort(String server) {

		String[] segments = server.split("\\:");

		this.host = segments[0];

		if (segments.length > 1) {

			this.port = Compat.cast(segments[1], Integer.class);

		}

	}

	public HostAndPort(String host, int port) {
		this.host = host;
		this.port = port;
	}

	public String getHost() {
		return host;
	}

	public int getPort() {
		return port;
	}

}
