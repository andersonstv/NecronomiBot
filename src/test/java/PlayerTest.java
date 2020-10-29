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

import io.github.andersonstv.character.Player;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PlayerTest {
    public Player testPlayer;
    public Player testPlayer2;

    @Before
    public void setUp(){
        testPlayer = new Player("Usuariana");
        testPlayer2 = new Player("Usuariano");
    }

    @Test
    public void testGetCurrent(){
        Assert.assertNull(testPlayer.getCurrent());
    }
    @Test
    public void testSetCurrent(){
        testPlayer.createCocCharacter("JohnDoe");
        Assert.assertNull(testPlayer.getCurrent());
        testPlayer.setCurrent("JohnDoe");
        Assert.assertTrue(testPlayer.getCurrent().getId().equals("JohnDoe"));
    }
    @Test
    public void testCreateWoDCharacter(){
        testPlayer.createWodCharacter("JohnDoe");
        Assert.assertTrue(testPlayer.getCharacterMap().containsKey("JohnDoe"));
    }
    @Test
    public void testCreateCoCCharacter(){
        testPlayer.createCocCharacter("JohnDoe");
        Assert.assertTrue(testPlayer.getCharacterMap().containsKey("JohnDoe"));
    }
    @Test
    public void testCheckNoCurrent(){
        Assert.assertEquals(testPlayer.check("Intelligence"), "No active character");
    }
    @Test
    public void testCheckCoC(){
        testPlayer.createCocCharacter("JohnDoe");
        testPlayer.setCurrent("JohnDoe");
        Assert.assertTrue(testPlayer.check("int").contains("**Results:** "));
    }
}
