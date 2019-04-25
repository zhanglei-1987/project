package cn.quickly.project.utility.collection;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import cn.quickly.project.utility.map.Maps;

public class MapsTest {

	@Test
	public void testGetMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("channelId", 141);
		map.put("channelId2", 141);
		System.out.println(Maps.getMap(map, "channelId"));
	}

}
