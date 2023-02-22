package com.muneer.sudoku;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeSet;

/**
 * @author Muneer Ahmed Syed
 * @version 0.5
 * This class solves Sodoku puzzle
 * TODO: to process row & box and col & box combination
 * TODO: to process three probable (may not be needed)
 * TODO: other ways of elimination (refer www.extremesudoku.info)
 * TODO: to try randomization if puzzle is not getting solved after too many iterations
 * TODO: optimize performance
 */
public class SudokuSolver {

	private int a[][];
	private TreeSet<Integer> p[][];
	//private int counter = 0;
	private int tryc = 0;

	private static final Integer N1 = 1;
	private static final Integer N2 = 2;
	private static final Integer N3 = 3;
	private static final Integer N4 = 4;
	private static final Integer N5 = 5;
	private static final Integer N6 = 6;
	private static final Integer N7 = 7;
	private static final Integer N8 = 8;
	private static final Integer N9 = 9;

	public SudokuSolver() {
		init();
		//setKnownValues();
	}

	public static void main(String[] args) {
		SudokuSolver sudokuSolver = new SudokuSolver();
		String inputFileName = null;
		if (args.length != 0) {
			inputFileName = args[0];
		}
		if (inputFileName == null || inputFileName.equals("")) {
		System.err.println("Invalid input for file name");
			System.exit(5);
		}
		sudokuSolver.readInputFromFile(inputFileName);
		sudokuSolver.solve();

	}

	@SuppressWarnings("unchecked")
	private void init() {
		a = new int[9][9];
		p = new TreeSet[9][9];
		for(int i=0; i<9; i++) {
			for(int j=0; j<9; j++) {
				a[i][j] = 0;
				initProbables(i, j);
			}
		}
	}

	private void initProbables(int i, int j) {
		p[i][j] = new TreeSet<Integer>();
		p[i][j].add(N1);
		p[i][j].add(N2);
		p[i][j].add(N3);
		p[i][j].add(N4);
		p[i][j].add(N5);
		p[i][j].add(N6);
		p[i][j].add(N7);
		p[i][j].add(N8);
		p[i][j].add(N9);
	}

