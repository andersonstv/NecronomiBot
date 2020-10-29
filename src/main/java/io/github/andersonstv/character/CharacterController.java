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

package io.github.andersonstv.character;

import io.github.andersonstv.util.FormatUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CharacterController {
    Map<String, Player> playerMap;

    public CharacterController() {
        this.playerMap = new HashMap<>();
    }
    public String createCharacter(String messageContent, String userId){
        String[] input = messageContent.split(" ");
        String response = "";
        if (input.length == 3){
            switch (input[1].toLowerCase()){
                case "wod":
                    if (createWodCharacter(input[2], userId)){
                        response = "Character created successfully.";
                    } else {
                        response = "That Character already exists.";
                    }
                    break;
                case "coc":
                    if (createCocCharacter(input[2], userId)){
                        response = "Character created successfully.";
                    } else {
                        response = "That Character already exists.";
                    }
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
            response = removeChar(userId, input[1]);
        } else {
            response = "Invalid Input: Try delete <name>";
        }
        return response;
    }

    public boolean createWodCharacter(String charName, String userId){
        if (!playerMap.containsKey(userId)) {
            playerMap.put(userId, new Player(userId));
        }
        return playerMap.get(userId).createWodCharacter(charName);
    }
    public boolean createCocCharacter(String charName, String userId){
        if (!playerMap.containsKey(userId)) {
            playerMap.put(userId, new Player(userId));
        }
        return playerMap.get(userId).createCocCharacter(charName);
    }
    public String removeChar(String userId, String charId){
        Character deleted = playerMap.get(userId).removeChar(charId);
        String response;
        if (deleted == null){
            response = "Character not found.";
        } else {
            response = "Character Removed";
        }
        return response;
    }
    public String printChars(String userId){
        Player player = playerMap.get(userId);
        return player == null ? "Player has no characters" : player.toString();
    }
    public String printCharacter(String userId, String charName){
        if (playerMap.containsKey(userId)){
            return playerMap.get(userId).printChar(charName);
        }
        return "Player not found.";
    }
    public String printCharacters(String messageContent, String userId){
        return printChars(userId);
    }
    public String getCurrent(String userId){
        if (playerMap.containsKey(userId)){
            Player player = playerMap.get(userId);
            return player.getCurrent() == null ? "No active character." : player.getCurrent().getId();
        }
        return "Player not found.";
    }
    public String currentChar(String messageContent, String userID){
        String[] input = messageContent.split(" ");
        String response;
        if (input.length > 1){
            response = setCurrent(userID, input[1]);
        } else {
            response = getCurrent(userID);
        }
        return response;
    }
    public String setCurrent(String userId, String charName){
        if (playerMap.containsKey(userId)){
            Player player = playerMap.get(userId);
            player.setCurrent(charName);
            return "Changed active character";
        } else {
            return "Player not found";
        }
    }
    public String setStat(String messageContent, String userId){
        Matcher quotes = Pattern.compile(FormatUtil.quotesRegex).matcher(messageContent.toLowerCase());
        Matcher value = Pattern.compile(FormatUtil.integerRegex).matcher(messageContent.toLowerCase());
        if (!playerMap.containsKey(userId)){
            return "Player not found";
        }
        if (quotes.find() && value.find()){
            boolean op = playerMap.get(userId).setStat(quotes.group(1), Integer.parseInt(value.group(1)));
            return op ? "Operation Successful" : "No active character";
        } else {
            return "Invalid Input: Try : $set \"<skill>\" <value";
        }
    }
    public String check(String messageContent, String userId){
        Matcher m = Pattern.compile(FormatUtil.quotesRegex).matcher(messageContent.toLowerCase());
        int length = m.groupCount();
        if (!playerMap.containsKey(userId)){
            return "Player not found";
        }
        if (length == 2 && m.find()){
            return playerMap.get(userId).check(m.group(1), m.group(2));
        } else if (length == 1 && m.find()){
            return playerMap.get(userId).check(m.group(1));
        }
        return "Invalid Input: Try $check <skill>";
    }
}
