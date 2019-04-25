package cn.quickly.project.utility.reflect;

import java.util.Map;

public class CustomClassloader extends ClassLoader {

	private Map<String, byte[]> classBytesMap;

	public CustomClassloader(Map<String, byte[]> classBytesMap) {
		this.classBytesMap = classBytesMap;
	}

	public CustomClassloader(Map<String, byte[]> classBytesMap, ClassLoader classloader) {
		super(classloader);
		this.classBytesMap = classBytesMap;

	}

	@Override
	public Class<?> loadClass(String className) throws ClassNotFoundException {
		if (classBytesMap.containsKey(className)) {
			byte[] classBytes = classBytesMap.get(className);
			return super.defineClass(className, classBytes, 0, classBytes.length);
		}
		return super.loadClass(className);
	}

}
