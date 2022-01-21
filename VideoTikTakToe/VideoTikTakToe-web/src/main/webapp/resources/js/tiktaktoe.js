/**
 *  Das Skript, um die Interaktionen mit dem TikTakToe zu verarbeiten
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
const exitButtonHidden = document.getElementById("game:exitButtonHidden");
const abbrechenButton = document.getElementById("game:abbrechenButton");
const abbrechenButtonHidden = document.getElementById("game:abbrechenButtonHidden");
const winningMessageTextElement = document.querySelector("[id='game:data-winning-message-text']");
const winningMessageScoreElement = document.querySelector("[id='game:data-winning-message-score']");
var currentCell;
var currentClass;
var score1 = 0;
var score2 = 0;
var socket;
let circleTurn;

//SP - HiddenValues
var rundenAnzahl = document.getElementById('game:bestof').value;
var scoreSpieler1 = document.getElementById('game:scoreSpieler1');
var siegeSpieler1 = document.getElementById('game:siegeSpieler1');
var niederlagenSpieler1 = document.getElementById('game:niederlagenSpieler1');
var scoreSpieler2 = document.getElementById('game:scoreSpieler2');
var siegeSpieler2 = document.getElementById('game:siegeSpieler2');
var niederlagenSpieler2 = document.getElementById('game:niederlagenSpieler2');

var spieler1 = document.getElementById('game:spielerName1').value;
var spieler2 = document.getElementById('game:spielerName2').value;
var isAdmin = document.getElementById('game:isAdmin').value;




//Websocket
socket = new WebSocket('ws://localhost:8080/VideoTikTakToe-web/echo');
socket.onmessage = function(e) {  
    if(e.data === 'restart') {
		//hier das game beenden
		manageGame();
	} else if (e.data  === 'exit'){
		//hier das game beenden, Aufruf einer MB Methoden
		exitButtonHidden.click();
	} else if (e.data  === 'abbrechen'){
		//hier das game abbrechen, Aufruf einer MB Methoden
		abbrechenButtonHidden.click();
	} else {
		//handle click event
        var currentCellID = e.data;
        var currentCell = document.getElementById(currentCellID);
	    currentClass = circleTurn ? CIRCLE_CLASS : X_CLASS;
	    if(currentCell != null){
			currentCell.classList.add(currentClass);
	    }
	    if (checkWin(currentClass)) {
	      endGame(false);
	    } else if (isDraw()) {
	      endGame(true);
	    } else {
	      swapTurns();
	      setBoardHoverClass();
	    }
	}
};

function sendRestart() {
    socket.send('restart');
}

function sendExit() {
	socket.send('exit');
}

function sendAbbrechen() {
	socket.send('abbrechen');
}

 
//init
rundenAnzahl = parseInt(rundenAnzahl);
exitButton.style.display = "none";
restartButton.addEventListener("click", sendRestart);
exitButton.addEventListener("click", sendExit);
abbrechenButton.addEventListener("click", sendAbbrechen);
manageGame();

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
  cell = e.target;
  var id = cell.id;
  socket.send(id);
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
		exitButton.style.display = "block";
    	restartButton.style.display = "none";
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
