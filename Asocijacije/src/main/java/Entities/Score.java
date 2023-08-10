package Entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "score")
public class Score implements Serializable {
    @Id
    private int account_id;
    private int score;

    public Score() {
    }

    public int getAccount() {
        return account_id;
    }

    public void setAccount(int account) {
        this.account_id = account;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score += score;
        if(this.score < 0)
            this.score = 0;
    }
}
