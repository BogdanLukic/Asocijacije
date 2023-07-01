package Database;

import Models.Account;
import Models.ERegistrationStatus;

public interface IDatabase {
    public String login(Account account);
    public ERegistrationStatus register(Account account);
}
