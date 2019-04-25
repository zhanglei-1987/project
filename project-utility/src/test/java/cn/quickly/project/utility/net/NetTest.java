package cn.quickly.project.utility.net;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.TrustManagerFactory;

import org.junit.Test;

public class NetTest {

	@Test
	public void testManager() throws Exception {

		System.out.println(KeyManagerFactory.getDefaultAlgorithm());
		System.out.println(TrustManagerFactory.getDefaultAlgorithm());

	}

}
