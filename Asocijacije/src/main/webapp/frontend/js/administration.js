function setListOfUser(list){
    place_for_user = document.querySelector("#active-users");
    place_for_user.innerHTML = "";

    active__user = list.active_user_list;
    for(i of active__user){
        if(i.account.role.id == 2){
           
                place_for_user.innerHTML += `<div class="active-user">
                <div>
                    <img class="active-user-character" src="../images/characters/${i.account.character}.png">
                </div>
                <div class="active-user-info">
                    <div>
                        <p>${i.account.username}</p>
                    </div>
                    <div class="active-user-info-status">
                        <div class="online"><p>Online</p></div>
                        <p>Rezultat: <span>${i.score}</span></p>
                    </div>
                </div>
                <div class="active-user-options">
                    <i class="bi bi-trash trash trash-n-a"></i>
                </div>
                </div>`;
        }
    }
    
    no_active__user = list.score_list;
    for(i of no_active__user){
        if(i.account.role.id == 2){
            place_for_user.innerHTML += `<div class="active-user">
            <div>
                <img class="active-user-character" src="../images/characters/${i.account.character}.png">
            </div>
            <div class="active-user-info">
                <div>
                    <p>${i.account.username}</p>
                </div>
                <div class="active-user-info-status">
                    <div class="offline"><p>Offline</p></div>
                    <p>Rezultat: <span>${i.score}</span></p>
                </div>
            </div>
            <div class="active-user-options" onclick = deleteAccount(${JSON.stringify(i.account_id)})>
                <i class="bi bi-trash trash clicable"></i>
            </div>
        </div>`;
        }
    }

}
var list_of_associations;
function renderAssociations(list){
    list_of_associations = list;
    association_list_field = document.querySelector("#association-list");
    association_list_field.innerHTML = "";
    for(i of list){
        as_id = i.finalAnswer.id;
        association_list_field.innerHTML += `<div class="association">
                                                <div>
                                                    <p>${i.finalAnswer.name}</p>
                                                </div>
                                                <div class="association-columns">
                                                    <div><p>${i.column_a.name}</p></div>
                                                    <div><p>${i.column_b.name}</p></div>
                                                    <div><p>${i.column_c.name}</p></div>
                                                    <div><p>${i.column_d.name}</p></div>
                                                </div>
                                                <div class="association-edit">
                                                    <div onclick='editAssociation(${JSON.stringify(i)})'><i class="bi bi-eye clicable"></i></div>
                                                    <div onclick=deleteAssociation(${as_id})><i class="bi bi-trash clicable"></i></div>
                                                </div>
                                            </div>`;
    }
}
// =========================================================
a1 = document.querySelector("#a1");
a2 = document.querySelector("#a2");
a3 = document.querySelector("#a3");
a4 = document.querySelector("#a4");

b1 = document.querySelector("#b1");
b2 = document.querySelector("#b2");
b3 = document.querySelector("#b3");
b4 = document.querySelector("#b4");

c1 = document.querySelector("#c1");
c2 = document.querySelector("#c2");
c3 = document.querySelector("#c3");
c4 = document.querySelector("#c4");

d1 = document.querySelector("#d1");
d2 = document.querySelector("#d2");
d3 = document.querySelector("#d3");
d4 = document.querySelector("#d4");

column_a = document.querySelector("#column-a");
column_b = document.querySelector("#column-b");
column_c = document.querySelector("#column-c");
column_d = document.querySelector("#column-d");
column_final = document.querySelector("#column-final");

disable_skip = document.querySelector("#disable-skip");
verify_edit = document.querySelector("#verify-edit");
disable_skip_button = document.querySelector("#disable-skip-button");

a1.addEventListener("input", function () { this.value = this.value.toUpperCase(); });
a2.addEventListener("input", function () { this.value = this.value.toUpperCase(); });
a3.addEventListener("input", function () { this.value = this.value.toUpperCase(); });
a4.addEventListener("input", function () { this.value = this.value.toUpperCase(); });
column_a.addEventListener("input", function () { this.value = this.value.toUpperCase(); });

b1.addEventListener("input", function () { this.value = this.value.toUpperCase(); });
b2.addEventListener("input", function () { this.value = this.value.toUpperCase(); });
b3.addEventListener("input", function () { this.value = this.value.toUpperCase(); });
b4.addEventListener("input", function () { this.value = this.value.toUpperCase(); });
column_b.addEventListener("input", function () { this.value = this.value.toUpperCase(); });

c1.addEventListener("input", function () { this.value = this.value.toUpperCase(); });
c2.addEventListener("input", function () { this.value = this.value.toUpperCase(); });
c3.addEventListener("input", function () { this.value = this.value.toUpperCase(); });
c4.addEventListener("input", function () { this.value = this.value.toUpperCase(); });
column_c.addEventListener("input", function () { this.value = this.value.toUpperCase(); });

