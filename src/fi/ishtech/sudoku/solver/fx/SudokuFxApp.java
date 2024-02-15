package fi.ishtech.sudoku.solver.fx;

import java.awt.Button;
import java.awt.Color;
import java.awt.Insets;
import java.awt.Label;
import java.awt.TextField;

import fi.ishtech.sudoku.solver.SudokuSolver;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class SudokuFxApp extends Application {

    private TextField[][] sudokuGrid;
    private Button solveButton;
    private Button newButton;
    private Label exceptionLabel;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Sudoku Solver");

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(createSudokuGrid());
        borderPane.setBottom(createBottomPanel());

        Scene scene = new Scene(borderPane, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private GridPane createSudokuGrid() {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        gridPane.setPadding(new Insets(10));

        sudokuGrid = new TextField[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                TextField textField = createTextField();
                sudokuGrid[i][j] = textField;
                gridPane.add(textField, j, i);
            }
        }

        return gridPane;
    }

    private HBox createBottomPanel() {
        solveButton = new Button("Solve");
        solveButton.setOnAction(e -> solveSudoku());

        newButton = new Button("New");
        newButton.setOnAction(e -> resetSudoku());

        exceptionLabel = new Label("");
        exceptionLabel.setTextFill(Color.BLACK);

        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(solveButton, newButton);

        HBox exceptionBox = new HBox();
        exceptionBox.setAlignment(Pos.CENTER);
        exceptionBox.getChildren().add(exceptionLabel);

        HBox bottomPanel = new HBox(10);
        bottomPanel.setAlignment(Pos.CENTER);
        bottomPanel.getChildren().addAll(buttonBox, exceptionBox);

        return bottomPanel;
    }

    private void solveSudoku() {
        // Disable input fields and Solve button after clicking
        disableInputFields();
        solveButton.setDisable(true);

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
            // Handle exception by displaying the message in the exceptionLabel
            exceptionLabel.setText(e.getMessage() != null ? e.getMessage() : e.getClass().getName());
            exceptionLabel.setTextFill(Color.RED);
        }
    }

    private void updateUI(int[][] solvedArray) {
        // Update the UI with the solved results
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                TextField textField = sudokuGrid[i][j];
                String currentValue = textField.getText();
                String solvedValue = String.valueOf(solvedArray[i][j]);

                // Set text fill based on input or solved value
                if (!currentValue.isEmpty()) {
                    // User-input value
                    textField.setTextFill(Color.RED);
                } else {
                    // Solved value
                    textField.setTextFill(Color.BLUE);
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
        exceptionLabel.setTextFill(Color.BLACK);

        // Enable input fields and clear their text
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sudokuGrid[i][j].setEditable(true);
                sudokuGrid[i][j].setText("");
                sudokuGrid[i][j].setTextFill(Color.BLACK); // Reset text fill to default
            }
        }

        // Enable Solve button
        solveButton.setDisable(false);
    }

    private TextField createTextField() {
        TextField textField = new TextField();
        textField.setAlignment(Pos.CENTER);

        // Add a TextFormatter to restrict input to a single digit
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!isValidInput(newValue)) {
                textField.setText(oldValue);
            }
        });

        return textField;
    }

    private boolean isValidInput(String text) {
        // Check if the resulting text is a single digit between 1 and 9 or an empty string
        return text.matches("[1-9]") || text.isEmpty();
    }

}
