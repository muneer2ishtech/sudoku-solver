package com.muneer.sudoku;

/**
 * @author Muneer Ahmed Syed
 * @version 0.1
 * This class is to solve sodoku puzzle
 */
public class SudokuSolver {

	private int a[][];
	private String p[][];
	//private int counter = 0;
	private int tryc = 0;

	public SudokuSolver() {
		init();
		setKnownValues();
	}

	public static void main(String[] args) {
		SudokuSolver sudokuSolver = new SudokuSolver();
		
		sudokuSolver.solve();
		//sudokuSolver.printProbables();
		
		sudokuSolver.print();
	}

	private void init() {
		a = new int[9][9];
		p = new String[9][9];
		for(int i=0; i<9; i++) {
			for(int j=0; j<9; j++) {
				a[i][j] = 0;
				p[i][j] = "123456789";
			}
		}
	}

	public void solve() {
		resetProbables();
		//findProbables();
		printProbables();
		do {
			for(int i=0; i<9; i++) {
				solveOneMissingInRow(i);
				for(int j=0; j<9; j++) {
					System.out.println("****************** i=" + i + " j=" + j);
					solveOneMissingInCol(j);
					solveOneMissingInBox(i, j);
					solveUniqueProbable();
					if(a[i][j] == 0) {
						solveByElimination(i, j);
						solveByProbability(i, j);
						//resetProbables(i, j);
						//print();
					}
					resetProbables(i, j);
				}
			}

			solveByRow();
			//solveByCol();
			//solveByBox();
			print();
		} while(!checkIfAllResolved());
	}

	private boolean checkIfAllResolved() {
		tryc ++;
		resetProbables();
		if (tryc % 1000 == 0 ) {
			System.out.println("Tried and failed after " + tryc + " attempts");
			printProbables();
			//solveByRow();
			//solveByCol();
			//solveByBox();
			System.exit(1);
		}
		for(int i=0; i<9; i++) {
			for(int j=0; j<9; j++) {
				if(a[i][j] == 0) {
					return false;
				}
			}
		}

		return true;
	}

	private void solveByProbability() {
		for(int i=0; i<9; i++) {
			for(int j=0; j<9; j++) {
				if(a[i][j] == 0) {
					//System.out.println("counter=" + ++ counter);
					findProbables(i, j);
					if((p[i][j]).length() == 1) {
						a[i][j] = Integer.parseInt(p[i][j]);
						resetProbables(i, j);
					}
				}
			}
		}
	}

	private void solveByProbability(int row, int col) {
		if(a[row][col] == 0) {
			//System.out.println("counter=" + ++ counter);
			findProbables(row, col);
			if((p[row][col]).length() == 1) {
				a[row][col] = Integer.parseInt(p[row][col]);
				resetProbables(row, col);
			}
		}
	}

	private void solveByRow() {
		for(int i=0; i<9; i++) {
			solveByRow(i);
		}
		resetProbables();
	}

	private void solveByRow(int row) {
		String rowProbables = "123456789";
		int blanks;
		int cans;
		int lastCanCol = 0;
		int num;

		for(int j=0; j<9; j++) {
			rowProbables = rowProbables.replaceAll(String.valueOf(a[row][j]), "");
		}

		blanks = rowProbables.length();

		for(int k=0; k<blanks; k++) {
			cans = 0;
			num = Integer.parseInt(String.valueOf(rowProbables.charAt(k)));
			for(int j=0; j<9; j++) {
				if(a[row][j] == 0) {
					cans = 0;
					if(canBeInCol(j, num) && canBeInBox(row, j, num)) {
						++cans;
						lastCanCol = j;
					}
				}
			}
			if(cans == 1) {
				a[row][lastCanCol] = num;
				resetProbables(row, lastCanCol);
				print();
			}
		}
	}
	
	private void solveByCol() {
		for(int j=0; j<9; j++) {
			solveByCol(j);
		}
		resetProbables();
	}

	private void solveByCol(int col) {
		String colProbables = "123456789";
		int blanks;
		int cans;
		int lastCanRow = 0;
		int num;
		for(int i=0; i<9; i++) {
			colProbables = colProbables.replaceAll(String.valueOf(a[i][col]), "");
		}
		blanks = colProbables.length();
		for(int k=0; k<blanks; k++) {
			cans = 0;
			num = Integer.parseInt(String.valueOf(colProbables.charAt(k)));
			for(int i=0; i<9; i++) {
				if(a[i][col] == 0) {
					cans = 0;
					if(canBeInRow(i, num) && canBeInBox(i, col, num)) {
						cans++;
						lastCanRow = i;
					}
				}
			}
			if(cans == 1) {
				a[lastCanRow][col] = num;
				resetProbables(lastCanRow, col);
				print();
			}
		}
	}

