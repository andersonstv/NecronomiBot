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
    public void testDiceRollEmpty(){
        String result = diceController.rollExpression("");
        Assert.assertEquals("**Invalid Input:** Dice Expression does not match XdY format.", result);
    }
    @Test
    public void testRollExpressionInvalid(){
        String result = diceController.rollExpression("adb");
        Assert.assertEquals("**Invalid Input:** Dice Expression does not match XdY format.", result);
    }
    @Test
    public void testDiceRollValid(){
        String result = diceController.rollExpression("3d6");
        System.out.println(result);
        result = diceController.rollExpression("3d12");
        System.out.println(result);
        result = diceController.rollExpression("d12");
        System.out.println(result);
        Assert.assertTrue(Math.floor(Math.log(10) + 1) == Math.ceil(Math.log(10)));
        try{
            result = diceController.rollExpression("551d20");
            System.out.println(result);
        } catch (Exception e){
            System.out.println(e.toString());
        }
    }
}
