package RMI;

import Models.Challenge;
import Models.ColumnQuest;
import Models.GameStatus;
import Models.RequestedField;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.UUID;

public interface IEngine extends Remote {
    void createNewGame(UUID uuid, Challenge challenge) throws RemoteException;
    String getTextFromPlace(RequestedField requested_field) throws RemoteException;
    Challenge getPlayers(UUID uuid) throws RemoteException;
    GameStatus getGameStatus(UUID uuid) throws RemoteException;
    ColumnQuest testColumnQuest(ColumnQuest columnQuest) throws RemoteException;
    GameStatus endTurn(UUID uuid) throws RemoteException;
    void removeGame(UUID uuid) throws RemoteException;
}
