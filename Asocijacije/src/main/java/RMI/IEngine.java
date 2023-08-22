package RMI;

import Entities.Accounts;
import Models.*;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.UUID;

public interface IEngine extends Remote {
    void createNewGame(UUID uuid, Challenge challenge) throws RemoteException;
    String getTextFromPlace(RequestedField requested_field) throws RemoteException;
    Challenge getPlayers(UUID uuid) throws RemoteException;
    GameStatus getGameStatus(UUID uuid) throws RemoteException;
    ColumnQuest testColumnQuest(ColumnQuest columnQuest) throws RemoteException;
    GameStatus endTurn(UUID uuid) throws RemoteException;
    void removeGame(UUID uuid) throws RemoteException;
    boolean countNotPlayable(UUID uuid) throws RemoteException;
    GameStatus surrender(Accounts surr) throws RemoteException;
}
