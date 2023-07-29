package Models;

import Entities.*;

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

    public GameStatus() {
        status_column_a = new Column_A();
        status_column_b = new Column_B();
        status_column_c = new Column_C();
        status_column_d = new Column_D();
        status_konacno_resenje = new FinalAnswer();
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
        this.points_of_challanger = points_of_challanger;
    }

    public int getPoints_of_enemy() {
        return points_of_enemy;
    }

    public void setPoints_of_enemy(int points_of_enemy) {
        this.points_of_enemy = points_of_enemy;
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
}
