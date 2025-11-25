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
const maxTry = 10000;

function solveSoduku(inputMatrix) {
    console.log("input:", inputMatrix);

    for (let i = 0; i < 9; i++) {
        result[i] = [];
        for (let j = 0; j < 9; j++) {
            result[i][j] = inputMatrix[i][j];
        }
    }

    doSolve(false);

    console.log("result:", result);
    console.log("tries:", tryc);

    return result;
}

function doSolve(exitOnSolved) {
    do {
        resetProbables();

        solveUniqs(exitOnSolved);
        solveUniqProbablesInReverse(exitOnSolved);

        tryc++;
    } while (!exitAfterMaxTry() && !isResolved(exitOnSolved));
}

function isResolved(exitOnSolved) {
    //console.log("try:" + tryc);

    for (let i = 0; i < 9; i++) {
        for (let j = 0; j < 9; j++) {
            if (!result[i][j]) {
                return false;
            }
        }
    }

    return true;
}

function exitAfterMaxTry() {
    return tryc >= maxTry;
}

function resetProbables() {
    for (let i = 0; i < 9; i++) {
        for (let j = 0; j < 9; j++) {
            resetProbablesBasedOnRowCol(i, j);
        }
    }
    //console.log(probs);
}

function resetProbablesBasedOnRowCol(row, col) {
    if (result[row][col]) {
        probs[row][col].length = 0;

        eliminateRowUnProbables(row, col);
        eliminateColUnProbables(row, col);
        eliminateBoxUnProbables(row, col);
    }
}

function eliminateRowUnProbables(row, col) {
    for (let j = 0; j < 9; j++) {
        if (j == col) {
            continue;
        }
        tryc++;
        removeFromArray(probs[row][j], result[row][col]);
        isThisSolved(row, j);
    }
}

function eliminateColUnProbables(row, col) {
    for (let i = 0; i < 9; i++) {
        if (i == row) {
            continue;
        }
        tryc++;
        removeFromArray(probs[i][col], result[row][col]);
        isThisSolved(i, col);
    }
}

function eliminateBoxUnProbables(row, col) {
    let boxI = parseInt(row / 3) * 3;
    let boxJ = parseInt(col / 3) * 3;
    for (let i = boxI; i < (boxI + 3); i++) {
        for (let j = boxJ; j < (boxJ + 3); j++) {
            if (i == row && j == col) {
                continue;
            }
            tryc++;
            removeFromArray(probs[i][j], result[row][col]);
            isThisSolved(i, j);
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

function solveUniqProbablesInReverse(exitOnSolved) {
    for (let i = 0; i < 9; i++) {
        solveUniqProbableInRowReverse(i);
    }

    for (let j = 0; j < 9; j++) {
        solveUniqProbableInColReverse(j);
    }

    for (let boxI = 0; boxI < 9; boxI += 3) {
        for (let boxJ = 0; boxJ < 9; boxJ += 3) {
            solveUniqProbableInBoxReverse(boxI, boxJ);
        }
    }

    isResolved(exitOnSolved);
}

function solveUniqProbableInRowReverse(row) {
    for (let n = 1; n <= 9; n++) {
        let counter = 0;
        let foundCol = 10;
        for (let j = 0; j < 9; j++) {
            if (probs[row][j].indexOf(n) > -1) {
                foundCol = j;
                counter++;
            }
        }
        if (counter == 1) {
            // n is present only once
            setSolvedValue(row, foundCol, n);
        }
    }
}

function solveUniqProbableInColReverse(col) {
    for (let n = 1; n <= 9; n++) {
        let counter = 0;
        let foundRow = 10;
        for (let i = 0; i < 9; i++) {
            if (probs[i][col].indexOf(n) > -1) {
                foundRow = i;
                counter++;
            }
        }
        if (counter == 1) {
            // n is present only once
            setSolvedValue(foundRow, col, n);
        }
    }
}

function solveUniqProbableInBoxReverse(row, col) {
    for (let n = 1; n <= 9; n++) {
        let counter = 0;
        let foundRow = 10;
        let foundCol = 10;
        let boxI = parseInt(row / 3) * 3;
        let boxJ = parseInt(col / 3) * 3;
        for (let i = boxI; i < (boxI + 3); i++) {
            for (let j = boxJ; j < (boxJ + 3); j++) {
                if (probs[i][j].indexOf(n) > -1) {
                    foundRow = i;
                    foundCol = j;
                    counter++;
                }
            }
        }
        if (counter == 1) {
            // n is present only once
            setSolvedValue(foundRow, foundCol, n);
        }
    }
}

function isThisSolved(row, col) {
    if (probs[row][col] && probs[row][col].length == 1) {
        result[row][col] = probs[row][col][0];
        probs[row][col].length = 0;

        resetProbablesBasedOnRowCol(row, col);
    }
}

function setSolvedValue(row, col, num) {
    result[row][col] = num;
    probs[row][col].length = 0;
    resetProbablesBasedOnRowCol(row, col);
}