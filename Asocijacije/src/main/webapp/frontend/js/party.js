function method(button){
    button.classList.add('flip-horizontal-bottom');
    if(button.value == "false"){
        [column, place] = button.id.split('');
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

var settedGameInfo = false; 
function setGameInfo(obj){  
    party_page = document.querySelector(".party-page");
    party_page.style='display:';
    party_page.classList.add('scale-in-center');
    console.log(obj);
    settedGameInfo = true;
    img_challanger = document.querySelector("#img-challanger");
    img_challanger.src = `../images/characters/${obj.challenge.challenger.character_id}.png`;
    img_enemy = document.querySelector("#img-enemy");
    img_enemy.src = `../images/characters/${obj.challenge.enemy.character_id}.png`;

    name_challanger = document.querySelector("#name-challanger");
    name_challanger.innerHTML = `${obj.challenge.challenger.username}`;
    name_enemy = document.querySelector("#name-enemy");
    name_enemy.innerHTML = `${obj.challenge.enemy.username}`;

    points_challanger = document.querySelector("#points-challanger");
    points_enemy = document.querySelector("#points-enemy");
    points_challanger.innerHTML = obj.points_of_challanger;
    points_enemy.innerHTML = obj.points_of_enemy;

    setFields(obj);

}
function setFields(obj){
    status_column_a = obj.status_column_a;
    status_column_b = obj.status_column_b;
    status_column_c = obj.status_column_c;
    status_column_d = obj.status_column_d;

    // A
    if(status_column_a.one!=null){
        place = document.querySelector("#a1");
        place.innerHTML = status_column_a.one;
        place.classList.add('flip-horizontal-bottom');
        place.value = "true";
    }
    if(status_column_a.two!=null){
        place = document.querySelector("#a2");
        place.innerHTML = status_column_a.two;
        place.classList.add('flip-horizontal-bottom');
        place.value = "true";
    }
    if(status_column_a.three!=null){
        place = document.querySelector("#a3");
        place.innerHTML = status_column_a.three;
        place.classList.add('flip-horizontal-bottom');
        place.value = "true";
    }
    if(status_column_a.four!=null){
        place = document.querySelector("#a4");
        place.innerHTML = status_column_a.four;
        place.classList.add('flip-horizontal-bottom');
        place.value = "true";
    }
    // B
    if(status_column_b.one!=null){
        place = document.querySelector("#b1");
        place.innerHTML = status_column_b.one;
        place.classList.add('flip-horizontal-bottom');
        place.value = "true";
    }
    if(status_column_b.two!=null){
        place = document.querySelector("#b2");
        place.innerHTML = status_column_b.two;
        place.classList.add('flip-horizontal-bottom');
        place.value = "true";
    }
    if(status_column_b.three!=null){
        place = document.querySelector("#b3");
        place.innerHTML = status_column_b.three;
        place.classList.add('flip-horizontal-bottom');
        place.value = "true";
    }
    if(status_column_b.four!=null){
        place = document.querySelector("#b4");
        place.innerHTML = status_column_b.four;
        place.classList.add('flip-horizontal-bottom');
        place.value = "true";
    }
    // C
    if(status_column_c.one!=null){
        place = document.querySelector("#c1");
        place.innerHTML = status_column_c.one;
        place.classList.add('flip-horizontal-bottom');
        place.value = "true";
    }
    if(status_column_c.two!=null){
        place = document.querySelector("#c2");
        place.innerHTML = status_column_c.two;
        place.classList.add('flip-horizontal-bottom');
        place.value = "true";
    }
    if(status_column_c.three!=null){
        place = document.querySelector("#c3");
        place.innerHTML = status_column_c.three;
        place.classList.add('flip-horizontal-bottom');
        place.value = "true";
    }
    if(status_column_c.four!=null){
        place = document.querySelector("#c4");
        place.innerHTML = status_column_c.four;
        place.classList.add('flip-horizontal-bottom');
        place.value = "true";
    }
    // D
    if(status_column_d.one!=null){
        place = document.querySelector("#d1");
        place.innerHTML = status_column_d.one;
        place.classList.add('flip-horizontal-bottom');
        place.value = "true";
    }
    if(status_column_d.two!=null){
        place = document.querySelector("#d2");
        place.innerHTML = status_column_d.two;
        place.classList.add('flip-horizontal-bottom');
        place.value = "true";
    }
    if(status_column_d.three!=null){
        place = document.querySelector("#d3");
        place.innerHTML = status_column_d.three;
        place.classList.add('flip-horizontal-bottom');
        place.value = "true";
    }
    if(status_column_d.four!=null){
        place = document.querySelector("#d4");
        place.innerHTML = status_column_d.four;
        place.classList.add('flip-horizontal-bottom');
        place.value = "true";
    }

}