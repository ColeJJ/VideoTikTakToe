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
const board = document.getElementById("game:board");
const winningMessageElement = document.getElementById("game:winningMessage");
const restartButton = document.getElementById("game:restartButton");
const exitButton = document.getElementById("game:exitButton");
const winningMessageTextElement = document.querySelector("[id='game:data-winning-message-text']");
const winningMessageScoreElement = document.querySelector("[id='game:data-winning-message-score']");
var score1 = 0;
var score2 = 0;
let circleTurn;

//SP - Variablen
var rundenAnzahl = document.getElementById('game:bestof').value;
var scoreSpieler1 = document.getElementById('game:scoreSpieler1');
var siegeSpieler1 = document.getElementById('game:siegeSpieler1');
var niederlagenSpieler1 = document.getElementById('game:niederlagenSpieler1');
var scoreSpieler2 = document.getElementById('game:scoreSpieler2');
var siegeSpieler2 = document.getElementById('game:siegeSpieler2');
var niederlagenSpieler2 = document.getElementById('game:niederlagenSpieler2');
var spieler1 = document.getElementById('game:spielerName1').value;
var spieler2 = document.getElementById('game:spielerName2').value;


 
//init
rundenAnzahl = parseInt(rundenAnzahl);
exitButton.style.display = "none";
manageGame();
restartButton.addEventListener("click", manageGame);

function manageGame() {
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
			winningMessageTextElement.innerText = spieler2 + " Wins!";
		 } else {
			scoreSpieler1.value++;
			score1++;
			winningMessageScoreElement.innerText = score1 + ":" + score2;
			winningMessageTextElement.innerText = spieler1+ " Wins!";
		}
  }
  
  checkEndBestOf();
  
  winningMessageElement.classList.add("show");
}

function checkEndBestOf(){
	if(score1 > (rundenAnzahl / 2)) {
		siegeSpieler1.value++;
		niederlagenSpieler2.value++;
		winningMessageScoreElement.innerText = score1 + ":" + score2;
    	winningMessageTextElement.innerText = spieler1 + " hat gewonnen!";
    	restartButton.style.display = "none";
		exitButton.style.display = "block";
	} else if (score2 > (rundenAnzahl / 2)){
		siegeSpieler2.value++;
		niederlagenSpieler1.value++;
		winningMessageScoreElement.innerText = score1 + ":" + score2;
    	winningMessageTextElement.innerText = spieler2 + " hat gewonnen!";
    	restartButton.style.display = "none";
		exitButton.style.display = "block";
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
