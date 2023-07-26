msg_input_field = document.querySelector("#new_msg");

msg_input_field.addEventListener('keydown', function(event) {
    if (event.keyCode === 13)
        sendMsg();
    });

function setActiveUsers(list){
    list = JSON.parse(list);
    
    var active_users_field = document.querySelector("#active-users");
    var str = "";
    list.forEach(element => {
        elementJSON = JSON.stringify(element);
        encodedElementJSON = encodeURIComponent(elementJSON);
        str+=`<div class="active-user">
            <div>
                <img class="active-user-character" src="../images/characters/${element.character}.png">
            </div>
            <div class="active-user-info">
                <div>
                    <p>${element.username}</p>
                </div>
                <div class="active-user-info-status">
                    <div class="dot free"></div>
                    <p>Spreman za igru</p>
                </div>
            </div>
            <div class="active-user-options">
                <div onclick="startPrivateMessaging('${encodedElementJSON}')" class='clicable'>
                    <img src="../images/icons/message.png">
                </div>
                <div id="swords-${element.username}" class="clicable" onclick="add_dots('${encodedElementJSON}')">
                    <img src="../images/icons/sword-on.png">
                </div>
                <div id="loader-${element.username}" class="loader" style="display: none;">
                    <span class="loader__dot">.</span>
                    <span class="loader__dot">.</span>
                    <span class="loader__dot">.</span>
                </div>
                <div id="x-${element.username}" class="x" style="display: none;">
                    <i class="bi bi-x"></i>
                </div>
            </div>
        </div>`;
    });
    active_users_field.innerHTML = str;
}
function setNewGlobalMsg(msg,username){
    messages_place = document.querySelector("#chat-message");
    if(msg.username == username)
    {
        messages_place.innerHTML +=`
            <div class="chat-message-one">
            <div>
                <p class="chat-message-username">Ti: </p>
            </div>
            <div class="chat-message-msg">
                <p>${msg.text}</p>
            </div>
        </div>
        `;
    }
    else
    {
        messages_place.innerHTML +=`
            <div class="chat-message-one">
            <div>
                <p class="chat-message-username">${msg.username}:</p>
            </div>
            <div class="chat-message-msg">
                <p>&nbsp;${msg.text}</p>
            </div>
        </div>
        `;
    }
}

var priv_msg_send;

function setNewPrivateMsg(person){
    person = decodeURIComponent(person);
    person = JSON.parse(person);
    chat_field_heder = document.querySelector("#chat-field-heder");
    plain_html = `
        <div onclick="setChat('global')" id="global-chat" class="active">
            <p>Global chat</p>
        </div>
        <div class="no-active global-active" id="private-chat">
            <div onclick="setChat('private')" class="new-message-open">
                <div>
                    <img src="../images/characters/${person[0]['character_id']}.png">
                </div>
                <div>
                    <p>${person[0]['username']}</p>
                </div>
            </div>
        </div>
    `;
    chat_field_heder.innerHTML = plain_html;
    priv_msg_send=person;

    setChat('private');
    
    plain_html_field = `<div class="welcome-msg">
                    <p>Sve poruke će biti obrisane nakon izlaska iz privatnog chat-a! </p>
                </div>`;
    for(var message in person){
        plain_html_field+=`<div class="chat-message-one">
                    <div>
                        <p class="chat-message-username">${person[0]['username']}:</p>
                    </div>
                    <div class="chat-message-msg">
                        <p>&nbsp;${person[message]['text']}</p>
                    </div>
                </div>`;
    }
    private_chat_field = document.querySelector("#private-chat-message");
    private_chat_field.innerHTML = "";
    private_chat_field.innerHTML = plain_html_field;

    clearChat(account_information.username,person[0]['username']);
    clearList(person);

}
function clearList(person){
    username = person[0]['username'];
    delete saved_list_unreader_messages[username];
    setFieldOfListMessages_loop(saved_list_unreader_messages);
}
// ========================================
var chat_type = 'global'
function setChat(switcher){
    global_chat = document.querySelector("#global-chat");
    private_chat = document.querySelector("#private-chat");

    global_chat_field = document.querySelector("#chat-message");
    private_chat_field = document.querySelector("#private-chat-message");
    chat_type = switcher;
    switch(switcher){
        case 'global':
            private_chat.classList.add('global-active');
            global_chat.classList.remove('private-active');
            
            global_chat_field.style='display:visible';
            private_chat_field.style='display:none';

            priv_msg_send = '';
            private_chat.innerHTML = '';
            break;
        case 'private':
            global_chat.classList.add('private-active');
            private_chat.classList.remove('global-active');

            global_chat_field.style='display:none';
            private_chat_field.style='display:visible';
            
            break;
    }
}

