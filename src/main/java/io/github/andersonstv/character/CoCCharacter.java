package io.github.andersonstv.character;

public class CoCCharacter extends GenericCharacter {

    public CoCCharacter(String charName, String userID){
        super(charName, userID);
        setDefault(charName);
    }
    public void setDefault(String charName) {
        descriptions.put("name", charName);
    }

}
