package Socket;

import Entities.Accounts;
import Models.*;
import RMI.IEngine;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.rmi.Naming;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

class ServerSocketIO {

    private static Configuration config;
    private static SocketIOServer server;

    private static IEngine engine;

    private static ServerSocketIO my_server;

    private void setConfiguration(){
        config = new Configuration();
        config.setHostname("localhost");
        config.setPort(2020);
        try{
            engine = (IEngine) Naming.lookup("rmi://localhost:5353/engine");
        }
        catch (Exception e){
            System.out.println("RMI nije pokrenut, pokrenuti StartRMI");
            System.exit(1);
        }

    }
    private void setConnectionListener(){
        server.addConnectListener(new ConnectListener() {
            @Override
            public void onConnect(SocketIOClient client) {
                System.out.println("Klijent se povezao: " + client.getSessionId());
            }
        });
    }
    private void setDisconnectListener(){
        server.addDisconnectListener(new DisconnectListener() {
            @Override
            public void onDisconnect(SocketIOClient client) {
                System.out.println("Klijent se odspojio: " + client.getSessionId());
                ActiveUsers.removeUser(client);
            }
        });
    }
    private ServerSocketIO(){
        setConfiguration();
        server = new SocketIOServer(config);
        setConnectionListener();
        setDisconnectListener();
        setAllEventListener();
        server.start();
        System.out.println("Server je pokrenut na http://localhost:2020");
    }

    private static Object lock = new Object();
    public static void startServer(){
        if(server == null) {
            synchronized (lock){
                if(server == null)
                    my_server = new ServerSocketIO();
            }
        }
    }
//    ============================================
    private void setAllEventListener(){
        register();
        getListOfActiveUsers();
        globalChat();
        privateChat();
        clearChat();
        challenge();
        challengeResponse();
        forwardPlayer();
        forwardLabel();
        play();
    }
    ObjectMapper objectMapper = new ObjectMapper();
    private void register(){
        server.addEventListener("register", String.class, new DataListener<String>() {
            @Override
            public void onData(SocketIOClient client, String message, AckRequest ackRequest) throws Exception {
                UserSessionData usd = objectMapper.readValue(message, UserSessionData.class);
                usd.setSocket(client);
                ActiveUsers.addUser(UUID.fromString(usd.getUuid()), usd, client);
                ActiveUsers.getInbox(usd.getAccounts().getUsername());
            }
        });

        server.addEventListener("registerGame", String.class, new DataListener<String>() {
            @Override
            public void onData(SocketIOClient client, String message, AckRequest ackRequest) throws Exception {
                UserSessionData usd = objectMapper.readValue(message, UserSessionData.class);
                usd.setSocket(client);
                usd.getAccounts().setStatus(true);
                ActiveUsers.addUser(UUID.fromString(usd.getUuid()), usd, client);
                ActiveUsers.getInbox(usd.getAccounts().getUsername());
            }
        });

    }
    private void getListOfActiveUsers(){
        server.addEventListener("getListOfActiveUsers", String.class, new DataListener<String>() {
            @Override
            public void onData(SocketIOClient client, String message, AckRequest ackRequest) throws Exception {
                UUID uuid = UUID.fromString(message);
                List<Accounts> response = ActiveUsers.getAllActiveUsers(uuid);
                String response_string = objectMapper.writeValueAsString(response);
                client.sendEvent("getListOfActiveUsers",response_string);
            }
        });
    }

    private void globalChat(){
        server.addEventListener("global-message", String.class, new DataListener<String>() {
            @Override
            public void onData(SocketIOClient socketIOClient, String message, AckRequest ackRequest) throws Exception {
                Message global_msg = objectMapper.readValue(message,Message.class);
                ActiveUsers.sendGlobalMessage(global_msg);
            }
        });
    }

    private void privateChat(){
        server.addEventListener("private-message", String.class, new DataListener<String>() {
            @Override
            public void onData(SocketIOClient socketIOClient, String message, AckRequest ackRequest) throws Exception {
                PrivateMessage private_msg = objectMapper.readValue(message,PrivateMessage.class);
                ActiveUsers.sendPrivateMessage(private_msg);

            }
        });
    }

