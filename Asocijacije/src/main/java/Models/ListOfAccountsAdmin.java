package Models;

import Entities.Accounts;
import Entities.Score;

import java.util.List;

public class ListOfAccountsAdmin {
    List<Score> score_list;
    List<Score> active_user_list;

    public List<Score> getScore_list() {
        return score_list;
    }

    public void setScore_list(List<Score> score_list) {
        this.score_list = score_list;
    }

    public List<Score> getActive_user_list() {
        return active_user_list;
    }

    public void setActive_user_list(List<Score> active_user_list) {
        this.active_user_list = active_user_list;
    }
}
