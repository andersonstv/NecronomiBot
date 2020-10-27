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
import io.github.andersonstv.util.ImportUtil;

public class WoDCharacter extends GenericCharacter {
    private int[] hp;
    private int[] willpower;

    public WoDCharacter(String charName) {
        super(charName);
        setDefault();
    }
    public void setDefault(){
        attributes = ImportUtil.importMapCSV(ImportUtil.filepath + "wodDefaultAttributes.csv");
        skills = ImportUtil.importMapCSV(ImportUtil.filepath + "wodDefaultSkills.csv");
        hp = new int[]{ attributes.get("size") + attributes.get("stamina"),0};
        willpower = new int[]{attributes.get("composure") + attributes.get("resolve"), 0};
    }
    public String skillCheck(String skillName){
        if (skills.containsKey(skillName)){
            return DiceUtil.wodRoll(skills.get(skillName), 8);
        } else {
            return "Skill not found.";
        }
    }
    public String skillCheck(String skillName, String attName){
        if (skills.containsKey(skillName) && attributes.containsKey(attName)){
            int total = skills.get(skillName) + attributes.get(attName);
            return DiceUtil.wodRoll(total);
        } else {
            return "Skill Or Attribute not found.";
        }
    }
    public String attributeCheck(String attName){
        if (attributes.containsKey(attName)){
            return DiceUtil.wodRoll(attributes.get(attName));
        } else {
            return "Attribute not found.";
        }
    }
}
