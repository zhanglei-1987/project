package cn.quickly.project.utility.json;

import cn.quickly.project.utility.lang.Strings;

public final class JSONFormater {

	public static String format(String json) {

		if (Strings.isEmpty(json)) {
			return "";
		}

		char last = '\0', current = '\0';

		StringBuilder builder = new StringBuilder();

		for (int i = 0, indent = 0, inner = 0; i < json.length(); i++) {
			last = current;
			current = json.charAt(i);
			switch (current) {
			case '{':
			case '[':
				if (inner == 0) {
					builder.append(current).append('\n');
					indent++;
					builder.append(Strings.rpad("", '\t', indent));
				} else {
					builder.append(current);
				}
				break;
			case '}':
			case ']':
				if (inner == 0) {
					builder.append('\n');
					indent--;
					builder.append(Strings.rpad("", '\t', indent));
					builder.append(current);
				} else {
					builder.append(current);
				}
				break;
			case ',':
				builder.append(current);
				if (last != '\\') {
					builder.append('\n');
					builder.append(Strings.rpad("", '\t', indent));
				}
				break;
			case '\'':
			case '"':
				if (last != '\\') {
					if (inner > 0) {
						inner--;
					} else {
						inner++;
					}
				}
			default:
				builder.append(current);
			}
		}

		return builder.toString();
	}

}
