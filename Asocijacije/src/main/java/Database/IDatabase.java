package Database;

import Entities.Accounts;
import Entities.Score;
import Models.ERegistrationStatus;
import Models.EndGame;
import Models.GameStatus;

public interface IDatabase {
    Accounts login(Accounts account);
    ERegistrationStatus register(Accounts account);
    Score getScore(Accounts account);
    void setPoints(GameStatus gameStatus, EndGame challanger, EndGame enemy);
}
