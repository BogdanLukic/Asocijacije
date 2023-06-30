package Database;

import Models.Account;

public interface IDatabase {
    public Account login(Account account);
}
