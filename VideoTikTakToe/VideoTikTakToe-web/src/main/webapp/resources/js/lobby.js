/**
 * 
 */
 
const startGameButton = document.getElementById("lobby:startGameButton");
const startGameButtonHidden = document.getElementById("lobby:startGameButtonHidden");

//Websocket
socket = new WebSocket('ws://localhost:8080/VideoTikTakToe-web/echo');
socket.onmessage = function(e) {
	startGameButtonHidden.click();
};

function sendStartGame() {
    socket.send('Das Spiel wird bei allen Lobbyspielern gestartet.');
}

//init
startGameButtonHidden.addEventListener("click", sendStartGame);
startGameButtonHidden.style.display = "none";
