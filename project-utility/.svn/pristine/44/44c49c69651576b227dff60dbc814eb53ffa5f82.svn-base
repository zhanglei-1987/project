package cn.quickly.project.utility.lang;

import java.io.File;
import java.io.IOException;

import cn.quickly.project.utility.io.Streams;

public final class Shell {

	private Shell() {
		throw new UnsupportedOperationException();
	}

	public static String execute(String cmd, String encoding) {

		return execute(cmd.split("\\s+"), encoding);

	}

	public static String execute(String[] cmd, String encoding) {

		return text(Processes.process(cmd), encoding);

	}

	public static String execute(String cmd, String[] envp, String encoding) {

		return text(Processes.process(cmd, envp), encoding);

	}

	public static String execute(String[] cmd, String[] envp, String encoding) {

		return text(Processes.process(cmd, envp), encoding);

	}

	public static String execute(String cmd, String[] envp, File dir, String encoding) {

		return text(Processes.process(cmd, envp, dir), encoding);

	}

	public static String execute(String[] cmd, String[] envp, File dir, String encoding) {

		return text(Processes.process(cmd, envp, dir), encoding);

	}

	public static String text(Process process, String encoding) {

		try {

			return new String(Streams.getBytes(process.getInputStream()), encoding);

		} catch (IOException e) {

			throw Exceptions.argument(e);

		} finally {

			process.destroy();

		}

	}

}
