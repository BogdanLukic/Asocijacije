package RMI;

import Models.Challenge;
import Models.RequestedField;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.UUID;

public interface IEngine extends Remote {
    void createNewGame(UUID uuid, Challenge challenge) throws RemoteException;
    String getTextFromPlace(RequestedField requested_field) throws RemoteException;
    Challenge getPlayers(UUID uuid) throws RemoteException;

}
