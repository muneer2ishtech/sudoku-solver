let startTime;
let timerInterval;

function startTimer() {
    startTime = new Date().getTime();
    timerInterval = setInterval(updateTimer, 10);
}

function stopTimer() {
    clearInterval(timerInterval);
}

function resetTimer() {
    stopTimer();
    document.getElementById("timer").innerHTML = formatTime(0);
}

function updateTimer() {
    const elapsedTime = new Date().getTime() - startTime;
    document.getElementById("timer").innerHTML = formatTime(elapsedTime);
}

function formatTime(elapsedTime) {
    const hours = Math.floor(elapsedTime / (3600 * 1000));
    const minutes = Math.floor((elapsedTime % (3600 * 1000)) / (60 * 1000));
    const seconds = Math.floor((elapsedTime % (60 * 1000)) / 1000);
    const milliseconds = elapsedTime % 1000;

    return `${padNumber(hours)}:${padNumber(minutes)}:${padNumber(seconds)}.${padNumber(milliseconds, 3)}`;
}

function padNumber(number, length = 2) {
    return ('0' + number).slice(-length);
}
