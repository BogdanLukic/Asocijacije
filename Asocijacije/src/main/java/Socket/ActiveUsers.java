package Socket;

import Entities.Accounts;
import com.corundumstudio.socketio.SocketIOClient;

import java.util.*;

class ActiveUsers {
    private static HashMap<Accounts, SocketIOClient> list_of_active_users = new HashMap<Accounts,SocketIOClient>();

    public static void addUser(Accounts account,SocketIOClient socket){
        if(!list_of_active_users.containsKey(account)) {
            synchronized (list_of_active_users){
                list_of_active_users.put(account,socket);
            }
        }
    }
    public static List<Accounts> getAllActiveUsers(){
        List<Accounts> active_users = new ArrayList<Accounts>();
        for (Map.Entry<Accounts,SocketIOClient> entity : list_of_active_users.entrySet()) {
            active_users.add(entity.getKey());
        }
        return active_users;
    }
    public static List<Accounts> getAllActiveUsers(SocketIOClient client){
        List<Accounts> active_users = new ArrayList<Accounts>();
        for (Map.Entry<Accounts,SocketIOClient> entity : list_of_active_users.entrySet()) {
            if(entity.getValue() != client)
                active_users.add(entity.getKey());
        }
        return active_users;
    }

}
