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
