package Database;

import Entities.Accounts;
import Models.ERegistrationStatus;

public interface IDatabase {
    public Accounts login(Accounts account);
    public ERegistrationStatus register(Accounts account);
}
