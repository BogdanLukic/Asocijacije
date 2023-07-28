package Models;

import Entities.Accounts;

import java.io.Serializable;
import java.util.UUID;

public class Challenge implements Serializable {
    private Accounts challenger;
    private Accounts enemy;

    private boolean response;
    private UUID uuid;

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

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}
