package cn.quickly.project.utility.net;

import java.security.KeyStore;
import java.security.Provider;
import java.security.SecureRandom;

import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import cn.quickly.project.utility.collection.Arrays;
import cn.quickly.project.utility.lang.Exceptions;
import cn.quickly.project.utility.security.Providers;

public final class SocketFactorys {

	public static final String DEFAULT_ALGORITHM = "TLS";

	private SocketFactorys() {
		throw new UnsupportedOperationException();
	}

	public static SSLSocketFactory trust() {
		return trust(Providers.detect(Providers.TYPE_SSL_CONTEXT, DEFAULT_ALGORITHM));
	}

	public static SSLSocketFactory trust(Provider provider) {

		TrustAllX509TrustManager manager = new TrustAllX509TrustManager();

		return get(DEFAULT_ALGORITHM, null, Arrays.as(manager), new SecureRandom(), provider);
	}

	public static SSLSocketFactory trust(KeyStore keyStore) {
		return get(null, TrustManagers.pkix(keyStore));
	}

	public static SSLSocketFactory trust(KeyStore keyStore, Provider provider) {
		return get(null, TrustManagers.pkix(keyStore), provider);
	}

	public static SSLSocketFactory key(KeyStore keyStore, String password) {
		return get(KeyManagers.x509(keyStore, password), null);
	}

	public static SSLSocketFactory key(KeyStore keyStore, String password, Provider provider) {
		return get(KeyManagers.x509(keyStore, password), null, provider);
	}

	public static SSLSocketFactory get(KeyStore keyStore, String password) {
		return get(KeyManagers.x509(keyStore, password), TrustManagers.pkix(keyStore));
	}

	public static SSLSocketFactory get(KeyStore keyStore, String password, Provider provider) {
		return get(KeyManagers.x509(keyStore, password), TrustManagers.pkix(keyStore), provider);
	}

	public static SSLSocketFactory get(KeyManager[] keyManagers, TrustManager[] trustManagers) {
		return get(DEFAULT_ALGORITHM, keyManagers, trustManagers, new SecureRandom());
	}

	public static SSLSocketFactory get(KeyManager[] keyManagers, TrustManager[] trustManagers, Provider provider) {
		return get(DEFAULT_ALGORITHM, keyManagers, trustManagers, new SecureRandom(), provider);
	}

	public static SSLSocketFactory get(String algorithm, KeyManager[] keyManagers, TrustManager[] trustManagers) {
		return get(algorithm, keyManagers, trustManagers, new SecureRandom());
	}

	public static SSLSocketFactory get(String algorithm, KeyManager[] keyManagers, TrustManager[] trustManagers, Provider provider) {
		return get(algorithm, keyManagers, trustManagers, new SecureRandom(), provider);
	}

	public static SSLSocketFactory get(String algorithm, KeyManager[] keyManagers, TrustManager[] trustManagers, SecureRandom random) {
		return get(algorithm, keyManagers, trustManagers, random, Providers.detect(Providers.TYPE_SSL_CONTEXT, algorithm));
	}

	public static SSLSocketFactory get(String algorithm, KeyManager[] keyManagers, TrustManager[] trustManagers, SecureRandom random, Provider provider) {
		try {

			SSLContext context = SSLContext.getInstance(algorithm, provider);
			context.init(keyManagers, trustManagers, random);
			return context.getSocketFactory();

		} catch (Exception e) {
			throw Exceptions.argument(e);
		}

	}

}
