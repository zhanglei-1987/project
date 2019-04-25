package cn.quickly.project.utility.json;

import java.io.InputStream;

import org.junit.Test;

import cn.quickly.project.utility.io.StreamSeeker;
import cn.quickly.project.utility.io.Streams;

public class JSONArrayTest {

	@Test
	public void testMenus() throws Exception {

		try (InputStream in = StreamSeeker.find("find:menus.json")) {

			String text = Streams.getString(in, "UTF-8");

			JSONArray array = JSONArray.from(text);

			System.out.println(array);

		}

	}

}
