package Database;

import Entities.Accounts;
import Entities.Score;
import Models.Association;
import Models.ERegistrationStatus;
import Models.EndGame;
import Models.GameStatus;

import java.util.List;

public interface IDatabase {
    Accounts login(Accounts account);
    ERegistrationStatus register(Accounts account);
    Score getScore(Accounts account);
    void setPoints(GameStatus gameStatus, EndGame challanger, EndGame enemy);
    List<Score> getTopThree();
    List<Score> getAllAccounts();
    void removeAccount(int account_id);
}
