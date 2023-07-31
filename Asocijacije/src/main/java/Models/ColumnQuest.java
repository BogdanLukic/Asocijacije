package Models;

import java.io.Serializable;
import java.util.UUID;

public class ColumnQuest implements Serializable {
    UUID uuid_of_game;
    String column;
    String text;
    boolean response;
    EChallange winner;

    public ColumnQuest() {
    }

    public UUID getUuid_of_game() {
        return uuid_of_game;
    }

    public void setUuid_of_game(UUID uuid_of_game) {
        this.uuid_of_game = uuid_of_game;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isResponse() {
        return response;
    }

    public void setResponse(boolean response) {
        this.response = response;
    }

    public EChallange getWinner() {
        return winner;
    }

    public void setWinner(EChallange winner) {
        this.winner = winner;
    }
}
