import Database.*;
import Database.*;
import Entities.*;
import Models.Challenge;
import Models.Game;
import RMI.IEngine;

import java.rmi.Naming;
import java.util.UUID;

public class Test {

    public static void main(String[] args) {

//        IDatabase db = Database.getConnection();
//
//        Accounts accounts = new Accounts();
//        accounts.setEmail("bogdanlukic20@gmail.com");
//        accounts.setPassword("Bogdan123");
//        accounts.setCharacter(2);
//        accounts.setUsername("Bocaa7");
//        System.out.println(db.register(accounts));

//        IDatabaseAsocijacije db = DatabaseAsocijacije.getConnection();
//        System.out.println(db.numOfRows());

        try{
            Game game = new Game();
            Challenge challenge = new Challenge();
            Accounts accounts = new Accounts();
            accounts.setPassword("password");
            accounts.setEmail("email");
            accounts.setCharacter(2);
            Role r = new Role();
            r.setId(2);
            r.setName("Perica1");
            accounts.setRole(r);
            accounts.setUsername("username");
            challenge.setChallenger(accounts);
            challenge.setEnemy(accounts);
            challenge.setResponse(true);
            game.setChallenge(challenge);

            IEngine engine = (IEngine) Naming.lookup("rmi://localhost:5353/engine");
//            engine.createNewGame(UUID.randomUUID(),game);
        }
        catch (Exception e){
            e.printStackTrace();
        }


    }
}
