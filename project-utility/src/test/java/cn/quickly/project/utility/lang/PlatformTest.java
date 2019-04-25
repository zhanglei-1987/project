package cn.quickly.project.utility.lang;

import org.junit.Test;

public class PlatformTest {

	@Test
	public void testIsWindows() {

		System.out.println(Platform.isWindows());

		System.out.println(Platform.isWindowsCE());

	}

	@Test
	public void testIsX11() {

		System.out.println(Platform.isX11());

	}

	@Test
	public void testIs64Bit() {

		System.out.println(Platform.is64Bit());

	}

	@Test
	public void testGetCpuCount() {

		System.out.println(Platform.getCpuCount());

	}

	@Test
	public void testGetMaxMemory() {

		System.out.println(Platform.getMaxMemory());

	}

	@Test
	public void testGetInetAddresses() {

		Printer.println(Platform.getInetAddresses());

	}

	@Test
	public void testGetMacAddresses() {
		Printer.println(Platform.getMacAddresses());
	}

}
