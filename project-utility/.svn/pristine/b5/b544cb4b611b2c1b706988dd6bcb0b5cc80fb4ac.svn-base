package cn.quickly.project.utility.text;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import cn.quickly.project.utility.lang.SupplierThreadLocal;

public final class FormatFactory {

	private static FormatFactory factory = new FormatFactory();

	private ThreadLocal<Map<String, DateFormat>> dateFormats = new SupplierThreadLocal<>(() -> new HashMap<>());

	private ThreadLocal<Map<String, NumberFormat>> numberFormats = new SupplierThreadLocal<>(() -> new HashMap<>());

	private ThreadLocal<Map<String, MessageFormat>> messageFormats = new SupplierThreadLocal<>(() -> new HashMap<>());

	private FormatFactory() {

	}

	public static FormatFactory getFactory() {
		return factory;
	}

	public DateFormat getDateFormat(String pattern) {

		Map<String, DateFormat> formats = dateFormats.get();

		DateFormat dateFormat = formats.get(pattern);

		if (dateFormat == null) {

			dateFormat = new SimpleDateFormat(pattern);

			formats.putIfAbsent(pattern, dateFormat);

		}

		return dateFormat;
	}

	public NumberFormat getNumberFormat(String pattern) {

		Map<String, NumberFormat> formats = numberFormats.get();

		NumberFormat numberFormat = formats.get(pattern);

		if (numberFormat == null) {

			numberFormat = new DecimalFormat(pattern);

			formats.put(pattern, numberFormat);

		}

		return numberFormat;

	}

	public MessageFormat getMessageFormat(String pattern) {

		Map<String, MessageFormat> formats = messageFormats.get();

		MessageFormat messageFormat = formats.get(pattern);

		if (messageFormat == null) {

			messageFormat = new MessageFormat(pattern);

			formats.put(pattern, messageFormat);

		}

		return messageFormat;

	}

	public synchronized void clear() {
		dateFormats.get().clear();
		numberFormats.get().clear();
		messageFormats.get().clear();
	}

}
