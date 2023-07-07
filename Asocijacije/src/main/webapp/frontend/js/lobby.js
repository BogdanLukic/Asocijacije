msg_input_field = document.querySelector("#new_msg");

msg_input_field.addEventListener('keydown', function(event) {
    if (event.keyCode === 13)
        sendGlobalMsg();
    });

function setActiveUsers(list){
    list = JSON.parse(list);
    
    var active_users_field = document.querySelector("#active-users");
    var str = "";
    list.forEach(element => {
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
                <div>
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