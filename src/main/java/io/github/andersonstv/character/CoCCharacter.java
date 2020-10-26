
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

public class CoCCharacter extends GenericCharacter {

    public CoCCharacter(String charName, String id){
        super(id+"coc");;
        setDefault(charName);
    }
    public void setDefault(String charName) {
        descriptions.put("name", charName);
        attributes.put("STR", 0);
        attributes.put("DEX", 0);
        attributes.put("INT", 0);
        attributes.put("CON", 0);
        attributes.put("APP", 0);
        attributes.put("POW", 0);
        attributes.put("SIZ", 0);
        attributes.put("EDU", 0);

    }
    public String skillCheck(String skillName){
        if (skills.containsKey(skillName)){
            return DiceUtil.cocRoll10s(skills.get(skillName));
        } else {
            return "Skill not found.";
        }
    }
}
