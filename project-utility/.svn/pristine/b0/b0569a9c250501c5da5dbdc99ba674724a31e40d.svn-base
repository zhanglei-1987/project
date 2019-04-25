package cn.quickly.project.utility.thirdparty;

import java.io.File;
import java.util.Properties;

import org.apache.commons.net.ftp.FTPClient;
import org.junit.Before;
import org.junit.Test;

import cn.quickly.project.utility.lang.Printer;

public class FtpTest {

	private Properties setting = new Properties();

	@Before
	public void setup() {

		setting.setProperty("host", "192.168.89.222");
		setting.setProperty("port", "21");

		setting.setProperty("username", "root");
		setting.setProperty("password", "fft#2011");

	}

	@Test
	public void testList() throws Exception {

		FTPClient ftp = Ftps.client(setting);

		Printer.println(Ftps.list(ftp, "/data1/ftp/", true));

		ftp.disconnect();

	}

	@Test
	public void testDownload() {

		Printer.dump(Ftps.download(setting, "/data1/ftp/chinaPay/"));

		Ftps.download(setting, "/data1/ftp/chinaPay/", new File("d:/ftp.zip"));

	}

}
