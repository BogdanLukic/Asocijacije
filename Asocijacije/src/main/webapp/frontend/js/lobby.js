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
                <div>
                    <img src="../images/icons/sword-on.png">
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

// ==========================================

function invite_response(x){
    
}

overlay = document.querySelector("#overlay");

function open_overlay(){
    overlay.style.display = 'flex';
    overlay.classList.add('scale-in-center');
    setTimeout(() => {
        overlay.classList.remove("scale-in-center");
    }, 600);
}
function close_invite(){
    overlay.classList.add('scale-out-center');
    setTimeout(() => {
        overlay.classList.remove("scale-out-center");
        overlay.style.display = 'none';
    }, 1000);
    
}