package cn.quickly.project.utility.lang;

import java.io.Closeable;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import cn.quickly.project.utility.function.NoneAction;
import cn.quickly.project.utility.function.NoneSupplier;
import cn.quickly.project.utility.function.UnaryAction;
import cn.quickly.project.utility.function.UnarySupplier;
import cn.quickly.project.utility.map.UserSession;
import cn.quickly.project.utility.reflect.AnnotationInjector;
import cn.quickly.project.utility.reflect.BeanInjector;
import cn.quickly.project.utility.reflect.FieldInjector;
import cn.quickly.project.utility.text.FormatFactory;
import cn.quickly.project.utility.time.DateTimeFormatters;
import cn.quickly.project.utility.xml.Jaxbs;
import cn.quickly.project.utility.xml.Saxs;

public final class Quiet {

	private Quiet() {
		throw new UnsupportedOperationException();
	}

	public static void close(Closeable closeable) {

		if (closeable != null) {
			try {
				closeable.close();
			} catch (Throwable e) {
			}
		}

	}

	public static void close(ResultSet rs) {

		if (rs != null) {
			try {
				rs.close();
			} catch (Throwable e) {
			}
		}

	}

	public static void close(ResultSet rs, boolean recursion) {

		if (rs != null) {

			close(rs);

			try {

				close(rs.getStatement());

			} catch (Throwable e) {
			}

		}

	}

	public static void close(Statement stmt) {

		if (stmt != null) {
			try {
				stmt.close();
			} catch (Throwable e) {
			}
		}

	}

	public static void close(Connection connection) {

		if (connection != null) {
			try {
				connection.close();
			} catch (Throwable e) {
			}
		}

	}

	public static void close(Socket socket) {

		if (socket != null) {
			try {
				socket.close();
			} catch (Throwable e) {
			}
		}

	}

	public static void clear() {

		FormatFactory.getFactory().clear();

		BeanInjector.clear();

		FieldInjector.clear();

		AnnotationInjector.clear();

		UserSession.clear();

		Enums.clear();

		Jaxbs.clear();

		Saxs.clear();

		DateTimeFormatters.clear();

	}

	public static void await(long sleep) {
		try {
			Thread.sleep(sleep);
		} catch (InterruptedException e) {
		}
	}

	public static <T> void tryCatch(T source, UnaryAction<T> actor) {

		try {

			actor.execute(source);

		} catch (Exception e) {
		}

	}

	public static void tryCatch(NoneAction action) {

		try {

			action.execute();

		} catch (Exception e) {
		}

	}

	public static <T, V> V tryCatch(T source, UnarySupplier<T, V> supplier) {

		try {

			return supplier.get(source);

		} catch (Exception e) {
		}

		return null;

	}

	public static <V> V tryCatch(NoneSupplier<V> supplier) {

		try {

			return supplier.get();

		} catch (Exception e) {
		}

		return null;

	}

	public static void retry(int count, NoneAction action) {

		for (int i = 0; i < count; i++) {

			try {

				action.execute();

				break;

			} catch (Exception e) {
			}

		}

	}

	public static <T> void retry(int count, T source, UnaryAction<T> action) {

		for (int i = 0; i < count; i++) {

			try {

				action.execute(source);

				break;

			} catch (Exception e) {
			}

		}

	}

	public static <V> V retry(int count, NoneSupplier<V> supplier) {

		for (int i = 0; i < count; i++) {

			try {

				return supplier.get();

			} catch (Exception e) {

			}

		}

		return null;

	}

	public static <T, V> V retry(int count, T source, UnarySupplier<T, V> supplier) {

		for (int i = 0; i < count; i++) {

			try {

				return supplier.get(source);

			} catch (Exception e) {

			}

		}

		return null;

	}

}
