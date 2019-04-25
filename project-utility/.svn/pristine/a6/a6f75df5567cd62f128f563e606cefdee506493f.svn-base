package cn.quickly.project.utility.lang;

import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

import javax.mail.internet.MimeUtility;

import org.junit.Test;

import cn.quickly.project.utility.map.Maps;
import cn.quickly.project.utility.map.SupplierHashMap;

public class StringsTest {

	@Test
	public void testEscape() {
		System.out.println(Strings.escape("api.shtml?eli=2012030&sli=2012000成功"));
	}

	@Test
	public void testJoin() {
		String[] datas = { "fafds", "12312", "aaaa" };
		System.out.println(Strings.join(datas, "/"));
	}

	@Test
	public void testCount() {
		String[] datas = { "fafds", "12312", "aaaa" };
		System.out.println(Strings.join(datas, "---"));
		System.out.println(Strings.count("1da1111人发生地方撒发生飞洒sd111ads1111", '发'));

		DecimalFormat format = new DecimalFormat("0.00");
		System.out.println(format.format(0.1261445295));
	}

	@Test
	public void testJpan() throws Exception {
		String s = "123ABCabc全角漢字、かな、カナ";
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			if (ch >= '0' && ch <= '9') {
				System.out.println(ch + "\t是数字");
			} else if (ch >= 'a' && ch <= 'z' || ch >= 'A' && ch <= 'Z') {
				System.out.println(ch + "\t是英文字母");
			} else if (ch >= '\u3041' && ch <= '\u30fe') // 日文的Unicode范围
			{
				System.out.println(ch + "\t是日文");
			} else if (ch >= '\u4E00' && ch <= '\u9FA5') // 中文的Unicode范围
			{
				System.out.println(ch + "\t是汉字");
			} else {
				System.out.println(ch + "\t是其它字符");
			}
		}
	}

	@Test
	public void testEcode() throws Exception {
		String fileName = "我是自作主张";
		String encoding = "UTF-8";
		System.out.println(MimeUtility.encodeWord(fileName, encoding, "Q"));
		System.out.println(URLEncoder.encode(fileName, encoding));
	}

	@Test
	public void testLpad() {
		System.out.println(Strings.lpad("---", '0', 2));
		System.out.println(Strings.lpad("---", '0', 6));
	}

	@Test
	public void testRpad() {
		System.out.println(Strings.rpad("---", '0', 6));
	}

	@Test
	public void testTokened() {

		Properties properties = new Properties();
		properties.setProperty("name", "zhanglei");
		properties.put("test", "-2--2");
		properties.put("date", new Date());

		String text = "\"'${name}'\"| ${date:yyyy-MM-dd HH:mm:ss} ";

		System.out.println(Strings.tokened(text, properties));
	}

	@Test
	public void testTokenedSupplier() {

		Map<String, Object> data = new SupplierHashMap<>((key) -> {

			if (key.startsWith("now")) {

				Calendar calendar = Calendar.getInstance();

				if (key.endsWith("d")) {

					calendar.add(Calendar.DAY_OF_MONTH, Integer.parseInt(key.replaceAll("now|d", "")));

				}

				return calendar.getTime();

			}

			return null;

		}, false);

		System.out.println(Strings.tokened("name-${now-90d:yyyy-MM-dd}", data));
		System.out.println(Strings.tokened("name-${now:yyyy-MM-dd}", data));

		System.out.println(data);

	}

	@Test
	public void testSubstring() {

		Printer.println(Strings.substring("1234567", 0, -2));

		Printer.println(Strings.substring("1234567", 2, -2));

		Printer.println(Strings.substring("1234567", "2", "5"));

		Printer.println(Strings.substring("1234567", "7", "5"));

	}

	@Test
	public void testSuffix() {

		Printer.println(Strings.suffix("1234567", "34"));

	}

	@Test
	public void testPrefix() {

		Printer.println(Strings.prefix("1234567", "34"));

	}

	@Test
	public void testSwap() {

		System.out.println(Strings.swap("123"));
		System.out.println(Strings.swap("213"));

		System.out.println(Strings.swap("1234"));
		System.out.println(Strings.swap("2143"));

	}

	@Test
	public void testFormat() {

		System.out.println(Strings.format("{name}", Maps.asMap("name", "123")));

	}

}
