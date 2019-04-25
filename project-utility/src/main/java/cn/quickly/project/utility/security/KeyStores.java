package cn.quickly.project.utility.security;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.Provider;
import java.security.cert.Certificate;
import java.util.HashSet;
import java.util.Set;

import cn.quickly.project.utility.collection.Collections;
import cn.quickly.project.utility.collection.Enumerations;
import cn.quickly.project.utility.io.StreamSeeker;
import cn.quickly.project.utility.lang.Assert;
import cn.quickly.project.utility.lang.Exceptions;
import cn.quickly.project.utility.lang.Quiet;

public final class KeyStores {

	private KeyStores() {
		throw new UnsupportedOperationException();
	}

	public static KeyStore load(File keyStoreFile, String password) {
		return load("JKS", keyStoreFile, password);
	}

	public static KeyStore load(String algorithm, File keyStoreFile, String password) {

		try {
			return load(algorithm, new FileInputStream(keyStoreFile), password);
		} catch (FileNotFoundException e) {
			throw Exceptions.argument(e);
		}

	}

	public static KeyStore load(String algorithm, String keyStoreFile, String password) {

		return load(algorithm, StreamSeeker.find(keyStoreFile), password);

	}

	public static KeyStore load(String algorithm, String keyStoreFile, String password, Provider provider) {

		return load(algorithm, StreamSeeker.find(keyStoreFile), password, provider);

	}

	public static KeyStore load(String algorithm, InputStream in, String password) {

		return load(algorithm, in, password, Providers.detect(Providers.TYPE_KEY_STORE, algorithm));

	}

	public static KeyStore load(String algorithm, InputStream in, String password, Provider provider) {

		Assert.isNotNull(in, "the KeyStore not found.");

		try {

			KeyStore keyStore = KeyStore.getInstance(algorithm, provider);

			keyStore.load(in, password.toCharArray());

			Quiet.close(in);

			return keyStore;

		} catch (Exception e) {

			throw Exceptions.argument(e);

		}
	}

	public static Key key(KeyStore keyStore, String password) {

		try {

			for (String alias : Enumerations.asSet(keyStore.aliases())) {

				if (keyStore.isKeyEntry(alias)) {
					return keyStore.getKey(alias, password.toCharArray());
				}

			}

			return null;

		} catch (Exception e) {

			throw Exceptions.argument(e);

		}

	}

	public static Certificate certificate(KeyStore keyStore) {

		try {

			for (String alias : Enumerations.asSet(keyStore.aliases())) {

				if (keyStore.isCertificateEntry(alias)) {
					return keyStore.getCertificate(alias);
				}

			}

			return null;

		} catch (Exception e) {

			throw Exceptions.argument(e);

		}

	}

	public static Certificate[] certificates(KeyStore keyStore) {

		try {

			Set<Certificate> certificates = new HashSet<Certificate>();

			for (String alias : Enumerations.asSet(keyStore.aliases())) {

				if (keyStore.isCertificateEntry(alias)) {
					certificates.add(keyStore.getCertificate(alias));
				}

			}

			return Collections.toArray(Certificate.class, certificates);

		} catch (Exception e) {

			throw Exceptions.argument(e);

		}

	}
}
