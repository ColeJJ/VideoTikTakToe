/**
 *  Das Skript, um Websockets Interaktionen in der Lobby zu ermoeglichen
 */
 
const startGameButton = document.getElementById("lobby:startGameButton");
const startGameButtonHidden = document.getElementById("lobby:startGameButtonHidden");

//Websocket
socket = new WebSocket('ws://localhost:8080/VideoTikTakToe-web/echo');
socket.onmessage = function(e) {
	if(e.data === 'gameStart'){
		startGameButtonHidden.click();
	}
};

function sendStartGame() {
    socket.send('gameStart');
}

//init
startGameButton.addEventListener("click", sendStartGame);
startGameButtonHidden.style.display = "none";
