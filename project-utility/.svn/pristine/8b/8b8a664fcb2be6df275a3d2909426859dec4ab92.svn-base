package cn.quickly.project.utility.security;

import java.io.File;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

public final class X509Certificates {

	private X509Certificates() {
		throw new UnsupportedOperationException();
	}

	public static Map<String, X509Certificate> map(KeyStore keyStore) {

		return map(Certificates.list(keyStore));

	}

	public static Map<String, X509Certificate> map(File dir) {

		return map(Certificates.list(Certificates.ALGORITHM_X509, dir));

	}

	public static Map<String, X509Certificate> map(Certificate[] certificates) {

		Map<String, X509Certificate> x509Certificates = new HashMap<String, X509Certificate>();

		for (Certificate certificate : certificates) {

			if (certificate instanceof X509Certificate) {

				X509Certificate x509Certificate = (X509Certificate) certificate;

				x509Certificates.put(x509Certificate.getSerialNumber() + "", x509Certificate);

			}

		}

		return x509Certificates;

	}

}
