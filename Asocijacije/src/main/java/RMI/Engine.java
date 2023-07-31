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
        gameAnswer.setPlay(EChallange.open);
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
        gameAnswer.setPlay(EChallange.play);
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

    @Override
    public ColumnQuest testColumnQuest(ColumnQuest columnQuest) throws RemoteException {
        GameAnswer gameAnswer = in_game_list.get(columnQuest.getUuid_of_game());
        switch (columnQuest.getColumn()){
            case "a":
                if(gameAnswer.getColumn_a().getName().equals(columnQuest.getText()))
                {
                    columnQuest.setResponse(true);
                    EChallange winner = gameAnswer.getOn_turn();
                    columnQuest.setWinner(winner);
                    gameAnswer.getStatus_column_a().setName(columnQuest.getText());
                    gameAnswer.getStatus_column_a().setWinner(winner);
                    gameAnswer.getColumn_a().setWinner(winner);
                    gameAnswer.setStatus_column_a(gameAnswer.getColumn_a());
                }
                else
                    columnQuest.setResponse(false);
                break;
            case "b":
                if(gameAnswer.getColumn_b().getName().equals(columnQuest.getText()))
                {
                    columnQuest.setResponse(true);
                    EChallange winner = gameAnswer.getOn_turn();
                    columnQuest.setWinner(winner);
                    gameAnswer.getStatus_column_b().setName(columnQuest.getText());
                    gameAnswer.getStatus_column_b().setWinner(winner);
                    gameAnswer.getColumn_b().setWinner(winner);
                    gameAnswer.setStatus_column_b(gameAnswer.getColumn_b());
                }
                else
                    columnQuest.setResponse(false);
                break;
            case "c":
                if(gameAnswer.getColumn_c().getName().equals(columnQuest.getText()))
                {
                    columnQuest.setResponse(true);
                    EChallange winner = gameAnswer.getOn_turn();
                    columnQuest.setWinner(winner);
                    gameAnswer.getStatus_column_c().setName(columnQuest.getText());
                    gameAnswer.getStatus_column_c().setWinner(winner);
                    gameAnswer.getColumn_c().setWinner(winner);
                    gameAnswer.setStatus_column_c(gameAnswer.getColumn_c());
                }
                else
                    columnQuest.setResponse(false);
                break;
            case "d":
                if(gameAnswer.getColumn_d().getName().equals(columnQuest.getText()))
                {
                    columnQuest.setResponse(true);
                    EChallange winner = gameAnswer.getOn_turn();
                    columnQuest.setWinner(winner);
                    gameAnswer.getStatus_column_d().setName(columnQuest.getText());
                    gameAnswer.getStatus_column_d().setWinner(winner);
                    gameAnswer.getColumn_d().setWinner(winner);
                    gameAnswer.setStatus_column_d(gameAnswer.getColumn_d());
                }
                else
                    columnQuest.setResponse(false);
                break;
        }
        return columnQuest;
    }

    @Override
    public GameStatus endTurn(UUID uuid) throws RemoteException{
        System.out.println("END-TURN");
        GameAnswer gameAnswer = in_game_list.get(uuid);
        gameAnswer.setPlay(EChallange.open);
        if(gameAnswer.getOn_turn() == EChallange.challanger)
            gameAnswer.setOn_turn(EChallange.enemy);
        else
            gameAnswer.setOn_turn(EChallange.challanger);
        return getGameStatus(uuid);
    }

}
