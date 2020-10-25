package io.github.andersonstv;

public class WoDCharacter extends GenericCharacter {

    public WoDCharacter(String charName, String userID) {
        super(charName, userID);
        setDefault(charName);
    }
    public void setDefault(String charName){
        descriptions.put("name", charName);
        attributes.put("Intelligence", 1);
        attributes.put("Strength", 1);
        attributes.put("Presence", 1);
        attributes.put("Wits", 1);
        attributes.put("Dexterity", 1);
        attributes.put("Manipulation", 1);
        attributes.put("Resolve", 1);
        attributes.put("Stamina", 1);
        attributes.put("Composure", 1);
    }

}
