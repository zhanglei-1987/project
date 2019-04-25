package cn.quickly.project.utility.security;

import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.Certificate;

public final class Signatures {

	public static final String ALGORITHM_MD5_WITH_RSA = "MD5withRSA";

	public static final String ALGORITHM_SHA1_WITH_RSA = "SHA1withRSA";

	public static final String ALGORITHM_SHA256_WITH_RSA = "SHA256withRSA";

	public static final String ALGORITHM_SHA384_WITH_RSA = "SHA384withRSA";

	public static final String ALGORITHM_SHA512_WITH_RSA = "SHA512withRSA";

	private Signatures() {
		throw new UnsupportedOperationException();
	}

	public static boolean verify(String algorithm, byte[] data, byte[] sign, Certificate certificate) throws Exception {

		return verify(algorithm, data, sign, certificate, Providers.detect(Providers.TYPE_SIGNATURE, algorithm));

	}

	public static boolean verify(String algorithm, byte[] data, byte[] sign, Certificate certificate, Provider provider) throws Exception {

		Signature signature = Signature.getInstance(algorithm, provider);
		signature.initVerify(certificate);
		signature.update(data);
		return signature.verify(sign);

	}

	public static boolean verify(String algorithm, byte[] data, byte[] sign, PublicKey publicKey) throws Exception {

		return verify(algorithm, data, sign, publicKey, Providers.detect(Providers.TYPE_SIGNATURE, algorithm));

	}

	public static boolean verify(String algorithm, byte[] data, byte[] sign, PublicKey publicKey, Provider provider) throws Exception {

		Signature signature = Signature.getInstance(algorithm, provider);
		signature.initVerify(publicKey);
		signature.update(data);
		return signature.verify(sign);

	}

	public static byte[] sign(String algorithm, byte[] data, PrivateKey privateKey) throws Exception {

		return sign(algorithm, data, privateKey, Providers.detect(Providers.TYPE_SIGNATURE, algorithm));

	}

	public static byte[] sign(String algorithm, byte[] data, PrivateKey privateKey, Provider provider) throws Exception {

		Signature signature = Signature.getInstance(algorithm, provider);
		signature.initSign(privateKey);
		signature.update(data);
		return signature.sign();

	}

}
