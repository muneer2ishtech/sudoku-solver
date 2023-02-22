package com.muneer.sudoku;

import java.util.TreeSet;

/**
 * @author Muneer Ahmed Syed
 * @version 0.2
 * This class solves Sodoku puzzle
 * TODO: to process two probable in reverse
 * TODO: to process three probable
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
		setKnownValues();
	}

	public static void main(String[] args) {
		SudokuSolver sudokuSolver = new SudokuSolver();

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

	public void solve() {
		resetProbables();

		do {
			for(int i=0; i<9; i++) {
				solveUniqProbableInRow(i);
				for(int j=0; j<9; j++) {
					System.out.println("****************** i=" + i + " j=" + j);

					solveUniqProbable(i, j);

					solveUniqProbableInCol(j);
					solveUniqProbableInBox(i, j);

					solveDoubleProbableInCol(j);
					solveDoubleProbableInBox(i, j);

					solveUniqProbable(i, j);

					if(a[i][j] != 0) {
						resetProbables(i, j);
					}

					isResolved();
				}

				solveDoubleProbableInRow(i);

			}

			exitAfterMaxTry();

		} while(! isResolved());
	}

	private void exitSuccess() {
		System.out.println("Successful in " + tryc + " iterations");
		printPuzzle();
		System.exit(0);
	}

	private void exitAfterMaxTry() {
		tryc ++;
		int maxTry = 1000;
		//resetProbables();
		if (tryc % maxTry == 0 ) {
			System.out.println("Tried and failed after " + tryc + " iterations");
			printProbables();
			System.exit(3);
		}
	}

	private boolean isResolved() {
		for(int i=0; i<9; i++) {
			for(int j=0; j<9; j++) {
				if(a[i][j] == 0) {
					return false;
				}
			}
		}

		if (! isValid()) {
			System.exit(2);
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
			for (int j=0; j<9; j++) {
				for (int k=0; k<9; k++) {
					if (j != k && a[i][j] != 0 && a[i][j] == a[i][k]) {
						// duplicate found
						System.out.println("Duplicate value " + a[i][j] + " found for a[" + i + "][" + j + "] & a[" + i + "][" + k + "]");
						return true;
					}
				}
				
			}
		}
		return false;
	}

	private boolean isColInvalid() {
		// validate row
		for (int j=0; j<9; j++) {
			for (int i=0; i<9; i++) {
				for (int k=0; k<9; k++) {
					if (i != k &&  a[i][j] != 0 && a[i][j] == a[k][j]) {
						// duplicate found
						System.out.println("Duplicate value " + a[i][j] + " found for a[" + i + "][" + j + "] & a[" + k + "][" + j + "]");
						return true;
					}
				}
				
			}
		}
		return false;
	}

	private boolean isBoxInvalid() {
		// validate row
		for (int boxI=0; boxI<9; boxI+=3) {
			for (int boxJ=0; boxJ<9; boxJ+=3) {
				for (int i=boxI; i<boxI+3; i++) {
					for (int j=boxJ; j<boxJ+3; j++) {
						for (int k=boxI; k<boxI+3; k++) {
							for (int l=boxJ; l<boxJ+3; l++) {
								if (i != k && j != l  && a[i][j] != 0 && a[i][j] == a[k][l]) {
									// duplicate found
									System.out.println("Duplicate value " + a[i][j] + " found for a[" + i + "][" + j + "] & a[" + k + "][" + l + "]");
									return true;
								}
							}
						}
					}
				}
			}
		}

		return false;
	}

	private void solveUniqProbableInRow(int row) {
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

	private void solveUniqProbable(int row, int col) {
		if(a[row][col] == 0 && p[row][col].size() == 1 ) {
			setSolvedValue(row, col, p[row][col].first().intValue());
		}		
	}

	private void solveUniqProbableInCol(int col) {
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
		isValid();
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
					printProbables();
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
					printProbables();
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
						printProbables();
					}
				}
			}
		}
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
		}

		eliminateRowUnProbables(row, col);
		eliminateColUnProbables(row, col);
		eliminateBoxUnProbables(row, col);

		//printProbables();
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
/*
	private char toChar(int digit) {
		switch(digit) {
			case 0: return '0';
			case 1: return '1';
			case 2: return '2';
			case 3: return '3';
			case 4: return '4';
			case 5: return '5';
			case 6: return '6';
			case 7: return '7';
			case 8: return '8';
			case 9: return '9';
		}

		return '0';
	}
*/

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

	public void setKnownValues() {
		//	a[0][0] = 0;
		//	a[0][1] = 0;
		//	a[0][2] = 0;
		//	a[0][3] = 0;
		//	a[0][4] = 0;
		//	a[0][5] = 0;
		//	a[0][6] = 0;
		//	a[0][7] = 0;
		//	a[0][8] = 0;

		//	a[1][0] = 0;
			a[1][1] = 5;
		//	a[1][2] = 0;
		//	a[1][3] = 0;
			a[1][4] = 2;
		//	a[1][5] = 0;
		//	a[1][6] = 0;
			a[1][7] = 6;
		//	a[1][8] = 0;

		//	a[2][0] = 0;
		//	a[2][1] = 0;
			a[2][2] = 1;
			a[2][3] = 4;
		//	a[2][4] = 0;
			a[2][5] = 6;
			a[2][6] = 5;
		//	a[2][7] = 0;
		//	a[2][8] = 0;

		//	a[3][0] = 0;
		//	a[3][1] = 0;
			a[3][2] = 6;
		//	a[3][3] = 0;
		//	a[3][4] = 0;
		//	a[3][5] = 0;
			a[3][6] = 1;
		//	a[3][7] = 0;
		//	a[3][8] = 0;

		//	a[4][0] = 0;
			a[4][1] = 3;
		//	a[4][2] = 0;
		//	a[4][3] = 0;
			a[4][4] = 8;
		//	a[4][5] = 0;
		//	a[4][6] = 0;
			a[4][7] = 2;
		//	a[4][8] = 0;

			a[5][0] = 0;
		//	a[5][1] = 0;
			a[5][2] = 9;
		//	a[5][3] = 0;
			a[5][4] = 0;
		//	a[5][5] = 0;
			a[5][6] = 6;
		//	a[5][7] = 0;
		//	a[5][8] = 0;

		//	a[6][0] = 0;
		//	a[6][1] = 0;
			a[6][2] = 5;
			a[6][3] = 7;
		//	a[6][4] = 0;
			a[6][5] = 1;
			a[6][6] = 9;
		//	a[6][7] = 0;
		//	a[6][8] = 0;

		//	a[7][0] = 0;
			a[7][1] = 7;
		//	a[7][2] = 0;
		//	a[7][3] = 0;
			a[7][4] = 3;
		//	a[7][5] = 0;
		//	a[7][6] = 0;
			a[7][7] = 4;
			a[7][8] = 0;

		//	a[8][0] = 0;
		//	a[8][1] = 0;
		//	a[8][2] = 0;
		//	a[8][3] = 0;
		//	a[8][4] = 0;
		//	a[8][5] = 0;
		//	a[8][6] = 0;
		//	a[8][7] = 0;
		//	a[8][8] = 0;

		printPuzzle();

		if (! isValid()) {
			System.exit(1);
		}
	}
}
