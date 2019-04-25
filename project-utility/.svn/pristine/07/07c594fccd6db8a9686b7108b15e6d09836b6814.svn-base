package cn.quickly.project.utility.thirdparty;

import java.io.File;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

import cn.quickly.project.utility.lang.Printer;

import com.jcraft.jsch.ChannelSftp;

public class SftpsTest {

	private Properties setting = new Properties();

	@Before
	public void setup() {

		setting.setProperty("host", "59.151.65.97");
		setting.setProperty("port", "9822");

		setting.setProperty("username", "fftadmin");
		setting.setProperty("password", "123456");

	}

	@Test
	public void testList() throws Exception {

		ChannelSftp sftp = Sftps.channel(setting);

		Printer.println(Sftps.list(sftp, "/Z2002131000014/", true));

		sftp.disconnect();
		sftp.getSession().disconnect();

	}

	@Test
	public void testDownload() {

		Printer.dump(Sftps.download(setting, "/Z2002131000014/20170909/trans/Z2002131000014-20170909.key"));

		Printer.dump(Sftps.download(setting, "/Z2002131000014/20170909/trans/"));

		Sftps.download(setting, "/Z2002131000014/", new File("d:/epcc.zip"));

	}

}
