package fi.ishtech.sudoku.solver.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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
		return value == null ? null
				: Optional.ofNullable(lookup.get(value)).orElseThrow(() -> new NoSuchElementException(
						"Invalid value(" + value + ") for IntEnum. Value must be between 1 and 9 (inclusive)."));
	}

	public static IntEnum fromValue(String value) {
		return value == null || value.isBlank() ? null : fromValue(Integer.parseInt(value));
	}

	public static EnumSet<IntEnum> all() {
		return EnumSet.allOf(IntEnum.class);
	}

	public static Integer toInteger(IntEnum e) {
		return e == null ? null : e.getValue();
	}

	public static int toInt(IntEnum e) {
		return e == null ? 0 : e.getValue();
	}

	public static Set<Integer> toSetOfIntegers(EnumSet<IntEnum> es) {
		return es == null ? null : es.stream().map(IntEnum::getValue).collect(Collectors.toSet());
	}

}
