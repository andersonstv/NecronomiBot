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

import java.util.HashMap;
import java.util.Map;

public class CharacterController {
    Map<String, Player> playerMap;

    public CharacterController() {
        this.playerMap = new HashMap<>();
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
    public String getCurrent(String userId){
        if (playerMap.containsKey(userId)){
            Player player = playerMap.get(userId);
            return player.getCurrent() == null ? "No active character." : player.getCurrent().getId();
        }
        return "Player not found.";
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
    public String check(String messageContent, String userId){
        String[] input = messageContent.split(" ");
        if (!playerMap.containsKey(userId)){
            return "Player not found";
        }
        if (messageContent.length() == 3){
            return playerMap.get(userId).check(input[1], input[2]);
        } else if (messageContent.length() == 2){
            return playerMap.get(userId).check((input[1]));
        }
        return "Invalid Input: Try $check <skill>";
    }
}
