import Database.DbContext;
import Database.IDatabase;
import Models.Account;

public class Test {
    public static void main(String[] args) {
        IDatabase db = DbContext.getConnection();
        Account account = new Account();
        account.setUsername("bogdanlukic");
        account.setPassword("bogdan123");
//        account = db.login(account);
        System.out.println(account.toString());
    }
}
