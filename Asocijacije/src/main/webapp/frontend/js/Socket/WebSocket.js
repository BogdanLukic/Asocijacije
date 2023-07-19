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
                privateMsgChat();
            }
        )
    }
    else{
        account_information = JSON.parse(sessionStorage.getItem("account_information"));
        register();
        privateMsgChat();
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
}

function getListOfActiveUsers(){
    socket.emit('getListOfActiveUsers',uuid);
}

function sendMsg(){
    msg_type = getChatType();
    switch(msg_type){
        case 'global':
            sendGlobalMsg();
            break;
        case 'private':
            sendPrivateMsg();
            break;
    }
}

function sendGlobalMsg(){
    msg = document.querySelector("#new_msg");
    obj = new Object();
    obj.username = account_information.username;
    obj.text = msg.value;
    socket.emit("global-message",JSON.stringify(obj));
    msg.value ="";
}

function sendPrivateMsg(){
    msg = document.querySelector("#new_msg");
    msgTo = getPrivMsgSend();
    obj = new Object();
    
    obj.username = account_information.username;
    obj.text = msg.value;
    // obj.message_to = msgTo.username; !!
    obj.messageTo = msgTo;
    socket.emit("private-message",JSON.stringify(obj));

    private_chat_field = document.querySelector("#private-chat-message");
    plain_html=`<div class="chat-message-one">
                    <div>
                        <p class="chat-message-username">Ti:</p>
                    </div>
                    <div class="chat-message-msg">
                        <p>&nbsp;${msg.value}</p>
                    </div>
                </div>`;
    private_chat_field.innerHTML +=  plain_html;
    msg.value ="";
}

function clearChat(username,msgTo){
    obj = Object();
    obj.username = username;
    obj.messageTo = msgTo;
    socket.emit("remove-chat",JSON.stringify(obj));
}

// EVENTS
// ===========================================================================
function addEvents(){
    getListOfActiveUsersEvent();
    globalMsg();
    privateMsg();
}

function getListOfActiveUsersEvent(){
    socket.on('getListOfActiveUsers',(data)=>{
        setActiveUsers(data);
    })
}
function globalMsg(){
    socket.on('global-message',(data)=>{
        global_msg = JSON.parse(data);
        setNewGlobalMsg(global_msg,account_information.username);
    }) 
}

var private_messages;

function privateMsg(){
    socket.on('private-message',(data)=>{
        data = JSON.parse(data);
        private_messages = data;
        setFieldOfListMessages(private_messages);
    })
}

function getPrivateMessages(){
    return private_messages;
}

function privateMsgChat(){
    let str = "private-message-"+account_information.username;
    socket.on(str,(data)=>{
        data = JSON.parse(data);
        if(getPrivMsgSend() == data.username){
            str = `<div class="chat-message-one">
                    <div>
                        <p class="chat-message-username">${data.username}:</p>
                    </div>
                    <div class="chat-message-msg">
                        <p>${data.text}</p>
                    </div>
                </div>`;
            place=document.querySelector("#private-chat-message");
            place.innerHTML+=str;
            obj = new Object();
            obj.username = account_information.username;
            obj.messageTo = data.username;
            socket.emit("remove-chat",JSON.stringify(obj));
        }
    })
}