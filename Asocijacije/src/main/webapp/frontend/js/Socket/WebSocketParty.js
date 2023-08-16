function initParty(){
    if(sessionStorage.uuid_of_game==null)
        window.open("lobby.jsp","_self");
    init();
    registerGame();
    getGameStatus();
    addEventsParty();
}
function registerGame(){
    if(!sessionStorage.getItem('uuid'))
    {
        uuid= uuidv4();
        sessionStorage.setItem('uuid',uuid);
    }
    else
        uuid = sessionStorage.getItem('uuid');

    obj = new Object();
    obj.accounts = account_information;
    obj.uuid = uuid;
    obj.socket = null

    socket.emit("registerGame",JSON.stringify(obj));
}

// EMITS
// ===========================================================================
function sendTurn(obj){
    socket.emit("get-label",JSON.stringify(obj));
}

function getGameStatus(){
    obj = new Object();
    obj.uuid = sessionStorage.getItem('uuid_of_game');
    socket.emit("get-game-status",JSON.stringify(obj));
}

function guessColumnEmit(obj){
    socket.emit("column-quest",JSON.stringify(obj));
}

function endTurn(obj){
    socket.emit("end-turn",JSON.stringify(obj));
}

function not_playable(){
    socket.emit("not-playable",sessionStorage.getItem('uuid_of_game'));
}

// EVENTS
// ===========================================================================
function addEventsParty(){
    reciveTurn();
    reciveStatus();
    reciveGuessColumn();
    reciveNotYourTurn();
    reciveEndGame();
}
function reciveTurn(){
    socket.on('send-turn',(data)=>{
        obj = JSON.parse(data);
        setTextInField(obj);
    });
    socket.on('your-turn',(data)=>{
        setDisable(data);
        console.log('igras');
        // restartTimer();
        startTimer(true);
    });
}
function reciveStatus(){
    socket.on('send-game-status',(data)=>{
        obj = JSON.parse(data);
        setGameInfo(obj);
        restartTimer(obj.second);
        // startTimer();
        console.log(obj);
    });
}
function reciveGuessColumn(){
    socket.on('column-quest',(data)=>{
        obj = JSON.parse(data);
        setGuessTurn(obj);
    })
}
function reciveNotYourTurn(){
    socket.on('not-your-turn',(data)=>{
        setDisable('default');
        // restartTimer();
        startTimer(false);
    }) 
}
function reciveEndGame(){
    socket.on('end-game',(data)=>{
        data = JSON.parse(data);
        console.log(data);
        sessionStorage.removeItem('uuid_of_game');
        displayEndGame(data);
    })
}