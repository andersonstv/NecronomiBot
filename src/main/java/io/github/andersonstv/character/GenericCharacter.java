package io.github.andersonstv.character;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public abstract class GenericCharacter implements Character{
    protected String id;
    protected Map<String, Integer> attributes;
    protected Map<String, Integer> skills;
    protected Map<String, String> descriptions;

    public GenericCharacter(String id){
        this.id = id;
        attributes = new LinkedHashMap<>();
        skills = new LinkedHashMap<>();
        descriptions = new LinkedHashMap<>();
    }

    @Override
    public String getId() {
        return id;
    }

    public Map<String, Integer> getAttributes() {
        return attributes;
    }

    public Map<String, Integer> getSkills() {
        return skills;
    }

    public Map<String, String> getDescriptions() {
        return descriptions;
    }

    public void setStat(String statName, int value){
        if (attributes.containsKey(statName)){
            attributes.put(statName, value);
        } else{
            skills.put(statName, value);
        }
    }
    public void removeStat(String statName){
        attributes.remove(statName);
        skills.remove(statName);
    }
    public String getStat(String statName){
        if (attributes.containsKey(statName)){
            return statName + attributes.get(statName);
        } else if (skills.containsKey(statName)){
            return statName + skills.get(statName);
        }
        return "Not found.";
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

    public abstract String check(String stat);
    public abstract String check(String stat, String statSecondary);

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

    @Override
    public String toString() {
        return "GenericCharacter{" +
                "id='" + id + '\'' +
                ", attributes=" + attributes +
                ", skills=" + skills +
                ", descriptions=" + descriptions +
                '}';
    }
}
