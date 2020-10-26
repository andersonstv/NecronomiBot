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

import net.dv8tion.jda.api.entities.User;

import java.util.HashMap;
import java.util.Map;

public class CharacterController {
    Map<String, Player> playerMap;

    public CharacterController() {
        this.playerMap = new HashMap<>();
    }
    public void addPlayer(String userId){
        if (!playerMap.containsKey(userId)){
            playerMap.put(userId, new Player(userId));
        }
    }
    public boolean addCharacter(Character newCharacter, String userId){
        if (playerMap.containsKey(userId)){
            return playerMap.get(userId).addCharacter(newCharacter);
        } else{
            return false;
        }
    }
    public Character removeChar(String userId, String charId){
        return playerMap.get(userId).removeChar(charId);
    }
}
