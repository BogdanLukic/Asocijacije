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
    <!-- Scripts -->
    <script src="https://cdn.socket.io/4.5.4/socket.io.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/uuid@8.3.2/dist/umd/uuidv4.min.js"></script>
</head>
  <body onload="init()">
    <div id="overlay" style="display: none; color: white;">
        <!-- <div class="invite">
            <div>
                <img src="../images/characters/6.png">
            </div>
            <div class="invite-info">
                <h3>Pera Peric</h3>
                <p>te izaziva na borbu</p>
            </div>
            <div class="invire-response">
                <div class="invire-response-field accept clicable" onclick="close_invite()">
                    <i class="bi bi-check2"></i>
                </div>
                <div><div id="app"></div></div>
                <div class="invire-response-field denied clicable" onclick="close_invite()">
                    <i class="bi bi-x"></i>
                </div>
            </div>
        </div> -->
    </div>

    <div id="bodyContainer">
        <div class="lobby-logo">
            <img src="../images/logo/logo.png">
        </div>
        <div class="lobby-page">
            <div class="lobby-page-left">
                <!-- Personal info -->
                <div class="lobby-page-personal-info">
                    <div>
                        <img class="character" src="../images/characters/<%=account.getCharacter()%>.png">
                        <!-- <img class="character" src="../images/characters/7.png"> -->
                    </div>
                    <div class="lobby-page-personal-info-info">
                        <!-- <p>Korisničko ime: <span>Bogdan Lukic</span></p>
                        <p>Email: <span>bogdanlukic2002@gmail.com</span></p>
                        <p>Trenutni rezultat: <span>140</span></p> -->
                       <p>Korisničko ime: <span><%=account.getUsername()%></span></p>
                       <p>Email: <span><%=account.getEmail()%></span></p>
                       <p>Trenutni rezultat: <span></span></p>
                    </div>
                </div>

                <!-- CHAT -->
                <div class="chat-field">
                    <div id="chat-field-heder" class="chat-field-heder">
                        <div onclick="setChat('global')" id="global-chat" class="active">
                            <p>Global chat</p>
                        </div>

                        <div class="no-active global-active" id="private-chat">
                            <!-- <div onclick="setChat('private')" class="new-message-open">
                                <div>
                                    <img src="../images/characters/2.png">
                                </div>
                                <div>
                                    <p>Bogdan Lukic</p>
                                </div>
                            </div> -->
                        </div>

                    </div>

                    <div id="chat-message" class="chat-message">
                        <div class="welcome-msg">
                            <p>Dobrodošli u naš grupni chat za igru asocijacije!</p>
                        </div>
                        <!-- <div class="chat-message-one">
                            <div>
                                <p class="chat-message-username">Server:</p>
                            </div>
                            <div class="chat-message-msg">
                                <p>Dobrodošli na server</p>
                            </div>
                        </div> -->
                    </div>
                    
                    <div id="private-chat-message" style="display: none;" class="chat-message">
                        <div class="welcome-msg">
                            <p>Sve poruke će biti obrisane nakon izlaska iz privatnog chat-a! </p>
                        </div>
                        <!-- <div class="chat-message-one">
                            <div>
                                <p class="chat-message-username">Server:</p>
                            </div>
                            <div class="chat-message-msg">
                                <p>Dobrodošli na server</p>
                            </div>
                        </div> -->
                    </div>

                    <div class="chat-insert">
                        <div class="chat-insert-input">
                            <input id="new_msg" type="text" class="input-classic input-classic-bs" placeholder="Text...">
                        </div>
                        <div>
                            <div class="play-button-solo" onclick="sendMsg()">
                                <p class="txt-shadow">Pošalji</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            
            <!-- ROW -->
            <div class="lobby-page-right">
                <div class="lobby-page-row">
                    <div class="place">
                        <p>Aktivni korisnici:</p>
                        <div class="active-users-field" id="active-users">
                            <!-- <div class="active-user">
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
                                    <div class="clicable" onclick="startPrivateMessaging('a')">
                                        <img src="../images/icons/message.png">
                                    </div>
                                    <div class="clicable" onclick="open_overlay()">
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
                                    <div class="clicable">
                                        <img src="../images/icons/message.png">
                                    </div>
                                    <div id="swords" class="clicable" onclick="add_dots()">
                                        <img src="../images/icons/sword-off.png">
                                    </div>
                                    <div id="loader" class="loader" style="display: none;">
                                        <span class="loader__dot">.</span>
                                        <span class="loader__dot">.</span>
                                        <span class="loader__dot">.</span>
                                    </div>
                                </div>
                            </div> -->
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


                <div class="list-message">
                    <div>
                        <p>Neotvorene poruke:</p>
                    </div>
                    <div class="list-message-list" id="field_of_list_messages">
                        <!-- <div class="new-message">
                            <div>
                                <img src="../images/characters/2.png">
                            </div>
                            <div>
                                <p>Bogdan Lukic</p>
                            </div>
                            <div class="new-message-num">
                                <p>4</p>
                            </div>
                        </div> -->
                    </div>
                </div>

            </div>

        </div>
    </div>

    <script src="../js/lobby.js"></script>
    <script src="../js/Socket/WebSocket.js"></script>
</body>
</html>