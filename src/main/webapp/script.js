// boardDetails();
// check();

const X_CLASS = 'x';
const O_CLASS = 'o';
const WINNING_COMBINATIONS = [
    [0, 1, 2],
    [3, 4, 5],
    [6, 7, 8],
    [0, 3, 6],
    [1, 4, 7],
    [2, 5, 8],
    [0, 4, 8],
    [2, 4, 6]
]

const cellElements = document.querySelectorAll('[data-cell]');
const board = document.getElementById('board');
const create = document.getElementById('createGame');
const winningMessageElement = document.getElementById("winningMessage");
const restartButton = document.getElementById('restartButton');
const createButton = document.getElementById('createButton');
const createInput = document.getElementById('createInput');
const winningMessageTextElement = document.querySelector('[data-winning-message-text]')
const waiting = document.getElementById('waiting');
const readyButton = document.getElementById('ready');
const manualRestartButton = document.getElementById('manualRestart');

let xCount = 0;
let oCount = 0;
let boardCheck = [];
let circleTurn;
let gameKey = undefined;

restartButton.addEventListener('click', resetGame);
createButton.addEventListener('click', createGame);
createInput.addEventListener('keypress', function (event) {
    if (event.key === "Enter") {
        event.preventDefault();
        createButton.click();
    }
});

readyButton.addEventListener('click', resetGame);

manualRestartButton.addEventListener('click', resetGame)

function createGame() {
    if (document.getElementById('createInput').value != undefined) {
        gameKey = document.getElementById('createInput').value
        fetch("http://3.210.183.75:8080/tictactoe/tictactoeserver/createGame?key=" + gameKey)
            .then(res => res.text())
            .then(resData => {
                if (resData == 'X') {
                    console.log("waiting for other player")
                    create.style.display = "none";
                    waiting.style.display = "flex";
                    checkLoop();
                } if (resData == 'O') {
                    console.log("Let the games begin!")
                    startGameO();

                } else {
                    console.log(resData)
                }
            })
            .catch(error => console.warn(error));
    }
}

function resetGame() {
    fetch("http://3.210.183.75:8080/tictactoe/tictactoeserver/reset?key=" + gameKey)
        .then(() => {
            location.reload();
        })
}

function startGameX() {
    waiting.style.display = "none";
    board.style.display = "grid";
    manualRestartButton.style.display = "flex";
    cellElements.forEach(cell => {
        cell.classList.remove(X_CLASS)
        cell.classList.remove(O_CLASS)
        cell.addEventListener('click', handleClickX)
        cell.addEventListener('click', handleClickX, { once: true })
    })
    circleTurn = false;
    board.classList.add(X_CLASS);
    xCount = 0;
    oCount = 0;
    boardDetails();
    check();
}


function startGameO() {
    create.style.display = "none";
    board.style.display = "grid";
    manualRestartButton.style.display = "flex";
    cellElements.forEach(cell => {
        cell.classList.remove(X_CLASS)
        cell.classList.remove(O_CLASS)
        cell.addEventListener('click', handleClickO)
        cell.addEventListener('click', handleClickO, { once: true })
    })
    circleTurn = false;
    board.classList.add(O_CLASS);
    xCount = 0;
    oCount = 0;
    boardDetails();
    check();
}

async function checkLoop() {
    await fetch("http://3.210.183.75:8080/tictactoe/tictactoeserver/check?key=" + gameKey)
        .then(res => { return res.text() })
        .then(resData => {
            if (resData == "true") {
                startGameX()
            } else {
                checkLoop()
            }
        })
        .catch(error => console.warn(error));
}

