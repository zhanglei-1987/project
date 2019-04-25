package cn.quickly.project.utility.thirdparty;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.SftpATTRS;

import cn.quickly.project.utility.compress.Zips;
import cn.quickly.project.utility.io.Streams;
import cn.quickly.project.utility.lang.Exceptions;
import cn.quickly.project.utility.lang.Quiet;
import cn.quickly.project.utility.lang.Strings;

/**
 * Sftp上传与下载
 * 
 * 配置
 * 
 * host ： 地址
 * 
 * port ：端口号，默认22
 * 
 * username：用户名
 * 
 * password：密码
 * 
 * encoding：ZIP文件名编码
 * 
 * remoteUri：服务端资源路径
 * 
 * 
 */
public final class Sftps {

	public static ChannelSftp channel(Properties setting) {

		return (ChannelSftp) JSchs.channel(setting, "sftp");

	}

	public static Set<String> list(ChannelSftp sftp, String remoteUri, boolean recursion) {

		Set<String> paths = new LinkedHashSet<String>();

		try {

			for (Object item : sftp.ls(remoteUri)) {

				LsEntry entry = (LsEntry) item;

				String fileName = entry.getFilename();

				if (".".equals(fileName) || "..".equals(fileName)) {
					continue;
				}

				String path = Strings.concat(remoteUri, "/", fileName).replace("//", "/");

				SftpATTRS attrs = entry.getAttrs();

				if (attrs.isDir()) {

					paths.add(Strings.concat(path, "/"));

					paths.addAll(list(sftp, path, recursion));

				} else {
					paths.add(path);
				}

			}

		} catch (Exception e) {
			throw Exceptions.argument(e);
		}

		return paths;
	}

	public static void upload(Properties setting, InputStream in, String remoteUri) {

		ChannelSftp sftp = channel(setting);

		try {

			OutputStream out = sftp.put(remoteUri);

			Streams.copy(in, out);

			Quiet.close(out);
			Quiet.close(in);

			sftp.disconnect();

			sftp.getSession().disconnect();

		} catch (Exception e) {
			throw Exceptions.argument(e);
		}

	}

	public static void upload(Properties setting, byte[] data, String remoteUri) {
		upload(setting, Streams.input(data), remoteUri);
	}

	public static void upload(Properties setting, File file, String remoteUri) {
		upload(setting, Streams.input(file), remoteUri);
	}

	public static byte[] download(Properties setting, String remoteUri) {
		ByteArrayOutputStream out = Streams.output();
		download(setting, remoteUri, out);
		return out.toByteArray();
	}

	public static void download(Properties setting, String remoteUri, File file) {
		download(setting, remoteUri, Streams.output(file));
	}

	public static void download(Properties setting, String remoteUri, OutputStream out) {

		ChannelSftp sftp = channel(setting);

		try {

			if (remoteUri.endsWith("/")) {

				Map<String, InputStream> files = new LinkedHashMap<String, InputStream>();

				for (String path : list(sftp, remoteUri, true)) {
					if (!path.endsWith("/")) {
						files.put(Strings.suffix(path, remoteUri), sftp.get(path));
					}
				}

				Zips.zip(out, files, setting.getProperty("encoding", "GBK"));

			} else {

				Streams.copy(sftp.get(remoteUri), out, true);

			}

			sftp.disconnect();

			sftp.getSession().disconnect();

		} catch (Exception e) {
			throw Exceptions.argument(e);
		} finally {
			Quiet.close(out);
		}

	}
}
