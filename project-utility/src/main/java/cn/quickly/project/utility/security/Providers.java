package cn.quickly.project.utility.security;

import java.security.Provider;
import java.security.Security;

import cn.quickly.project.utility.lang.Strings;

public final class Providers {

	public static final String TYPE_KEY_STORE = "KeyStore";

	public static final String TYPE_MESSAGE_DIGEST = "MessageDigest";

	public static final String TYPE_CIPHER = "Cipher";

	public static final String TYPE_SSL_CONTEXT = "SSLContext";

	public static final String TYPE_SIGNATURE = "Signature";

	public static final String TYPE_KEY_GENERATOR = "KeyGenerator";

	public static final String TYPE_KEY_FACTORY = "KeyFactory";

	public static final String TYPE_KEY_PAIR_GENERATOR = "KeyPairGenerator";

	public static final String TYPE_KEY_MANAGER_FACTORY = "KeyManagerFactory";

	public static final String TYPE_TRUST_MANAGER_FACTORY = "TrustManagerFactory";

	public static final String TYPE_CERTIFICATE_FACTORY = "CertificateFactory";

	private Providers() {
		throw new UnsupportedOperationException();
	}

	public static Provider detect(String type, String algorithm) {

		algorithm = Strings.prefix(algorithm, "/");

		for (Provider provider : Security.getProviders()) {
			if (provider.getService(type, algorithm) != null) {
				return provider;
			}
		}

		return null;

	}
}
