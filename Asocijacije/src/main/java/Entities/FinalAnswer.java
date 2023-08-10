package Entities;

import Models.EChallange;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "konacno_resenje")
public class FinalAnswer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Transient
    private EChallange winner;

    public FinalAnswer(){
        winner = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EChallange getWinner() {
        return winner;
    }

    public void setWinner(EChallange winner) {
        this.winner = winner;
    }
}
