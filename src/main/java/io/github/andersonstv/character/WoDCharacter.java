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

import io.github.andersonstv.character.GenericCharacter;

public class WoDCharacter extends GenericCharacter {

    public WoDCharacter(String charName, String userID) {
        super(charName, userID);
        id += "WoD";
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
