package fi.ishtech.sudoku.solver;

import java.util.EnumSet;
import java.util.stream.IntStream;

import fi.ishtech.sudoku.solver.enums.IntEnum;

public class SudokuAltSolver {

	public IntEnum[][] solve(String[][] input) {
		return solve(readInputFromStrArray(input));
	}

	public IntEnum[][] solve(IntEnum[][] input) {
		initProbables();

		// TODO

		return input;
	}

	public IntEnum[][] readInputFromStrArray(String[][] input) {
		// @formatter:off
		return input == null ? null
				: IntStream.range(0, input.length)
					.mapToObj(i -> IntStream.range(0, input[i].length)
						.mapToObj(j -> IntEnum.fromValue(input[i][j]))
						.toArray(IntEnum[]::new))
					.toArray(IntEnum[][]::new);
		// @formatter:on
	}

	@SuppressWarnings("unchecked")
	private void initProbables() {
		// @formatter:off
		EnumSet<IntEnum>[][] probs = IntStream.range(0, 9)
				.mapToObj(i -> IntStream.range(0, 9)
						.mapToObj(j -> IntEnum.all())
						.toArray(EnumSet[]::new))
				.toArray(EnumSet[][]::new);
		// @formatter:on
		System.out.println(probs);
	}

	private boolean isValid() {
		// TODO

		return true;
	}

}
