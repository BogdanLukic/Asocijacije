package Models;

import java.io.Serializable;

public class EndGame implements Serializable {
    private boolean win;
    private int points;

    public boolean isWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
