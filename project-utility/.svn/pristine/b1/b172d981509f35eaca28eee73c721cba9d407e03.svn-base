package cn.quickly.project.utility.type;

import cn.quickly.project.utility.lang.Strings;
import cn.quickly.project.utility.reflect.Classes;

public class ClassConverter implements TypeConverter<Class<?>> {

	@Override
	public Class<?> convert(Class<?> type, Object value) {
		
		return Classes.getClass(Strings.valueOf(value));
		
	}

}
