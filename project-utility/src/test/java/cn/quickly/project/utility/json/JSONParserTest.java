package cn.quickly.project.utility.json;

import java.io.IOException;

import org.junit.Test;

import cn.quickly.project.utility.io.StreamSeeker;
import cn.quickly.project.utility.io.Streams;
import cn.quickly.project.utility.json.JSONObject;

public class JSONParserTest {

	@Test
	public void testFile() throws IOException {

		String json = Streams.getString(StreamSeeker.classpath("/torrent.json"), "UTF-8");

		System.out.println(json);

		System.out.println(JSONObject.from(json));

	}

	@Test
	public void testText() {

		System.out.println(JSONObject.from("{'before.name':'data'}"));

	}

}
