package cn.quickly.project.utility.net;

import org.junit.Test;

import cn.quickly.project.utility.lang.Printer;

public class InetAddressesTest {

	@Test
	public void testGetInterfaceAddresses() {

		Printer.dump(InetAddresses.getInterfaceAddresses());

	}

}