async function check() {
    await fetch("http://3.210.183.75:8080/tictactoe/tictactoeserver/check?key=" + gameKey)
        .then(res => res.text())
        .then(resData => {
            if (resData == "false" && gameKey != undefined) {
                winningMessageElement.classList.add('show');
                winningMessageTextElement.innerText = `Game Over!`;
            } if (resData == "true" && gameKey != undefined) {
                if (checkWin(X_CLASS)) {
                    endGame(false, X_CLASS)
                    console.log("X is the winner!")
                } else if (checkWin(O_CLASS)) {
                    endGame(false, O_CLASS)
                    console.log("O is the winner!")
                } else if (isDraw()) {
                    endGame(true)
                }
            }
        })
        .then(() => { check(); })
        .catch(error => console.warn(error));
}

async function handleClickX(e) {
    // boardDetails();
    await boardDetails();
    const cell = e.target;
    const coordinates = e.target.id.split(",")
    const y = coordinates[0];
    const x = coordinates[1];

    if (xCount <= oCount) {
        fetch("http://3.210.183.75:8080/tictactoe/tictactoeserver/move?key=" + gameKey + "&tile=" + X_CLASS + "&y=" + y + "&x=" + x)
            .then(res => { return res.text() })
            .then(resData => {
                if (resData != "[TAKEN]" && X_CLASS) {
                    placeMark(cell, X_CLASS);
                    // document.getElementsByClassName("board x").style.cursor = "not-allowed";
                    // boardDetails();
                    // check();
                }
                // else {
                //     if(!cell.classList.contains(X_CLASS)){
                //         placeMark(cell, O_CLASS)
                //     }
                // }
            })
            .catch(error => console.warn(error));
    } else {
        console.log("It's not yet your turn!");
    }
}

async function handleClickO(e) {
    // boardDetails();
    // check();
    await boardDetails();
    const cell = e.target;
    const coordinates = e.target.id.split(",")
    const y = coordinates[0];
    const x = coordinates[1];

    if (xCount > oCount) {
        fetch("http://3.210.183.75:8080/tictactoe/tictactoeserver/move?key=" + gameKey + "&tile=" + O_CLASS + "&y=" + y + "&x=" + x)
            .then(res => { return res.text() })
            .then(resData => {
                if (resData != "[TAKEN]") {
                    placeMark(cell, O_CLASS)
                    // document.getElementsByClassName("board o").style.cursor = "not-allowed";
                    // boardDetails();
                    // check();
                }
                // else {
                //     placeMark(cell, X_CLASS)
                // }
            })
            .catch(error => console.warn(error));
    } else {
        console.log("It's not yet your turn!")
    }
}

function endGame(draw, currentClass) {
    // boardDetails();
    if (draw) {
        winningMessageTextElement.innerText = "Draw!"
    } else {
        winningMessageTextElement.innerText = `${currentClass.toUpperCase()} Wins!`
    }
    winningMessageElement.classList.add('show')
}

function isDraw() {
    return [...cellElements].every(cell => {
        return cell.classList.contains(X_CLASS) || cell.classList.contains(O_CLASS)
    })
}

function placeMark(cell, currentClass) {
    cell.classList.add(currentClass)
}

function checkWin(currentClass) {
    return WINNING_COMBINATIONS.some(combination => {
        return combination.every(index => {
            return cellElements[index].classList.contains(currentClass)
        })
    })
}

async function boardDetails() {
    await fetch("http://3.210.183.75:8080/tictactoe/tictactoeserver/board?key=" + gameKey)
        .then(res => { return res.text() })
        .then(resData => {
            xCount = 0;
            oCount = 0;
            boardCheck = resData.split(":");
            let i = 0;
            do {
                if (boardCheck?.[i] == 'x') {
                    cellElements[i].classList.add('x')
                    xCount++;
                } else if (boardCheck?.[i] == 'o') {
                    cellElements[i].classList.add('o')
                    oCount++;
                } i++
            } while (i < boardCheck.length);
            // if (xCount <= oCount) {
            //     document.getElementsByClassName("board x").style.cursor = "allowed";
            // } if (xCount > oCount) {
            //     document.getElementsByClassName("board o").style.cursor = "allowed";
            // }
        })
        .then(() => { boardDetails(); })

        .catch(error => console.warn(error));
}