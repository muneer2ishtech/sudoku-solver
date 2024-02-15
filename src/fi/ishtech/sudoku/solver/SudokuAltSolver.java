package fi.ishtech.sudoku.solver;

import java.util.EnumSet;
import java.util.stream.IntStream;

import fi.ishtech.sudoku.solver.enums.IntEnum;

public class SudokuAltSolver {

	private final EnumSet<IntEnum>[][] probs;
	private final IntEnum[][] input;

	private IntEnum[][] result;

	private boolean printLogs = true;

	public SudokuAltSolver(IntEnum[][] input) {
		this.input = input;
		this.probs = initProbables();
	}

	public SudokuAltSolver(String[][] input) {
		this(readInputFromStrArray(input));
	}

	private static IntEnum[][] readInputFromStrArray(String[][] input) {
		return IntEnum.fromStringArray(input);
	}

	public int[][] solve() {
		result = IntEnum.deepClone(input);

		printResultInt();
		printProbablesInt();

		resetProbables();

		// TODO

		return IntEnum.toIntArray(result);
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

	private void resetProbables() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				resetProbables(i, j);
			}
		}
		printProbablesInt();
	}

	private void resetProbables(int row, int col) {
		if (result[row][col] != null) {
			probs[row][col].clear();
		}
	}

	@SuppressWarnings("unused")
	private void printProbables() {
		if (printLogs) {
			IntEnum.print(probs);
		}
	}

	private void printProbablesInt() {
		if (printLogs) {
			IntEnum.printInt(probs);
		}
	}

	@SuppressWarnings("unused")
	private void printResult() {
		if (printLogs) {
			IntEnum.print(result);
		}
	}

	private void printResultInt() {
		if (printLogs) {
			IntEnum.printInt(result);
		}
	}

	@SuppressWarnings("unused")
	private boolean isValid() {
		// TODO

		return true;
	}

}
