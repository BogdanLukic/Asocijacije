<%@ page import="Entities.Accounts" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    if(session.getAttribute("token") == null){
        response.sendRedirect("login.jsp");
        return;
    }
    Accounts account = (Accounts) session.getAttribute("token");
%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Asocijacije</title>
    <!-- CSS -->
    <link rel="stylesheet" href="../css/styles.css">
    <link rel="stylesheet" href="../css/lobby.css">
    <!-- Icons -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
    <script src="https://cdn.socket.io/4.5.4/socket.io.min.js"></script>
</head>
  <body onload="init()">
    <div id="bodyContainer">
        <div class="lobby-logo">
            <img src="../images/logo/logo.png">
        </div>
        <div class="lobby-page">
            <div class="lobby-page-personal-info">
                <div>
                    <img class="character" src="../images/characters/<%=account.getCharacter()%>.png">
                </div>
                <div class="lobby-page-personal-info-info">
                    <p>Korisničko ime: <span><%=account.getUsername()%></span></p>
                    <p>Email: <span><%=account.getEmail()%></span></p>
                    <p>Trenutni rezultat: <span></span></p>
                </div>
            </div>
            <div class="lobby-page-row">
                <div class="place">
                    <p>Aktivni korisnici:</p>
                    <div class="active-users-field">
                        <div class="active-user">
                            <div>
                                <img class="active-user-character" src="../images/characters/0.png">
                            </div>
                            <div class="active-user-info">
                                <div>
                                    <p>Pera Peric</p>
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
                        </div>
                        <div class="active-user">
                            <div>
                                <img class="active-user-character" src="../images/characters/4.png">
                            </div>
                            <div class="active-user-info">
                                <div>
                                    <p>Zika Zikic</p>
                                </div>
                                <div class="active-user-info-status">
                                    <div class="dot in-game"></div>
                                    <p>Već u igri</p>
                                </div>
                            </div>
                            <div class="active-user-options">
                                <div>
                                    <img src="../images/icons/message.png">
                                </div>
                                <div>
                                    <img src="../images/icons/sword-off.png">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="place">
                    <p>Najbolji rezultati:</p>
                    <div class="active-users-field">
                        <div class="active-user">
                            <div>
                                <img class="active-user-character" src="../images/characters/4.png">
                            </div>
                            <div class="active-user-info">
                                <div>
                                    <p>Pera Peric</p>
                                </div>
                                <div class="active-user-info-status">
                                    <p>Trenutno najbolji rezultat: <span>430</span></p>
                                </div>
                            </div>
                            <div class="active-user-options">
                                <div>
                                    <img src="../images/icons/first place.png">
                                </div>
                            </div>
                        </div>
                        <div class="active-user">
                            <div>
                                <img class="active-user-character" src="../images/characters/2.png">
                            </div>
                            <div class="active-user-info">
                                <div>
                                    <p>Uka Ukic </p>
                                </div>
                                <div class="active-user-info-status">
                                    <p>Trenutno najbolji rezultat: <span>430</span></p>
                                </div>
                            </div>
                            <div class="active-user-options">
                                <div>
                                    <img src="../images/icons/second place.png">
                                </div>
                            </div>
                        </div>
                        <div class="active-user">
                            <div>
                                <img class="active-user-character" src="../images/characters/6.png">
                            </div>
                            <div class="active-user-info">
                                <div>
                                    <p>Bogdan Lukic</p>
                                </div>
                                <div class="active-user-info-status">
                                    <p>Trenutno najbolji rezultat: <span>430</span></p>
                                </div>
                            </div>
                            <div class="active-user-options">
                                <div>
                                    <img src="../images/icons/third place.png">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- CHAT -->
        <div class="chat-field">
            <div class="chat-field-heder">
                <div class="active">
                    <p>Global chat</p>
                </div>
                <div class="no-active">

                </div>
            </div>
            <div class="chat-message">
                <div class="chat-message-one">
                    <div>
                        <p class="chat-message-username">Bogdan Lukic:</p>
                    </div>
                    <div class="chat-message-msg">
                        <p>asdasdsadsasasdasdsadsasasdasdsadsasasdasdsadsasasdasdsadsasasdasdsadsas</p>
                    </div>
                </div>
            </div>
            <div class="chat-insert">
                <div class="chat-insert-input">
                    <input type="text" class="input-classic input-classic-bs" placeholder="Text...">
                </div>
                <div>
                    <div class="play-button-solo">
                        <p class="txt-shadow">Pošalji</p>
                    </div>
                </div>
            </div>
        </div>
    </div>

<script src="../js/Socket/WebSocket.js"></script>
  </body>
</html>