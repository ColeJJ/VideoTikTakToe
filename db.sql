Create Table SP_Lobby (
    LobbyID INT NOT NULL,
    Lobbyname VARCHAR(20) NOT NULL,
    Lobbydauer INT NOT NULL,
    Audioeinstellung NUMBER(1) NOT NULL,
    Videoeinstellung NUMBER(1) NOT NULL,
    Constraint PK_Lobby PRIMARY KEY (LobbyID)
);

Create Table SP_User (
    UserID INT NOT NULL,
    Benutzername VARCHAR(20) NOT NULL,
    Passwort VARCHAR(30) NOT NULL,
    email VARCHAR(40),
    admin NUMBER(1),
    LobbyID INT,
    Constraint PK_User PRIMARY KEY (UserID),
    Constraint FK_Lobby FOREIGN KEY (LobbyID) REFERENCES SP_Lobby(LobbyID)
);