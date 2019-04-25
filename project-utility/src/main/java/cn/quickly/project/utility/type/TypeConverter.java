package cn.quickly.project.utility.type;

public interface TypeConverter<T> {

	T convert(Class<?> type, Object value);

}
