package RMI;

import Database.DatabaseAsocijacije;
import Database.IDatabaseAsocijacije;
import Models.Challenge;
import Models.Game;
import Models.RequestedField;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

public class Engine extends UnicastRemoteObject implements IEngine{

    private static Map<UUID, Game> in_game_list;
    private IDatabaseAsocijacije databaseAsocijacije;
    private Random random;

    public Engine() throws RemoteException {
        super();
        in_game_list = new HashMap<UUID, Game>();
        databaseAsocijacije = DatabaseAsocijacije.getConnection();
        random = new Random();
    }
    @Override
    public void createNewGame(UUID uuid, Challenge challenge) throws RemoteException{
        int num_of_row = databaseAsocijacije.numOfRows();
        int asocijacije = random.nextInt(num_of_row) + 1;

        Game game = new Game();
        game.setChallenge(challenge);
        game.setAsocijacija(asocijacije);
        game.setKolona_a(databaseAsocijacije.getKolonaA(asocijacije));
        game.setKolona_b(databaseAsocijacije.getKolonaB(asocijacije));
        game.setKolona_c(databaseAsocijacije.getKolonaC(asocijacije));
        game.setKolona_d(databaseAsocijacije.getKolonaD(asocijacije));
        game.setKonacno_resenje(databaseAsocijacije.getKonacnoResenje(asocijacije));
        synchronized (in_game_list){
            in_game_list.put(uuid,game);
        }
    }

    @Override
    public String getTextFromPlace(RequestedField requested_field) throws RemoteException{
        Game game = in_game_list.get(requested_field.getUuid());
        System.out.println("USAO");
        switch (requested_field.getColumn()){
            case "a":
                return game.getKolona_a().getTextFromPlace(requested_field.getPlace());
            case "b":
                return game.getKolona_b().getTextFromPlace(requested_field.getPlace());
            case "c":
                return game.getKolona_c().getTextFromPlace(requested_field.getPlace());
            case "d":
                return game.getKolona_d().getTextFromPlace(requested_field.getPlace());
            default:
                return "";
        }
    }

    @Override
    public Challenge getPlayers(UUID uuid) throws RemoteException{
        return in_game_list.get(uuid).getChallenge();
    }
}
