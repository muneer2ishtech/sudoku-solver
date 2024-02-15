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

import fi.ishtech.sudoku.solver.SudokuSolver;

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

		// Read input values from UI
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				String text = sudokuGrid[i][j].getText();

				System.out.print("[" + i + "][" + j + "]=" + (text == null || text.isEmpty() ? "\'\'" : text));

				inputArray[i][j] = text;
			}
			System.out.println("");
		}

		try {
			// Pass the 2D array to solver
			SudokuSolver sudokuSolver = new SudokuSolver();
			int[][] solvedArray = sudokuSolver.solve(inputArray);

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
			if (string.isEmpty() || string.matches("[0-9]")) {
				super.insertString(fb, offset, string, attr);
			}
		}

		@Override
		public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
				throws BadLocationException {
			if (text.isEmpty() || text.matches("[0-9]")) {
				super.replace(fb, offset, length, text, attrs);
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
