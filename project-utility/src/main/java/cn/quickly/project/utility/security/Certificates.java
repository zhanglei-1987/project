package cn.quickly.project.utility.security;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.Provider;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.util.HashSet;
import java.util.Set;

import cn.quickly.project.utility.collection.Collections;
import cn.quickly.project.utility.io.Files;
import cn.quickly.project.utility.io.StreamSeeker;
import cn.quickly.project.utility.lang.Assert;
import cn.quickly.project.utility.lang.Exceptions;
import cn.quickly.project.utility.lang.Quiet;

public final class Certificates {

	public static final String ALGORITHM_X509 = "X.509";

	public static final String ALGORITHM_PGP = "PGP";

	public static final String ALGORITHM_SDSI = "SDSI";

	private Certificates() {
		throw new UnsupportedOperationException();
	}

	public static Certificate one(KeyStore keyStore) {
		return KeyStores.certificate(keyStore);
	}

	public static Certificate[] list(KeyStore keyStore) {
		return KeyStores.certificates(keyStore);
	}

	public static Certificate load(String algorithm, File file) {
		return load(algorithm, file, Providers.detect(Providers.TYPE_CERTIFICATE_FACTORY, algorithm));
	}

	public static Certificate load(String algorithm, File file, Provider provider) {
		try {
			return load(algorithm, new FileInputStream(file), provider);
		} catch (FileNotFoundException e) {
			throw Exceptions.argument(e);
		}
	}

	public static Certificate load(String algorithm, String file) {
		return load(algorithm, StreamSeeker.find(file));
	}

	public static Certificate load(String algorithm, String file, Provider provider) {
		return load(algorithm, StreamSeeker.find(file), provider);
	}

	public static Certificate load(String algorithm, InputStream in) {
		return load(algorithm, in, Providers.detect(Providers.TYPE_CERTIFICATE_FACTORY, algorithm));
	}

	public static Certificate load(String algorithm, InputStream in, Provider provider) {

		try {

			Assert.isNotNull(in, "the Certificate not found.");

			CertificateFactory factory = CertificateFactory.getInstance(algorithm, provider);

			Certificate certificate = factory.generateCertificate(in);

			Quiet.close(in);

			return certificate;

		} catch (Exception e) {
			throw Exceptions.argument(e);
		}

	}

	public static Certificate[] list(String algorithm, File dir) {
		return list(algorithm, dir, Providers.detect(Providers.TYPE_CERTIFICATE_FACTORY, algorithm));
	}

	public static Certificate[] list(String algorithm, File dir, Provider provider) {

		Assert.isTrue(dir.isDirectory(), "the argument file must be directory.");

		Set<Certificate> certificates = new HashSet<Certificate>();

		for (File file : Files.list(dir)) {

			try {
				Certificate certificate = load(algorithm, new FileInputStream(file), provider);
				if (certificate != null) {
					certificates.add(certificate);
				}
			} catch (Exception e) {
			}

		}

		return Collections.toArray(Certificate.class, certificates);
	}

}
