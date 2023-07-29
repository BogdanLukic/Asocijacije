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

// EVENTS
// ===========================================================================
function addEventsParty(){
    reciveTurn();
    reciveStatus();
}
function reciveTurn(){
    socket.on('send-turn',(data)=>{
        obj = JSON.parse(data);
        setTextInField(obj);
    })
}
function reciveStatus(){
    socket.on('send-game-status',(data)=>{
        obj = JSON.parse(data);
        setGameInfo(obj);
    })
}
