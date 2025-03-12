let gameId = null;
let playerId = null;
let currentPlayerTurn = null;
let player1Id = null;
let player2Id = null;
let player1Name = null;
let player2Name = null;
let player1ShipsPlaced = false;
let player2ShipsPlaced = false;

document.addEventListener('DOMContentLoaded', () => {
    const topBoardTiles = document.querySelectorAll('.board__top .board__tile');

    topBoardTiles.forEach(tile => {
        tile.addEventListener('click', () => {
            const index = tile.getAttribute('data-index');
            shootCell(index);
        });
    });
});

function updateTurnIndicator() {
    const turnIndicator = document.getElementById('turn-indicator');
    const topBoardTiles = document.querySelectorAll('.board__top .board__tile');

    if (!player1ShipsPlaced || !player2ShipsPlaced) {
        turnIndicator.textContent = 'Waiting for setup...';
        topBoardTiles.forEach(tile => {
            tile.disabled = true;
        });
    } else if (currentPlayerTurn === player1Id) {
        turnIndicator.textContent = `${player1Name}'s turn!`;
        const player1TopBoard = document.getElementById('player1-opponent-board');
        const player2TopBoard = document.getElementById('player2-opponent-board');
        enableShootButtons(player1TopBoard);
        disableShootButtons(player2TopBoard);
    } else if (currentPlayerTurn === player2Id) {
        turnIndicator.textContent = `${player2Name}'s turn!`;
        const player1TopBoard = document.getElementById('player1-opponent-board');
        const player2TopBoard = document.getElementById('player2-opponent-board');
        enableShootButtons(player2TopBoard);
        disableShootButtons(player1TopBoard);
    }
}

function enableShootButtons(boardElement) {
    if (boardElement) {
        const tiles = boardElement.querySelectorAll('.board__tile');
        tiles.forEach(tile => {
            tile.disabled = false;
        });
    }
}

function disableShootButtons(boardElement) {
    if (boardElement) {
        const tiles = boardElement.querySelectorAll('.board__tile');
        tiles.forEach(tile => {
            tile.disabled = true;
        });
    }
}

function createGame() {
    fetch('/game/create', {
        method: 'POST'
    })
        .then(response => response.json())
        .then(data => {
            gameId = data.id;
            console.log('Game created with ID:', gameId);
            currentPlayerTurn = null;
            player1ShipsPlaced = false;
            player2ShipsPlaced = false;
            updateTurnIndicator();
        })
        .catch(error => console.error('Error creating game:', error));
}

function createPlayer() {
    const name = prompt('Enter your name:');
    if (name && gameId) {
        fetch(`/player/create/${gameId}?name=${name}`, {
            method: 'POST'
        })
            .then(response => response.json())
            .then(data => {
                playerId = data.id;
                if (!player1Id) {
                    player1Id = playerId;
                    player1Name = name;
                    alert('Player 1 created. Now place your ships.');
                } else if (!player2Id) {
                    player2Id = playerId;
                    player2Name = name;
                    alert('Player 2 created. Now place your ships.');
                }
                console.log('Player created with ID:', playerId);
                updateTurnIndicator();
            })
            .catch(error => console.error('Error creating player:', error));
    } else {
        alert('Please create a game first and enter a name.');
    }
}

function placeShip() {
    if (playerId) {
        const shipType = prompt('Enter ship type (CARRIER, BATTLESHIP, DESTROYER, SUBMARINE, PATROL_BOAT):');
        const row = prompt('Enter starting row (0-9):');
        const col = prompt('Enter starting column (0-9):');
        const isHorizontal = confirm('Is the ship horizontal? (OK for Yes, Cancel for No)');

        if (shipType && row && col) {
            fetch(`/player/${playerId}/placeShip?shipType=${shipType}&row=${row}&col=${col}&isHorizontal=${isHorizontal}`, {
                method: 'POST'
            })
                .then(response => response.json())
                .then(data => {
                    console.log('Ship placed:', data);
                    if (playerId === player1Id) {
                        player1ShipsPlaced = true;
                    } else if (playerId === player2Id) {
                        player2ShipsPlaced = true;
                    }
                    if (player1ShipsPlaced && player2ShipsPlaced) {
                        currentPlayerTurn = player1Id;
                        updateTurnIndicator();
                        alert('All ships placed. Player 1 starts shooting!');
                    }

                    updateBoard(playerId);
                })
                .catch(error => console.error('Error placing ship:', error));
        }
    } else {
        alert('Please create a player first.');
    }
}

