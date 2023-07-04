import Database.Database;
import Database.IDatabase;
import Entities.Accounts;

public class Test {

    public static void main(String[] args) {

        IDatabase db = Database.getConnection();

        Accounts accounts = new Accounts();
        accounts.setEmail("bogdanlukic20@gmail.com");
        accounts.setPassword("Bogdan123");
        accounts.setCharacter(2);
        accounts.setUsername("Bocaa7");
        System.out.println(db.register(accounts));


    }
}
