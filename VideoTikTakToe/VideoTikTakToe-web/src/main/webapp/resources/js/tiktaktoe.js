/**
 * 
 */

const X_CLASS = "x";
const CIRCLE_CLASS = "circle";
const WINNING_COMBINATIONS = [
  [0, 1, 2],
  [3, 4, 5],
  [6, 7, 8],
  [0, 3, 6],
  [1, 4, 7],
  [2, 5, 8],
  [0, 4, 8],
  [2, 4, 6],
];
const cellElements = document.querySelectorAll('.cell');
const board = document.getElementById("board");
const winningMessageElement = document.getElementById("winningMessage");
const restartButton = document.getElementById("restartButton");
const winningMessageTextElement = document.querySelector("[id='data-winning-message-text']");
const winningMessageScoreElement = document.querySelector("[id='data-winning-message-score']");
var score1 = 0;
var score2 = 0;
var endBestOf = false;
let circleTurn;

//SP - Variablen
var rundenAnzahl = document.getElementById('bestof').value;
rundenAnzahl = parseInt(rundenAnzahl);
var scoreSpieler1 = document.getElementById('scoreSpieler1');
var scoreSpieler2 = document.getElementById('scoreSpieler2');
 
manageGame();

restartButton.addEventListener("click", manageGame);

function manageGame() {
  if(endBestOf){
	//
  }
  circleTurn = false;
  cellElements.forEach((cell) => {
    cell.classList.remove(X_CLASS);
    cell.classList.remove(CIRCLE_CLASS);
    cell.removeEventListener("click", handleClick);
    cell.addEventListener("click", handleClick, { once: true });
  });
  setBoardHoverClass();
  winningMessageElement.classList.remove("show");
}

function endBestOf() {
  circleTurn = false;
  cellElements.forEach((cell) => {
    cell.classList.remove(X_CLASS);
    cell.classList.remove(CIRCLE_CLASS);
    cell.removeEventListener("click", handleClick);
    cell.addEventListener("click", handleClick, { once: true });
  });
  setBoardHoverClass();
  winningMessageElement.classList.remove("show");
}

function handleClick(e) {
  const cell = e.target;
  const currentClass = circleTurn ? CIRCLE_CLASS : X_CLASS;
  placeMark(cell, currentClass);
  if (checkWin(currentClass)) {
    endGame(false);
  } else if (isDraw()) {
    endGame(true);
  } else {
    swapTurns();
    setBoardHoverClass();
  }
}

function endGame(draw) {
  if (draw) {
		winningMessageScoreElement.innerText = score1 + ":" + score2;
	    winningMessageTextElement.innerText = "Draw!";
  } else {
		 if(circleTurn) {
			scoreSpieler2.value++;
			score2++;
			winningMessageScoreElement.innerText = score1 + ":" + score2;
			winningMessageTextElement.innerText = "O'sWins!";
		 } else {
			scoreSpieler1.value++;
			score1++;
			winningMessageScoreElement.innerText = score1 + ":" + score2;
			winningMessageTextElement.innerText = "X'sWins!";
		}
  }
  
  checkEndBestOf();
  
  winningMessageElement.classList.add("show");
}

function checkEndBestOf(){
	if(score1 == rundenAnzahl) {
		endBestOf = true;
		winningMessageScoreElement.innerText = score1 + ":" + score2;
    	winningMessageTextElement.innerText = "Player 1 won the best of!";
    	restartButton.value = "Leave Game";
	} else if (score2 == rundenAnzahl){
		endBestOf = true;
		winningMessageScoreElement.innerText = score1 + ":" + score2;
    	winningMessageTextElement.innerText = "Player 2 won the best of!";
    	restartButton.value = "Leave Game";
	}
}

function isDraw() {
  return [...cellElements].every((cell) => {
    return (
      cell.classList.contains(X_CLASS) || cell.classList.contains(CIRCLE_CLASS)
    );
  });
}

function placeMark(cell, currentClass) {
  cell.classList.add(currentClass);
}

function swapTurns() {
  circleTurn = !circleTurn;
}

function setBoardHoverClass() {
  board.classList.remove(X_CLASS);
  board.classList.remove(CIRCLE_CLASS);
  if (circleTurn) {
    board.classList.add(CIRCLE_CLASS);
  } else {
    board.classList.add(X_CLASS);
  }
}

function checkWin(currentClass) {
  return WINNING_COMBINATIONS.some((combination) => {
    return combination.every((index) => {
      return cellElements[index].classList.contains(currentClass);
    });
  });
}
