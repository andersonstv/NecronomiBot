import io.github.andersonstv.DiceController;
import org.junit.Before;
import org.junit.Test;

public class DiceRollTest {
    public DiceController diceController;
    @Before
    public void setUp(){
        diceController = new DiceController();
    }
    @Test
    public void testDice(){
        String testString = "2d20+2d10+2";
        System.out.println(diceController.simpleRoll(testString));
    }
}
