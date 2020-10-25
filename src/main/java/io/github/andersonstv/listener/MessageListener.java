package io.github.andersonstv.listener;

import io.github.andersonstv.dice.DiceController;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class MessageListener extends ListenerAdapter {
    public DiceController diceController;
    private String integerRegex;

    public MessageListener() {
        super();
        this.diceController = new DiceController();
        integerRegex = "^\\d+$";
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;

        String messageContent = event.getMessage().getContentRaw();
        MessageChannel channel = event.getChannel();
        String[] messageArray = messageContent.split(" ");
        String response;
        if (messageContent.charAt(0) == '$') {
            response = switch (messageArray[0]) {
                case "$ping" -> "Pong!";
                case "$roll" -> respondDiceRoll(messageContent, "simple");
                case "$wod" -> respondDiceRoll(messageContent, "wod");
                case "$coc" -> respondDiceRoll(messageContent, "coc");
                default -> "Command not recognized";
            };
            channel.sendMessage(response).queue();
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
                if (inputArray.length >= 2 && inputArray[1].matches(integerRegex)){
                    int quantity = Integer.parseInt(inputArray[1]);
                    int difficulty;
                    if (inputArray.length == 3 && inputArray[2].matches(integerRegex)){
                        difficulty = Integer.parseInt(inputArray[2]);
                    } else{
                        difficulty = 8;
                    }
                    result = diceController.nwodRoll(quantity, difficulty);
                } else {
                    result = "Invalid Input: Try $wod <number of dice> <success difficulty>." +
                            "Ex: $wod 6 8";
                }
                break;
            case "coc":
                if (inputArray.length == 2 && inputArray[1].matches(integerRegex)){
                    int challenge = Integer.parseInt(inputArray[1]);
                    result = diceController.cocRoll(challenge);
                } else {
                    result = "Invalid Input: Try $coc <skill level>." +
                            "Ex: $cod 45 ";
                }
        }
        return result;
    }
}
