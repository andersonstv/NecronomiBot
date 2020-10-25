package io.github.andersonstv;

import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class MessageListener extends ListenerAdapter {
    public DiceController diceController;

    public MessageListener() {
        super();
        this.diceController = new DiceController();
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;

        String messageContent = event.getMessage().getContentRaw();
        MessageChannel channel = event.getChannel();
        String response;

        if (messageContent.equals("$ping")) {
            response = "Pong!";
            channel.sendMessage(response).queue();
        }else if(messageContent.contains("$roll")){
            response = respondDiceRoll(messageContent, "simple");
            channel.sendMessage(response).queue();
        } else if (messageContent.contains("$wod")){
            response = respondDiceRoll(messageContent, "wod");
            channel.sendMessage(response).queue();
        } else if (messageContent.contains("$testCoC")){
            response = respondDiceRoll(messageContent, "coc");
            channel.sendMessage(messageContent).queue();
        }
    }
    public String respondDiceRoll(String messageContent, String rollType){
        String input = messageContent.replace(" ", "");
        String[] inputArray = messageContent.split(" ");
        String result = "";
        switch (rollType){
            case "simple":
                result = diceController.simpleRoll(input.replace("$roll", ""));
                break;
            case "wod":
                result = diceController.wodRoll(input.replace("$wod", ""));
                break;
            case "coc":
                if (inputArray.length == 2 && inputArray[1].matches("^\\d+$")){
                    int challenge = Integer.parseInt(inputArray[1]);
                    result = diceController.cocRoll(challenge);
                } else {
                    result = "Invalid Input";
                }
        }
        return result;
    }
}
