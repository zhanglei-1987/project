package cn.quickly.project.utility.reflect;

import java.lang.reflect.Constructor;

public final class Constructors {

	public Constructors() {
		throw new UnsupportedOperationException();
	}

	@SuppressWarnings("unchecked")
	public static <T> Constructor<T> getConstructor(Class<T> type, Class<?>... argTypes) {

		try {

			Constructor<T> constructor = type.getConstructor(argTypes);

			if (constructor != null) {

				return constructor;

			}

		} catch (Exception e) {
		}

		Constructor<?>[] constructors = type.getDeclaredConstructors();

		for (int i = 0; i < constructors.length; i++) {

			Class<?>[] paramtypes = constructors[i].getParameterTypes();

			if (paramtypes.length == argTypes.length) {

				boolean canuse = true;

				for (int j = 0; j < paramtypes.length; ++j) {

					if (!paramtypes[j].isAssignableFrom(argTypes[j])) {

						canuse = false;

						break;

					}

				}

				if (canuse == true) {

					return (Constructor<T>) constructors[i];

				}

			}

		}

		return null;
	}

}
