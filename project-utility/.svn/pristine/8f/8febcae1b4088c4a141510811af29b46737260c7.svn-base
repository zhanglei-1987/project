package cn.quickly.project.utility.regex;

import java.util.regex.Matcher;

import org.junit.Test;

import cn.quickly.project.utility.lang.Printer;
import cn.quickly.project.utility.lang.Strings;

public class PatternsText {

	@Test
	public void testGetMatcher() {

		String token = "\\$\\{([^:]*?)(\\:([^\\}]*?))?\\}";

		String text = "${name}-${data:yyy-MM-dd}-${p.name}";

		Matcher matcher = Patterns.matcher(text, token);

		while (matcher.find()) {

			Printer.println(Strings.concat(matcher.group(1), "<-->", matcher.group(3)));

		}

	}
}
