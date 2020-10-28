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

import io.github.andersonstv.character.WoDCharacter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class WodCharacterTest {
    public WoDCharacter character;
    @Before
    public void setUp(){
        character = new WoDCharacter("Test");
    }
    @Test
    public void testDefault(){
        Assert.assertEquals(1, (int) character.getAttribute("intelligence"));
    }
    @Test
    public void testSkillCheck(){
        String check = character.check("medicine");
        Assert.assertTrue(check.contains("**Total Successes:** "));
    }
    @Test
    public void testSkillAttCheck(){
        String check = character.skillCheck("medicine", "intelligence");
        System.out.println();
        Assert.assertTrue(check.contains("**Total Successes:** "));
    }
    @Test
    public void testAttributeCheck(){
        String check = character.check("intelligence");
        Assert.assertTrue(check.contains("**Total Successes:** "));
    }
}
