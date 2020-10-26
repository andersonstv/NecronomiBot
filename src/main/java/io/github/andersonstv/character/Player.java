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

import java.util.LinkedHashMap;
import java.util.Map;

public class Player {
    Map<String, Character> characterMap;
    String userID;

    public Player(String userID) {
        characterMap = new LinkedHashMap<>();
    }

    public Map<String, Character> getCharacterMap() {
        return characterMap;
    }
    public boolean addCharacter(Character newCharacter){
        if(characterMap.containsKey(newCharacter.getId())){
            characterMap.put(newCharacter.getId(), newCharacter);
            return true;
        } else {
            return false;
        }
    }
    public Character removeChar(String charId){
        return characterMap.remove(charId);
    }

}
