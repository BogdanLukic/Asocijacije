img_count = 0;
function carousel_right(){
    img_count++;
    if(img_count >= 8)
        img_count = 0;
    img_character=document.querySelector("#img-character");
    img_character.src = `../images/characters/${img_count}.png`
}
function carousel_left(){
    img_count--;
    if(img_count <= -1)
        img_count = 7;
    img_character=document.querySelector("#img-character");
    img_character.src = `../images/characters/${img_count}.png`
}

// event-listener for enter
var username = document.querySelector("#username");
var username_error_msg = document.querySelector("#username-error");
var email = document.querySelector("#email");
var email_error_msg = document.querySelector("#email-error");
var password1 = document.querySelector("#password1");
var password2 = document.querySelector("#password2");
var password_error_msg = document.querySelector("#password-error");
var button = document.querySelector("#button");

username.addEventListener('keydown', function(event) {
    if (event.keyCode === 13)
        test();
    });
email.addEventListener('keydown', function(event) {
if (event.keyCode === 13)
    test();
});
password1.addEventListener('keydown', function(event) {
    if (event.keyCode === 13)
        test();
    });
password2.addEventListener('keydown', function(event) {
    if (event.keyCode === 13)
        test();
    });
button.addEventListener('click', function() {
    test();
});

function username_test(username){
    const regex = new RegExp("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).+$");
    if(regex.test(username)){
        username_error_msg.style.visibility = 'hidden';
        return true;
    }
    username_error_msg.style = 'visibility:visible';
    return false;
}
function email_test(email){
    const regex = new RegExp("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$");
    if(regex.test(email)){
        email_error_msg.style.visibility = 'hidden';
        return true;
    }
    email_error_msg.style = 'visibility:visible';   
    return false;
}
function password_test(password){
    const regex = new RegExp("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{6,}$");
    if(regex.test(password)){
        password_error_msg.style.visibility = 'hidden';
        return true;
    }
    password_error_msg.innerHTML = "Šifa mora sadrzati velika, mala slova i brojeve. Minimalne duzina 6 karaktera.";
    password_error_msg.style = 'visibility:visible';
    return false;
}

function test(){

    if(username_test(username.value) && email_test(email.value) && password_test(password1.value)){
        if(password1.value == password2.value)
        {
            password_error_msg.style.visibility = 'hidden';
            registration();
        }
        else{
            password_error_msg.innerHTML = "Šife se moraju poklapati";
            password_error_msg.style = 'visibility:visible';
        }
    }
}

function registration(){
    let account = {
        "username":`${username.value}`,
        "password":`${password1.value}`,
        "email":`${email.value}`,
        "character":`${img_count}`
    };
    let options = {
        method: 'POST',
        headers: {
            'Content-Type': 
                'application/json;charset=utf-8'
        },
        body: JSON.stringify(account)   
    }
    let fetchRes = fetch("http://localhost:8081/Asocijacije_war_exploded/register",options);
    fetchRes.then(res => res.json())
        .then(
            d=>{
                switch(d.response){
                    case "Success_registration":
                        window.open('login.jsp','_self');
                        break;
                    case "Email_is_existing":
                        password_error_msg.innerHTML = "Nalog na ovoj email adresi postoji";
                        password_error_msg.style = 'visibility:visible';
                        break;
                    case "Username_is_existing":
                        password_error_msg.innerHTML = "Nalog sa ovim imenom već postoji";
                        password_error_msg.style = 'visibility:visible';
                        break;
                }   
            }
        )
}