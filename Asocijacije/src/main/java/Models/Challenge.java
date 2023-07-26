package Models;

import Entities.Accounts;

public class Challenge {
    private Accounts challenger;
    private Accounts enemy;

    private boolean response;

    public Accounts getChallenger() {
        return challenger;
    }

    public void setChallenger(Accounts challenger) {
        this.challenger = challenger;
    }

    public Accounts getEnemy() {
        return enemy;
    }

    public void setEnemy(Accounts enemy) {
        this.enemy = enemy;
    }

    public boolean isResponse() {
        return response;
    }

    public void setResponse(boolean response) {
        this.response = response;
    }
}
