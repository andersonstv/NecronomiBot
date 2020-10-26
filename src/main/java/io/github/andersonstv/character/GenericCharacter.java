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

public class GenericCharacter implements Character{
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
