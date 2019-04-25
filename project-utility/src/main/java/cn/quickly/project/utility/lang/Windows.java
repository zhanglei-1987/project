package cn.quickly.project.utility.lang;

import java.io.File;

import cn.quickly.project.utility.collection.Arrays;
import cn.quickly.project.utility.io.Directory;
import cn.quickly.project.utility.io.StreamSeeker;
import cn.quickly.project.utility.io.Streams;

public class Windows {

	private Windows() {
		throw new UnsupportedOperationException();
	}

	public static String getCPUSerial() {

		return Windows.vbs("classpath:cpu-serial.vbs");

	}

	public static String getMotherboardSerial() {

		return Windows.vbs("classpath:motherboard-serial.vbs");

	}

	public static String getDiskSerial(String drive) {

		return Windows.vbs("classpath:disk-serial.vbs", drive);

	}

	public static String vbs(String script, String... arguments) {

		return Quiet.tryCatch(() -> {

			File file = new File(Directory.temporary(), script);

			if (!file.exists()) {

				Streams.copy(StreamSeeker.find(script), file);

			}

			String[] cmd = Arrays.merge(Arrays.as("cscript", "//NoLogo", file.getPath()), arguments);

			String text = Shell.execute(cmd, "GBK");

			if (!Strings.isEmpty(text)) {

				return text.trim();

			}

			return null;

		});

	}

}
