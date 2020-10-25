package io.github.andersonstv;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class GenericCharacter {
    protected String id;
    protected Map<String, Integer> attributes;
    protected Map<String, Integer> skills;
    protected Map<String, String> descriptions;

    public GenericCharacter(String charName, String userID){
        id = charName+userID;
        attributes = new HashMap<>();
        skills = new HashMap<>();
        descriptions = new HashMap<>();
    }
    public void addSkill(String skillName, int value){
        skills.put(skillName, value);
    }
    public Integer getSkill(String skillName){
        return skills.get(skillName);
    }
    public void addAttribute(String attName, int value){
        skills.put(attName, value);
    }
    public Integer getAttribute(String attName){
        return attributes.get(attName);
    }
    public void addDescription(String descriptionName, String value){
        descriptions.put(descriptionName, value);
    }
    public String getDescription(String descriptionName){
        return descriptions.get(descriptionName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenericCharacter that = (GenericCharacter) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
