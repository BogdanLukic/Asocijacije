package Socket;

import Entities.Accounts;
import Models.Message;
import Models.UserSessionData;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;

class ServerSocketIO {

    private static Configuration config;
    private static SocketIOServer server;

    private static ServerSocketIO my_server;

    private void setConfiguration(){
        config = new Configuration();
        config.setHostname("localhost");
        config.setPort(2020);
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
    }
    ObjectMapper objectMapper = new ObjectMapper();
    private void register(){
        server.addEventListener("register", String.class, new DataListener<String>() {
            @Override
            public void onData(SocketIOClient client, String message, AckRequest ackRequest) throws Exception {
                UserSessionData usd = objectMapper.readValue(message, UserSessionData.class);
                usd.setSocket(client);
                ActiveUsers.addUser(UUID.fromString(usd.getUuid()), usd, client);
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
}
