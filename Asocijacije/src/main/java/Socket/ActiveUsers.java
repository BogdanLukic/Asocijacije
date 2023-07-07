package Socket;

import Entities.Accounts;
import Models.Message;
import Models.UserSessionData;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;

class ActiveUsers {
    private static HashMap<UUID, UserSessionData> list_of_active_users = new HashMap<UUID,UserSessionData>();

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
    public static List<Accounts> getAllActiveUsers(){
        List<Accounts> active_users = new ArrayList<Accounts>();
        for (Map.Entry<UUID,UserSessionData> entry : list_of_active_users.entrySet()) {
            active_users.add(entry.getValue().getAccounts());
        }
        return active_users;
    }
    public static List<Accounts> getAllActiveUsers(UUID uuid){
        List<Accounts> active_users = new ArrayList<Accounts>();
        for (Map.Entry<UUID,UserSessionData> entry : list_of_active_users.entrySet()) {
            if(!entry.getKey().equals(uuid))
                active_users.add(entry.getValue().getAccounts());
        }
        return active_users;
    }

    public static List<Accounts> getAllActiveUsers(SocketIOClient socket){
        List<Accounts> active_users = new ArrayList<Accounts>();
        for (Map.Entry<UUID,UserSessionData> entry : list_of_active_users.entrySet()) {
            if(!entry.getValue().getSocket().equals(socket))
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

}
