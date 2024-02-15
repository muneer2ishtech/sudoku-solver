package fi.ishtech.sudoku.solver.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import fi.ishtech.sudoku.solver.SudokuSolver;

public class SudokuFileApp {

	public static void main(String[] args) {
		String inputFileName = null;

		if (args.length != 0) {
			inputFileName = args[0];
		}

		if (inputFileName == null || inputFileName.equals("")) {
			System.err.println("Invalid input for file name");
			System.exit(5);
		}

		SudokuFileApp sudokuFileApp = new SudokuFileApp();
		sudokuFileApp.solveFile(inputFileName);
	}

	private int[][] solveFile(String inputFileName) {
		SudokuSolver sudokuSolver = new SudokuSolver();
		return sudokuSolver.solve(readInputFromFile(inputFileName));
	}

	private int[][] readInputFromFile(String absPath) {
		int a[][] = new int[9][9];
		try {
			File file = new File(absPath);
			if (!file.exists()) {
				System.err.println("File " + absPath + " not found");
				System.exit(5);
			}
			if (!file.isFile()) {
				System.err.println(absPath + " is not a proper file");
				System.exit(5);
			}
			if (!file.canRead()) {
				System.err.println(absPath + " is not readable file");
				System.exit(5);
			}
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String s;
			int i = 0;
			while ((s = br.readLine()) != null) {
				if (s != null && s.length() != 0 && !s.equals("\n")) {
					for (int j = 0; j < s.length() && j < 9 && i < 9; j++) {
						char c = s.charAt(j);
						if (c == ' ') {
							c = '0';
						}
						a[i][j] = Integer.parseInt(String.valueOf(c));
					}
				}
				i++;
			}
			fr.close();

		} catch (NumberFormatException e) {
			System.err.println("Input file " + absPath + " has non-numeric characters");
			System.exit(5);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(5);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(5);
		}

		return a;
	}

}
