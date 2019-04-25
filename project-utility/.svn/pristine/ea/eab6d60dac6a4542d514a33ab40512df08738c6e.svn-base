package cn.quickly.project.utility.lang;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Consumer;
import java.util.regex.Matcher;

import cn.quickly.project.utility.codec.Hex;
import cn.quickly.project.utility.io.Streams;
import cn.quickly.project.utility.json.JSONFormater;
import cn.quickly.project.utility.json.JSONSerializer;
import cn.quickly.project.utility.math.Maths;
import cn.quickly.project.utility.reflect.Compat;
import cn.quickly.project.utility.regex.Patterns;
import cn.quickly.project.utility.time.Clock;

public final class Printer {

	private Printer() {
		throw new UnsupportedOperationException();
	}

	public static void match(String text, String regex) {

		Matcher matcher = Patterns.matcher(text, regex);

		while (matcher.find()) {

			System.out.print("[ ");

			for (int i = 1, group = matcher.groupCount(); i <= group; i++) {

				System.out.print(matcher.group(i));

				if (i < group) {
					System.out.print(", ");
				}

			}

			System.out.println("]");
		}
	}

	public static void println(Object object) {

		if (Objects.isBasic(object)) {
			System.out.println(object);
		} else {
			dump(object);
		}

	}

	public static void dump(Object object) {

		if (object != null) {

			System.out.println(JSONFormater.format(JSONSerializer.stringify(object)));

		} else {

			System.out.println("[ the argument is null ]");

		}

	}

	public static void dump(InputStream input) {

		if (input != null) {

			try {

				int offset = 0, size = 2048;

				for (;;) {

					byte[] buffer = Streams.getBytes(input, size);

					if (buffer.length == 0) {
						break;
					}

					System.out.println(Hex.dump(buffer, offset));

					if (buffer.length < size) {
						break;
					}

					offset += size;

				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("[ the print input-stream object is null ]");
		}

	}

	public static void dump(byte[] input) {
		if (input != null) {
			System.out.println(Hex.dump(input));
		} else {
			System.out.println("[ the print byte array is null ]");
		}
	}

	public static void dump(Connection conn) {
		try {

			DatabaseMetaData dmd = conn.getMetaData();
			System.out.print("驱动器:");
			System.out.println(dmd.getDriverName());
			System.out.print("版本:");
			System.out.println(dmd.getDriverVersion());
			System.out.print("URL:");
			System.out.println(dmd.getURL());
			System.out.print("用户名:");
			System.out.println(dmd.getUserName());
			System.out.print("系统内置函数:");
			System.out.print(dmd.getNumericFunctions() + ',');
			System.out.print(dmd.getStringFunctions() + ',');
			System.out.print(dmd.getTimeDateFunctions() + ',');
			System.out.println(dmd.getSystemFunctions());
			System.out.print("支持的数据类型:");
			ResultSet rs = dmd.getTypeInfo();
			while (rs.next()) {
				System.out.print(rs.getString("TYPE_NAME") + ",");
			}
			System.out.println();

			Quiet.close(rs);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void dump(ResultSet rs) {
		try {
			ResultSetMetaData rsmd = rs.getMetaData();
			int len = rsmd.getColumnCount();
			System.out.print("Catalog:");
			for (int i = 1; i <= len; i++) {
				System.out.print(rsmd.getCatalogName(i) + '\t');
			}
			System.out.println();
			System.out.print("Name   :");
			for (int i = 1; i <= len; i++) {
				System.out.print(rsmd.getColumnName(i) + '\t');
			}
			System.out.println();
			System.out.print("Label  :");
			for (int i = 1; i <= len; i++) {
				System.out.print(rsmd.getColumnLabel(i) + '\t');
			}
			System.out.println();
			System.out.print("Class  :");
			for (int i = 1; i <= len; i++) {
				System.out.print(rsmd.getColumnClassName(i) + '\t');
			}
			System.out.println();
			System.out.print("Datas  :");
			while (rs.next()) {
				for (int i = 1; i <= len; i++) {
					System.out.print(rs.getObject(i) + "\t");
				}
				System.out.println();
				System.out.print("        ");
			}
			System.out.println();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@SafeVarargs
	public static void compete(int[] counts, Consumer<Integer>... runnables) {
		compete(counts, Arrays.asList(runnables));
	}

	public static void compete(int[] counts, Collection<Consumer<Integer>> runnables) {

		int countWidth = ((int) Maths.max(Compat.cast(counts, double[].class)) + "").length();

		int dataWidth = Math.max(10, countWidth);

		for (int count : counts) {

			System.out.print(Strings.rpad(count, ' ', countWidth) + "   :   ");

			Iterables.forEach(runnables, (j, runnable) -> {

				if (runnable == null) {

					System.out.print(Strings.rpad("-1", ' ', dataWidth) + "\t");

				} else {

					long start = Clock.now();

					Loops.loop(1, count, (i) -> {

						runnable.accept(i);

					});

					System.out.print(Strings.rpad(Clock.now() - start, ' ', dataWidth));
				}

			});

			System.out.println();

		}

	}

}