	private void solveByElimination(int row, int col) {
		//System.out.println("counter=" + ++ counter);
		int num;
		for(int k=0; k<p[row][col].length(); k++) {
			num = Integer.parseInt(String.valueOf(p[row][col].charAt(k)));
			if((! canBeInRow(row, num)) || (! canBeInCol(col, num)) || (! canBeInBox(row, col, num))) {
				p[row][col] = (p[row][col]).replaceAll(String.valueOf(num), "");
			}
		}
		solveUniqueProbable(row, col);
	}

	private void solveUniqueProbable(int row, int col) {
		if(a[row][col] == 0) {
			if(p[row][col].length() == 1) {
				a[row][col] = Integer.parseInt(p[row][col]);
				resetProbables(row, col);
				print();
			}
		}
	}

	private void solveUniqueProbable() {
		//System.out.println("counter=" + ++ counter);
		for(int i=0; i<9; i++) {
			for(int j=0; j<9; j++) {
				solveUniqueProbable(i, j);
			}
		}
	}

	private void solveOneMissingInRow(int row) {
		int count = 0;
		int missingCol = 0;
		String probables = "123456789";
		for(int j=0; j<9; j++) {
			missingCol = j;
			if(a[row][j] == 0) {
				if(++count > 1) {
					break;
				}
			} else {
				probables = probables.replaceAll(String.valueOf(a[row][j]), "");
			}
		}

		if(count==1 && probables.length()==1) {
			// only one is missing in the row
			a[row][missingCol] = Integer.parseInt(probables);
			print();
		}
	}

	private void solveOneMissingInCol(int col) {
		int count = 0;
		int missingRow = 0;
		String probables = "123456789";
		for(int i=0; i<9; i++) {
			missingRow = i;
			if(a[i][col] == 0) {
				if(++count > 1) {
					break;
				}
			} else {
				probables = probables.replaceAll(String.valueOf(a[i][col]), "");
			}
		}

		if(count==1 && probables.length()==1) {
			// only one is missing in the row
			a[missingRow][col] = Integer.parseInt(probables);
			print();
		}
	}

	private void solveOneMissingInBox(int row, int col) {
		int count = 0;
		int missingRow = 0;
		int missingCol = 0;
		String probables = "123456789";
		int boxI = (row / 3) * 3;
		int boxJ = (col / 3) * 3;
		for(int i=boxI; i<(boxI+3); i++) {
			for(int j=boxJ; j<(boxJ+3); j++) {
				missingRow = i;
				missingCol = j;
				if(a[i][j] == 0) {
					if(++count > 1) {
						break;
					}
				} else {
					probables = probables.replaceAll(String.valueOf(a[i][j]), "");
				}
			}
		}

		if(count==1 && probables.length()==1) {
			// only one is missing in the box
			a[missingRow][missingCol] = Integer.parseInt(probables);
			print();
		}
	}

	private boolean canBeInRow(int row, int num) {
		for(int j=0; j<9; j++) {
			if(num == a[row][j]) {
				return false;
			}
		}
		return true;
	}

	private boolean canBeInCol(int col, int num) {
		for(int i=0; i<9; i++) {
			if(num == a[i][col]) {
				return false;
			}
		}

		return true;
	}

	private boolean canBeInBox(int row, int col, int num) {
		int boxI = (row / 3) * 3;
		int boxJ = (col / 3) * 3;
		for(int i=boxI; i<(boxI+3); i++) {
			for(int j=boxJ; j<(boxJ+3); j++) {
				if(num == a[i][j]) {
					return false;
				}
			}
		}
		return true;
	}
	
	private void findProbables() {
		//resetProbables();
		for(int i=0; i<9; i++) {
			for(int j=0; j<9; j++) {
				findProbables(i, j);
			}
		}
	}

	private void findProbables(int row, int col) {
		//resetProbables(row, col);
		if(a[row][col] == 0) {
			eliminateRowUnProbables(row, col);
			eliminateColUnProbables(row, col);
			eliminateBoxUnProbables(row, col);
			//printProbables();
		}
	}
	
	private void resetProbables() {
		for(int i=0; i<9; i++) {
			for(int j=0; j<9; j++) {
				resetProbables(i, j);
			}
		}
		//printProbables();
	}

	private void resetProbables(int row, int col) {
		if(a[row][col] == 0) {
			if (p[row][col].equals("")) {
				p[row][col] = "123456789";
			}
			findProbables(row, col);
		}
		if(a[row][col] != 0) {
			p[row][col] = "";
		}
		//printProbables();
	}

