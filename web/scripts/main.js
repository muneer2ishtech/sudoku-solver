function newPuzzle() {
    resetTimer();
    clearAndEnableInputs();
    enableSolveButton();
}

function disableInputs() {
    for (let i = 0; i < 9; i++) {
        for (let j = 0; j < 9; j++) {
            document.getElementById(`inp-${i}-${j}`).disabled = true;
        }
    }
}

function clearAndEnableInputs() {
    for (let i = 0; i < 9; i++) {
        for (let j = 0; j < 9; j++) {
            const inputElement = document.getElementById(`inp-${i}-${j}`);
            resetInputStyles(inputElement);
            inputElement.value = '';
            inputElement.disabled = false;
        }
    }
}

function disableSolveButton() {
    toggleButton("solve-button", true);
}

function enableSolveButton() {
    toggleButton("solve-button", false);
}

function diableResetButton() {
    toggleButton("reset-button", true);
}

function enableResetButton() {
    toggleButton("reset-button", false);
}

function toggleButton(id, disabled) {
    var button = document.getElementById(id);
    button.disabled = disabled;
    if (disabled) {
        button.classList.add("disabled");
    } else {
        button.classList.remove("disabled");
    }
}

function resetInputStyles(inputElement) {
    // Reset classes
    inputElement.classList.remove('output-red', 'output-green');
}

function validateInput(input) {
    input.value = input.value.replace(/[^1-9]/g, '');
}

function readInputMatrix() {
    const inputMatrix = [];

    for (let i = 0; i < 9; i++) {
        inputMatrix[i] = [];
        for (let j = 0; j < 9; j++) {
            const inputValue = document.getElementById(`inp-${i}-${j}`).value;
            inputMatrix[i][j] = inputValue !== '' ? parseInt(inputValue) : 0;
        }
    }
    console.log("inputmatirx:" + inputMatrix);

    return inputMatrix;
}

function outputResultMatrix(resultMatrix, inputMatrix) {
    for (let i = 0; i < 9; i++) {
        for (let j = 0; j < 9; j++) {
            const inputElement = document.getElementById(`inp-${i}-${j}`);
            const inputValue = inputMatrix[i][j];
            const resultValue = resultMatrix[i][j] == 0 ? '' : resultMatrix[i][j];

            resetInputStyles(inputElement);

            // Set value
            inputElement.value = resultValue;

            // Add classes for styling
            if (resultValue == inputValue) {
                inputElement.classList.add('output-red');
            } else {
                inputElement.classList.add('output-green');
            }
        }
    }
}

function solvePuzzle() {
    // disable user input and button click
    disableInputs();
    disableSolveButton();

    // read inputs
    const inputMatrix = readInputMatrix();

    // start timer
    startTimer();

    // solve
    resultMatrix = doSolveSoduku(inputMatrix);

    setTimeout(function () {
        console.log("...");
    }, 500000);

    // stop timer
    stopTimer();

    // output result
    outputResultMatrix(resultMatrix, inputMatrix);
}

function doSolveSoduku(inputMatrix) {
    const resultMatrix = [];

    for (let i = 0; i < 9; i++) {
        resultMatrix[i] = [];
        for (let j = 0; j < 9; j++) {
            resultMatrix[i][j] = inputMatrix[i][j];
        }
    }
    console.log("resultMatrix:" + resultMatrix);

    resultMatrix[4][4] = 4;

    return resultMatrix;
}
