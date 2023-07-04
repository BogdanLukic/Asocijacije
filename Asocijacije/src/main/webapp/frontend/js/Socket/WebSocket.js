function init(){
    getConnection();
    getToken();
}
var socket;
function getConnection(){
    if(!sessionStorage.getItem("socketSessionId"))
    {
        socket = io("ws://localhost:2020"); 
        socket.on('connect', () => {
            sessionStorage.setItem('socketSessionId', socket.id);
          });
    
        socket.on('getListOfActiveUsers',(data)=>{
            console.log('Lista aktivnih korisnika:', data);
        })
    }
    else
    {
        sessionId = sessionStorage.getItem("socketSessionId");
        socket = io("ws://localhost:2020", { query: { sessionId } }); 
        console.log(socket);
        // socket.on('connect', () => {});
        socket.on('getListOfActiveUsers',(data)=>{
            console.log('Lista aktivnih korisnika:', data);
        })
    }
}

var token;
function getToken(){
    if(!sessionStorage.getItem("token"))
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
                sessionStorage.setItem("token",d.token);
                token = d.token;
                socket.emit('register',token);  
                socket.emit('getListOfActiveUsers',token);
            }
        )
    }
    else{
        getListOfActiveUsers();
    }
}

function getListOfActiveUsers(){
    if(socket != null)
        socket.emit('getListOfActiveUsers',token);
}
