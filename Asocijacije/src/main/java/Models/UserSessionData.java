package Models;

import Entities.Accounts;
import com.corundumstudio.socketio.SocketIOClient;

import java.util.UUID;

public class UserSessionData {

    private Accounts accounts;

    private String uuid;

    private SocketIOClient socket;


    public UserSessionData() {
    }

    public UserSessionData(Accounts accounts, SocketIOClient socket) {
        this.accounts = accounts;
        this.socket = socket;
    }

    public Accounts getAccounts() {
        return accounts;
    }

    public String getUuid() {
        return uuid;
    }

    public SocketIOClient getSocket() {
        return socket;
    }

    public void setSocket(SocketIOClient socket) {
        this.socket = socket;
    }
}
