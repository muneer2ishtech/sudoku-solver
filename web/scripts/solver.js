const probs = [
    [[1, 2, 3, 4, 5, 6, 7, 8, 9], [1, 2, 3, 4, 5, 6, 7, 8, 9], [1, 2, 3, 4, 5, 6, 7, 8, 9], [1, 2, 3, 4, 5, 6, 7, 8, 9], [1, 2, 3, 4, 5, 6, 7, 8, 9], [1, 2, 3, 4, 5, 6, 7, 8, 9], [1, 2, 3, 4, 5, 6, 7, 8, 9], [1, 2, 3, 4, 5, 6, 7, 8, 9], [1, 2, 3, 4, 5, 6, 7, 8, 9]],
    [[1, 2, 3, 4, 5, 6, 7, 8, 9], [1, 2, 3, 4, 5, 6, 7, 8, 9], [1, 2, 3, 4, 5, 6, 7, 8, 9], [1, 2, 3, 4, 5, 6, 7, 8, 9], [1, 2, 3, 4, 5, 6, 7, 8, 9], [1, 2, 3, 4, 5, 6, 7, 8, 9], [1, 2, 3, 4, 5, 6, 7, 8, 9], [1, 2, 3, 4, 5, 6, 7, 8, 9], [1, 2, 3, 4, 5, 6, 7, 8, 9]],
    [[1, 2, 3, 4, 5, 6, 7, 8, 9], [1, 2, 3, 4, 5, 6, 7, 8, 9], [1, 2, 3, 4, 5, 6, 7, 8, 9], [1, 2, 3, 4, 5, 6, 7, 8, 9], [1, 2, 3, 4, 5, 6, 7, 8, 9], [1, 2, 3, 4, 5, 6, 7, 8, 9], [1, 2, 3, 4, 5, 6, 7, 8, 9], [1, 2, 3, 4, 5, 6, 7, 8, 9], [1, 2, 3, 4, 5, 6, 7, 8, 9]],
    [[1, 2, 3, 4, 5, 6, 7, 8, 9], [1, 2, 3, 4, 5, 6, 7, 8, 9], [1, 2, 3, 4, 5, 6, 7, 8, 9], [1, 2, 3, 4, 5, 6, 7, 8, 9], [1, 2, 3, 4, 5, 6, 7, 8, 9], [1, 2, 3, 4, 5, 6, 7, 8, 9], [1, 2, 3, 4, 5, 6, 7, 8, 9], [1, 2, 3, 4, 5, 6, 7, 8, 9], [1, 2, 3, 4, 5, 6, 7, 8, 9]],
    [[1, 2, 3, 4, 5, 6, 7, 8, 9], [1, 2, 3, 4, 5, 6, 7, 8, 9], [1, 2, 3, 4, 5, 6, 7, 8, 9], [1, 2, 3, 4, 5, 6, 7, 8, 9], [1, 2, 3, 4, 5, 6, 7, 8, 9], [1, 2, 3, 4, 5, 6, 7, 8, 9], [1, 2, 3, 4, 5, 6, 7, 8, 9], [1, 2, 3, 4, 5, 6, 7, 8, 9], [1, 2, 3, 4, 5, 6, 7, 8, 9]],
    [[1, 2, 3, 4, 5, 6, 7, 8, 9], [1, 2, 3, 4, 5, 6, 7, 8, 9], [1, 2, 3, 4, 5, 6, 7, 8, 9], [1, 2, 3, 4, 5, 6, 7, 8, 9], [1, 2, 3, 4, 5, 6, 7, 8, 9], [1, 2, 3, 4, 5, 6, 7, 8, 9], [1, 2, 3, 4, 5, 6, 7, 8, 9], [1, 2, 3, 4, 5, 6, 7, 8, 9], [1, 2, 3, 4, 5, 6, 7, 8, 9]],
    [[1, 2, 3, 4, 5, 6, 7, 8, 9], [1, 2, 3, 4, 5, 6, 7, 8, 9], [1, 2, 3, 4, 5, 6, 7, 8, 9], [1, 2, 3, 4, 5, 6, 7, 8, 9], [1, 2, 3, 4, 5, 6, 7, 8, 9], [1, 2, 3, 4, 5, 6, 7, 8, 9], [1, 2, 3, 4, 5, 6, 7, 8, 9], [1, 2, 3, 4, 5, 6, 7, 8, 9], [1, 2, 3, 4, 5, 6, 7, 8, 9]],
    [[1, 2, 3, 4, 5, 6, 7, 8, 9], [1, 2, 3, 4, 5, 6, 7, 8, 9], [1, 2, 3, 4, 5, 6, 7, 8, 9], [1, 2, 3, 4, 5, 6, 7, 8, 9], [1, 2, 3, 4, 5, 6, 7, 8, 9], [1, 2, 3, 4, 5, 6, 7, 8, 9], [1, 2, 3, 4, 5, 6, 7, 8, 9], [1, 2, 3, 4, 5, 6, 7, 8, 9], [1, 2, 3, 4, 5, 6, 7, 8, 9]],
    [[1, 2, 3, 4, 5, 6, 7, 8, 9], [1, 2, 3, 4, 5, 6, 7, 8, 9], [1, 2, 3, 4, 5, 6, 7, 8, 9], [1, 2, 3, 4, 5, 6, 7, 8, 9], [1, 2, 3, 4, 5, 6, 7, 8, 9], [1, 2, 3, 4, 5, 6, 7, 8, 9], [1, 2, 3, 4, 5, 6, 7, 8, 9], [1, 2, 3, 4, 5, 6, 7, 8, 9], [1, 2, 3, 4, 5, 6, 7, 8, 9]]
];

