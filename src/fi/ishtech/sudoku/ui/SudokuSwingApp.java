package fi.ishtech.sudoku.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

import fi.ishtech.sudoku.solver.SudokuAltSolver;
//import fi.ishtech.sudoku.solver.SudokuSolver;

public class SudokuSwingApp extends JFrame {

	private static final long serialVersionUID = -240736298882872710L;

	private JTextField[][] sudokuGrid;
	private JButton solveButton;
	private JButton newButton;
	private JLabel exceptionLabel; // New label for displaying sample text

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
				JTextField textField = createTextField();
				sudokuGrid[i][j] = textField;
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

		exceptionLabel = new JLabel("");
		exceptionLabel.setForeground(Color.BLACK);

		JPanel buttonPanel = new JPanel();
		buttonPanel.add(solveButton);
		buttonPanel.add(newButton);

		JPanel exceptionPanel = new JPanel();
		exceptionPanel.add(exceptionLabel);

		JPanel southPanel = new JPanel();
		southPanel.setLayout(new BorderLayout());
		southPanel.add(buttonPanel, BorderLayout.NORTH);
		southPanel.add(exceptionPanel, BorderLayout.SOUTH);

		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new BorderLayout());
		contentPanel.add(mainPanel, BorderLayout.CENTER);
		contentPanel.add(southPanel, BorderLayout.SOUTH);

		getContentPane().add(contentPanel);
	}

	private void solveSudoku() {
		// Disable input fields and Solve button after clicking
		disableInputFields();
		solveButton.setEnabled(false);

		String[][] inputArray = new String[9][9];

		System.out.println("Input:");
/*
		// Read input values from UI
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				String text = sudokuGrid[i][j].getText();

				System.out.print("[" + i + "][" + j + "]=" + (text == null || text.isEmpty() ? "\'\'" : text));

				inputArray[i][j] = text;
			}
			System.out.println("");
		}
*/

		inputArray[0][0]="";
		inputArray[0][1]="";
		inputArray[0][2]="";
		inputArray[0][3]="";
		inputArray[0][4]="1";
		inputArray[0][5]="6";
		inputArray[0][6]="";
		inputArray[0][7]="7";
		inputArray[0][8]="";
		inputArray[1][0]="";
		inputArray[1][1]="2";
		inputArray[1][2]="8";
		inputArray[1][3]="";
		inputArray[1][4]="";
		inputArray[1][5]="";
		inputArray[1][6]="1";
		inputArray[1][7]="";
		inputArray[1][8]="";
		inputArray[2][0]="";
		inputArray[2][1]="";
		inputArray[2][2]="";
		inputArray[2][3]="3";
		inputArray[2][4]="";
		inputArray[2][5]="";
		inputArray[2][6]="";
		inputArray[2][7]="6";
		inputArray[2][8]="";
		inputArray[3][0]="5";
		inputArray[3][1]="";
		inputArray[3][2]="7";
		inputArray[3][3]="9";
		inputArray[3][4]="";
		inputArray[3][5]="";
		inputArray[3][6]="";
		inputArray[3][7]="";
		inputArray[3][8]="";
		inputArray[4][0]="";
		inputArray[4][1]="";
		inputArray[4][2]="1";
		inputArray[4][3]="";
		inputArray[4][4]="";
		inputArray[4][5]="";
		inputArray[4][6]="4";
		inputArray[4][7]="";
		inputArray[4][8]="";
		inputArray[5][0]="";
		inputArray[5][1]="";
		inputArray[5][2]="";
		inputArray[5][3]="";
		inputArray[5][4]="";
		inputArray[5][5]="1";
		inputArray[5][6]="5";
		inputArray[5][7]="";
		inputArray[5][8]="9";
		inputArray[6][0]="";
		inputArray[6][1]="3";
		inputArray[6][2]="";
		inputArray[6][3]="";
		inputArray[6][4]="";
		inputArray[6][5]="5";
		inputArray[6][6]="";
		inputArray[6][7]="";
		inputArray[6][8]="";
		inputArray[7][0]="";
		inputArray[7][1]="";
		inputArray[7][2]="5";
		inputArray[7][3]="";
		inputArray[7][4]="";
		inputArray[7][5]="";
		inputArray[7][6]="7";
		inputArray[7][7]="2";
		inputArray[7][8]="";
		inputArray[8][0]="";
		inputArray[8][1]="9";
		inputArray[8][2]="";
		inputArray[8][3]="7";
		inputArray[8][4]="8";
		inputArray[8][5]="";
		inputArray[8][6]="";
		inputArray[8][7]="";
		inputArray[8][8]="";

		try {
			SudokuAltSolver sudokuAltSolver = new SudokuAltSolver(inputArray);
			int[][] solvedArray = sudokuAltSolver.solve();
		/*
			// Pass the 2D array to solver
			SudokuSolver sudokuSolver = new SudokuSolver();
			int[][] solvedArray = sudokuSolver.solve(inputArray);
		*/

			// Update the UI with the solved results
			updateUI(solvedArray);

			// Clear any previous exception message
			exceptionLabel.setText("");
		} catch (Exception e) {
			// Handle exception by displaying the message in the exceptionPanel
			exceptionLabel.setText(e.getMessage() != null ? e.getMessage() : e.getClass().getName());
			exceptionLabel.setForeground(Color.RED);
		}
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
		// reset the exceptionLabel
		exceptionLabel.setText("");
		exceptionLabel.setForeground(Color.BLACK);

		// Enable input fields and clear their text
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

	private JTextField createTextField() {
		JTextField textField = new JTextField();
		textField.setHorizontalAlignment(JTextField.CENTER);

		// Add a DocumentFilter to restrict input to a single digit
		AbstractDocument document = (AbstractDocument) textField.getDocument();
		document.setDocumentFilter(new SingleDigitDocumentFilter());

		return textField;
	}

	private class SingleDigitDocumentFilter extends DocumentFilter {
		@Override
		public void insertString(DocumentFilter.FilterBypass fb, int offset, String string, AttributeSet attr)
				throws BadLocationException {
			String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
			String newText = currentText.substring(0, offset) + string + currentText.substring(offset);

			if (isValidInput(newText)) {
				super.insertString(fb, offset, string, attr);
			}
		}

		@Override
		public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
				throws BadLocationException {
			String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
			String newText = currentText.substring(0, offset) + text + currentText.substring(offset + length);

			if (isValidInput(newText)) {
				super.replace(fb, offset, length, text, attrs);
			}
		}

		private boolean isValidInput(String text) {
			// Check if the resulting text is a single digit between 1 and 9 or an empty
			// string
			return text.matches("[1-9]") || text.isEmpty();
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
