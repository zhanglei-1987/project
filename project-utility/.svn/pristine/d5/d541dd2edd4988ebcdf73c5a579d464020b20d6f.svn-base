package cn.quickly.project.utility.security;

import java.security.PrivateKey;
import java.security.PublicKey;

import cn.quickly.project.utility.codec.Hex;

public class RawAsymmetryKey implements PublicKey, PrivateKey {

	private static final long serialVersionUID = 1L;

	private byte[] encoded;

	private String algorithm;

	public RawAsymmetryKey(byte[] encoded, String algorithm) {
		this.encoded = encoded;
		this.algorithm = algorithm;
	}

	@Override
	public String getAlgorithm() {
		return algorithm;
	}

	@Override
	public String getFormat() {
		return "RAW";
	}

	@Override
	public byte[] getEncoded() {
		return encoded;
	}

	@Override
	public String toString() {
		return "RawAsymmetryKey [encoded=" + Hex.upper(encoded) + ", algorithm=" + algorithm + "]";
	}

}