	private void readInputFromFile(String absPath) {
		try {
			File file = new File(absPath);
			if (! file.exists() ) {
				System.err.println("File " + absPath + " not found");
				System.exit(5);
			}
			if (! file.isFile()) {
				System.err.println(absPath + " is not a proper file");
				System.exit(5);	
			}
			if (! file.canRead()) {
				System.err.println(absPath + " is not readable file");
				System.exit(5);
			}
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr); 
			String s;
			int i = 0;
			while((s = br.readLine()) != null) {
				if (s != null && s.length() != 0 && ! s.equals("\n")) {
					for (int j=0; j<s.length() && j<9 && i<9; j++) {
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
			printPuzzle();

			if (! isValid()) {
				exitAsInvalid();
			}

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
	}

	public void solve() {
		resetProbables();

		do {
			solveUniqs();

			for(int i=0; i<9; i++) {
				solveDoubleProbableInRow(i);
				solveDoubleProbableInRowReverse(i);
			}
			for(int j=0; j<9; j++) {
				solveDoubleProbableInCol(j);
				solveDoubleProbableInColReverse(j);
			}
			for(int i=0; i<9; i+=3) {
				for(int j=0; j<9; j+=3) {
					solveDoubleProbableInBox(i, j);
					solveDoubleProbableInBoxReverse(i, j);
				}
			}

			exitAfterMaxTry();

		} while(! isResolved());
	}

	private void exitSuccess() {
		System.out.println("Successful in " + tryc + " iterations");
		printPuzzle();
		System.exit(0);
	}

	private void exitAsInvalid() {
		print();
		System.exit(2);
	}

	private void exitAfterMaxTry() {
		tryc ++;
		int maxTry = 10000;
		//resetProbables();
		if (tryc % 100000 == 0 ) {
			System.out.println("Tried " + tryc + " iterations");
			print();
		}
		if (tryc == maxTry) {
			System.err.println("Tried and failed after " + tryc + " iterations");
			print();
			System.exit(3);
		}
	}

	private boolean isResolved() {
		//print();
		for(int i=0; i<9; i++) {
			for(int j=0; j<9; j++) {
				if(a[i][j] == 0) {
					//resetProbables(i, j);
					return false;
				}
			}
		}

		if (! isValid()) {
			exitAsInvalid();
		}

		exitSuccess();
		
		return true;
	}

	private boolean isValid() {
		if (isRowInvalid()) {
			return false; 
		}
		if (isColInvalid()) {
			return false; 
		}
		if (isBoxInvalid()) {
			return false; 
		}

		return true;
	}

	private boolean isRowInvalid() {
		// validate row
		for (int i=0; i<9; i++) {
			if (isRowInvalid(i)) {
				return true;
			}
		}
		return false;
	}

	private boolean isRowInvalid(int row) {
		// validate row
		for (int j=0; j<9; j++) {
			for (int k=0; k<9; k++) {
				if (j != k && a[row][j] != 0 && a[row][j] == a[row][k]) {
					// duplicate found
					printDups(row, j, row, k);
					return true;
				}
			}
		}
		return false;
	}

	private boolean isColInvalid() {
		// validate col
		for (int j=0; j<9; j++) {
			if (isColInvalid(j)) {
				return true;
			}
		}
		return false;
	}

	private boolean isColInvalid(int col) {
		// validate col
		for (int i=0; i<9; i++) {
			for (int k=0; k<9; k++) {
				if (i != k &&  a[i][col] != 0 && a[i][col] == a[k][col]) {
					// duplicate found
					printDups(i, col, k, col);
					return true;
				}
			}
		}
		return false;
	}

	private boolean isBoxInvalid() {
		// validate box
		for (int boxI=0; boxI<9; boxI+=3) {
			for (int boxJ=0; boxJ<9; boxJ+=3) {
				if (isBoxInvalid(boxI, boxJ)) {
					return true;
				}
			}
		}

		return false;
	}

	private boolean isBoxInvalid(int row, int col) {
		// validate box
		int boxI = (row / 3) * 3;
		int boxJ = (col / 3) * 3;

		for (int i=boxI; i<(boxI+3); i++) {
			for (int j=boxJ; j<(boxJ+3); j++) {
				for (int k=boxI; k<(boxI+3); k++) {
					for (int l=boxJ; l<(boxJ+3); l++) {
						if (i != k && j != l  && a[i][j] != 0 && a[i][j] == a[k][l]) {
							// duplicate found
							printDups(i, j, k, l);
							return true;
						}
					}
				}
			}
		}

		return false;
	}
	
	private void solveUniqs() {
		for(int i=0; i<9; i++) {
			solveUniqProbableInRow(i);
			solveUniqProbableInRowReverse(i);

			if (isRowInvalid(i)) {
				exitAsInvalid();
			}
		}

		for(int j=0; j<9; j++) {
			solveUniqProbableInCol(j);
			solveUniqProbableInColReverse(j);

			if (isColInvalid(j)) {
				exitAsInvalid();
			}
		}

		for (int boxI=0; boxI<9; boxI+=3) {
			for (int boxJ=0; boxJ<9; boxJ+=3) {
				solveUniqProbableInBox(boxI, boxJ);
				solveUniqProbableInBoxReverse(boxI, boxJ);

				if (isBoxInvalid(boxI, boxJ)) {
					exitAsInvalid();
				}
			}
		}

		isResolved();
	}

	private void solveUniqProbable(int row, int col) {
		if(a[row][col] == 0) {
			if (p[row][col].size() == 1 ) {
				setSolvedValue(row, col, p[row][col].first().intValue());
			} else {
				resetProbables(row, col);
			}
		}
	}

	private void solveUniqProbableInRow(int row) {
		for(int j=0; j<9; j++) {
			solveUniqProbable(row, j);
		}
	}

	private void solveUniqProbableInRowReverse(int row) {
		for (int n=1; n<=9; n++) {
			int counter = 0;
			int foundCol = 10;
			for(int j=0; j<9; j++) {
				if (p[row][j].contains(n)) {
					foundCol = j;
					counter ++;
				}
			}
			if (counter == 1) {
				// n is present only once
				setSolvedValue(row, foundCol, n);
			}
		}
	}

	private void solveUniqProbableInCol(int col) {
		for(int i=0; i<9; i++) {
			solveUniqProbable(i, col);
		}
	}

	private void solveUniqProbableInColReverse(int col) {
		for (int n=1; n<=9; n++) {
			int counter = 0;
			int foundRow = 10;
			for(int i=0; i<9; i++) {
				if (p[i][col].contains(n)) {
					foundRow = i;
					counter ++;
				}
			}
			if (counter == 1) {
				// n is present only once
				setSolvedValue(foundRow, col, n);
			}
		}
	}

	private void solveUniqProbableInBox(int row, int col) {
		int boxI = (row / 3) * 3;
		int boxJ = (col / 3) * 3;
		for(int i=boxI; i<(boxI+3); i++) {
			for(int j=boxJ; j<(boxJ+3); j++) {
				solveUniqProbable(i, j);
			}
		}
	}

	private void solveUniqProbableInBoxReverse(int row, int col) {
		for (int n=1; n<=9; n++) {
			int counter = 0;
			int foundRow = 10;
			int foundCol = 10;
			int boxI = (row / 3) * 3;
			int boxJ = (col / 3) * 3;
			for(int i=boxI; i<(boxI+3); i++) {
				for(int j=boxJ; j<(boxJ+3); j++) {
					if (p[i][j].contains(n)) {
						foundRow = i;
						foundCol = j;
						counter ++;
					}
				}
			}
			if (counter == 1) {
				// n is present only once
				setSolvedValue(foundRow, foundCol, n);
			}
		}
	}

	private void setSolvedValue(int row, int col, int val) {
		a[row][col] = val;
		resetProbables(row, col);
		print();
	}

	private void resetProbables() {
		for(int i=0; i<9; i++) {
			for(int j=0; j<9; j++) {
				resetProbables(i, j);
			}
		}
		printProbables();
	}

	private void resetProbables(int row, int col) {
		if(a[row][col] == 0) {
			if (p[row][col] == null || p[row][col].size() == 0) {
				initProbables(row, col);
			}
		} else {
			p[row][col].clear();

			eliminateRowUnProbables(row, col);
			eliminateColUnProbables(row, col);
			eliminateBoxUnProbables(row, col);

			//printProbables();
		}
	}

	private void eliminateRowUnProbables(int row, int col) {
		for(int j=0; j<9; j++) {
			p[row][j].remove(a[row][col]);
		}
	}
	
	private void eliminateColUnProbables(int row, int col) {
		for(int i=0;i<9; i++) {
			p[i][col].remove(a[row][col]);
		}
	}

	private void eliminateBoxUnProbables(int row, int col) {
		int boxI = (row / 3) * 3;
		int boxJ = (col / 3) * 3;
		for(int i=boxI; i<(boxI+3); i++) {
			for(int j=boxJ; j<(boxJ+3); j++) {
				p[i][j].remove(a[row][col]);
			}
		}
	}

	private void solveDoubleProbableInRow(int row) {
		for(int j=0; j<9; j++) {
			if (p[row][j].size() == 2) {
				// double probable candidate
				boolean doubleFound = false;
				int foundCol = 10;

				for(int k=0; k<9; k++) {
					if (k != j && p[row][k].size() == 2 && p[row][k].containsAll(p[row][j])) {
						// double found
						foundCol = k;
						doubleFound = true;
					}
				}
				if (doubleFound) {
					// remove these probable from other elements of row
					for(int k=0; k<9; k++) {
						if (k != j && k != foundCol) {
							p[row][k].removeAll(p[row][j]);
						}
					}
					//printProbables();
				}
			}
		}
	}

	private void solveDoubleProbableInCol(int col) {
		for(int i=0; i<9; i++) {
			if (p[i][col].size() == 2) {
				// double probable candidate
				boolean doubleFound = false;
				int foundRow = 10;

				for(int k=0; k<9; k++) {
					if (k != i && p[k][col].size() == 2 && p[k][col].containsAll(p[i][col])) {
						// double found
						foundRow = k;
						doubleFound = true;
					}
				}
				if (doubleFound) {
					// remove these probable from other elements of col
					for(int k=0; k<9; k++) {
						if (k != i && k != foundRow) {
							p[k][col].removeAll(p[i][col]);
						}
					}
					//printProbables();
				}
			}
		}
	}

	private void solveDoubleProbableInBox(int row, int col) {
		int boxI = (row / 3) * 3;
		int boxJ = (col / 3) * 3;

		for(int i=boxI; i<(boxI+3); i++) {
			for(int j=boxJ; j<(boxJ+3); j++) {
				if (p[i][j].size() == 2) {
					// double probable candidate
					boolean doubleFound = false;
					int foundRow = 10;
					int foundCol = 10;

					for(int k=boxI; k<(boxI+3); k++) {
						for(int l=boxJ; l<(boxJ+3); l++) {
							if (!(k == i && l == j) && p[k][l].size() == 2 && p[k][l].containsAll(p[i][j])) {
								// double found
								foundRow = k;
								foundCol = l;
								doubleFound = true;
							}
						}
					}
					if (doubleFound) {
						// remove these probable from other elements of col
						for(int k=boxI; k<(boxI+3); k++) {
							for(int l=boxJ; l<(boxJ+3); l++) {
								if (!(k == i && l == j) && !(k == foundRow && l == foundCol)) {
									p[k][l].removeAll(p[i][j]);
								}
							}
						}
						//printProbables();
					}
				}
			}
		}
	}

	private void solveDoubleProbableInRowReverse(int row) {
		for(int j=0; j<9; j++) {
			solveDoubleProbableInRowReverse(row, j);
		}
	}

	private void solveDoubleProbableInRowReverse(int row, int col) {
		if (p[row][col].size() > 2) {
			// double probable candidate
			Integer[] arr = new Integer[0];
			arr = p[row][col].toArray(arr);
			for (int x=0; x<arr.length; x++) {
				int first = arr[x];
				for (int y=x+1; y<arr.length; y++) {
					int second = arr[y];
					int foundCol = 10;
					int counter = 1;
					for(int k=0; k<9; k++) {
						if (k != col && (p[row][k].contains(first) || p[row][k].contains(second))) {
							counter ++;
							foundCol = k;
						}
					}
					if (counter == 2) {
						// double found, keep this double and remove others from probables of those boxes
						if (p[row][col].contains(first) && p[row][col].contains(second)
								&& p[row][foundCol].contains(first) && p[row][foundCol].contains(second)) {
							/*
							if (foundCol == 10) {
								System.out.println("foundCol="+foundCol);
							}
							*/
							if (p[row][col].size() > 2) { 
								p[row][col].clear();
								p[row][col].add(first);
								p[row][col].add(second);
							}
	
							if (p[row][foundCol].size() > 2) { 
								p[row][foundCol].clear();
								p[row][foundCol].add(first);
								p[row][foundCol].add(second);
							}
	
							//printProbables();
							return;
						}
					}
				}
			}
		}
	}

	private void solveDoubleProbableInColReverse(int col) {
		for(int i=0; i<9; i++) {
			solveDoubleProbableInColReverse(i, col);
		}
	}

	private void solveDoubleProbableInColReverse(int row, int col) {
		if (p[row][col].size() > 2) {
			// double probable candidate
			Integer[] arr = new Integer[0];
			arr = p[row][col].toArray(arr);
			for (int x=0; x<arr.length; x++) {
				int first = arr[x];
				for (int y=x+1; y<arr.length; y++) {
					int second = arr[y];
					int foundRow = 10;
					int counter = 1;
					for(int k=0; k<9; k++) {
						if (k != row && (p[k][col].contains(first) || p[k][col].contains(second))) {
							counter ++;
							foundRow = k;
						}
					}
					if (counter == 2) {
						if (p[row][col].contains(first) && p[row][col].contains(second)
								&& p[foundRow][col].contains(first) && p[foundRow][col].contains(second)) {
							// double found, keep this double and remove others from probables of those boxes
							if (p[row][col].size() > 2) {
								p[row][col].clear();
								p[row][col].add(first);
								p[row][col].add(second);
							}
	
							if (p[foundRow][col].size() > 2) { 
								p[foundRow][col].clear();
								p[foundRow][col].add(first);
								p[foundRow][col].add(second);
							}
	
							//printProbables();
							return;
						}
					}
				}
			}
		}
	}

	private void solveDoubleProbableInBoxReverse(int row, int col) {
		int boxI = (row / 3) * 3;
		int boxJ = (col / 3) * 3;

		for (int i=boxI; i<(boxI+3); i++) {
			for (int j=boxJ; j<(boxJ+3); j++) {
				if (p[i][j].size() > 2) {
					// double probable candidate
					Integer[] arr = new Integer[0];
					arr = p[i][j].toArray(arr);
					for (int x=0; x<arr.length; x++) {
						int first = arr[x];
						for (int y=x+1; y<arr.length; y++) {
							int second = arr[y];
							int foundRow = 10;
							int foundCol = 10;
							int counter = 1;
							for (int k=boxI; k<(boxI+3); k++) {
								for (int l=boxJ; l<(boxJ+3); l++) {
									if (!(k == i && l == j) && (p[k][l].contains(first) || p[k][l].contains(second))) {
										counter ++;
										foundRow = k;
										foundCol = l;
									}
								}
							}
							if (counter == 2) {
								// double found, keep this double and remove others from probables of those boxes
								if (p[i][j].contains(first) && p[i][j].contains(second)
										&& p[foundRow][foundCol].contains(first)
										&& p[foundRow][foundCol].contains(second)) {
									printProbables();
									if (p[i][j].size() > 2) {
										p[i][j].clear();
										p[i][j].add(first);
										p[i][j].add(second);
									}
			
									if (p[foundRow][foundCol].size() > 2) { 
										p[foundRow][foundCol].clear();
										p[foundRow][foundCol].add(first);
										p[foundRow][foundCol].add(second);
									}
			
									printProbables();
									return;
								}
							}
						}
					}
				}
			}
		}
	}

	private void print() {
		printPuzzle();
		printProbables();
	}

	private void printPuzzle() {
		System.out.println("------------------");
		for(int i=0; i<9; i++) {
			//System.out.print("|");
			for(int j=0; j<9; j++) {
				if(a[i][j] == 0) {
					System.out.print(" ");
				} else {
					System.out.print(a[i][j]);
				}
				System.out.print(" ");
				//System.out.print("|");
			}
			System.out.println();
			//System.out.println("\n------------------");	
		}
	}
	
	private void printProbables() {
		System.out.println("==================");
		for(int i=0; i<9; i++) {
			for(int j=0; j<9; j++) {
				System.out.print("p" + i + j + "=");
				printProbables(i, j);
			}
			System.out.println();
		}
	}

	private void printProbables(int row, int col) {
		for(int k=1; k<=9; k++) {
			if(p[row][col].contains(k)) {
				System.out.print(k);
			} else {
				System.out.print(" ");
			}
		}
		System.out.print("\t");
	}

	private void printDups(int i, int j, int k, int l) {
		System.err.println("Duplicate value " + a[i][j] + " found for a[" + i + "][" + j + "] & a[" + k + "][" + l + "]");		
	}

	public void setKnownValues() {
		//	a[0][0] = 0;
		//	a[0][1] = 0;
			a[0][2] = 3;
			a[0][3] = 7;
		//	a[0][4] = 0;
			a[0][5] = 4;
			a[0][6] = 2;
		//	a[0][7] = 0;
		//	a[0][8] = 0;

		//	a[1][0] = 0;
		//	a[1][1] = 0;
		//	a[1][2] = 0;
		//	a[1][3] = 0;
		//	a[1][4] = 0;
		//	a[1][5] = 0;
		//	a[1][6] = 0;
		//	a[1][7] = 0;
		//	a[1][8] = 0;

			a[2][0] = 5;
		//	a[2][1] = 0;
			a[2][2] = 8;
		//	a[2][3] = 0;
		//	a[2][4] = 0;
		//	a[2][5] = 0;
			a[2][6] = 6;
		//	a[2][7] = 0;
			a[2][8] = 1;

			a[3][0] = 2;
		//	a[3][1] = 0;
		//	a[3][2] = 0;
		//	a[3][3] = 0;
			a[3][4] = 9;
		//	a[3][5] = 0;
		//	a[3][6] = 0;
		//	a[3][7] = 0;
			a[3][8] = 3;

		//	a[4][0] = 0;
		//	a[4][1] = 0;
		//	a[4][2] = 0;
			a[4][3] = 8;
		//	a[4][4] = 0;
			a[4][5] = 6;
		//	a[4][6] = 0;
		//	a[4][7] = 0;
		//	a[4][8] = 0;

			a[5][0] = 1;
		//	a[5][1] = 0;
		//	a[5][2] = 0;
		//	a[5][3] = 0;
			a[5][4] = 7;
		//	a[5][5] = 0;
		//	a[5][6] = 0;
		//	a[5][7] = 0;
			a[5][8] = 6;

			a[6][0] = 3;
		//	a[6][1] = 0;
			a[6][2] = 1;
		//	a[6][3] = 0;
		//	a[6][4] = 0;
		//	a[6][5] = 0;
			a[6][6] = 5;
		//	a[6][7] = 0;
			a[6][8] = 8;

		//	a[7][0] = 0;
		//	a[7][1] = 0;
		//	a[7][2] = 0;
		//	a[7][3] = 0;
		//	a[7][4] = 0;
		//	a[7][5] = 0;
		//	a[7][6] = 0;
		//	a[7][7] = 0;
		//	a[7][8] = 0;

		//	a[8][0] = 0;
		//	a[8][1] = 0;
			a[8][2] = 9;
			a[8][3] = 4;
		//	a[8][4] = 0;
			a[8][5] = 5;
			a[8][6] = 7;
		//	a[8][7] = 0;
		//	a[8][8] = 0;

		printPuzzle();

		if (! isValid()) {
			exitAsInvalid();
		}
	}
}