const result = [];

var tryc = 0;

function initProabables() {
    console.log(probs);
}

function solveSoduku(inputMatrix) {
    console.log(inputMatrix);
    //initProabables();
    for (let i = 0; i < 9; i++) {
        result[i] = [];
        for (let j = 0; j < 9; j++) {
            result[i][j] = inputMatrix[i][j];
        }
    }

    console.log(result);

    doSolve(false);

    return result;
}

function doSolve(exitOnSolved) {
    do {
        resetProbables();

        solveUniqs(exitOnSolved);
        //solveUniqProbablesInReverse(exitOnSolved);

        tryc++;
    } while (!isResolved(exitOnSolved));
}

function isResolved(exitOnSolved) {
    console.log("try:" + tryc);
    return true;
}

function resetProbables() {
    for (let i = 0; i < 9; i++) {
        for (let j = 0; j < 9; j++) {
            resetProbablesBasedOnRowCol(i, j);
        }
    }
    console.log(probs);
}

function resetProbablesBasedOnRowCol(row, col) {
    console.log("row:" + row + ", col:" + col);
    if (result[row][col]) {
        probs[row][col].length = 0;

        eliminateRowUnProbables(row, col);
        eliminateColUnProbables(row, col);
        eliminateBoxUnProbables(row, col);
    }
}

function eliminateRowUnProbables(row, col) {
    for (let j = 0; j < 9; j++) {
        removeFromArray(probs[row][j], result[row][col]);
    }
}

function eliminateColUnProbables(row, col) {
    for (let i = 0; i < 9; i++) {
        removeFromArray(probs[i][col], result[row][col]);
    }
}

function eliminateBoxUnProbables(row, col) {
    let boxI = parseInt(row / 3) * 3;
    let boxJ = parseInt(col / 3) * 3;
    for (let i = boxI; i < (boxI + 3); i++) {
        for (let j = boxJ; j < (boxJ + 3); j++) {
            removeFromArray(probs[i][j], result[row][col]);
        }
    }
}

function removeFromArray(arr, val) {
    if (arr && arr.length > 0) {
        let i = arr.indexOf(val);

        if (i !== -1) {
            // Use splice to remove the element
            arr.splice(i, 1);
        }
    }
}

function solveUniqs(exitOnSolved) {
    for (let i = 0; i < 9; i++) {
        solveUniqInRow(i);
    }

    for (let j = 0; j < 9; j++) {
        solveUniqInCol(j);
    }

    for (let boxI = 0; boxI < 9; boxI += 3) {
        for (let boxJ = 0; boxJ < 9; boxJ += 3) {
            solveUniqInBox(boxI, boxJ);
        }
    }

    isResolved(exitOnSolved);
}

function solveUniqInRow(row) {
    solveUniqProbableInRow(row);
}

function solveUniqProbableInRow(row) {
    for (let j = 0; j < 9; j++) {
        solveUniqProbable(row, j);
    }
}

function solveUniqInCol(col) {
    solveUniqProbableInCol(col);
}

function solveUniqProbableInCol(col) {
    for (let i = 0; i < 9; i++) {
        solveUniqProbable(i, col);
    }
}

function solveUniqInBox(row, col) {
    solveUniqProbableInBox(row, col);
}

function solveUniqProbableInBox(row, col) {
    let boxI = parseInt(row / 3) * 3;
    let boxJ = parseInt(col / 3) * 3;
    for (let i = boxI; i < (boxI + 3); i++) {
        for (let j = boxJ; j < (boxJ + 3); j++) {
            solveUniqProbable(i, j);
        }
    }
}

function solveUniqProbable(row, col) {
    if (!result[row][col]) {
        if (probs[row][col].length == 1) {
            result[row][col] = probs[row][col][0];
            probs[row][col].length = 0;
        }
    }
}
