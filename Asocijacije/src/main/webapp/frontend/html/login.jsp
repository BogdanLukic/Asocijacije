<%@ page import="Database.IDatabase" %>
<%@ page import="Database.Database" %>
<%@ page import="Entities.Accounts" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean id="login" class="Models.LoginBean"></jsp:useBean>
<jsp:setProperty name="login" property="*"></jsp:setProperty>

<%--Init--%>
<%
  if(session.getAttribute("token") != null)
    response.sendRedirect("lobby.jsp");

  boolean error = false;
  if(session.getAttribute("error-login")!=null)
    error = (boolean) session.getAttribute("error-login");
  IDatabase db = Database.getConnection();
%>
<%-- API --%>
<%
  if(request.getParameter("login")!=null && login.getUsername()!=null && login.getPassword()!=null){
    Accounts account = new Accounts();
    account.setUsername(login.getUsername());
    account.setPassword(login.getPassword());
    Accounts acc_response = db.login(account);

    if(acc_response == null){
      System.out.println("Null-sam");
      error = true;
      session.setAttribute("error-login",true);
      response.sendRedirect("login.jsp");
    }
    else {
      session.setAttribute("token",acc_response);
      session.removeAttribute("error-login");
      if(acc_response.getRole().getId() == 2)
        response.sendRedirect("lobby.jsp");
      else
        response.sendRedirect("administration.jsp");
    }
  }
  else{
    session.removeAttribute("error-login");
  }
%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Asocijacije</title>
  <!-- CSS -->
  <link rel="stylesheet" href="../css/styles.css">
  <link rel="stylesheet" href="../css/loginPage.css">
  <!-- Icons -->
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
</head>
<body>
<div id="bodyContainer">
  <div class="login-page">
    <div class="login-logo">
      <img src="../images/logo/logo.png">
    </div>
    <form action="login.jsp" method="post" id="login-form">
      <div class="login-fields">
        <div class="login-field">
          <div>
            <p class="txt-shadow">Korisničko ime</p>
          </div>
          <div>
            <input type="text" name="username" value="" class="input-classic input-classic-bs" placeholder="Unesite korisničko ime" id="username">
          </div>
        </div>
        <div class="login-field">
          <div>
            <p>Šifra</p>
          </div>
          <div class="login-field-password input-password-bs">
            <div class="login-field-password-input">
              <input type="password" name="password" value="" class="input-classic" placeholder="Unesite šifru" id="password">
            </div>
            <div class="login-field-password-icon" onclick="show_pass()">
              <i class="bi bi-eye"></i>
            </div>
          </div>
        </div>
      </div>
      <%
        if(error){
      %>
        <div>
          <p class="error-msg">Korisničko ime ili šifra nisu ispravne</p>
        </div>
      <%}%>
      <div class="play-button-field">
        <div class="play-button" id="button">
          <input type="hidden" name="login" value="1">
          <p class="txt-shadow">Igraj</p>
          <img src="../images/icons/uil_rocket.png">
        </div>
      </div>
    </form>
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