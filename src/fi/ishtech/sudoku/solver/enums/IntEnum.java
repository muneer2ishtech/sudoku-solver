package fi.ishtech.sudoku.solver.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum IntEnum {

	// @formatter:off
	ONE(1),
	TWO(2),
	THREE(3),
	FOUR(4),
	FIVE(5),
	SIX(6),
	SEVEN(7),
	EIGHT(8),
	NINE(9);
	// @formatter:on

	private int value;

	IntEnum(int value) {
		if (value < 1 || value > 9) {
			throw new IllegalArgumentException(
					"Invalid value(" + value + ") for IntEnum. Value must be between 1 and 9 (inclusive).");
		}
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}

	private static final Map<Integer, IntEnum> lookup = new HashMap<>();

	static {
		for (IntEnum e : IntEnum.values()) {
			lookup.put(e.value, e);
		}
	}

	public static IntEnum fromValue(Integer value) {
		if (value == null) {
			return null;
		}

		if (lookup.containsKey(value)) {
			return lookup.get(value);
		} else {
			throw new RuntimeException(
					"Invalid value(" + value + ") for IntEnum. Value must be between 1 and 9 (inclusive).");
		}
	}

	public static EnumSet<IntEnum> all() {
		return EnumSet.allOf(IntEnum.class);
	}

}
