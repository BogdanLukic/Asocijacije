function init(){
    getConnection();
    getAccountInformations();
}
var socket;
function getConnection(){
    if(!sessionStorage.getItem("socketSessionId"))
    {
        socket = io("ws://localhost:2020"); 
        socket.on('connect', () => {
            sessionStorage.setItem('socketSessionId', socket.id);
            console.log("Uspesno ste se prijavili");
          });
    }
    else
    {
        sessionId = sessionStorage.getItem("socketSessionId");
        socket = io("ws://localhost:2020", { query: { sessionId } }); 
        console.log("Ponovo ste se prijavili");
    }
    addEvents();
}

var account_information;
function getAccountInformations(){
    if(!sessionStorage.getItem("account_information"))
    {
        let options = {
            method: 'GET',
            headers: {
                'Content-Type': 
                    'application/json;charset=utf-8'
            }  
        }
        let fetchRes = fetch("http://localhost:8081/Asocijacije_war_exploded/getAccount",options);
        fetchRes.then(res => res.json())
        .then(
            d=>{
                sessionStorage.setItem("account_information",d.token);
                account_information = JSON.parse(d.token);
                register();
            }
        )
    }
    else{
        account_information = JSON.parse(sessionStorage.getItem("account_information"));
        register();
    }
}

// EMITS
// ===========================================================================
var uuid;
function register(){
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

    socket.emit("register",JSON.stringify(obj));
    getListOfActiveUsers();
}

function getListOfActiveUsers(){
    console.log(uuid);
    socket.emit('getListOfActiveUsers',uuid);
}

// EVENTS
// ===========================================================================
function addEvents(){
    getListOfActiveUsersEvent();
}

function getListOfActiveUsersEvent(){
    socket.on('getListOfActiveUsers',(data)=>{
        console.log('Lista aktivnih korisnika:', data);
        setActiveUsers(data);
    })
}