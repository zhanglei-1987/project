package cn.quickly.project.utility.net;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import cn.quickly.project.utility.net.Urls;

public class UrlsTest {

	@Test
	public void testQuery() throws UnsupportedEncodingException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", "zl");
		params.put("age", 26);

		System.out.println(Urls.query("http://baidu.com", params, "utf-8"));
		System.out.println(Urls.query("http://baidu.com?", params, "utf-8"));

		System.out.println(Urls.query("http://baidu.com?age=26&name=zl", "utf-8"));

	}

	@Test
	public void testQueryMap() throws UnsupportedEncodingException {

		String url = "magnet:?xt=urn:btih:9427DB3C1DBA5361DB84A4FEA23F90DA09E0FB26&dn=12%E5%B2%81%E5%BD%9D%E6%97%8F%E5%A5%B3%E7%94%9F.rar&tr=http%3A%2F%2Ftracker.ktxp.com%3A6868%2Fannounce&tr=http%3A%2F%2Ftracker.ktxp.com%3A7070%2Fannounce&tr=udp%3A%2F%2Ftracker.ktxp.com%3A6868%2Fannounce&tr=udp%3A%2F%2Ftracker.ktxp.com%3A7070%2Fannounce&tr=http%3A%2F%2Fbtfans.3322.org%3A8000%2Fannounce&tr=http%3A%2F%2Fbtfans.3322.org%3A8080%2Fannounce&tr=http%3A%2F%2Fbtfans.3322.org%3A6969%2Fannounce&tr=http%3A%2F%2Ftracker.bittorrent.am%2Fannounce&tr=udp%3A%2F%2Ftracker.bitcomet.net%3A8080%2Fannounce&tr=http%3A%2F%2Ftk3.5qzone.net%3A8080%2F&tr=http%3A%2F%2Ftracker.btzero.net%3A8080%2Fannounce&tr=http%3A%2F%2Fscubt.wjl.cn%3A8080%2Fannounce&tr=http%3A%2F%2Fbt.popgo.net%3A7456%2Fannounce&tr=http%3A%2F%2Fthetracker.org%2Fannounce&tr=http%3A%2F%2Ftracker.prq.to%2Fannounce&tr=http%3A%2F%2Ftracker.publicbt.com%2Fannounce&tr=http%3A%2F%2Ftracker.dmhy.org%3A8000%2Fannounce&tr=http%3A%2F%2Fbt.titapark.com%3A2710%2Fannounce&tr=http%3A%2F%2Ftracker.tjgame.enorth.com.cn%3A8000%2Fannounce&";

		System.out.println(Urls.query(url, "UTF-8").get("dn"));

	}

	@Test
	public void testGetMarshaled() {

		System.out.println(Urls.marshaled("https://w/d/../c"));
		System.out.println(Urls.marshaled("http://w/d/./c"));

	}

	@Test
	public void testRewrite() {

		System.out.println("/aaa/bb".replaceAll("/(.*?)/(.*?)", "$1-$2"));

		System.out.println("/xxx/aaa_jsp".replaceAll("(.*?)_(.*?)", "$1.$2"));

		System.out.println("/aaa_jsp".replaceAll("/(.*?)/(.*?)", "$1.$2"));

	}

}
