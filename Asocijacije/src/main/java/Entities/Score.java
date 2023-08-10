package Entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "score")
public class Score implements Serializable {
    @Id
    private int account_id;
    private int score;
    transient
    @OneToOne
    private Accounts account;

    public Score() {
    }

    public void setAccount_id(int account) {
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

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount(Accounts account) {
        this.account = account;
    }

    public Accounts getAccount() {
        return account;
    }
}
