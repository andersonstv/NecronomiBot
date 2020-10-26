import io.github.andersonstv.dice.DiceUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DiceRollTest {
    public String[] keywords;
    

    public boolean contains(String[] keys, String result){
        boolean containsKeyword = false;
        for (String keyword : keys) {
            containsKeyword = result.contains(keyword);
        }
        return containsKeyword;
    }
    @Test
    public void testSimpleBasic(){
        keywords = new String[]{"2d12"};
        String result = DiceUtil.simple("2d12");
        Assert.assertTrue(contains(keywords, result));
    }
    @Test
    public void testSimpleWithSum(){
        keywords = new String[]{"2d6"};
        String result = DiceUtil.simple("2d6+2");
        Assert.assertTrue(contains(keywords, result));
    }
    @Test
    public void testSimpleMultiroll(){
        keywords = new String[]{"2d6", "1d6"};
        String result =  DiceUtil.simple("2d6+1d6");
        Assert.assertTrue(contains(keywords, result));
    }
    @Test
    public void testSimpleMultirollWithSum(){
        keywords = new String[]{"2d6", "3d10"};
        String result = DiceUtil.simple("$roll 2d6+5+3d10");
        Assert.assertTrue(contains(keywords, result));
    }
    @Test
    public void testWod(){
        keywords = new String[]{"**Total Successes:** ", "**Successes:** ", "**Failures:** "};
        String result = DiceUtil.wod("$wod 3 8");
        Assert.assertTrue(contains(keywords, result));
    }
    @Test
    public void testCocSuccess(){
        keywords = new String[]{"**Results:** ","Critical", "Extreme", "Hard", "Regular", "Fumble!", "Failure!", " Success!"};
        String result = DiceUtil.coc("$coc 100");

        System.out.println(result);
        Assert.assertTrue(contains(keywords, result));
    }
    @Test
    public void testCoc10sFailure(){
        keywords = new String[]{"**Results:** ","Critical", "Extreme", "Hard", "Regular", "Fumble!", "Failure!", " Success!"};
        String result = DiceUtil.cocRoll10s(0);
        System.out.println(result);
        //Assert.assertTrue(contains(keywords, result));
    }
    @Test
    public void testCoc10sSuccess(){
        keywords = new String[]{"**Results:** ","Critical", "Extreme", "Hard", "Regular", "Fumble!", "Failure!", " Success!"};
        String result = DiceUtil.cocRoll10s(100);
        System.out.println(result);
        //Assert.assertTrue(contains(keywords, result));
    }
}
