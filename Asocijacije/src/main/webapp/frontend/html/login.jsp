<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Asocijacije</title>
  <!-- CSS -->
  <link rel="stylesheet" href="../css/styles.css">
  <link rel="stylesheet" href="../css/login.css">
  <!-- Icons -->
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
</head>
<body>
<div id="bodyContainer">
  <div class="login-page">
    <div class="login-logo">
      <img src="../images/logo/logo.png">
    </div>
    <div class="login-fields">
      <div class="login-field">
        <div>
          <p class="txt-shadow">Korisničko ime</p>
        </div>
        <div>
          <input type="text" class="input-classic input-classic-bs" placeholder="Unesite korisničko ime" id="username">
        </div>
      </div>
      <div class="login-field">
        <div>
          <p>Šifra</p>
        </div>
        <div class="login-field-password input-password-bs">
          <div class="login-field-password-input">
            <input type="password" class="input-classic" placeholder="Unesite šifru" id="password">
          </div>
          <div class="login-field-password-icon">
            <i class="bi bi-eye"></i>
          </div>
        </div>
      </div>
    </div>
    <!-- <div> -->
      <div>
        <p class="error-msg">Korisničko ime ili šifra nisu ispravne</p> 
      </div>
    <!-- </div> -->
    <div class="play-button-field">
      <div class="play-button" id="button">
        <p class="txt-shadow">Igraj</p>
        <img src="../images/icons/uil_rocket.png">
      </div>
    </div>
    <div class="create-one">
      <div>
        <p class="txt-shadow">Nemaš nalog?</p>
      </div>
      <div>
        <a href="../html/register.html" class="txt-shadow">Kreiraj ga</a>
      </div>
    </div>
  </div>
</div>

<script src="../js/API/login.js"></script>

</body>
</html>