	private void eliminateRowUnProbables(int row, int col) {
		for(int j=0; j<9; j++) {
			if((p[row][col]).indexOf(String.valueOf(a[row][j])) == -1) {
				// means a[row][j]=0 or a[row][j] is already eliminated from probables 
				continue;
			} else {
				// a[row][j] cannot come in a[row][col]
				p[row][col] = (p[row][col]).replaceAll(String.valueOf(a[row][j]), "");
			}
		}
	}
	
	private void eliminateColUnProbables(int row, int col) {
		for(int i=0;i<9; i++) {
			if((p[row][col]).indexOf(String.valueOf(a[i][col])) == -1) {
				// means a[i][col]=0 or a[i][col] is already eliminated from probables 
				continue;
			} else {
				// a[i][col] cannot come in a[row][col]
				p[row][col] = (p[row][col]).replaceAll(String.valueOf(a[i][col]), "");
			}
		}
	}

	private void eliminateBoxUnProbables(int row, int col) {
		int boxI = (row / 3) * 3;
		int boxJ = (col / 3) * 3;
		for(int i=boxI; i<(boxI+3); i++) {
			for(int j=boxJ; j<(boxJ+3); j++) {
				if((p[row][col]).indexOf(String.valueOf(a[i][j])) == -1) {
					// means a[i][j]=0 or a[i][j] is already eliminated from probables 
					continue;
				} else {
					// a[i][j] cannot come in a[row][col]
					p[row][col] = (p[row][col]).replaceAll(String.valueOf(a[i][j]), "");
				}
			}
		}
	}

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

	public void setKnownValues() {
	//	a[0][0] = 3;
	//	a[0][1] = 3;
	//	a[0][2] = 3;
		a[0][3] = 3;
	//	a[0][4] = 3;
	//	a[0][5] = 3;
	//	a[0][6] = 3;
	//	a[0][7] = 3;
		a[0][8] = 1;

		a[1][0] = 5;
	//	a[1][1] = 8;
		a[1][2] = 6;
	//	a[1][3] = 8;
		a[1][4] = 8;
		a[1][5] = 4;
	//	a[1][6] = 8;
	//	a[1][7] = 8;
	//	a[1][8] = 8;

		a[2][0] = 9;
	//	a[2][1] = 8;
	//	a[2][2] = 8;
	//	a[2][3] = 8;
		a[2][4] = 6;
	//	a[2][5] = 6;
	//	a[2][6] = 8;
	//	a[2][7] = 8;
	//	a[2][8] = 8;

	//	a[3][0] = 8;
		a[3][1] = 4;
	//	a[3][2] = 8;
		a[3][3] = 6;
	//	a[3][4] = 2;
	//	a[3][5] = 6;
	//	a[3][6] = 8;
		a[3][7] = 2;
		a[3][8] = 7;

	//	a[4][0] = 8;
	//	a[4][1] = 8;
		a[4][2] = 5;
	//	a[4][3] = 8;
	//	a[4][4] = 2;
	//	a[4][5] = 6;
		a[4][6] = 8;
	//	a[4][7] = 8;
	//	a[4][8] = 8;

		a[5][0] = 2;
		a[5][1] = 6;
	//	a[5][2] = 8;
	//	a[5][3] = 8;
	//	a[5][4] = 2;
		a[5][5] = 8;
	//	a[5][6] = 8;
		a[5][7] = 5;
	//	a[5][8] = 8;

	//	a[6][0] = 8;
	//	a[6][1] = 8;
	//	a[6][2] = 8;
	//	a[6][3] = 8;
		a[6][4] = 7;
	//	a[6][5] = 6;
	//	a[6][6] = 8;
	//	a[6][7] = 8;
		a[6][8] = 6;

	//	a[7][0] = 8;
	//	a[7][1] = 8;
	//	a[7][2] = 8;
		a[7][3] = 1;
		a[7][4] = 3;
	//	a[7][5] = 6;
		a[7][6] = 7;
	//	a[7][7] = 8;
		a[7][8] = 2;

		a[8][0] = 6;
	//	a[8][1] = 8;
	//	a[8][2] = 8;
	//	a[8][3] = 8;
	//	a[8][4] = 2;
		a[8][5] = 9;
	//	a[8][6] = 8;
	//	a[8][7] = 8;
	//	a[8][8] = 8;

	}
	
	private void print() {
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
		printProbables();
	}
	
	private void printProbables() {
		System.out.println("==================");
		for(int i=0; i<9; i++) {
			for(int j=0; j<9; j++) {
				System.out.print("p" + i + j + "=" + p[i][j]);
				for(int k=9; k>p[i][j].length(); k--) {
					System.out.print(" ");
				}
			}
			System.out.println();
		}
	}
	
	private void printNonBalnkProbables() {
		for(int i=0; i<9; i++) {
			for(int j=0; j<9; j++) {
				if(! p[i][j].equals("")) {
					System.out.print("p" + i + j + "=" + p[i][j]);
				}
			}
		}
	}
}
