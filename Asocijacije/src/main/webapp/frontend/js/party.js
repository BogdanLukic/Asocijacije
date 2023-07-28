function method(button){
    button.classList.add('flip-horizontal-bottom');
    if(button.value == "false"){
        [column, place] = button.id.split('');
        console.log(column, place);
        obj = new Object();
        obj.column = column;
        obj.place = place;
        obj.uuid = sessionStorage.getItem('uuid_of_game');
        sendTurn(obj);
    }
}

function setTextInField(obj){
    str_id = obj.column+obj.place;
    place = document.querySelector(`#${str_id}`);
    place.innerHTML = obj.text;
    place.classList.add('flip-horizontal-bottom');
    place.value = "true";
}
