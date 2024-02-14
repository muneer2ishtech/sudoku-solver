package fi.ishtech.sudoku.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class SudokuSwingApp extends JFrame {

	private static final long serialVersionUID = -240736298882872710L;

	private JTextField[][] sudokuGrid;

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
				mainPanel.add(textField);
			}
		}

		JButton solveButton = new JButton("Solve");
		solveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				solveSudoku();
			}
		});

		JPanel buttonPanel = new JPanel();
		buttonPanel.add(solveButton);

		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		getContentPane().add(buttonPanel, BorderLayout.SOUTH);
	}

	private void solveSudoku() {
		int[][] sudokuArray = new int[9][9];

		// Read input values from UI
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				String text = sudokuGrid[i][j].getText();
				sudokuArray[i][j] = (text.isEmpty()) ? 0 : Integer.parseInt(text);
			}
		}

		// Pass the 2D array to another method for solving
		solveSudokuPuzzle(sudokuArray);
	}

	private void solveSudokuPuzzle(int[][] sudokuArray) {
		// Implement your Sudoku-solving logic here
		// You can use the provided 2D array 'sudokuArray'
		// Update the UI or display the solution as needed
	}

	private void updateUI(int[][] solvedArray) {
		// Update the UI with the solved results
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				sudokuGrid[i][j].setText(String.valueOf(solvedArray[i][j]));
			}
		}
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
