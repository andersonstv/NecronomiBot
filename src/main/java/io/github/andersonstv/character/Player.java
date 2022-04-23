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
    public String printChar(String charName){
        if (characterMap.containsKey(charName)){
            return characterMap.get(charName).toString();
        }
        return "Character not found";
    }
    public boolean setStat(String statName, int value){
        if (current != null){
            current.setStat(statName, value);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Your Characters: ").append(FormatUtil.sep);
        for (Character c : characterMap.values()) {
            result.append(c.getId()).append(FormatUtil.sep);
        }
        return result.toString();
    }
}
