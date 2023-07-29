package RMI;

import Database.DatabaseAsocijacije;
import Database.IDatabaseAsocijacije;
import Models.*;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

public class Engine extends UnicastRemoteObject implements IEngine{

    private static Map<UUID, GameAnswer> in_game_list;
    private IDatabaseAsocijacije databaseAsocijacije;
    private Random random;

    public Engine() throws RemoteException {
        super();
        in_game_list = new HashMap<UUID, GameAnswer>();
        databaseAsocijacije = DatabaseAsocijacije.getConnection();
        random = new Random();
    }
    @Override
    public void createNewGame(UUID uuid, Challenge challenge) throws RemoteException{
        int num_of_row = databaseAsocijacije.numOfRows();
        int asocijacije = random.nextInt(num_of_row) + 1;

        GameAnswer gameAnswer = new GameAnswer();
        gameAnswer.setOn_turn(EChallange.challanger);
        gameAnswer.setChallenge(challenge);
        gameAnswer.setAsocijacija(asocijacije);
        gameAnswer.setColumn_a(databaseAsocijacije.getKolonaA(asocijacije));
        gameAnswer.setColumn_b(databaseAsocijacije.getKolonaB(asocijacije));
        gameAnswer.setColumn_c(databaseAsocijacije.getKolonaC(asocijacije));
        gameAnswer.setColumn_d(databaseAsocijacije.getKolonaD(asocijacije));
        gameAnswer.setKonacno_resenje(databaseAsocijacije.getKonacnoResenje(asocijacije));
        synchronized (in_game_list){
            in_game_list.put(uuid, gameAnswer);
        }
    }

    @Override
    public String getTextFromPlace(RequestedField requested_field) throws RemoteException{
        GameAnswer gameAnswer = in_game_list.get(requested_field.getUuid());
        String text;
        switch (requested_field.getColumn()){
            case "a":
                text = gameAnswer.getColumn_a().getTextFromPlace(requested_field.getPlace());
                gameAnswer.getStatus_column_a().setTextOnPlace(requested_field.getPlace(), text);
                return text;
            case "b":
                text = gameAnswer.getColumn_b().getTextFromPlace(requested_field.getPlace());
                gameAnswer.getStatus_column_b().setTextOnPlace(requested_field.getPlace(), text);
                return text;
            case "c":
                text = gameAnswer.getColumn_c().getTextFromPlace(requested_field.getPlace());
                gameAnswer.getStatus_column_c().setTextOnPlace(requested_field.getPlace(), text);
                return text;
            case "d":
                text = gameAnswer.getColumn_d().getTextFromPlace(requested_field.getPlace());
                gameAnswer.getStatus_column_d().setTextOnPlace(requested_field.getPlace(), text);
                return text;
            default:
                return "";
        }
    }
    @Override
    public Challenge getPlayers(UUID uuid) throws RemoteException{
        return in_game_list.get(uuid).getChallenge();
    }

    @Override
    public GameStatus getGameStatus(UUID uuid) throws RemoteException {
        GameAnswer game = in_game_list.get(uuid);
        GameStatus response = game.getGameStatus();
        return response;
    }


}
