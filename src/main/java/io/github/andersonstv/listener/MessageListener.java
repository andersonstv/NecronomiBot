package io.github.andersonstv.listener;


import io.github.andersonstv.dice.DiceController;
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
        String[] messageArray = messageContent.split(" ");
        String response;
        if (messageContent.charAt(0) == '$') {
            response = switch (messageArray[0]) {
                case "$ping" -> "Pong!";
                case "$roll" -> diceController.simpleExpression(messageContent);
                case "$wod" -> diceController.wodExpression(messageContent);
                case "$coc" -> diceController.cocExpression(messageContent);
                default -> "Command not recognized";
            };
            channel.sendMessage(response).queue();
        }
    }

}
