function method(button){
    button.classList.add('flip-horizontal-bottom');
    if(button.value == "false"){
        [column, place] = button.id.split('');
        obj = new Object();
        obj.column = column;
        obj.place = place;
        obj.uuid = sessionStorage.getItem('uuid_of_game');

        container = document.querySelector(".party-page");
        container.classList.remove('disable-inputs');
        container.classList.add('disable-button');

        disable_skip = document.querySelector("#disable-skip");
        disable_skip.classList.remove('disable-skip');
        disable_skip.classList.add('no-disable-skip');

        place = document.querySelector(`#column-${obj.column}`);
        place.classList.remove('disable-manual');

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

function setDisable(class_for_remove){
    switch(class_for_remove){
        case "open":
            place = document.querySelector(".party-page");
            place.classList.remove('everything');
            break;
        case "play":
            place = document.querySelector(".party-page");
            place.classList.remove('everything');

            container = document.querySelector(".party-page");
            container.classList.remove('disable-inputs');
            container.classList.add('disable-button');   

            disable_skip = document.querySelector("#disable-skip");
            disable_skip.classList.remove('disable-skip');
            disable_skip.classList.add('no-disable-skip');
            break;
        case "default":
            place = document.querySelector(".party-page");
            place.classList.add('everything');

            container = document.querySelector(".party-page");
            container.classList.add('disable-inputs');
            container.classList.remove('disable-button');  

            disable_skip = document.querySelector("#disable-skip");
            disable_skip.classList.add('disable-skip');
            disable_skip.classList.remove('no-disable-skip');
    }
}

function setGameInfo(obj){  
    if(obj!=null)
    {
        party_page = document.querySelector(".party-page");
        party_page.style='display:';
        party_page.classList.add('scale-in-center');
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
        setInputEnalbe(obj);
    }
    else{
        endGame();
    }
}
function endGame(){
    sessionStorage.removeItem('uuid_of_game');
    window.open("lobby.jsp","_self");
}
function setInputFields(column,place){
    if(column.winner == 'challanger')
        place.classList.add('challanger-win');
    else if (column.winner == 'enemy')
        place.classList.add('enemy-win');
}
function setFields(obj){
    status_column_a = obj.status_column_a;
    status_column_b = obj.status_column_b;
    status_column_c = obj.status_column_c;
    status_column_d = obj.status_column_d;
    status_column_final = obj.status_konacno_resenje;

    // A
    if(status_column_a.one!=null){
        place = document.querySelector("#a1");
        place.innerHTML = status_column_a.one;
        place.classList.add('flip-horizontal-bottom');
        place.value = "true";
        setInputFields(status_column_a,place);
    }
    if(status_column_a.two!=null){
        place = document.querySelector("#a2");
        place.innerHTML = status_column_a.two;
        place.classList.add('flip-horizontal-bottom');
        place.value = "true";
        setInputFields(status_column_a,place);
    }
    if(status_column_a.three!=null){
        place = document.querySelector("#a3");
        place.innerHTML = status_column_a.three;
        place.classList.add('flip-horizontal-bottom');
        place.value = "true";
        setInputFields(status_column_a,place);
    }
    if(status_column_a.four!=null){
        place = document.querySelector("#a4");
        place.innerHTML = status_column_a.four;
        place.classList.add('flip-horizontal-bottom');
        place.value = "true";
        setInputFields(status_column_a,place);
    }
    if(status_column_a.name!=null){
        place = document.querySelector("#column-a");
        place.value = status_column_a.name;
        place.classList.add('flip-horizontal-bottom');
        setInputFields(status_column_a,place);
    }
    // B
    if(status_column_b.one!=null){
        place = document.querySelector("#b1");
        place.innerHTML = status_column_b.one;
        place.classList.add('flip-horizontal-bottom');
        place.value = "true";
        setInputFields(status_column_b,place);
    }
    if(status_column_b.two!=null){
        place = document.querySelector("#b2");
        place.innerHTML = status_column_b.two;
        place.classList.add('flip-horizontal-bottom');
        place.value = "true";
        setInputFields(status_column_b,place);
    }
    if(status_column_b.three!=null){
        place = document.querySelector("#b3");
        place.innerHTML = status_column_b.three;
        place.classList.add('flip-horizontal-bottom');
        place.value = "true";
        setInputFields(status_column_b,place);
    }
    if(status_column_b.four!=null){
        place = document.querySelector("#b4");
        place.innerHTML = status_column_b.four;
        place.classList.add('flip-horizontal-bottom');
        place.value = "true";
        setInputFields(status_column_b,place);
    }
    if(status_column_b.name!=null){
        place = document.querySelector("#column-b");
        place.value = status_column_b.name;
        place.classList.add('flip-horizontal-bottom');
        place.disabled = true;
        setInputFields(status_column_b,place);
    }
    // C
    if(status_column_c.one!=null){
        place = document.querySelector("#c1");
        place.innerHTML = status_column_c.one;
        place.classList.add('flip-horizontal-bottom');
        place.value = "true";
        setInputFields(status_column_c,place);
    }
    if(status_column_c.two!=null){
        place = document.querySelector("#c2");
        place.innerHTML = status_column_c.two;
        place.classList.add('flip-horizontal-bottom');
        place.value = "true";
        setInputFields(status_column_c,place);
    }
    if(status_column_c.three!=null){
        place = document.querySelector("#c3");
        place.innerHTML = status_column_c.three;
        place.classList.add('flip-horizontal-bottom');
        place.value = "true";
        setInputFields(status_column_c,place);
    }
    if(status_column_c.four!=null){
        place = document.querySelector("#c4");
        place.innerHTML = status_column_c.four;
        place.classList.add('flip-horizontal-bottom');
        place.value = "true";
        setInputFields(status_column_c,place);
    }
    if(status_column_c.name!=null){
        place = document.querySelector("#column-c");
        place.value = status_column_c.name;
        place.classList.add('flip-horizontal-bottom');
        place.disabled = true;
        setInputFields(status_column_c,place);
    }
    // D
    if(status_column_d.one!=null){
        place = document.querySelector("#d1");
        place.innerHTML = status_column_d.one;
        place.classList.add('flip-horizontal-bottom');
        place.value = "true";
        setInputFields(status_column_d,place);
    }
    if(status_column_d.two!=null){
        place = document.querySelector("#d2");
        place.innerHTML = status_column_d.two;
        place.classList.add('flip-horizontal-bottom');
        place.value = "true";
        setInputFields(status_column_d,place);
    }
    if(status_column_d.three!=null){
        place = document.querySelector("#d3");
        place.innerHTML = status_column_d.three;
        place.classList.add('flip-horizontal-bottom');
        place.value = "true";
        setInputFields(status_column_d,place);
    }
    if(status_column_d.four!=null){
        place = document.querySelector("#d4");
        place.innerHTML = status_column_d.four;
        place.classList.add('flip-horizontal-bottom');
        place.value = "true";
        setInputFields(status_column_d,place);
    }
    if(status_column_d.name!=null){
        place = document.querySelector("#column-d");
        place.value = status_column_d.name;
        place.classList.add('flip-horizontal-bottom');
        place.disabled = "true";
        setInputFields(status_column_d,place);
    }
    // final
    if(status_column_final.name != null){
        place = document.querySelector("#column-final");
        place.value = status_column_final.name;
        place.classList.add('flip-horizontal-bottom');
        place.disabled = "true";
        setInputFields(status_column_final,place);
    }
}

input_column_a = document.querySelector("#column-a"); 
input_column_b = document.querySelector("#column-b");
input_column_c = document.querySelector("#column-c");
input_column_d = document.querySelector("#column-d");
input_column_final = document.querySelector("#column-final");


input_column_a.addEventListener("keydown", function (event) {
    if (event.key === "Enter" || event.keyCode === 13) {
      guessColumn(this);
      event.preventDefault();
      input_column_a.blur();
    }
  });
  input_column_b.addEventListener("keydown", function (event) {
    if (event.key === "Enter" || event.keyCode === 13) {
      guessColumn(this);
      event.preventDefault();
      input_column_b.blur();
    }
  });
  input_column_c.addEventListener("keydown", function (event) {
    if (event.key === "Enter" || event.keyCode === 13) {
      guessColumn(this);
      event.preventDefault();
      input_column_c.blur();
    }
  });
  input_column_d.addEventListener("keydown", function (event) {
    if (event.key === "Enter" || event.keyCode === 13) {
        guessColumn(this);
        event.preventDefault();
        input_column_d.blur();
    }
  });
  input_column_final.addEventListener("keydown", function (event) {
    if (event.key === "Enter" || event.keyCode === 13) {
        guessColumn(this);
        event.preventDefault();
        input_column_final.blur();
    }
  });

//   ====================================================================
input_column_a.addEventListener("input", function () {
    this.value = this.value.toUpperCase();
  });
  input_column_b.addEventListener("input", function () {
    this.value = this.value.toUpperCase();
  });
  input_column_c.addEventListener("input", function () {
    this.value = this.value.toUpperCase();
  });
  input_column_d.addEventListener("input", function () {
    this.value = this.value.toUpperCase();
  });
  input_column_final.addEventListener("input", function () {
    this.value = this.value.toUpperCase();
  });

//   ====================================================================

function setInputEnalbe(obj){
    if(obj.status_column_a.one != null || 
        obj.status_column_a.two != null || 
        obj.status_column_a.three != null || 
        obj.status_column_a.four != null ){
            input_column_a.classList.remove('disable-manual');
            input_column_a.classList.add('enable-manual');
        }
    if(obj.status_column_b.one != null || 
        obj.status_column_b.two != null || 
        obj.status_column_b.three != null || 
        obj.status_column_b.four != null ){
            input_column_b.classList.remove('disable-manual');
            input_column_b.classList.add('enable-manual');
    }
    if(obj.status_column_c.one != null || 
        obj.status_column_c.two != null || 
        obj.status_column_c.three != null || 
        obj.status_column_c.four != null ){
            input_column_c.classList.remove('disable-manual');
            input_column_c.classList.add('enable-manual');
    }
    if(obj.status_column_d.one != null || 
        obj.status_column_d.two != null || 
        obj.status_column_d.three != null || 
        obj.status_column_d.four != null ){
            input_column_d.classList.remove('disable-manual');
            input_column_d.classList.add('enable-manual');
    }

    if(obj.status_column_a.name != null || 
        obj.status_column_b.name != null ||
        obj.status_column_c.name != null ||
        obj.status_column_d.name != null){
            input_column_final.classList.remove('disable-manual');
    }
    canPlay(obj);
}

function canPlay(obj){
    if(obj.status_column_a.one != null && obj.status_column_a.two !=null && obj.status_column_a.three !=null && obj.status_column_a.four !=null &&
        obj.status_column_b.one != null && obj.status_column_b.two !=null && obj.status_column_b.three !=null && obj.status_column_b.four !=null &&
        obj.status_column_c.one != null && obj.status_column_c.two !=null && obj.status_column_c.three !=null && obj.status_column_c.four !=null && 
        obj.status_column_d.one != null && obj.status_column_d.two !=null && obj.status_column_d.three !=null && obj.status_column_d.four !=null)
        {
            place = document.querySelector(".party-page");
            place.classList.remove('everything'); 
            place.classList.remove('disable-inputs');
    
            disable_skip = document.querySelector("#disable-skip");
            disable_skip.classList.remove('disable-skip');
            disable_skip.classList.add('no-disable-skip');

            console.log("USAO");
            not_playable();
        }
}

function guessColumn(column){
    obj = new Object;
    obj.uuid_of_game = sessionStorage.getItem('uuid_of_game');
    [q,w] = column.id.split('-');
    obj.column = w;
    obj.text = column.value;
    console.log(obj);
    guessColumnEmit(obj);
}
function setGuessTurn(obj){
    if(obj.response){
        place1 = document.querySelector(`#${obj.column}1`);
        place2 = document.querySelector(`#${obj.column}2`);
        place3 = document.querySelector(`#${obj.column}3`);
        place4 = document.querySelector(`#${obj.column}4`);
        input_place = document.querySelector(`#column-${obj.column}`);
        if(obj.winner == 'challanger'){
            place1.classList.add('challanger-win');
            place2.classList.add('challanger-win');
            place3.classList.add('challanger-win');
            place4.classList.add('challanger-win');
            input_place.classList.add('challanger-win');
        }
        else{
            place1.classList.add('enemy-win');
            place2.classList.add('enemy-win');
            place3.classList.add('enemy-win');
            place4.classList.add('enemy-win');
            input_place.classList.add('enemy-win'); 
        }
    }
    else{
        input_place = document.querySelector(`#column-${obj.column}`);
        input_place.classList.add('shake-horizontal');
        input_place.value = obj.text;
        setTimeout(()=>{
            input_place.classList.remove('shake-horizontal');
            input_place.value='';
        },1200);
    }
}

function endTurnMethod(){
    obj = new Object();
    obj.uuid = sessionStorage.getItem('uuid_of_game');
    endTurn(obj);
    setDisable("default");
}

function goToLobby(){
    window.open("lobby.jsp","_self");
}

var opened = false;
function displayEndGame(obj){
    
    if(opened == false){
        opened = true;
        div_overlay = document.querySelector("#overlay");
    setTimeout(()=>{
        div_overlay.style.display = '';
        div_overlay.classList.add('scale-in-center');
        if(obj.win == true){
            div_overlay.innerHTML+=`<div class="overlay-notify">
            <div>
                <h2>Čestitamo, osvojili ste:</h2>
            </div>
            <div>
                <h3>${obj.points} poena</h3>
            </div>
            <div class="">
                <button onclick="goToLobby()" class="play-button-solo skip">Početna</button>
            </div>
        </div>`;
        }
        else{
            if(obj.points == 0)
            {
                div_overlay.innerHTML+=`<div class="overlay-notify">
                    <div>
                        <h2>Rezultat je nerešen:</h2>
                    </div>
                    <div>
                        <h3>${obj.points} poena</h3>
                    </div>
                    <div class="">
                        <button onclick="goToLobby()" class="play-button-solo skip">Početna</button>
                    </div>
                </div>`;
            }
            else{
                div_overlay.innerHTML+=`<div class="overlay-notify">
                <div>
                    <h2>Nažalost, izgubili ste:</h2>
                </div>
                <div>
                    <h3>${obj.points} poena</h3>
                </div>
                <div class="">
                    <button onclick="goToLobby()" class="play-button-solo skip">Početna</button>
                </div>
            </div>`;
            }
        }
    },550)
    }
}