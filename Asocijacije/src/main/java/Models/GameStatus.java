package Models;

import Entities.*;
import Services.Timer;

import java.io.Serializable;

public class GameStatus implements Serializable {
    private Challenge challenge;
    private int points_of_challanger;
    private int points_of_enemy;

    private Column_A status_column_a;
    private Column_B status_column_b;
    private Column_C status_column_c;
    private Column_D status_column_d;
    private FinalAnswer status_konacno_resenje;
    private EChallange on_turn;
    private EChallange play;

    private int second;

    public GameStatus() {
        status_column_a = new Column_A();
        status_column_b = new Column_B();
        status_column_c = new Column_C();
        status_column_d = new Column_D();
        status_konacno_resenje = new FinalAnswer();
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public Challenge getChallenge() {
        return challenge;
    }

    public void setChallenge(Challenge challenge) {
        this.challenge = challenge;
    }

    public int getPoints_of_challanger() {
        return points_of_challanger;
    }

    public void setPoints_of_challanger(int points_of_challanger) {
        this.points_of_challanger += points_of_challanger;
    }

    public int getPoints_of_enemy() {
        return points_of_enemy;
    }

    public void setPoints_of_enemy(int points_of_enemy) {
        this.points_of_enemy += points_of_enemy;
    }

    public Column_A getStatus_column_a() {
        return status_column_a;
    }

    public Column_B getStatus_column_b() {
        return status_column_b;
    }

    public Column_C getStatus_column_c() {
        return status_column_c;
    }

    public Column_D getStatus_column_d() {
        return status_column_d;
    }

    public EChallange getOn_turn() {
        return on_turn;
    }

    public void setOn_turn(EChallange on_turn) {
        this.on_turn = on_turn;
    }

    public void setStatus_column_a(Column_A status_column_a) {
        this.status_column_a = status_column_a;
    }

    public void setStatus_column_b(Column_B status_column_b) {
        this.status_column_b = status_column_b;
    }

    public void setStatus_column_c(Column_C status_column_c) {
        this.status_column_c = status_column_c;
    }

    public void setStatus_column_d(Column_D status_column_d) {
        this.status_column_d = status_column_d;
    }

    public FinalAnswer getStatus_konacno_resenje() {
        return status_konacno_resenje;
    }

    public void setStatus_konacno_resenje(FinalAnswer status_konacno_resenje) {
        this.status_konacno_resenje = status_konacno_resenje;
    }
    public EChallange getPlay() {
        return play;
    }

    public void setPlay(EChallange play) {
        this.play = play;
    }
    public int calculatePoints(String column){
        int points = 1;
        switch (column){
            case "a":
                if(status_column_a.getOne() == null)
                    points++;
                if(status_column_a.getTwo() == null)
                    points++;
                if(status_column_a.getThree() == null)
                    points++;
                if(status_column_a.getFour() == null)
                    points++;
                return points;
            case "b":
                if(status_column_b.getOne() == null)
                    points++;
                if(status_column_b.getTwo() == null)
                    points++;
                if(status_column_b.getThree() == null)
                    points++;
                if(status_column_b.getFour() == null)
                    points++;
                return points;
            case "c":
                if(status_column_c.getOne() == null)
                    points++;
                if(status_column_c.getTwo() == null)
                    points++;
                if(status_column_c.getThree() == null)
                    points++;
                if(status_column_c.getFour() == null)
                    points++;
                return points;
            case "d":
                if(status_column_d.getOne() == null)
                    points++;
                if(status_column_d.getTwo() == null)
                    points++;
                if(status_column_d.getThree() == null)
                    points++;
                if(status_column_d.getFour() == null)
                    points++;
                return points;
        }
        return 0;
    }
}
