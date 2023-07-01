var button = document.getElementById('button');
var username = document.getElementById('username');
var password = document.getElementById('password');

username.addEventListener('keydown', function(event) {
    if (event.keyCode === 13)
        myFunction();
    });

password.addEventListener('keydown', function(event) {
    if (event.keyCode === 13)
        myFunction();
    });

button.addEventListener('click', function() {
    myFunction();
});

function myFunction() {
    alert('Funkcija je izvršena!');
}
  