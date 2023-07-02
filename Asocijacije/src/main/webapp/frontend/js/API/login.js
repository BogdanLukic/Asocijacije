var button = document.getElementById('button');
var username = document.getElementById('username');
var password = document.getElementById('password');

username.addEventListener('keydown', function(event) {
    if (event.keyCode === 13)
        test();
    });

password.addEventListener('keydown', function(event) {
    if (event.keyCode === 13)
        test();
    });

button.addEventListener('click', function() {
    test();
});

function show_pass(){
    switch(password.type){
        case'password':
            password.type = "text";
            break;
        default:
            password.type = "password";
            break;
    }
}

function test() {
    var error = document.getElementsByClassName("error-msg")[0];
    if(username.value.length == 0 || password.value.length == 0){
        error.style.visibility = 'visible';
    }
    else{
        error.style.visibility = 'hidden';
        var account = {
            username:username.value,
            password:password.value
        };
        let options = {
            method: 'POST',
            headers: {
                'Content-Type': 
                    'application/json;charset=utf-8'
            },
            body: JSON.stringify(account)   
        }
        let fetchRes = fetch("http://localhost:8081/Asocijacije_war_exploded/login",options);
        fetchRes.then(res => res.json())
            .then(
                d=>{
                    if(d !== null)
                    {
                        sessionStorage.setItem("token",d.token);
                        window.open('lobby.jsp','_self');
                    }
                    else
                        error.style.visibility = 'visible';
                }
            )
    }
        
}
  