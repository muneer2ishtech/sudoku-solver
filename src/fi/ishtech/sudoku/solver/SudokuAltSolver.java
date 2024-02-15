package fi.ishtech.sudoku.solver;

import java.util.EnumSet;
import java.util.stream.IntStream;

import fi.ishtech.sudoku.solver.enums.IntEnum;

public class SudokuAltSolver {

	private final EnumSet<IntEnum>[][] probs;
	private final IntEnum[][] input;

	public SudokuAltSolver(IntEnum[][] input) {
		this.input = input;
		this.probs = initProbables();
	}

	public SudokuAltSolver(String[][] input) {
		this(readInputFromStrArray(input));
	}

	public int[][] solve(String[][] input) {
		return solve(readInputFromStrArray(input));
	}

	public int[][] solve(IntEnum[][] input) {
		initProbables();

		// TODO

		return IntEnum.toIntArray(input);
	}

	private static IntEnum[][] readInputFromStrArray(String[][] input) {
		return IntEnum.fromStringArray(input);
	}

	@SuppressWarnings("unchecked")
	private EnumSet<IntEnum>[][] initProbables() {
		// @formatter:off
		return IntStream.range(0, 9)
				.mapToObj(i -> IntStream.range(0, 9)
						.mapToObj(j -> IntEnum.all())
						.toArray(EnumSet[]::new))
				.toArray(EnumSet[][]::new);
		// @formatter:on
	}

	private void printProbables() {
		IntEnum.print(probs);
	}

	private boolean isValid() {
		// TODO

		return true;
	}

}
