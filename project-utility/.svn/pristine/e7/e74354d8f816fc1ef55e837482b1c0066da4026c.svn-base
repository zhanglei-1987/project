package cn.quickly.project.utility.compress;

import java.io.File;

import org.junit.Test;

import cn.quickly.project.utility.compress.Gzips;
import cn.quickly.project.utility.lang.Printer;

public class ZipsTest {

	@Test
	public void testZip() throws Exception {

		Zips.zip(new File("d:/mock.zip"), new File("d:/captcha"), "GBK");

	}

	@Test
	public void testUnzip() throws Exception {

		Zips.unzip(new File("d:/mock.zip"), new File("d:/mock2"), "GBK");

		Printer.dump(Zips.unzip(new File("d:/mock.zip"), "新建文件夹/9.png", "GBK"));

		Printer.println(Zips.unzip(new File("d:/mock.zip"), "GBK"));

	}

	@Test
	public void testGzip() {
		Gzips.gzip(new File("d:/mock.gzip"), new File("d:/mock.zip"));
	}

	@Test
	public void testUngzip() {
		Gzips.ungzip(new File("d:/mock.gzip"), new File("d:/mock1.zip"));
	}

}
