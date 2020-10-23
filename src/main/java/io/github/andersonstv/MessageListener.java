package io.github.andersonstv;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Random;

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
        if (messageContent.equals("$ping")) {
            channel.sendMessage("Pong!").queue();
        }
        if(messageContent.contains("$roll")){
            try {
                String[] input = messageContent.split(" ");
                String response;
                if (input[0].equals("$roll") && input.length == 2) {
                    response = diceController.rollExpression(input[1]);
                } else {
                    response = "Invalid Input: Use $roll XdY";
                }
                channel.sendMessage(response).queue();
            } catch (Exception e){
                channel.sendMessage("ERROR").queue();
            }
        }
    }

    @Override
    public void onGuildJoin(@NotNull GuildJoinEvent event) {
        MessageChannel channel = event.getGuild().getSystemChannel();
        if (channel != null){
            channel.sendMessage("That is not dead which can eternal lie." + System.lineSeparator() +
                    "And with strange aeons even death may die.").queue();
        }
    }

}
