package cn.quickly.project.utility.lang;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;

import cn.quickly.project.utility.collection.Arrays;
import cn.quickly.project.utility.collection.Collections;
import cn.quickly.project.utility.regex.Patterns;

public final class Platform {

	private static boolean MAC = false;

	private static boolean LINUX = false;

	private static boolean WINDOWS = false;

	private static boolean SOLARIS = false;

	private static boolean FREEBSD = false;

	private static boolean OPENBSD = false;

	private static boolean WINDOWSCE = false;

	private static boolean BIT64 = false;

	private static int CPU_COUNT = 0;

	private static long MAX_MEMORY = 0;

	private Platform() {
		throw new UnsupportedOperationException();
	}

	public static final boolean isMac() {
		return MAC;
	}

	public static final boolean isLinux() {
		return LINUX;
	}

	public static final boolean isWindowsCE() {
		return WINDOWSCE;
	}

	public static final boolean isWindows() {
		return WINDOWS;
	}

	public static final boolean isSolaris() {
		return SOLARIS;
	}

	public static final boolean isFreeBSD() {
		return FREEBSD;
	}

	public static final boolean isOpenBSD() {
		return OPENBSD;
	}

	public static final boolean isX11() {
		return !isWindows() && !isMac();
	}

	public static final boolean is64Bit() {
		return BIT64;
	}

	public static final int getCpuCount() {
		return CPU_COUNT;
	}

	public static final long getMaxMemory() {
		return MAX_MEMORY;
	}

	public static String[] getInetAddresses() {

		String[] cmd = Platform.isWindows() ? Arrays.as("ipconfig", "/all") : Arrays.as("ifconfig", "-a");

		String encoding = Platform.isWindows() ? "GBK" : "UTF-8";

		String message = Shell.execute(cmd, encoding);

		String regex = "[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}";

		Matcher matcher = Patterns.matcher(message, regex);

		Set<String> ips = new HashSet<String>();

		while (matcher.find()) {

			ips.add(matcher.group());

		}

		return Collections.toArray(String.class, ips);

	}

	public static String[] getMacAddresses() {

		String[] cmd = Platform.isWindows() ? Arrays.as("ipconfig", "/all") : Arrays.as("ifconfig", "-a");

		String encoding = Platform.isWindows() ? "GBK" : "UTF-8";

		String message = Shell.execute(cmd, encoding);

		String regex = "[0-9a-fA-F]{2}-[0-9a-fA-F]{2}-[0-9a-fA-F]{2}-[0-9a-fA-F]{2}-[0-9a-fA-F]{2}-[0-9a-fA-F]{2}";

		Matcher matcher = Patterns.matcher(message, regex);

		Set<String> macs = new HashSet<String>();

		while (matcher.find()) {

			macs.add(matcher.group());

		}

		return Collections.toArray(String.class, macs);

	}

	static {

		Quiet.tryCatch(() -> {

			String osName = System.getProperty("os.name");

			if (osName.startsWith("Linux")) {

				LINUX = true;

			} else if (osName.startsWith("Mac") || osName.startsWith("Darwin")) {

				MAC = true;

			} else if (osName.startsWith("Windows CE")) {

				WINDOWSCE = true;

			} else if (osName.startsWith("Windows")) {

				WINDOWS = true;

			} else if (osName.startsWith("Solaris") || osName.startsWith("SunOS")) {

				SOLARIS = true;

			} else if (osName.startsWith("FreeBSD")) {

				FREEBSD = true;

			} else if (osName.startsWith("OpenBSD")) {

				OPENBSD = true;

			}

			String model = System.getProperty("sun.arch.data.model");
			if (model != null) {

				BIT64 = "64".equals(model);

			}

			String arch = System.getProperty("os.arch").toLowerCase();
			if ("x86_64".equals(arch) || "ppc64".equals(arch) || "sparcv9".equals(arch) || "amd64".equals(arch)) {

				BIT64 = true;

			}

			Runtime runtime = Runtime.getRuntime();

			CPU_COUNT = runtime.availableProcessors();

			MAX_MEMORY = runtime.maxMemory();

		});

	}

}