function getChatType(){
    return chat_type;
}

function startPrivateMessaging(account){
    private_chat_field = document.querySelector("#private-chat-message");
    private_chat_field.innerHTML = `<div class="welcome-msg">
                                        <p>Sve poruke će biti obrisane nakon izlaska iz privatnog chat-a! </p>
                                    </div>`;
    account = decodeURIComponent(account);
    account = JSON.parse(account);
    chat_field_heder = document.querySelector("#chat-field-heder");
    plain_html = `
                <div onclick="setChat('global')" id="global-chat" class="active">
                    <p>Global chat</p>
                </div>
                <div class="no-active global-active" id="private-chat">
                    <div onclick="setChat('private')" class="new-message-open">
                        <div>
                            <img src="../images/characters/${account.character}.png">
                        </div>
                        <div>
                            <p>${account.username}</p>
                        </div>
                    </div>
                </div>
    `;
    chat_field_heder.innerHTML = plain_html;
    priv_msg_send=account.username;
    setChat('private');
}

function getPrivMsgSend(){
    if(Array.isArray(priv_msg_send))
        return priv_msg_send[0]['username'];
    return priv_msg_send;
}

field_of_list_messages=document.querySelector("#field_of_list_messages");
var saved_list_unreader_messages;
function setFieldOfListMessages(list_unreader_messages){
    saved_list_unreader_messages = list_unreader_messages;
    setFieldOfListMessages_loop(saved_list_unreader_messages);
}

var old_msg = '';
function setFieldOfListMessages_loop(list_unreader_messages){
    plain_html = "";
    for(let element in list_unreader_messages)
    {
        elementJSON = JSON.stringify(list_unreader_messages[element]);
        encodedElementJSON = encodeURIComponent(elementJSON);
        if(getPrivMsgSend() != element)
            plain_html+=`
            <div class="new-message" onclick="setNewPrivateMsg('${encodedElementJSON}')">
                <div>
                    <img src='../images/characters/${list_unreader_messages[element][0]['character_id']}.png'>
                </div>
                <div>
                    <p>${element}</p>
                </div>
                <div class="new-message-num">
                    <p>${list_unreader_messages[element].length}</p>
                </div>
            </div>
            `;
    }
    field_of_list_messages.innerHTML = plain_html;
}

// Timer
// ==========================================
overlay = document.querySelector("#overlay");
enemy_save = '';
function setOverlay(obj){
    enemy_save = obj;
    overlay.innerHTML = ` <div class="invite">
                            <div>
                                <img src="../images/characters/${obj.character}.png">
                            </div>
                            <div class="invite-info">
                                <h3>${obj.username}</h3>
                                <p>te izaziva na borbu</p>
                            </div>
                            <div class="invire-response">
                                <div class="invire-response-field accept clicable" onclick="acceptChallenge()">
                                    <i class="bi bi-check2"></i>
                                </div>
                                <div><div id="app"></div></div>
                                <div class="invire-response-field denied clicable" onclick="declineChallenge()">
                                    <i class="bi bi-x"></i>
                                </div>
                            </div>
                        </div>`;
    open_overlay();
}
var back_interval = null; 
function open_overlay(){
    startTimer();
    overlay.style.display = 'flex';
    overlay.classList.add('scale-in-center');
    
    setTimeout(() => {
        overlay.classList.remove("scale-in-center");
    }, 600);

    back_interval = setTimeout(() => {
        close_invite();
    }, TIME_LIMIT*1000);
}
function close_invite(){
    overlay.classList.add('scale-out-center');
    setTimeout(() => {
        overlay.classList.remove("scale-out-center");
        overlay.style.display = 'none';
    }, 600);
    clearInterval(back_interval); 
    restartTimer();
}


let FULL_DASH_ARRAY = 283;
let WARNING_THRESHOLD = 10;
let ALERT_THRESHOLD = 5;

const COLOR_CODES = {
  info: {
    color: "green"
  },
  warning: {
    color: "orange",
    threshold: WARNING_THRESHOLD
  },
  alert: {
    color: "red",
    threshold: ALERT_THRESHOLD
  }
};

const TIME_LIMIT = 15;
let timePassed = 0;
let timeLeft = TIME_LIMIT;
let timerInterval = null;
let remainingPathColor = COLOR_CODES.info.color;

function onTimesUp() {
  clearInterval(timerInterval);
}

