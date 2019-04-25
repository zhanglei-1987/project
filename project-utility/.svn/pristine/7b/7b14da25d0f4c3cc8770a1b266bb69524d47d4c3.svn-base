package cn.quickly.project.utility.thirdparty;

import java.io.File;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.sleepycat.bind.ByteArrayBinding;
import com.sleepycat.bind.EntryBinding;
import com.sleepycat.bind.tuple.BigDecimalBinding;
import com.sleepycat.bind.tuple.BigIntegerBinding;
import com.sleepycat.bind.tuple.BooleanBinding;
import com.sleepycat.bind.tuple.ByteBinding;
import com.sleepycat.bind.tuple.CharacterBinding;
import com.sleepycat.bind.tuple.DoubleBinding;
import com.sleepycat.bind.tuple.FloatBinding;
import com.sleepycat.bind.tuple.IntegerBinding;
import com.sleepycat.bind.tuple.LongBinding;
import com.sleepycat.bind.tuple.ShortBinding;
import com.sleepycat.bind.tuple.StringBinding;
import com.sleepycat.bind.tuple.TupleBinding;
import com.sleepycat.collections.StoredKeySet;
import com.sleepycat.collections.StoredMap;
import com.sleepycat.je.Database;
import com.sleepycat.je.DatabaseConfig;
import com.sleepycat.je.DatabaseEntry;
import com.sleepycat.je.Environment;
import com.sleepycat.je.EnvironmentConfig;

import cn.quickly.project.utility.io.Serialization;
import cn.quickly.project.utility.lang.Exceptions;
import cn.quickly.project.utility.lang.Shutdown;
import cn.quickly.project.utility.lang.Strings;

public class Berkeleys {

	private static Map<File, Environment> environments = new ConcurrentHashMap<>();

	private static final Map<Class<?>, EntryBinding<?>> bindings = new HashMap<>();

	static {

		binding(new StringBinding(), String.class);
		binding(new CharacterBinding(), Character.class, char.class);
		binding(new BooleanBinding(), Boolean.class, boolean.class);
		binding(new ByteBinding(), Byte.class, byte.class);
		binding(new ShortBinding(), Short.class, short.class);
		binding(new IntegerBinding(), Integer.class, int.class);
		binding(new LongBinding(), Long.class, long.class);
		binding(new FloatBinding(), Float.class, float.class);
		binding(new DoubleBinding(), Double.class, double.class);
		binding(new BigDecimalBinding(), BigDecimal.class);
		binding(new BigIntegerBinding(), BigInteger.class);
		binding(new ByteArrayBinding(), byte[].class);

	}

	private static class SerializableBinding implements EntryBinding<Serializable> {

		static SerializableBinding INSTANCE = new SerializableBinding();

		private SerializableBinding() {
		}

		@Override
		public Serializable entryToObject(DatabaseEntry entry) {

			return (Serializable) Serialization.deserialize(entry.getData());

		}

		@Override
		public void objectToEntry(Serializable object, DatabaseEntry entry) {

			entry.setData(Serialization.serialize(object));

		}
	}

	public static synchronized Environment open(File store) {

		Environment environment = environments.get(store);

		if (environment == null) {

			if (!store.exists()) {
				store.mkdirs();
			}

			EnvironmentConfig environmentConfig = new EnvironmentConfig();
			environmentConfig.setAllowCreate(true);
			environmentConfig.setTransactional(false);
			environmentConfig.setConfigParam(EnvironmentConfig.MAX_MEMORY_PERCENT, "10");

			environment = new Environment(store, environmentConfig);

			Shutdown.addHook(() -> {

				environments.get(store).close();

				if (store.getAbsolutePath().startsWith(System.getProperty("java.io.tmpdir"))) {

					cn.quickly.project.utility.io.Files.delete(store);

				}

				store.delete();

			});

			environments.put(store, environment);

		}

		return environment;

	}

	public static Database open(File store, String name) {

		Environment environment = open(store);

		DatabaseConfig databaseConfig = new DatabaseConfig();
		databaseConfig.setAllowCreate(true);
		databaseConfig.setDeferredWrite(false);

		Database database = environment.openDatabase(null, name, databaseConfig);

		Shutdown.addHook(() -> {

			database.close();

		});

		return database;
	}

	public static void binding(EntryBinding<?> binding, Class<?>... types) {

		for (Class<?> type : types) {

			bindings.put(type, binding);

		}

	}

	@SuppressWarnings("unchecked")
	public static <T> EntryBinding<T> binding(Class<T> type) {

		EntryBinding<T> binding = TupleBinding.getPrimitiveBinding(type);

		if (binding == null) {

			binding = (EntryBinding<T>) SerializableBinding.INSTANCE;

		}

		return binding;

	}

	public static Database temporary(String name) {

		try {

			return open(Files.createTempDirectory(Strings.concat(name, "-")).toFile(), name);

		} catch (Exception e) {

			throw Exceptions.argument(e);

		}

	}

	public static <K, V> Map<K, V> map(Class<K> keyClass, Class<V> valueClass) {

		try {

			return map(Files.createTempDirectory("berkeleys-").toFile(), "map", keyClass, valueClass);

		} catch (Exception e) {

			throw Exceptions.argument(e);

		}

	}

	public static <K, V> Map<K, V> map(File store, String name, Class<K> keyClass, Class<V> valueClass) {

		Database database = open(store, name);

		EntryBinding<K> keyBinding = binding(keyClass);

		EntryBinding<V> valueBinding = binding(valueClass);

		return new StoredMap<K, V>(database, keyBinding, valueBinding, true);

	}

	public static <E> Set<E> set(Class<E> valueClass) {

		try {

			return set(Files.createTempDirectory("berkeleys-").toFile(), "set", valueClass);

		} catch (Exception e) {

			throw Exceptions.argument(e);

		}

	}

	public static <E> Set<E> set(File store, String name, Class<E> valueClass) {

		Database database = open(store, name);

		EntryBinding<E> valueBinding = binding(valueClass);

		return new StoredKeySet<E>(database, valueBinding, true);

	}

}
