package cn.quickly.project.utility.codec;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import cn.quickly.project.utility.collection.Arrays;
import cn.quickly.project.utility.io.StreamSeeker;
import cn.quickly.project.utility.io.Streams;
import cn.quickly.project.utility.lang.Printer;
import cn.quickly.project.utility.map.Maps;

public class BencodingTest {

	private static final String charset = "UTF-8";

	@Test
	public void testEncode() {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("string", "this is a test");
		map.put("integer", 123);
		map.put("object-array", new Object[] { Maps.asMap("test", "array"), "test array", 123 });
		map.put("int-array", new int[] { 1, 2, 3 });
		map.put("byte-array", new byte[] { 1, 2, 3 });
		map.put("object-collection", Arrays.asList("test"));

		System.out.println(map);

		byte[] data = Bencoding.encode(map, charset);

		System.out.println(Hex.lower(data));

		System.out.println(Bencoding.decode(data, charset, Arrays.asSet("byte-array")));

	}

	@Test
	public void testDecode() throws Exception {

		byte[] data = Streams.getBytes(StreamSeeker.classpath("/1.torrent"));

		Object map = Bencoding.decode(data, charset, Arrays.asSet("ed2k", "filehash", "pieces"));

		// System.out.println(JSONSerializer.stringify(map));

		Printer.dump(map);

	}

	@Test
	public void testDecodeDhtData() throws Exception {

		byte[] data = Streams.getBytes(StreamSeeker.classpath("/dht.data"));

		Set<String> binaryFields = Arrays.asSet("node-id", "node-id6", "nodes", "nodes6");

		Object dht = Bencoding.decode(data, "UTF-8", binaryFields);

		Printer.dump(dht);

	}

}
