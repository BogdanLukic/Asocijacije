package RMI;

import Database.DatabaseAsocijacije;
import Database.IDatabaseAsocijacije;
import Entities.Accounts;
import Models.*;
import com.google.protobuf.MapEntry;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

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
        GameAnswer gameAnswer = new GameAnswer();
        gameAnswer.setOn_turn(EChallange.challanger);
        gameAnswer.setPlay(EChallange.open);
        gameAnswer.setChallenge(challenge);

        gameAnswer.setKonacno_resenje(databaseAsocijacije.getKonacnoResenje());

        gameAnswer.setAsocijacija(gameAnswer.getAsocijacija());
        gameAnswer.setColumn_a(databaseAsocijacije.getKolonaA(gameAnswer.getKonacno_resenje().getId()));
        gameAnswer.setColumn_b(databaseAsocijacije.getKolonaB(gameAnswer.getKonacno_resenje().getId()));
        gameAnswer.setColumn_c(databaseAsocijacije.getKolonaC(gameAnswer.getKonacno_resenje().getId()));
        gameAnswer.setColumn_d(databaseAsocijacije.getKolonaD(gameAnswer.getKonacno_resenje().getId()));

        gameAnswer.getTimer().startTimer();

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
        if(game != null){
            GameStatus response = game.getGameStatus();
            return response;
        }
        return null;
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
                    int points = gameAnswer.calculatePoints("a");
                    if(winner == EChallange.challanger)
                        gameAnswer.setPoints_of_challanger(points);
                    else
                        gameAnswer.setPoints_of_enemy(points);
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
                    int points = gameAnswer.calculatePoints("b");
                    if(winner == EChallange.challanger)
                        gameAnswer.setPoints_of_challanger(points);
                    else
                        gameAnswer.setPoints_of_enemy(points);
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
                    int points = gameAnswer.calculatePoints("c");
                    if(winner == EChallange.challanger)
                        gameAnswer.setPoints_of_challanger(points);
                    else
                        gameAnswer.setPoints_of_enemy(points);
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
                    int points = gameAnswer.calculatePoints("d");
                    if(winner == EChallange.challanger)
                        gameAnswer.setPoints_of_challanger(points);
                    else
                        gameAnswer.setPoints_of_enemy(points);
                    gameAnswer.getStatus_column_d().setName(columnQuest.getText());
                    gameAnswer.getStatus_column_d().setWinner(winner);
                    gameAnswer.getColumn_d().setWinner(winner);
                    gameAnswer.setStatus_column_d(gameAnswer.getColumn_d());
                }
                else
                    columnQuest.setResponse(false);
                break;
            case "final":
                if(gameAnswer.getKonacno_resenje().getName().equals(columnQuest.getText())){
                    columnQuest.setResponse(true);
                    EChallange winner = gameAnswer.getOn_turn();
                    columnQuest.setWinner(winner);
                    int points = 5;
                    if(gameAnswer.getStatus_column_a().getWinner() == null){
                        gameAnswer.getColumn_a().setWinner(winner);
                        points += gameAnswer.calculatePoints("a");
                    }
                    gameAnswer.setStatus_column_a(gameAnswer.getColumn_a());

                    if(gameAnswer.getStatus_column_b().getWinner() == null){
                        gameAnswer.getColumn_b().setWinner(winner);
                        points += gameAnswer.calculatePoints("b");
                    }
                    gameAnswer.setStatus_column_b(gameAnswer.getColumn_b());

                    if(gameAnswer.getStatus_column_c().getWinner() == null){
                        gameAnswer.getColumn_c().setWinner(winner);
                        points += gameAnswer.calculatePoints("c");
                    }
                    gameAnswer.setStatus_column_c(gameAnswer.getColumn_c());

                    if(gameAnswer.getStatus_column_d().getWinner() == null){
                        gameAnswer.getColumn_d().setWinner(winner);
                        points += gameAnswer.calculatePoints("d");
                    }
                    gameAnswer.setStatus_column_d(gameAnswer.getColumn_d());

                    if(winner == EChallange.challanger){
                        gameAnswer.setPoints_of_challanger(points);
                    }
                    else{
                        gameAnswer.setPoints_of_enemy(points);
                    }

                    gameAnswer.getStatus_konacno_resenje().setName(columnQuest.getText());
                    gameAnswer.setStatus_konacno_resenje(gameAnswer.getKonacno_resenje());
                    gameAnswer.getStatus_konacno_resenje().setWinner(winner);
                }
                break;
        }
        return columnQuest;
    }

    @Override
    public GameStatus endTurn(UUID uuid) throws RemoteException{
        GameAnswer gameAnswer = in_game_list.get(uuid);
        gameAnswer.setPlay(EChallange.open);
        if(gameAnswer.getOn_turn() == EChallange.challanger){
            gameAnswer.setOn_turn(EChallange.enemy);
            gameAnswer.getTimer().stopTimer();
            gameAnswer.getTimer().startTimer();
        }
        else
        {
            gameAnswer.setOn_turn(EChallange.challanger);
            gameAnswer.getTimer().stopTimer();
            gameAnswer.getTimer().startTimer();
        }
        return getGameStatus(uuid);
    }

    @Override
    public void removeGame(UUID uuid) throws RemoteException{
        try{
            in_game_list.remove(uuid);
        }
        catch (Exception e){}
    }

    @Override
    public boolean countNotPlayable(UUID uuid) throws RemoteException {
        in_game_list.get(uuid).counting();
        if(in_game_list.get(uuid).getCounter() == 6)
            return true;
        return false;
    }

    @Override
    public GameStatus surrender(Accounts surr) throws RemoteException {
        for (Map.Entry<UUID, GameAnswer> entry : in_game_list.entrySet()) {
            if(entry.getValue().getChallenge().getChallenger().getUsername().equals(surr.getUsername()) ||
            entry.getValue().getChallenge().getEnemy().getUsername().equals(surr.getUsername())){
                return entry.getValue().getGameStatus();
            }
        }
        return null;
    }
}
