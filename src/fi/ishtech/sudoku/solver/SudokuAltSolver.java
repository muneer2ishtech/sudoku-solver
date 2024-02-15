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
		return input == null ? null
				: IntStream.range(0, 9).mapToObj(i -> IntStream.range(0, 9)
						.mapToObj(j -> IntEnum.fromValue(input[i][j])).toArray(IntEnum[]::new))
						.toArray(IntEnum[][]::new);
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
