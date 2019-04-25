package cn.quickly.project.utility.net;

import javax.net.ssl.HostnameVerifier;

public final class HostnameVerifiers {

	private HostnameVerifiers() {
		throw new UnsupportedOperationException();
	}

	public static HostnameVerifier trust() {
		return new TrustAllHostnameVerifier();
	}

	public static HostnameVerifier match(String pattern) {
		return new MatchHostnameVerifier(pattern);
	}
}
