package fi.ishtech.sudoku.solver;

import java.util.EnumSet;
import java.util.NoSuchElementException;

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
		IntEnum[][] a = new IntEnum[9][9];

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (!input[i][j].trim().isEmpty()) {
					try {
						a[i][j] = IntEnum.fromValue(input[i][j]);
					} catch (NoSuchElementException | NumberFormatException e) {
						throw e;
					} catch (Exception e) {
						throw new RuntimeException(
								"Invalid input '" + input[i][j] + "' at row:" + (i + 1) + ", col:" + (j + 1));
					}
				}
			}
		}
		if (!isValid()) {
			throw new RuntimeException("Invalid Input");
		}

		return a;
	}

	@SuppressWarnings("unchecked")
	private void initProbables() {
		EnumSet<IntEnum>[][] probs = new EnumSet[9][9];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				probs[i][j] = IntEnum.all();
			}
		}
		System.out.println(probs);
	}

	private boolean isValid() {
		// TODO

		return true;
	}

}
