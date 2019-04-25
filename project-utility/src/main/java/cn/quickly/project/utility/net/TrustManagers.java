package cn.quickly.project.utility.net;

import java.security.KeyStore;
import java.security.Provider;

import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

import cn.quickly.project.utility.lang.Exceptions;
import cn.quickly.project.utility.security.Providers;

public final class TrustManagers {

	private TrustManagers() {
		throw new UnsupportedOperationException();
	}

	public static TrustManager[] pkix(KeyStore keyStore) {
		String algorithm = "PKIX";
		Provider provider = Providers.detect(Providers.TYPE_TRUST_MANAGER_FACTORY, algorithm);
		return load(keyStore, algorithm, provider);
	}

	public static TrustManager[] load(KeyStore keyStore, String algorithm, Provider provider) {

		try {

			TrustManagerFactory factory = TrustManagerFactory.getInstance(algorithm, provider);

			factory.init(keyStore);

			return factory.getTrustManagers();

		} catch (Exception e) {
			throw Exceptions.argument(e);
		}

	}

}
