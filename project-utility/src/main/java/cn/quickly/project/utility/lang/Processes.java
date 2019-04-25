package cn.quickly.project.utility.lang;

import java.io.File;
import java.io.IOException;

public class Processes {

	private static final Runtime runtime = Runtime.getRuntime();

	private Processes() {
		throw new UnsupportedOperationException();
	}

	public static Process process(String cmd, String[] envp) {

		try {

			return runtime.exec(cmd, envp);

		} catch (IOException e) {
			throw Exceptions.argument(e);
		}

	}

	public static Process process(String cmd, String[] envp, File dir) {

		try {

			return runtime.exec(cmd, envp, dir);

		} catch (IOException e) {
			throw Exceptions.argument(e);
		}

	}

	public static Process process(String... cmd) {

		try {

			return runtime.exec(cmd);

		} catch (IOException e) {
			throw Exceptions.argument(e);
		}

	}

	public static Process process(String[] cmd, String[] envp) {

		try {

			return runtime.exec(cmd, envp);

		} catch (IOException e) {
			throw Exceptions.argument(e);
		}

	}

	public static Process process(String[] cmd, String[] envp, File dir) {

		try {

			return runtime.exec(cmd, envp, dir);

		} catch (IOException e) {
			throw Exceptions.argument(e);
		}

	}

	public static ProcessBuilder builder(String... cmds) {
		return new ProcessBuilder(cmds);
	}

}
