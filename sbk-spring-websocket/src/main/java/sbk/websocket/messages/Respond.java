package sbk.websocket.messages;

public class Respond
{

    private String content;

    public Respond() {
    }

    public Respond(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

}