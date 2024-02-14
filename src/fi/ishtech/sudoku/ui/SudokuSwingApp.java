package fi.ishtech.sudoku.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import fi.ishtech.sudoku.solver.SudokuSolver;

public class SudokuSwingApp extends JFrame {

	private static final long serialVersionUID = -240736298882872710L;

	private JTextField[][] sudokuGrid;
	private JButton solveButton;
	private JButton newButton;

	public SudokuSwingApp() {
		super("Sudoku Solver");
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		initializeUI();
	}

	private void initializeUI() {
		sudokuGrid = new JTextField[9][9];

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(9, 9, 5, 5));

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				JTextField textField = new JTextField();
				textField.setHorizontalAlignment(JTextField.CENTER);
				sudokuGrid[i][j] = textField;

				// Add a border to create a visual separation for 3x3 blocks
				int top = (i % 3 == 0) ? 2 : 0;
				int left = (j % 3 == 0) ? 2 : 0;
				int bottom = (i == 8) ? 2 : 0;
				int right = (j == 8) ? 2 : 0;

				textField.setBorder(BorderFactory.createMatteBorder(top, left, bottom, right, Color.GRAY));

				mainPanel.add(textField);
			}
		}

		solveButton = new JButton("Solve");
		solveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				solveSudoku();
			}
		});

		newButton = new JButton("New");
		newButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				resetSudoku();
			}
		});

		JPanel buttonPanel = new JPanel();
		buttonPanel.add(solveButton);
		buttonPanel.add(newButton);

		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		getContentPane().add(buttonPanel, BorderLayout.SOUTH);
	}

	private void solveSudoku() {
		String[][] inputArray = new String[9][9];

		System.out.println("Input:");

		// Read input values from UI
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				String text = sudokuGrid[i][j].getText();

				System.out.print("[" + i + "][" + j + "]=" + (text == null || text.isEmpty() ? "\'\'" : text));

				inputArray[i][j] = text;
			}
			System.out.println("");
		}

		// Disable input fields and Solve button after clicking
		disableInputFields();
		solveButton.setEnabled(false);

		// Pass the 2D array to solver
		SudokuSolver sudokuSolver = new SudokuSolver();
		int[][] solvedArray = sudokuSolver.solve(inputArray);

		// Update the UI with the solved results
		updateUI(solvedArray);
	}

	private void updateUI(int[][] solvedArray) {
		// Update the UI with the solved results
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				JTextField textField = sudokuGrid[i][j];
				String currentValue = textField.getText();
				String solvedValue = String.valueOf(solvedArray[i][j]);

				// Set font color based on input or solved value
				if (!currentValue.isEmpty()) {
					// User-input value
					textField.setForeground(Color.RED);
				} else {
					// Solved value
					textField.setForeground(Color.BLUE);
				}

				textField.setText(solvedValue);
			}
		}
	}

	private void disableInputFields() {
		// Disable input fields
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				sudokuGrid[i][j].setEditable(false);
			}
		}
	}

	private void resetSudoku() {
		// Enable input fields
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				sudokuGrid[i][j].setEditable(true);
				sudokuGrid[i][j].setText("");
				sudokuGrid[i][j].setForeground(Color.BLACK); // Reset font color to default
			}
		}

		// Enable Solve button
		solveButton.setEnabled(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new SudokuSwingApp().setVisible(true);
			}
		});
	}

}
