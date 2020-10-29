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

import io.github.andersonstv.util.DiceUtil;
import io.github.andersonstv.util.FormatUtil;

import java.util.LinkedHashMap;
import java.util.Map;

public class Player {
    Map<String, Character> characterMap;
    String userID;
    Character current;

    public Player(String userID) {
        characterMap = new LinkedHashMap<>();
        this.userID = userID;
    }

    public Character getCurrent() {
        return current;
    }

    public Map<String, Character> getCharacterMap() {
        return characterMap;
    }

    public void setCharacterMap(Map<String, Character> characterMap) {
        this.characterMap = characterMap;
    }

    public void setCurrent(Character current) {
        this.current = current;
    }

    public void setCurrent(String charName) {
        this.current = characterMap.get(charName);
    }

    public boolean createWodCharacter(String charName){
        Character newCharacter = new WoDCharacter(charName);
        if(!characterMap.containsKey(newCharacter.getId())){
            characterMap.put(newCharacter.getId(), newCharacter);
            return true;
        } else {
            return false;
        }
    }
    public boolean createCocCharacter(String charName){
        Character newCharacter = new CoCCharacter(charName);
        if(!characterMap.containsKey(newCharacter.getId())){
            characterMap.put(newCharacter.getId(), newCharacter);
            return true;
        } else {
            return false;
        }
    }
    public Character removeChar(String charId){
        return characterMap.remove(charId);
    }
    public String check(String stat){
        if (current != null){
            return current.check(stat);
        }
        return "No active character";
    }
    public String check(String stat, String secondaryStat){
        if (current != null){
            return current.check(stat, secondaryStat);
        }
        return "No active character";
    }
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Your Characters: ");
        for (Character c : characterMap.values()) {
            result.append(c.getId()).append(FormatUtil.sep);
        }
        return result.toString();
    }
}
