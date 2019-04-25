package cn.quickly.project.utility.net;

import java.util.regex.Pattern;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

public class MatchHostnameVerifier implements HostnameVerifier {

	private Pattern pattern;

	public MatchHostnameVerifier(String pattern) {

		this.pattern = Pattern.compile(pattern);

	}

	public boolean verify(String host, SSLSession session) {

		return pattern.matcher(host).matches();

	}

}
