import io.github.andersonstv.DiceController;
import io.github.andersonstv.DiceController;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert.*;

import java.util.Arrays;

public class DiceRollTest {
    public DiceController diceController;
    @Before
    public void setUp(){
        diceController = new DiceController();
    }
    @Test
    public void testRoll1(){
        Integer[] result = diceController.roll(2, 4);
        System.out.println(Arrays.toString(result));
    }
    @Test
    public void testRoll2(){
        Integer[] result = diceController.roll(10, 100);
        System.out.println(Arrays.toString(result));
    }
    @Test
    public void testDiceRollInvalid(){
        String result = diceController.rollExpression("");
        Assert.assertEquals("**Invalid Input:** Dice Expression does not match XdY format.", result);
    }
    @Test
    public void testDiceRollValid(){
        String result = diceController.rollExpression("3d6");
        result = diceController.rollExpression("3d12");
        result = diceController.rollExpression("d12");
    }
}
