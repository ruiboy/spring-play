package sbk.websocket;

import org.apache.commons.lang3.StringUtils;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import sbk.websocket.messages.Respond;
import sbk.websocket.messages.AddPlayer;

@Controller
public class PlayerController
{
    @MessageMapping("/addPlayer")
    @SendTo("/respond/player")
    public Respond greeting(AddPlayer message) throws Exception {
        Thread.sleep(500);
        if (StringUtils.equalsIgnoreCase("mcgovern", message.getName())) {
            return new Respond("Mmmmm probably not, " + message.getName() + " is injured :(");
        }
        return new Respond("Player Added, " + message.getName() + "!");
    }

}