d1.addEventListener("input", function () { this.value = this.value.toUpperCase(); });
d2.addEventListener("input", function () { this.value = this.value.toUpperCase(); });
d3.addEventListener("input", function () { this.value = this.value.toUpperCase(); });
d4.addEventListener("input", function () { this.value = this.value.toUpperCase(); });
column_d.addEventListener("input", function () { this.value = this.value.toUpperCase(); });

column_final.addEventListener("input", function () { this.value = this.value.toUpperCase(); });

// =========================================================

var save_association;
function editAssociation(association){
    console.log(association);
    save_association = association;

    a1.value = association.column_a.one;
    a2.value = association.column_a.two;
    a3.value = association.column_a.three;
    a4.value = association.column_a.four;
    column_a.value = association.column_a.name;

    b1.value = association.column_b.one;
    b2.value = association.column_b.two;
    b3.value = association.column_b.three;
    b4.value = association.column_b.four;
    column_b.value = association.column_b.name;

    c1.value = association.column_c.one;
    c2.value = association.column_c.two;
    c3.value = association.column_c.three;
    c4.value = association.column_c.four;
    column_c.value = association.column_c.name;

    d1.value = association.column_d.one;
    d2.value = association.column_d.two;
    d3.value = association.column_d.three;
    d4.value = association.column_d.four;
    column_d.value = association.column_d.name;

    column_final.value = association.finalAnswer.name;

    disable_skip.style = "display: none;";
    verify_edit.style.display = '';
}

function clearTable(){
    save_association = null;

    a1.value = "";
    a2.value = "";
    a3.value = "";
    a4.value = "";
    column_a.value = "";

    b1.value = "";
    b2.value = "";
    b3.value = "";
    b4.value = "";
    column_b.value = "";

    c1.value = "";
    c2.value = "";
    c3.value = "";
    c4.value = "";
    column_c.value = "";

    d1.value = "";
    d2.value = "";
    d3.value = "";
    d4.value = "";
    column_d.value = "";

    column_final.value = "";

    disable_skip.style.display = '';
    verify_edit.style = 'display: none';
    checkTable();
}

function checkTable(){
    if(a1.value != "" && a2.value != "" && a3.value != "" && a4.value != "" && column_a.value != "" &&
    b1.value != "" && b2.value != "" && b3.value != "" && b4.value != "" && column_b.value != "" &&
    c1.value != "" && c2.value != "" && c3.value != "" && c4.value != "" && column_c.value != "" &&
    d1.value != "" && d2.value != "" && d3.value != "" && d4.value != "" && column_d.value != "" &&
    column_final.value != "")
        disable_skip_button.classList.remove('disable');
    else
        disable_skip_button.classList.add('disable');
}

function addNewAssociation(){
    new_association = new Object();

    ca = new Object();
    ca.name = column_a.value;
    ca.one = a1.value;
    ca.two = a2.value;
    ca.three = a3.value;
    ca.four = a4.value;

    cb = new Object();
    cb.name = column_b.value;
    cb.one = b1.value;
    cb.two = b2.value;
    cb.three = b3.value;
    cb.four = b4.value;

    cc = new Object();
    cc.name = column_c.value;
    cc.one = c1.value;
    cc.two = c2.value;
    cc.three =c3.value;
    cc.four = c4.value;

    cd = new Object();
    cd.name = column_d.value;
    cd.one = d1.value;
    cd.two = d2.value;
    cd.three =d3.value;
    cd.four = d4.value;

    fa = new Object();
    fa.name = column_final.value;

    new_association.column_a = ca;
    new_association.column_b = cb;
    new_association.column_c = cc;
    new_association.column_d = cd;
    new_association.finalAnswer = fa;

    let options = {
        method: 'POST',
        headers: {
            'Content-Type': 
                'application/json;charset=utf-8'
        },
        body: JSON.stringify(new_association) 
    }
    let fetchRes = fetch("http://localhost:8081/Asocijacije_war_exploded/associationlist",options);
    fetchRes.then(res => res.json())
    .then(
        d=>{
            getAllAssociations();
            clearTable();
        }
    )
    
}
function deleteAssociation(id){
    console.log("BRISEM: "+id);
    let options = {
        method: 'DELETE',
        headers: {
            'Content-Type': 
                'application/json;charset=utf-8'
        },
        body: id
    }
    let fetchRes = fetch("http://localhost:8081/Asocijacije_war_exploded/associationlist",options);
    fetchRes.then()
    .then(
        d=>{
            console.log(d);
            getAllAssociations();
        }

    );
}

function deleteAccount(id){
    let options = {
        method: 'DELETE',
        headers: {
            'Content-Type': 
                'application/json;charset=utf-8'
        },
        body: id
    }
    let fetchRes = fetch("http://localhost:8081/Asocijacije_war_exploded/getAccount",options);
    fetchRes.then()
    .then(
        d=>{
            getAllUser();
        }
    );
}

function logout(){
    document.getElementById("logout-form").submit();
    sessionStorage.clear();
}