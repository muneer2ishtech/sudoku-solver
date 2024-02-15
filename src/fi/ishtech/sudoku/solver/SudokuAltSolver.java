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

	public int[][] solve() {
		result = IntEnum.deepClone(input);
		printResultInt();
		printProbables();

		resetProbables();

		// TODO

		return IntEnum.toIntArray(result);
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

	private void resetProbables() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				resetProbables(i, j);
			}
		}
		printProbables();
	}

	private void resetProbables(int row, int col) {
		if (input[row][col] != null) {
			probs[row][col].clear();
		}
	}

	private void printProbables() {
		if (printLogs) {
			IntEnum.print(probs);
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

	private boolean isValid() {
		// TODO

		return true;
	}

}
