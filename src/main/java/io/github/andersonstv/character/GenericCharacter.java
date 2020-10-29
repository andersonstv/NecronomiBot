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
import java.util.Objects;

public abstract class GenericCharacter implements Character{
    protected String id;
    protected Map<String, Integer> attributes;
    protected Map<String, Integer> skills;
    protected Map<String, String> descriptions;

    public GenericCharacter(String id){
        this.id = id;
        attributes = new HashMap<>();
        skills = new HashMap<>();
        descriptions = new HashMap<>();
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
        if (attributes.containsKey(statName)){
            attributes.remove(statName);
        }
        if (skills.containsKey(statName)){
            skills.remove(statName);
        }
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
