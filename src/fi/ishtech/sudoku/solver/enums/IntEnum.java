package fi.ishtech.sudoku.solver.enums;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

	public static IntEnum[][] fromStringArray(String[][] str) {
		// @formatter:off
		return str == null ? null
				: IntStream.range(0, str.length)
					.mapToObj(i -> IntStream.range(0, str[i].length)
						.mapToObj(j -> IntEnum.fromValue(str[i][j]))
						.toArray(IntEnum[]::new))
					.toArray(IntEnum[][]::new);
		// @formatter:on
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

	public static int[][] toIntArray(IntEnum[][] e) {
		// @formatter:off
		return (e == null) ? null :
				Arrays.stream(e)
					.map(row -> Arrays.stream(row)
							.mapToInt(IntEnum::toInt)
							.toArray())
					.toArray(int[][]::new);
		// @formatter:on
	}

	public static Set<Integer> toSetOfIntegers(EnumSet<IntEnum> es) {
		return es == null ? null : es.stream().map(IntEnum::getValue).collect(Collectors.toSet());
	}

	public static void print(EnumSet<IntEnum>[][] setArray) {
		if (setArray == null) {
			System.out.println(setArray);
		} else {
			int rows = setArray.length;
			int cols = setArray[0].length;

			StringBuilder sb = new StringBuilder();
			sb.append('[');
			for (int i = 0; i < rows; i++) {
				sb.append("\n[");
				for (int j = 0; j < cols; j++) {
					sb.append(setArray[i][j]);
					if (j == cols - 1) {
						sb.append(']');
					} else {
						sb.append(',');
					}
				}
				if (i == rows - 1) {
					sb.append("\n]");
				} else {
					sb.append(",");
				}
			}

			System.out.println(sb.toString());
		}
	}

}
