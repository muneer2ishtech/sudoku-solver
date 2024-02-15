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

		solve(false);

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

			eliminateRowUnProbables(row, col);
			eliminateColUnProbables(row, col);
			eliminateBoxUnProbables(row, col);
		}
	}

	private void eliminateRowUnProbables(int row, int col) {
		for (int j = 0; j < 9; j++) {
			probs[row][j].remove(result[row][col]);
		}
	}

	private void eliminateColUnProbables(int row, int col) {
		for (int i = 0; i < 9; i++) {
			probs[i][col].remove(result[row][col]);
		}
	}

	private void eliminateBoxUnProbables(int row, int col) {
		int boxI = (row / 3) * 3;
		int boxJ = (col / 3) * 3;
		for (int i = boxI; i < (boxI + 3); i++) {
			for (int j = boxJ; j < (boxJ + 3); j++) {
				probs[i][j].remove(result[row][col]);
			}
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