function startTimer() {
    document.getElementById("app").innerHTML = `
    <div class="base-timer">
    <svg class="base-timer__svg" viewBox="0 0 100 100" xmlns="http://www.w3.org/2000/svg">
        <g class="base-timer__circle">
        <circle class="base-timer__path-elapsed" cx="50" cy="50" r="45"></circle>
        <path
            id="base-timer-path-remaining"
            stroke-dasharray="283"
            class="base-timer__path-remaining ${remainingPathColor}"
            d="
            M 50, 50
            m -45, 0
            a 45,45 0 1,0 90,0
            a 45,45 0 1,0 -90,0
            "
        ></path>
        </g>
    </svg>
    <span id="base-timer-label" class="base-timer__label">${formatTime(
        timeLeft
    )}</span>
    </div>
    `;
  timerInterval = setInterval(() => {
    timePassed = timePassed += 1;
    timeLeft = TIME_LIMIT - timePassed;
    document.getElementById("base-timer-label").innerHTML = formatTime(timeLeft);
    setCircleDasharray();
    setRemainingPathColor(timeLeft);
    if (timeLeft <= 0) {
      onTimesUp();
    }
  }, 1000);
}

function restartTimer(){
        clearInterval(timerInterval);
        document.getElementById("app").innerHTML = ``;
        FULL_DASH_ARRAY = 283;
        WARNING_THRESHOLD = 10;
        ALERT_THRESHOLD = 5;
        timePassed = 0;
        timeLeft = TIME_LIMIT;
        timerInterval = null;
        remainingPathColor = COLOR_CODES.info.color;
}

function formatTime(time) {
  const minutes = Math.floor(time / 60);
  let seconds = time % 60;

  if (seconds < 10) {
    seconds = `0${seconds}`;
  }

  return `${seconds}`;
}

function setRemainingPathColor(timeLeft) {
  const { alert, warning, info } = COLOR_CODES;
  if (timeLeft <= alert.threshold) {
    document
      .getElementById("base-timer-path-remaining")
      .classList.remove(warning.color);
    document
      .getElementById("base-timer-path-remaining")
      .classList.add(alert.color);
  } else if (timeLeft <= warning.threshold) {
    document
      .getElementById("base-timer-path-remaining")
      .classList.remove(info.color);
    document
      .getElementById("base-timer-path-remaining")
      .classList.add(warning.color);
  }
}

function calculateTimeFraction() {
  const rawTimeFraction = timeLeft / TIME_LIMIT;
  return rawTimeFraction - (1 / TIME_LIMIT) * (1 - rawTimeFraction);
}

function setCircleDasharray() {
  const circleDasharray = `${(
    calculateTimeFraction() * FULL_DASH_ARRAY
  ).toFixed(0)} 283`;
  document
    .getElementById("base-timer-path-remaining")
    .setAttribute("stroke-dasharray", circleDasharray);
}

// =========================================================
function add_dots(enemy){
    enemy = decodeURIComponent(enemy);
    enemy = JSON.parse(enemy);

    sword = document.querySelector(`#swords-${enemy.username}`);
    loader = document.querySelector(`#loader-${enemy.username}`);

    sword.style = 'display: none';  
    loader.style.display = '';

    obj = new Object();
    obj.challenger = account_information;
    obj.enemy = enemy;

    sendInvate(obj);
}

// function remove_dots(obj){
//     sword = document.querySelector(`#swords-${obj.enemy}`);
//     loader = document.querySelector(`#loader-${obj.enemy}`);
//     switch(obj.response){
//         case true:
//             console.log('Krece mec');
//             break;
//         case false:
//             loader.style = 'display: none';  
//             sword.style.display = '';
//             break;
//     }
// }

function acceptChallenge(){
    obj = new Object();
    obj.challenger = enemy_save;
    obj.enemy = account_information;
    obj.response = true;
    sendIviteResponse(obj);
    close_invite();
}

function declineChallenge(){
    obj = new Object();
    obj.challenger = enemy_save;
    obj.enemy = account_information;
    obj.response = false;
    sendIviteResponse(obj);
    close_invite();
}

function responseChallenge(obj){
    if(obj.response){
        // approved challenge
    }
    else{
        // declined challenge
        loader = document.querySelector(`#loader-${obj.enemy.username}`);
        x = document.querySelector(`#x-${obj.enemy.username}`);
        sword = document.querySelector(`#swords-${obj.enemy.username}`);

        loader.style.display='none';
        x.style.display = '';

        setTimeout(()=>{
            x.style.display = 'none';
            sword.style.display = '';
        },2500);
    }
}