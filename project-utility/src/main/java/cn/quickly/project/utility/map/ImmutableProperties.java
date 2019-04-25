package cn.quickly.project.utility.map;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.Collection;
import java.util.InvalidPropertiesFormatException;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.function.BiFunction;

import cn.quickly.project.utility.lang.Immutables;
import cn.quickly.project.utility.lang.Iterables;

public class ImmutableProperties extends Properties {

	private static final long serialVersionUID = 1L;

	public ImmutableProperties(Properties properties) {

		Iterables.forEach(properties, (i, key, value) -> {

			super.put(key, value);

		});
	}

	@Override
	public synchronized Object setProperty(String key, String value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public synchronized void load(Reader reader) throws IOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public synchronized void load(InputStream inStream) throws IOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void save(OutputStream out, String comments) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void store(Writer writer, String comments) throws IOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void store(OutputStream out, String comments) throws IOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public synchronized void loadFromXML(InputStream in) throws IOException, InvalidPropertiesFormatException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void storeToXML(OutputStream os, String comment) throws IOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void storeToXML(OutputStream os, String comment, String encoding) throws IOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void list(PrintStream out) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void list(PrintWriter out) {
		throw new UnsupportedOperationException();
	}

	@Override
	public synchronized Object put(Object key, Object value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public synchronized Object remove(Object key) {
		throw new UnsupportedOperationException();
	}

	@Override
	public synchronized void putAll(Map<? extends Object, ? extends Object> t) {
		throw new UnsupportedOperationException();
	}

	@Override
	public synchronized void clear() {
		throw new UnsupportedOperationException();
	}

	@Override
	public synchronized void replaceAll(BiFunction<? super Object, ? super Object, ? extends Object> function) {
		throw new UnsupportedOperationException();
	}

	@Override
	public synchronized Object putIfAbsent(Object key, Object value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public synchronized boolean remove(Object key, Object value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public synchronized boolean replace(Object key, Object oldValue, Object newValue) {
		throw new UnsupportedOperationException();
	}

	@Override
	public synchronized Object replace(Object key, Object value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public synchronized Object merge(Object key, Object value, BiFunction<? super Object, ? super Object, ? extends Object> remappingFunction) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Set<Object> keySet() {
		return Immutables.set(super.keySet());
	}

	@Override
	public Collection<Object> values() {
		return Immutables.collection(super.values());
	}

}
