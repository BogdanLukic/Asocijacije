function initParty(){
    if(sessionStorage.uuid_of_game==null)
        window.open("lobby.jsp","_self");
    init();
    registerGame();
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

// EVENTS
// ===========================================================================
function addEventsParty(){
    reciveTurn();
}
function reciveTurn(){
    socket.on('send-turn',(data)=>{
        obj = JSON.parse(data);
        setTextInField(obj);
    })
}
