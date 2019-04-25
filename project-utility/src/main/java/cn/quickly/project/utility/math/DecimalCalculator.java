package cn.quickly.project.utility.math;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

import cn.quickly.project.utility.map.DefaultHashMap;
import cn.quickly.project.utility.thirdparty.Ognls;

public class DecimalCalculator {

	private Map<String, BigDecimal> context = new DefaultHashMap<String, BigDecimal>(BigDecimal.ZERO);

	private RoundingMode roundingMode = RoundingMode.HALF_UP;

	private int scale = 2;

	public DecimalCalculator() {

	}

	public DecimalCalculator(RoundingMode roundingMode, int scale) {
		this.roundingMode = roundingMode;
		this.scale = scale;
	}

	public BigDecimal add(String name, Number value) {

		BigDecimal base = context.get(name);

		if (value != null) {
			base = base.add(new BigDecimal(value + ""));
		}

		base = base.setScale(scale, roundingMode);

		context.put(name, base);

		return base;
	}

	public BigDecimal subtract(String name, Number value) {

		BigDecimal base = context.get(name);

		if (value != null) {
			base = base.subtract(new BigDecimal(value + ""));
		}

		base = base.setScale(scale, roundingMode);

		context.put(name, base);

		return base;

	}

	public BigDecimal multiply(String name, Number value) {
		BigDecimal base = context.get(name);

		if (value != null) {
			base = base.multiply(new BigDecimal(value + ""));
		}

		base = base.setScale(scale, roundingMode);

		context.put(name, base);

		return base;
	}

	public BigDecimal divide(String name, Number value) {
		BigDecimal base = context.get(name);

		if (value != null) {
			base = base.divide(new BigDecimal(value + ""));
		}

		base = base.setScale(scale, roundingMode);

		context.put(name, base);

		return base;
	}

	public BigDecimal exec(String script) {
		return Ognls.getValue(script, context, BigDecimal.class);
	}

	public Map<String, BigDecimal> getResult() {
		return context;
	}

}
