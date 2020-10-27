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

import io.github.andersonstv.character.CoCCharacter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CocCharacterTest {
    public CoCCharacter character;
    @Before
    public void setUp(){
        character = new CoCCharacter("Test");
    }
    @Test
    public void testDefault(){
        Assert.assertEquals(0, (int) character.getAttribute("str"));
    }
    @Test
    public void testSkillCheck(){
        String check = character.skillCheck("charm");
        Assert.assertTrue(check.contains("**Results:** "));
    }

    @Test
    public void testAttributeCheck(){
        String check = character.attributeCheck("dex");
        Assert.assertTrue(check.contains("**Results:** "));
    }
}
