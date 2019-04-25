package cn.quickly.project.utility.lang;

public final class Numbers {

	private Numbers() {
		throw new UnsupportedOperationException();
	}

	public static Object getDefaultValue(Class<?> targetType) {

		String name = targetType.getName();

		if (name.equals("short")) {
			return (short) 0;
		} else if (name.equals("float")) {
			return 0f;
		} else if (name.equals("int")) {
			return 0;
		} else if (name.equals("long")) {
			return 0l;
		} else if (name.equals("double")) {
			return 0d;
		} else if (name.equals("byte")) {
			return (byte) 0;
		} else if (name.equals("char")) {
			return ' ';
		}

		return null;

	}

}
