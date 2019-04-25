package cn.quickly.project.utility.net;

import java.security.KeyStore;
import java.security.Provider;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;

import cn.quickly.project.utility.lang.Exceptions;
import cn.quickly.project.utility.security.Providers;

public class KeyManagers {

	private KeyManagers() {
		throw new UnsupportedOperationException();
	}

	public static KeyManager[] x509(KeyStore keyStore, String password) {
		String algorithm = "SunX509";
		Provider provider = Providers.detect(Providers.TYPE_KEY_MANAGER_FACTORY, algorithm);
		return load(keyStore, password, algorithm, provider);
	}

	public static KeyManager[] load(KeyStore keyStore, String password, String algorithm, Provider provider) {

		try {

			KeyManagerFactory factory = KeyManagerFactory.getInstance(algorithm, provider);

			factory.init(keyStore, password.toCharArray());

			return factory.getKeyManagers();

		} catch (Exception e) {
			throw Exceptions.argument(e);
		}

	}
}
