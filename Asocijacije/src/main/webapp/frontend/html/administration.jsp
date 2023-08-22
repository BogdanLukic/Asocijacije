<%@ page import="Entities.Accounts" %><%
    if(session.getAttribute("token") == null){
        response.sendRedirect("login.jsp");
        return;
    }
    else {
        Accounts account = (Accounts) session.getAttribute("token");
        if(account.getRole().getId() != 1)
        {
            response.sendRedirect("login.jsp");
            return;
        }
    }
    if(request.getParameter("logout") != null){
        session.removeAttribute("token");
        response.sendRedirect("login.jsp");
        return;
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
    <link rel="stylesheet" href="../css/administration.css">
    <!-- Icons -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
    <!-- Scripts -->
    <script src="https://cdn.socket.io/4.5.4/socket.io.min.js"></script>  
  </head>
  <body onload="initAdmin()">
    <div id="bodyContainer">
        <div class="admin-page">
            <div class="admin-logo">
                <img src="../images/logo/logo.png">
                <p>Administracija</p>
            </div>
            <div class="logout">
                <form action="lobby.jsp" method="post" id="logout-form">
                    <input type="hidden" name="logout" value="1">
                    <p onclick="logout()" class="clicable">Izloguj se</p>
                </form>
            </div>
            <div class="admin-page-main">
                <div class="place">
                    <p>Lista korisnika:</p>
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
                                    <p>Rezultat: <span>35</span></p>
                                </div>
                            </div>
                            <div class="active-user-options">
                                <i class="bi bi-trash trash clicable"></i>
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
                                    <p>Rezultat: <span>35</span></p>
                                </div>
                            </div>
                            <div class="active-user-options">
                                <i class="bi bi-trash trash clicable trash-n-a"></i>
                            </div>
                        </div> -->
                    </div>
                </div>
                
                <div class="place">
                    <p>Lista asocijacija:</p>
                    <div class="active-users-field" id="association-list">
                        <!-- <div class="association">
                            <div>
                                <p>JAPAN</p>
                            </div>
                            <div class="association-columns">
                                <div><p>OSTRVO</p></div>
                                <div><p>SUNCE</p></div>
                                <div><p>TOJOTA</p></div>
                                <div><p>MORE</p></div>
                            </div>
                            <div class="association-edit">
                                <div><i class="bi bi-pencil"></i></div>
                                <div><i class="bi bi-trash"></i></div>
                            </div>
                        </div> -->
                    </div>
                </div>

                <!-- gameAnswer -->
                <div class="add-game">
                    <div>
                        <p>Dodajte asocijaciju</p>
                    </div>
                    <div class="game">
                        <div class="top">
                            <div class="row">
                                <div>
                                    <!-- <button onclick="method(this)" value="false" id="a1">A1</button> -->
                                    <input type="text" id="a1" class="input-classic-game input-classic-bs" value="" onkeyup="checkTable()" placeholder="A1" autocomplete="off">
                                </div>
                                <div>
                                    <!-- <button onclick="method(this)" value="false" id="b1">B1</button> -->
                                    <input type="text" id="b1" class="input-classic-game input-classic-bs" value="" onkeyup="checkTable()" placeholder="B1" autocomplete="off">
                                </div>
                            </div>
                            <div class="row row-2">
                                <div>
                                    <input type="text" id="a2" class="input-classic-game input-classic-bs" value="" onkeyup="checkTable()" placeholder="A2" autocomplete="off">
                                </div>
                                <div>
                                    <input type="text" id="b2" class="input-classic-game input-classic-bs" value="" onkeyup="checkTable()" placeholder="B2" autocomplete="off">
                                </div>
                            </div>
                            <div class="row row-3">
                                <div>
                                    <input type="text" id="a3" class="input-classic-game input-classic-bs" value="" onkeyup="checkTable()" placeholder="A3" autocomplete="off">
                                </div>
                                <div>
                                    <input type="text" id="b3" class="input-classic-game input-classic-bs" value="" onkeyup="checkTable()" placeholder="B3" autocomplete="off">
                                </div>
                            </div>
                            <div class="row row-4">
                                <div>
                                    <input type="text" id="a4" class="input-classic-game input-classic-bs" value="" onkeyup="checkTable()" placeholder="A4" autocomplete="off">
                                </div>
                                <div>
                                    <input type="text" id="b4" class="input-classic-game input-classic-bs" value="" onkeyup="checkTable()" placeholder="B4" autocomplete="off">
                                </div>
                            </div>
                            <div class="row row-5">
                                <div>
                                    <input id="column-a" class="input-classic-game input-classic-bs" value="" onkeyup="checkTable()" placeholder="Kolona A" autocomplete="off">
                                </div>
                                <div>
                                    <input id="column-b" class="input-classic-game input-classic-bs" value="" onkeyup="checkTable()" placeholder="Kolona B" autocomplete="off">
                                </div>
                            </div>
                        </div>
        
                        <div class="btn">
                            <div>
                                <input id="column-final" class="input-classic-game input-classic-bs" value="" onkeyup="checkTable()" placeholder="Konacno" autocomplete="off">
                            </div>
                            <div id="disable-skip" class="disable-skip">
                                <button id="disable-skip-button" class="play-button-solo skip disable" onclick="addNewAssociation()">Dodaj</button>
                            </div>
                            <div id="verify-edit" class="verify-edit" style="display: none;">
                                <!-- <div>
                                    <button class="verify-edit-check"><i class="bi bi-check2"></i></button>
                                </div> -->
                                <div style="margin-right: 77px;">
                                    <button class="verify-edit-x" onclick="clearTable()"><i class="bi bi-x"></i></button>
                                </div>
                            </div>
                        </div>
        
                        <div class="bottom">
                            <div class="row row-5">
                                <div>
                                    <input id="column-c" class="input-classic-game input-classic-bs" value="" onkeyup="checkTable()" placeholder="Kolona C" autocomplete="off">
                                </div>
                                <div>
                                    <input id="column-d" class="input-classic-game input-classic-bs" value="" onkeyup="checkTable()" placeholder="Kolona D" autocomplete="off">
                                </div>
                            </div>
                            <div class="row row-4">
                                <div>
                                    <input type="text" id="c1" class="input-classic-game input-classic-bs" value="" onkeyup="checkTable()" placeholder="C1" autocomplete="off">
                                </div>
                                <div>
                                    <input type="text" id="d1" class="input-classic-game input-classic-bs" value="" onkeyup="checkTable()" placeholder="D1" autocomplete="off">
                                </div>
                            </div>
                            <div class="row row-3">
                                <div>
                                    <input type="text" id="c2" class="input-classic-game input-classic-bs" value="" onkeyup="checkTable()" placeholder="C2" autocomplete="off">
                                </div>
                                <div>
                                    <input type="text" id="d2" class="input-classic-game input-classic-bs" value="" onkeyup="checkTable()" placeholder="D2" autocomplete="off">
                                </div>
                            </div>
                            <div class="row row-2">
                                <div>
                                    <input type="text" id="c3" class="input-classic-game input-classic-bs" value="" onkeyup="checkTable()" placeholder="C3" autocomplete="off">
                                </div>
                                <div>
                                    <input type="text" id="d3" class="input-classic-game input-classic-bs" value="" onkeyup="checkTable()" placeholder="D3" autocomplete="off">
                                </div>
                            </div>
                            <div class="row row-1">
                                <div>
                                    <input type="text" id="c4" class="input-classic-game input-classic-bs" value="" onkeyup="checkTable()" placeholder="C4" autocomplete="off">
                                </div>
                                <div>
                                    <input type="text" id="d4" class="input-classic-game input-classic-bs" value="" onkeyup="checkTable()" placeholder="D4" autocomplete="off">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
    <!-- scripts -->
    <script src="../js/Socket/WebSocket.js"></script>
    <script src="../js/Socket/WebSocketAdmin.js"></script>
    <script src="../js/administration.js"></script>
  </body>
</html>