package Models;

import Entities.*;

import java.io.Serializable;

public class GameAnswer extends GameStatus implements Serializable {

    private int asocijacija;
    private Column_A column_a;
    private Column_B column_b;
    private Column_C column_c;
    private Column_D column_d;
    private FinalAnswer konacno_resenje;

    public GameAnswer(){
    }

    public int getAsocijacija() {
        return asocijacija;
    }

    public void setAsocijacija(int asocijacija) {
        this.asocijacija = asocijacija;
    }

    public Column_A getColumn_a() {
        return column_a;
    }

    public void setColumn_a(Column_A column_a) {
        this.column_a = column_a;
    }

    public Column_B getColumn_b() {
        return column_b;
    }

    public void setColumn_b(Column_B column_b) {
        this.column_b = column_b;
    }

    public Column_C getColumn_c() {
        return column_c;
    }

    public void setColumn_c(Column_C column_c) {
        this.column_c = column_c;
    }

    public Column_D getColumn_d() {
        return column_d;
    }

    public void setColumn_d(Column_D column_d) {
        this.column_d = column_d;
    }

    public FinalAnswer getKonacno_resenje() {
        return konacno_resenje;
    }

    public void setKonacno_resenje(FinalAnswer konacno_resenje) {
        this.konacno_resenje = konacno_resenje;
    }

    public GameStatus getGameStatus(){
        GameStatus gameStatus = new GameStatus();
        gameStatus.setChallenge(this.getChallenge());
        gameStatus.setPoints_of_challanger(this.getPoints_of_challanger());
        gameStatus.setPoints_of_enemy(this.getPoints_of_enemy());

        gameStatus.setStatus_column_a(this.getStatus_column_a());
        gameStatus.setStatus_column_b(this.getStatus_column_b());
        gameStatus.setStatus_column_c(this.getStatus_column_c());
        gameStatus.setStatus_column_d(this.getStatus_column_d());

        gameStatus.setStatus_konacno_resenje(this.getStatus_konacno_resenje());
        gameStatus.setOn_turn(this.getOn_turn());
        gameStatus.setPlay(this.getPlay());

        return gameStatus;
    }
}
