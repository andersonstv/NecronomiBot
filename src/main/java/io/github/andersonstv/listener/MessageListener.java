/*
 *     NecronomiBot. A Discord Bot for use with RPGs (RolePlaying Games)
 *     Copyright (C) 2020  Anderson dos Santos Silva
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package io.github.andersonstv.listener;


import io.github.andersonstv.character.CharacterController;
import io.github.andersonstv.util.DiceUtil;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class MessageListener extends ListenerAdapter {
    CharacterController charController;

    public MessageListener() {
        super();
        charController = new CharacterController();
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;
        Message message = event.getMessage();
        String messageContent = message.getContentRaw();
        MessageChannel channel = event.getChannel();
        String authorId = message.getAuthor().getAsTag();


        String[] messageArray = messageContent.split(" ");
        String response;
        if (messageContent.charAt(0) == '$') {
            response = switch (messageArray[0]) {
                case "$ping" -> "Pong!";
                case "$roll" -> DiceUtil.simple(messageContent);
                case "$wod" -> DiceUtil.wod(messageContent);
                case "$coc" -> DiceUtil.coc(messageContent);
                case "$uwu" -> uwunator(messageContent);
                case "$create" -> createCharacter(messageContent, authorId);
                case "$delete" -> deleteCharacter(messageContent, authorId);
                case "$chars" -> printCharacters(messageContent, authorId);
                case "$char" -> currentChar(messageContent, authorId);
                default -> "Command not recognized";
            };
            channel.sendMessage(response).queue();
        }
    }
    public String uwunator(String messageContent){
        String response = messageContent.replace("$uwu", "").replaceAll("[rl]", "w");
        return response.replaceAll("n[aeiou]", "ny$1");
    }
    public String createCharacter(String messageContent, String userId){
        String[] input = messageContent.split(" ");
        String response = "";
        if (input.length == 3){
            switch (input[1].toLowerCase()){
                case "wod":
                    if (charController.createWodCharacter(input[2], userId)){
                        response = "Character created successfully.";
                    } else {
                        response = "That Character already exists.";
                    }
                    break;
                case "coc":
                    response = "Not implemented yet.";
                    break;
            }
        } else {
            response = "Invalid Input: Try $create <wod/coc> <name>";
        }
        return response;
    }
    public String deleteCharacter(String messageContent, String userId){
        String[] input = messageContent.split(" ");
        String response;
        if (input.length == 2){
            response = charController.removeChar(userId, input[1]);
        } else {
            response = "Invalid Input: Try delete <name>";
        }
        return response;
    }
    public String printCharacters(String messageContent, String userId){
        return charController.printChars(userId);
    }
    public String currentChar(String messageContent, String userID){
        String[] input = messageContent.split(" ");
        String response;
        if (input.length == 2){
            response = charController.setCurrent(userID, input[1]);
        } else {
            response = charController.getCurrent(userID);
        }
        return response;
    }

}
