package Socket;

import Entities.Accounts;
import Models.*;
import RMI.IEngine;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.rmi.RemoteException;
import java.util.*;

class ActiveUsers {
    private static HashMap<UUID, UserSessionData> list_of_active_users = new HashMap<UUID,UserSessionData>();
    private static HashMap<String, Inbox> private_message_list = new HashMap<>();

    public static void addUser(UUID uuid, UserSessionData usd, SocketIOClient socket){
        if(!list_of_active_users.containsKey(uuid)) {
            synchronized (list_of_active_users){
                list_of_active_users.put(uuid,usd);
            }
            sendAllChanges();
        }
        else {
            synchronized (list_of_active_users){
                UserSessionData client = list_of_active_users.get(uuid);
                client.setSocket(socket);
                System.out.println("Abdejtovan");
            }
            sendAllChanges();
        }
    }
    public static List<Accounts> getAllActiveUsers(UUID uuid){
        List<Accounts> active_users = new ArrayList<Accounts>();
        for (Map.Entry<UUID,UserSessionData> entry : list_of_active_users.entrySet()) {
            if(!entry.getKey().equals(uuid))
                active_users.add(entry.getValue().getAccounts());
        }
        return active_users;
    }

    public static void removeUser(SocketIOClient socket){
        for (Map.Entry<UUID,UserSessionData> entry: list_of_active_users.entrySet()) {
            if(entry.getValue().getSocket().equals(socket)) {
                synchronized (list_of_active_users){
                    list_of_active_users.remove(entry.getKey());
                    System.out.println("Obrisan" + socket.getSessionId());
                }
                sendAllChanges();
            }
        }
    }
    private static ObjectMapper objectMapper = new ObjectMapper();
    public static void sendAllChanges(){
        try{
            for (Map.Entry<UUID,UserSessionData> entry: list_of_active_users.entrySet()) {
                SocketIOClient socket =  entry.getValue().getSocket();
                List<Accounts> users = new ArrayList<>();
                for (Map.Entry<UUID,UserSessionData> e: list_of_active_users.entrySet()) {
                    if(!entry.getKey().equals(e.getKey()))
                      users.add(e.getValue().getAccounts());
                }
                socket.sendEvent("getListOfActiveUsers",objectMapper.writeValueAsString(users));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void sendGlobalMessage(Message message){
        try{
            for (Map.Entry<UUID,UserSessionData> entry:list_of_active_users.entrySet()) {
                SocketIOClient socket =  entry.getValue().getSocket();
                socket.sendEvent("global-message",objectMapper.writeValueAsString(message));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
    private static SocketIOClient getSocketPerUsername(String username){
        for (Map.Entry<UUID,UserSessionData> entry: list_of_active_users.entrySet()) {
            if(entry.getValue().getAccounts().getUsername().equals(username))
                return entry.getValue().getSocket();
        }
        return null;
    }
    private static int getCharacterId(String username){
        for (Map.Entry<UUID,UserSessionData> entry : list_of_active_users.entrySet()) {
            if(entry.getValue().getAccounts().getUsername().equals(username)){
                return entry.getValue().getAccounts().getCharacter();
            }
        }
        return -1;
    }
    public static void sendPrivateMessage(PrivateMessage privateMessage){
        if(!private_message_list.containsKey(privateMessage.getMessageTo())){
            synchronized (private_message_list){
                private_message_list.put(privateMessage.getMessageTo(),new Inbox());
            }
        }
        try{
            Inbox inbox =  private_message_list.get(privateMessage.getMessageTo());
            int characte_id = getCharacterId(privateMessage.getUsername());
            privateMessage.setCharacter_id(characte_id);
            inbox.addNewMessage(privateMessage.getUsername(),privateMessage);
            SocketIOClient socket = getSocketPerUsername(privateMessage.getMessageTo());
            socket.sendEvent("private-message",objectMapper.writeValueAsString(inbox.getAllMessages()));
            socket.sendEvent("private-message-"+privateMessage.getMessageTo(),objectMapper.writeValueAsString(privateMessage));
        }
        catch (Exception e){e.printStackTrace();}
    }

    public static void removeChat(String username, String messageTo){
        Inbox inbox =  private_message_list.get(username);
        inbox.removeChat(messageTo);
    }

    public static void getInbox(String username){
        try{
            Inbox inbox = private_message_list.get(username);
            if(inbox != null){
                SocketIOClient socket = getSocketPerUsername(username);
                socket.sendEvent("private-message",objectMapper.writeValueAsString(inbox.getAllMessages()));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void sendInvite(Challenge challenge){
        for (Map.Entry<UUID,UserSessionData> entry: list_of_active_users.entrySet()) {
            if(entry.getValue().getAccounts().getId() == challenge.getEnemy().getId()){
                SocketIOClient socket = entry.getValue().getSocket();
                try{
                    socket.sendEvent("challenge",objectMapper.writeValueAsString(challenge.getChallenger()));
                }
                catch (Exception e) {e.printStackTrace();}
            }
        }
    }
    public static void responseInvite(IEngine engine, Challenge challenge){
        if(challenge.isResponse()){
            UUID uuid = UUID.randomUUID();
            SocketIOClient socket_chalanger = getSocketPerUsername(challenge.getChallenger().getUsername());
            SocketIOClient socket_enemy = getSocketPerUsername(challenge.getEnemy().getUsername());
            challenge.setUuid(uuid);
            try{
                socket_chalanger.sendEvent("challenge-response",objectMapper.writeValueAsString(challenge));
                socket_enemy.sendEvent("challenge-response",objectMapper.writeValueAsString(challenge));
                engine.createNewGame(uuid,challenge);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        else {
            SocketIOClient socket = getSocketPerUsername(challenge.getChallenger().getUsername());
            try{
                socket.sendEvent("challenge-response",objectMapper.writeValueAsString(challenge));
            }
            catch (Exception e){
                e.printStackTrace();
            }

        }
    }
    public static void sendTurn(RequestedField requested_field, Challenge challenge){
        SocketIOClient socket_chalanger = getSocketPerUsername(challenge.getChallenger().getUsername());
        SocketIOClient socket_enemy = getSocketPerUsername(challenge.getEnemy().getUsername());

        try{
            socket_chalanger.sendEvent("send-turn",objectMapper.writeValueAsString(requested_field));
            socket_enemy.sendEvent("send-turn",objectMapper.writeValueAsString(requested_field));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void sendTurn(ColumnQuest columnQuest, Challenge challenge){
        SocketIOClient socket_chalanger = getSocketPerUsername(challenge.getChallenger().getUsername());
        SocketIOClient socket_enemy = getSocketPerUsername(challenge.getEnemy().getUsername());

        try{
            socket_chalanger.sendEvent("column-quest",objectMapper.writeValueAsString(columnQuest));
            socket_enemy.sendEvent("column-quest",objectMapper.writeValueAsString(columnQuest));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sendStatus(GameStatus gameStatus){

        SocketIOClient socket_chalanger = getSocketPerUsername(gameStatus.getChallenge().getChallenger().getUsername());
        SocketIOClient socket_enemy = getSocketPerUsername(gameStatus.getChallenge().getEnemy().getUsername());

        try{
            socket_chalanger.sendEvent("send-game-status",objectMapper.writeValueAsString(gameStatus));
            socket_enemy.sendEvent("send-game-status",objectMapper.writeValueAsString(gameStatus));
            if(gameStatus.getOn_turn() == EChallange.challanger)
            {
                socket_chalanger.sendEvent("your-turn",gameStatus.getPlay());
                socket_enemy.sendEvent("not-your-turn",gameStatus.getPlay());
            }
            else{
                socket_enemy.sendEvent("your-turn",gameStatus.getPlay());
                socket_chalanger.sendEvent("not-your-turn",gameStatus.getPlay());
            }
        }
        catch (Exception e) {}
    }

}