function updateBoard(playerId) {
    fetch(`/player/${playerId}/board`)
        .then(response => response.json())
        .then(data => {
            const boardElement = document.getElementById(`player${playerId === player1Id ? '1' : '2'}-board`);
            boardElement.innerHTML = '';

            data.cells.forEach(cell => {
                const cellElement = document.createElement('button');
                cellElement.classList.add('board__tile');
                cellElement.style.gridRow = cell.row + 1;
                cellElement.style.gridColumn = cell.col + 1;

                if (cell.isShip) {
                    cellElement.classList.add('ship');
                }
                if (cell.isHit) {
                    cellElement.classList.add('shot');
                }

                boardElement.appendChild(cellElement);
            });
        })
        .catch(error => console.error('Error fetching board:', error));
}

function updateBoardView(boardElement, boardData, isOpponentBoard) {
    boardElement.innerHTML = '';

    boardData.cells.forEach(cell => {
        const cellElement = document.createElement('button');
        cellElement.classList.add('board__tile');
        cellElement.style.gridRow = cell.row + 1;
        cellElement.style.gridColumn = cell.col + 1;

        if (isOpponentBoard) {
            if (cell.isHit) {
                if (cell.isShip) {
                    cellElement.classList.add('ship', 'shot');
                } else {
                    cellElement.classList.add('shot');
                }
            }
        } else {
            if (cell.isShip) {
                cellElement.classList.add('ship');
            }
            if (cell.isHit) {
                cellElement.classList.add('shot');
            }
        }

        if (isOpponentBoard) {
            cellElement.addEventListener('click', () => {
                const index = cell.row * 10 + cell.col;
                shootCell(index);
            });
        }

        boardElement.appendChild(cellElement);
    });

    if (isOpponentBoard) {
        const isCurrentPlayerBoard = (boardElement.id === `player${currentPlayerTurn === player1Id ? '1' : '2'}-opponent-board`);
        if (isCurrentPlayerBoard) {
            enableShootButtons(boardElement);
        } else {
            disableShootButtons(boardElement);
        }
    }
}

function shootCell(index) {
    if (gameId && player1ShipsPlaced && player2ShipsPlaced) {
        const row = Math.floor(index / 10);
        const col = index % 10;

        const shootingPlayerId = currentPlayerTurn;
        const opponentPlayerId = shootingPlayerId === player1Id ? player2Id : player1Id;

        fetch(`/game/${gameId}/player/${shootingPlayerId}/shoot?row=${row}&col=${col}`, {
            method: 'POST'
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Failed to shoot cell');
                }
                return fetch(`/player/${opponentPlayerId}/board`);
            })
            .then(response => response.json())
            .then(data => {
                console.log('Updated opponent board state:', data);

                const topBoardElement = document.getElementById(`player${shootingPlayerId === player1Id ? '1' : '2'}-opponent-board`);
                updateBoardView(topBoardElement, data, true);

                const bottomBoardElement = document.getElementById(`player${opponentPlayerId === player1Id ? '1' : '2'}-board`);
                updateBoardView(bottomBoardElement, data, false);

                currentPlayerTurn = opponentPlayerId;
                updateTurnIndicator();
            })
            .catch(error => {
                console.error('Error shooting cell:', error);
                alert('Error shooting cell: ' + error.message);
            });
    } else {
        alert('The game is not ready or ships are not placed.');
    }
}
