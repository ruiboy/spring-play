package sbk.websocket.messages;

public class AddPlayer
{

    private String name;

    public AddPlayer() {
    }

    public AddPlayer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}