    private void clearChat(){
        server.addEventListener("remove-chat", String.class, new DataListener<String>() {
            @Override
            public void onData(SocketIOClient socketIOClient, String message, AckRequest ackRequest) throws Exception {
                PrivateMessage chet_remove = objectMapper.readValue(message,PrivateMessage.class);
                ActiveUsers.removeChat(chet_remove.getUsername(),chet_remove.getMessageTo());
            }
        });
    }
    private void challenge(){
        server.addEventListener("challenge", String.class, new DataListener<String>() {
            @Override
            public void onData(SocketIOClient socketIOClient, String message, AckRequest ackRequest) throws Exception {
                Challenge challenge = objectMapper.readValue(message,Challenge.class);
                ActiveUsers.sendInvite(challenge);
            }
        });
    }
    private void challengeResponse(){
        server.addEventListener("challenge-response", String.class, new DataListener<String>() {
            @Override
            public void onData(SocketIOClient socketIOClient, String message, AckRequest ackRequest) throws Exception {
                Challenge challenge = objectMapper.readValue(message,Challenge.class);
                ActiveUsers.responseInvite(engine,challenge);
            }
        });
    }

//    IN-GAME
//    ==============================

    private void forwardPlayer(){
        server.addEventListener("get-game-status", String.class, new DataListener<String>() {
            @Override
            public void onData(SocketIOClient socketIOClient, String s, AckRequest ackRequest) throws Exception {
                Challenge challenge = objectMapper.readValue(s,Challenge.class);
                GameStatus gameStatus = engine.getGameStatus(challenge.getUuid());
                if(gameStatus != null)
                    ActiveUsers.sendStatus(gameStatus);
                else{
                    socketIOClient.sendEvent("send-game-status",objectMapper.writeValueAsString(null));
                }
            }
        });
    }
    private void forwardLabel(){
        server.addEventListener("get-label", String.class, new DataListener<String>() {
            @Override
            public void onData(SocketIOClient socketIOClient, String string, AckRequest ackRequest) throws Exception {
                RequestedField requestedField = objectMapper.readValue(string,RequestedField.class);
                requestedField.setText(engine.getTextFromPlace(requestedField));
                Challenge challenge = engine.getPlayers(requestedField.getUuid());
                ActiveUsers.sendTurn(requestedField,challenge);
            }
        });
    }
    private void play(){
        server.addEventListener("column-quest", String.class, new DataListener<String>() {
            @Override
            public void onData(SocketIOClient socketIOClient, String s, AckRequest ackRequest) throws Exception {
                ColumnQuest columnQuest = objectMapper.readValue(s,ColumnQuest.class);
                columnQuest = engine.testColumnQuest(columnQuest);
                if(!columnQuest.isResponse()) {
                    Challenge challenge = engine.getPlayers(columnQuest.getUuid_of_game());
                    ActiveUsers.sendTurn(columnQuest,challenge);
                    GameStatus gameStatus = engine.endTurn(columnQuest.getUuid_of_game());
                    ActiveUsers.sendStatus(gameStatus);
                }
                else {
                    GameStatus gameStatus = engine.getGameStatus(columnQuest.getUuid_of_game());
                    ActiveUsers.sendStatus(gameStatus);
                    if(gameStatus.getStatus_konacno_resenje().getWinner() != null){
                        ActiveUsers.sendEngGame(gameStatus);
                        engine.removeGame(gameStatus.getChallenge().getUuid());
                    }
                }
            }
        });
        server.addEventListener("end-turn", String.class, new DataListener<String>() {
            @Override
            public void onData(SocketIOClient socketIOClient, String s, AckRequest ackRequest) throws Exception {
                Challenge uuid_for_game = objectMapper.readValue(s,Challenge.class);
                GameStatus gameStatus = engine.endTurn(uuid_for_game.getUuid());
                ActiveUsers.sendStatus(gameStatus);
            }
        });
    }
}
