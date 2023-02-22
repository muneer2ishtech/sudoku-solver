package com.muneer.sudoku.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
// import javax.swing.UIManager;
// import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.SwingConstants;

import com.muneer.sudoku.SudokuSolver;

public class SudokuSwingApp
        implements ActionListener {

	private JFrame frame;

	private JTextField textField[][];

	private JButton buttonSolve;
	private JButton buttonReset;

	JTextArea textAreaErrorMessage;

	/**
	 * Launch the application.
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		try {
			SudokuSwingApp window = new SudokuSwingApp();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the application.
	 */
	public SudokuSwingApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setLookAndFeel();

		makeFrame();

		makeTextFields();

		makeButtons();

		makeTextAreaErrorMessage();

		makeLayout();
	}

	private void setLookAndFeel() {
		/*
		try {
		    //UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		    //UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		    //UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
		    //UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
		    //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		    //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
		    //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
		    //e.printStackTrace();
		    throw new RuntimeException(e);
		} catch (InstantiationException e) {
		    //e.printStackTrace();
		    throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
		    //e.printStackTrace();
		    throw new RuntimeException(e);
		} catch (UnsupportedLookAndFeelException e) {
		    //e.printStackTrace();
		    throw new RuntimeException(e);
		}
		*/
	}

	private void makeFrame() {
		frame = new JFrame();
		//frame.setMinimumSize(new Dimension(500, 600));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setTitle("Sudoku Solver");
	}

	private void makeButtons() {
		buttonSolve = makeButton("Solve");
		buttonSolve.addActionListener(this);

		buttonReset = makeButton("Reset");
		buttonReset.addActionListener(this);
	}

	private void makeTextFields() {
		if (textField == null) {
			textField = new JTextField[9][9];
		}

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				textField[i][j] = makeTextField(i + "" + j);
			}
		}

	}

	private JTextField makeTextField(String name) {
		JTextField textField = new JTextField();

		textField.setName("textField" + name);
		textField.setHorizontalAlignment(JTextField.CENTER);
		textField.setText(null);

		textField.setInputVerifier(new TextFieldVerifier(textField.getForeground()));
		Font font = new Font("Serif", Font.PLAIN, 20);
		textField.setFont(font);

		textField.setPreferredSize(new Dimension(50, 50));
		// textField.setColumns(10);

		return textField;
	}

	private JButton makeButton(String name) {
		JButton button = new JButton(name);
		button.setName("button" + name);

		return button;
	}

	private JTextArea makeTextAreaErrorMessage() {
		textAreaErrorMessage = new JTextArea();
		textAreaErrorMessage.setName("textArea" + "ErrorMessage");
		textAreaErrorMessage.setAutoscrolls(false);
		textAreaErrorMessage.setBackground(frame.getBackground());
		textAreaErrorMessage.setEditable(false);
		Font font = new Font("Serif", Font.PLAIN, 20);
		textAreaErrorMessage.setFont(font);
		textAreaErrorMessage.setForeground(Color.RED);
		textAreaErrorMessage.setVisible(false);

		return textAreaErrorMessage;
	}

	private void makeLayout() {
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());

		groupLayout.linkSize(textField[0][0], textField[0][1], textField[0][2], textField[0][3], textField[0][4],
		                     textField[0][5], textField[0][6], textField[0][7], textField[0][8],
		                     textField[1][0], textField[1][1], textField[1][2], textField[1][3], textField[1][4],
		                     textField[1][5], textField[1][6], textField[1][7], textField[1][8],
		                     textField[2][0], textField[2][1], textField[2][2], textField[2][3], textField[2][4],
		                     textField[2][5], textField[2][6], textField[2][7], textField[2][8],
		                     textField[3][0], textField[3][1], textField[3][2], textField[3][3], textField[3][4],
		                     textField[3][5], textField[3][6], textField[3][7], textField[3][8],
		                     textField[4][0], textField[4][1], textField[4][2], textField[4][3], textField[4][4],
		                     textField[4][5], textField[4][6], textField[4][7], textField[4][8],
		                     textField[5][0], textField[5][1], textField[5][2], textField[5][3], textField[5][4],
		                     textField[5][5], textField[5][6], textField[5][7], textField[5][8],
		                     textField[6][0], textField[6][1], textField[6][2], textField[6][3], textField[6][4],
		                     textField[6][5], textField[6][6], textField[6][7], textField[6][8],
		                     textField[7][0], textField[7][1], textField[7][2], textField[7][3], textField[7][4],
		                     textField[7][5], textField[7][6], textField[7][7], textField[7][8],
		                     textField[8][0], textField[8][1], textField[8][2], textField[8][3], textField[8][4],
		                     textField[8][5], textField[8][6], textField[8][7], textField[8][8]);
