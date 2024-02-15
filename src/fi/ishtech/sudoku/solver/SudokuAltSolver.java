package fi.ishtech.sudoku.solver;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import fi.ishtech.sudoku.solver.enums.IntEnum;

public class SudokuAltSolver {

	private final EnumSet<IntEnum>[][] probs;
	private final IntEnum[][] input;

	private IntEnum[][] result;

	private boolean printLogs = true;
	private int tryc = 0;

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

	private void solve(boolean exitOnSolved) {

		do {
			resetProbables();

			solveUniqs(exitOnSolved);
			solveUniqProbablesInReverse(exitOnSolved);

			tryc++;
		} while (!isResolved(exitOnSolved));
	}

	private boolean isResolved(boolean exitOnSolved) {
		if (printLogs) {
			System.out.println("Number of tries:" + tryc);
			printResultInt();
		}

		if (tryc == 20) {
			throw new RuntimeException("Failed after " + tryc);
		}

		// TODO
		return !anyNullsInResult();
	}

	private boolean anyNullsInResult() {
		return Arrays.stream(result).flatMap(Arrays::stream).anyMatch(Objects::isNull);
	}

	private void solveUniqs(boolean exitOnSolved) {
		for (int i = 0; i < 9; i++) {
			solveUniqInRow(i);
		}

		for (int j = 0; j < 9; j++) {
			solveUniqInCol(j);
		}

		for (int boxI = 0; boxI < 9; boxI += 3) {
			for (int boxJ = 0; boxJ < 9; boxJ += 3) {
				solveUniqInBox(boxI, boxJ);
			}
		}

		isResolved(exitOnSolved);
	}

	private void solveUniqProbablesInReverse(boolean exitOnSolved) {
		for (int i = 0; i < 9; i++) {
			solveUniqProbableInRowReverse(i);
		}

		for (int j = 0; j < 9; j++) {
			solveUniqProbableInColReverse(j);
		}

		for (int boxI = 0; boxI < 9; boxI += 3) {
			for (int boxJ = 0; boxJ < 9; boxJ += 3) {
				solveUniqProbableInBoxReverse(boxI, boxJ);
			}
		}

		isResolved(exitOnSolved);
	}

	private void solveUniqProbable(int row, int col) {
		if (result[row][col] == null) {
			if (probs[row][col].size() == 1) {
				result[row][col] = probs[row][col].stream().findFirst().get();
				probs[row][col].clear();
			}
		}
	}

	private void solveUniqInRow(int row) {
		solveUniqProbableInRow(row);
	}

	private void solveUniqProbableInRow(int row) {
		for (int j = 0; j < 9; j++) {
			solveUniqProbable(row, j);
		}
	}

	private void solveUniqProbableInRowReverse(int row) {
		// @formatter:off
		Set<IntEnum> combined = Arrays.stream(probs[row])
				.flatMap(Set::stream)
				.collect(Collectors.toSet());
		// @formatter:on

		for (IntEnum e : combined) {
			// @formatter:off
			long count = Arrays.stream(probs[row])
								.filter(set -> set.contains(e))
								.count();
			// @formatter:on

			if (count == 1) {
				// found only once
				// @formatter:off
		    	int foundCol = Arrays.asList(probs[row])
		    						.indexOf(Arrays.stream(probs[row])
		    								.filter(set -> set.contains(e))
		    								.findFirst()
		    								.orElse(null));
				// @formatter:on

				result[row][foundCol] = e;
				resetProbables(row, foundCol);
			} else {
				// found more than once
			}
		}
	}

	private void solveUniqInCol(int col) {
		solveUniqProbableInCol(col);
	}

	private void solveUniqProbableInColReverse(int col) {
		// @formatter:off
		Set<IntEnum> combined = Arrays.stream(probs)
				.map(row -> row[col])
				.collect(()
						-> EnumSet.noneOf(IntEnum.class), EnumSet::addAll, EnumSet::addAll);

		// @formatter:on

		for (IntEnum e : combined) {
			int count = 0;
			int foundRow = -1;
			for (int i = 0; i < 9; i++) {
				if (probs[i][col].contains(e)) {
					count++;
					foundRow = i;
				}
			}

			if (count == 1) {
				// found only once
				result[foundRow][col] = e; // can you assign directly or need to do IntEnum.fromValue(e.getValue())
				resetProbables(foundRow, col);
			} else {
				// found more than once
			}
		}
	}

	private void solveUniqProbableInCol(int col) {
		for (int i = 0; i < 9; i++) {
			solveUniqProbable(i, col);
		}
	}

	private void solveUniqInBox(int row, int col) {
		solveUniqProbableInBox(row, col);
	}

	private void solveUniqProbableInBox(int row, int col) {
		int boxI = (row / 3) * 3;
		int boxJ = (col / 3) * 3;
		for (int i = boxI; i < (boxI + 3); i++) {
			for (int j = boxJ; j < (boxJ + 3); j++) {
				solveUniqProbable(i, j);
			}
		}
	}

	private void solveUniqProbableInBoxReverse(int row, int col) {
		Set<IntEnum> combined = new HashSet<IntEnum>();

		int boxI = (row / 3) * 3;
		int boxJ = (col / 3) * 3;
		for (int i = boxI; i < (boxI + 3); i++) {
			for (int j = boxJ; j < (boxJ + 3); j++) {
				combined.addAll(probs[i][j]);
			}
		}

		for (IntEnum e : combined) {
			int count = 0;
			int foundRow = -1;
			int foundCol = -1;
			for (int i = boxI; i < (boxI + 3); i++) {
				for (int j = boxJ; j < (boxJ + 3); j++) {
					if (probs[i][j].contains(e)) {
						count++;
						foundRow = i;
						foundCol = j;
					}
				}
			}

			if (count == 1) {
				// found only once
				result[foundRow][foundCol] = e; // can you assign directly or need to do IntEnum.fromValue(e.getValue())
				resetProbables(foundRow, foundCol);
			} else {
				// found more than once
			}
		}
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
