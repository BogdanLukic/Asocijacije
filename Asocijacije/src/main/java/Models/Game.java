package Models;

import Entities.*;

import java.io.Serializable;

public class Game implements Serializable {
    private Challenge challenge;
    private int asocijacija;
    private Column_A column_a;
    private Column_B column_b;
    private Column_C column_c;
    private Column_D column_d;
    private FinalAnswer konacno_resenje;

    private int points_of_challanger;
    private int points_of_enemy;

    public Game(){
    }

    public Challenge getChallenge() {
        return challenge;
    }

    public void setChallenge(Challenge challenge) {
        this.challenge = challenge;
    }

    public int getAsocijacija() {
        return asocijacija;
    }

    public void setAsocijacija(int asocijacija) {
        this.asocijacija = asocijacija;
    }

    public Column_A getKolona_a() {
        return column_a;
    }

    public void setKolona_a(Column_A column_a) {
        this.column_a = column_a;
    }

    public Column_B getKolona_b() {
        return column_b;
    }

    public void setKolona_b(Column_B column_b) {
        this.column_b = column_b;
    }

    public Column_C getKolona_c() {
        return column_c;
    }

    public void setKolona_c(Column_C column_c) {
        this.column_c = column_c;
    }

    public Column_D getKolona_d() {
        return column_d;
    }

    public void setKolona_d(Column_D column_d) {
        this.column_d = column_d;
    }

    public FinalAnswer getKonacno_resenje() {
        return konacno_resenje;
    }

    public void setKonacno_resenje(FinalAnswer konacno_resenje) {
        this.konacno_resenje = konacno_resenje;
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

}