/*
		groupLayout.setAutoCreateGaps(true);
		groupLayout.setAutoCreateContainerGaps(true);
*/
		JSeparator separator1 = new JSeparator(SwingConstants.HORIZONTAL);
		JSeparator separator2 = new JSeparator(SwingConstants.HORIZONTAL);

		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.CENTER)
		        .addGroup(groupLayout.createSequentialGroup()
		                .addGap(2, 4, 10)
		                .addComponent(textField[0][0])
		                .addGap(1)
		                .addComponent(textField[0][1])
		                .addGap(1)
		                .addComponent(textField[0][2])
		                .addGap(4)
		                .addComponent(textField[0][3])
		                .addGap(1)
		                .addComponent(textField[0][4])
		                .addGap(1)
		                .addComponent(textField[0][5])
		                .addGap(4)
		                .addComponent(textField[0][6])
		                .addGap(1)
		                .addComponent(textField[0][7])
		                .addGap(1)
		                .addComponent(textField[0][8])
		                .addGap(2, 4, 10))
		        .addGroup(groupLayout.createSequentialGroup()
		                .addGap(2, 4, 10)
		                .addComponent(textField[1][0])
		                .addGap(1)
		                .addComponent(textField[1][1])
		                .addGap(1)
		                .addComponent(textField[1][2])
		                .addGap(4)
		                .addComponent(textField[1][3])
		                .addGap(1)
		                .addComponent(textField[1][4])
		                .addGap(1)
		                .addComponent(textField[1][5])
		                .addGap(4)
		                .addComponent(textField[1][6])
		                .addGap(1)
		                .addComponent(textField[1][7])
		                .addGap(1)
		                .addComponent(textField[1][8])
		                .addGap(2, 4, 10))
		        .addGroup(groupLayout.createSequentialGroup()
		                .addGap(2, 4, 10)
		                .addComponent(textField[2][0])
		                .addGap(1)
		                .addComponent(textField[2][1])
		                .addGap(1)
		                .addComponent(textField[2][2])
		                .addGap(4)
		                .addComponent(textField[2][3])
		                .addGap(1)
		                .addComponent(textField[2][4])
		                .addGap(1)
		                .addComponent(textField[2][5])
		                .addGap(4)
		                .addComponent(textField[2][6])
		                .addGap(1)
		                .addComponent(textField[2][7])
		                .addGap(1)
		                .addComponent(textField[2][8])
		                .addGap(2, 4, 10))
		        .addGroup(groupLayout.createSequentialGroup()
		                .addGap(2, 4, 10)
		                .addComponent(textField[3][0])
		                .addGap(1)
		                .addComponent(textField[3][1])
		                .addGap(1)
		                .addComponent(textField[3][2])
		                .addGap(4)
		                .addComponent(textField[3][3])
		                .addGap(1)
		                .addComponent(textField[3][4])
		                .addGap(1)
		                .addComponent(textField[3][5])
		                .addGap(4)
		                .addComponent(textField[3][6])
		                .addGap(1)
		                .addComponent(textField[3][7])
		                .addGap(1)
		                .addComponent(textField[3][8])
		                .addGap(2, 4, 10))
		        .addGroup(groupLayout.createSequentialGroup()
		                .addGap(2, 4, 10)
		                .addComponent(textField[4][0])
		                .addGap(1)
		                .addComponent(textField[4][1])
		                .addGap(1)
		                .addComponent(textField[4][2])
		                .addGap(4)
		                .addComponent(textField[4][3])
		                .addGap(1)
		                .addComponent(textField[4][4])
		                .addGap(1)
		                .addComponent(textField[4][5])
		                .addGap(4)
		                .addComponent(textField[4][6])
		                .addGap(1)
		                .addComponent(textField[4][7])
		                .addGap(1)
		                .addComponent(textField[4][8])
		                .addGap(2, 4, 10))
		        .addGroup(groupLayout.createSequentialGroup()
		                .addGap(2, 4, 10)
		                .addComponent(textField[5][0])
		                .addGap(1)
		                .addComponent(textField[5][1])
		                .addGap(1)
		                .addComponent(textField[5][2])
		                .addGap(4)
		                .addComponent(textField[5][3])
		                .addGap(1)
		                .addComponent(textField[5][4])
		                .addGap(1)
		                .addComponent(textField[5][5])
		                .addGap(4)
		                .addComponent(textField[5][6])
		                .addGap(1)
		                .addComponent(textField[5][7])
		                .addGap(1)
		                .addComponent(textField[5][8])
		                .addGap(2, 4, 10))
		        .addGroup(groupLayout.createSequentialGroup()
		                .addGap(2, 4, 10)
		                .addComponent(textField[6][0])
		                .addGap(1)
		                .addComponent(textField[6][1])
		                .addGap(1)
		                .addComponent(textField[6][2])
		                .addGap(4)
		                .addComponent(textField[6][3])
		                .addGap(1)
		                .addComponent(textField[6][4])
		                .addGap(1)
		                .addComponent(textField[6][5])
		                .addGap(4)
		                .addComponent(textField[6][6])
		                .addGap(1)
		                .addComponent(textField[6][7])
		                .addGap(1)
		                .addComponent(textField[6][8])
		                .addGap(2, 4, 10))
		        .addGroup(groupLayout.createSequentialGroup()
		                .addGap(2, 4, 10)
		                .addComponent(textField[7][0])
		                .addGap(1)
		                .addComponent(textField[7][1])
		                .addGap(1)
		                .addComponent(textField[7][2])
		                .addGap(4)
		                .addComponent(textField[7][3])
		                .addGap(1)
		                .addComponent(textField[7][4])
		                .addGap(1)
		                .addComponent(textField[7][5])
		                .addGap(4)
		                .addComponent(textField[7][6])
		                .addGap(1)
		                .addComponent(textField[7][7])
		                .addGap(1)
		                .addComponent(textField[7][8])
		                .addGap(2, 4, 10))
		        .addGroup(groupLayout.createSequentialGroup()
		                .addGap(2, 4, 10)
		                .addComponent(textField[8][0])
		                .addGap(1)
		                .addComponent(textField[8][1])
		                .addGap(1)
		                .addComponent(textField[8][2])
		                .addGap(4)
		                .addComponent(textField[8][3])
		                .addGap(1)
		                .addComponent(textField[8][4])
		                .addGap(1)
		                .addComponent(textField[8][5])
		                .addGap(4)
		                .addComponent(textField[8][6])
		                .addGap(1)
		                .addComponent(textField[8][7])
		                .addGap(1)
		                .addComponent(textField[8][8])
		                .addGap(2, 4, 10))
		        .addGroup(groupLayout.createSequentialGroup().addComponent(separator1))
		        .addGroup(groupLayout.createSequentialGroup()
		                .addComponent(buttonSolve)
		                .addGap(20)
		                .addComponent(buttonReset))
		        .addGroup(groupLayout.createSequentialGroup().addComponent(separator2))
		        .addGroup(groupLayout.createSequentialGroup()
		                  .addGap(2, 4, 10)
		                  .addComponent(textAreaErrorMessage)
		                  .addGap(2, 4, 10))
		        );

		groupLayout.setVerticalGroup(groupLayout.createSequentialGroup()
		        .addGap(2, 4, 10)
		        .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
		                .addComponent(textField[0][0])
		                .addComponent(textField[0][1])
		                .addComponent(textField[0][2])
		                .addComponent(textField[0][3])
		                .addComponent(textField[0][4])
		                .addComponent(textField[0][5])
		                .addComponent(textField[0][6])
		                .addComponent(textField[0][7])
		                .addComponent(textField[0][8]))
		        .addGap(1)
		        .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
		                .addComponent(textField[1][0])
		                .addComponent(textField[1][1])
		                .addComponent(textField[1][2])
		                .addComponent(textField[1][3])
		                .addComponent(textField[1][4])
		                .addComponent(textField[1][5])
		                .addComponent(textField[1][6])
		                .addComponent(textField[1][7])
		                .addComponent(textField[1][8]))
		        .addGap(1)
		        .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
		                .addComponent(textField[2][0])
		                .addComponent(textField[2][1])
		                .addComponent(textField[2][2])
		                .addComponent(textField[2][3])
		                .addComponent(textField[2][4])
		                .addComponent(textField[2][5])
		                .addComponent(textField[2][6])
		                .addComponent(textField[2][7])
		                .addComponent(textField[2][8]))
		        .addGap(4)
		        .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
		                .addComponent(textField[3][0])
		                .addComponent(textField[3][1])
		                .addComponent(textField[3][2])
		                .addComponent(textField[3][3])
		                .addComponent(textField[3][4])
		                .addComponent(textField[3][5])
		                .addComponent(textField[3][6])
		                .addComponent(textField[3][7])
		                .addComponent(textField[3][8]))
		        .addGap(1)
		        .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
		                .addComponent(textField[4][0])
		                .addComponent(textField[4][1])
		                .addComponent(textField[4][2])
		                .addComponent(textField[4][3])
		                .addComponent(textField[4][4])
		                .addComponent(textField[4][5])
		                .addComponent(textField[4][6])
		                .addComponent(textField[4][7])
		                .addComponent(textField[4][8]))
		        .addGap(1)
		        .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
		                .addComponent(textField[5][0])
		                .addComponent(textField[5][1])
		                .addComponent(textField[5][2])
		                .addComponent(textField[5][3])
		                .addComponent(textField[5][4])
		                .addComponent(textField[5][5])
		                .addComponent(textField[5][6])
		                .addComponent(textField[5][7])
		                .addComponent(textField[5][8]))
		        .addGap(4)
		        .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
		                .addComponent(textField[6][0])
		                .addComponent(textField[6][1])
		                .addComponent(textField[6][2])
		                .addComponent(textField[6][3])
		                .addComponent(textField[6][4])
		                .addComponent(textField[6][5])
		                .addComponent(textField[6][6])
		                .addComponent(textField[6][7])
		                .addComponent(textField[6][8]))
		        .addGap(1)
		        .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
		                .addComponent(textField[7][0])
		                .addComponent(textField[7][1])
		                .addComponent(textField[7][2])
		                .addComponent(textField[7][3])
		                .addComponent(textField[7][4])
		                .addComponent(textField[7][5])
		                .addComponent(textField[7][6])
		                .addComponent(textField[7][7])
		                .addComponent(textField[7][8]))
		        .addGap(1)
		        .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
		                .addComponent(textField[8][0])
		                .addComponent(textField[8][1])
		                .addComponent(textField[8][2])
		                .addComponent(textField[8][3])
		                .addComponent(textField[8][4])
		                .addComponent(textField[8][5])
		                .addComponent(textField[8][6])
		                .addComponent(textField[8][7])
		                .addComponent(textField[8][8]))
		        .addGap(2, 4, 10)
		        .addComponent(separator1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
		                      GroupLayout.PREFERRED_SIZE)
		        .addGap(5)
		        .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
		                .addComponent(buttonSolve)
		                .addComponent(buttonReset))
		        .addGap(5)
		        .addComponent(separator2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
		                      GroupLayout.PREFERRED_SIZE)
		        .addGap(10)
		        .addComponent(textAreaErrorMessage)
		        .addGap(10)
		        );

		frame.getContentPane().setLayout(groupLayout);
		autofitFrame();
	}

	private void switchSolveButton(boolean disable) {
		buttonSolve.setEnabled(disable);
	}

	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == buttonSolve) {
			switchSolveButton(false);
			try {
				actionSolve();
			} catch(Exception e) {
				//e.printStackTrace();
				showErrorMessage(e.getMessage());
			}
			
		} else if (event.getSource() == buttonReset) {
			actionReset();
			switchSolveButton(true);
		}
	}

	private void actionReset() {
		makeTextFields();
		makeTextAreaErrorMessage();
		frame.getContentPane().removeAll();
		makeLayout();
	}

	private void actionSolve() {
		String[][] input = getInput();
		String[][] output = solve(input);
		setOuput(input, output);
	}

	private String[][] getInput() {
		String input[][] = new String[9][9];

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				input[i][j] = textField[i][j].getText();
				// System.out.println(i + "" + j + ":" + input[i][j]);
			}
		}

		return input;
	}

	private void setOuput(String[][] input, String[][] output) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (input[i][j] == null || input[i][j].trim().isEmpty()) {
					textField[i][j].setForeground(Color.GREEN);
					textField[i][j].setText(output[i][j]);
				}
			}
		}
	}

	private String[][] solve(String[][] input) {
		String[][] output = new String[9][9];
		SudokuSolver sudokuSolver = new SudokuSolver();
		int a[][] = sudokuSolver.solve(input);

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (a[i][j] != 0) {
					output[i][j] = String.valueOf(a[i][j]);
				}
			}
		}

		return output;
	}

	void showErrorMessage(String message) {
		textAreaErrorMessage.setText(message);
		textAreaErrorMessage.setVisible(true);
		autofitFrame();
	}

	private void autofitFrame() {
	    frame.pack();
    }
	
}

class TextFieldVerifier
        extends InputVerifier {

	private Color originalColor;

	public TextFieldVerifier(Color originalColor) {
		this.originalColor = originalColor;
	}

	@Override
	public boolean verify(JComponent input) {
		JTextField textField = (JTextField) input;

		int length = textField.getText().length();

		if (length == 0) {
			// blank is fine
			textField.setForeground(originalColor);
			return true;
		}
		if (length > 1) {
			// lenght is more than 1
			textField.setForeground(Color.RED);
			return false;
		}
		try {
			Integer.parseInt(textField.getText());
		} catch (NumberFormatException e) {
			// is not a number
			textField.setForeground(Color.RED);
			return false;
		}

		textField.setForeground(originalColor);
		return true;
	}

}
