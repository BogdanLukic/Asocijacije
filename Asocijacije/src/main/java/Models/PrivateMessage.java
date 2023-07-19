package Models;

public class PrivateMessage extends Message{
    private String message_to;
    private int character_id;

    public String getMessageTo() {
        return message_to;
    }

    public void setMessageTo(String message_to) {
        this.message_to = message_to;
    }

    public int getCharacter_id() {
        return character_id;
    }

    public void setCharacter_id(int character_id) {
        this.character_id = character_id;
    }
}
