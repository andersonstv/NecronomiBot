import io.github.andersonstv.util.DiceUtil;
import org.junit.Assert;
import org.junit.Test;

public class DiceRollTest {
    public String[] keywords;
    

    public boolean contains(String[] keys, String result){
        boolean containsKeyword = false;
        for (String keyword : keys) {
            if(result.contains(keyword)){
                containsKeyword = true;

            }
        }
        return containsKeyword;
    }
    @Test
    public void testSimple(){
        keywords = new String[]{"2d12", "**Result:** "};
        String result = DiceUtil.simple("$roll 2d12");
        System.out.println(result);
        Assert.assertTrue(contains(keywords, result));
    }
    @Test
    public void testSimpleWithSum(){
        keywords = new String[]{"2d6", "**Result:** "};
        String result = DiceUtil.simple("$roll 2d6+2");
        Assert.assertTrue(contains(keywords, result));
    }
    @Test
    public void testSimpleMultiroll(){
        keywords = new String[]{"2d6", "1d6", "**Result:** "};
        String result =  DiceUtil.simple("$roll 2d6+1d6");
        Assert.assertTrue(contains(keywords, result));
    }
    @Test
    public void testSimpleMultirollWithSum(){
        keywords = new String[]{"2d6", "3d10", "**Result:** "};
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
    public void testWodRoll(){
        keywords = new String[]{"**Total Successes:** ", "**Successes:** ", "**Failures:** "};
        String result = DiceUtil.wodRoll(3, 8);
        Assert.assertTrue(contains(keywords, result));
    }
    @Test
    public void testCoc(){
        keywords = new String[]{"**Results:** ","Critical", "Extreme", "Hard", "Regular", "Fumble!", "Failure!", " Success!"};
        String result = DiceUtil.coc("$coc 100");

        System.out.println(result);
        Assert.assertTrue(contains(keywords, result));
    }
    @Test
    public void testCocFailure(){
        keywords = new String[]{"**Results:** ","Critical", "Extreme", "Hard", "Regular", "umble!", "ailure!", " Success!"};
        String result = DiceUtil.cocRoll(0);
        System.out.println(result);
        Assert.assertTrue(contains(keywords, result));
    }
    @Test
    public void testCocSuccess(){
        keywords = new String[]{"**Results:** ","Critical", "Extreme", "Hard", "Regular", "Fumble!", "Failure!", " Success!"};
        String result = DiceUtil.cocRoll(100);
        System.out.println(result);
        Assert.assertTrue(contains(keywords, result));
    }
    @Test
    public void testCocExtremeSuccess(){
        keywords = new String[]{"**Results:** ","Critical", "Extreme", "Hard", "Regular", "Fumble!", "Failure!", " Success!"};
        String result = DiceUtil.cocRoll(500);
        Assert.assertTrue(contains(keywords, result));
    }
    @Test
    public void testHardSuccess(){
        keywords = new String[]{"**Results:** ","Critical", "Extreme", "Hard", "Regular", "Fumble!", "Failure!", " Success!"};
        String result = DiceUtil.cocRoll(200);
        System.out.println(result);
        Assert.assertTrue(contains(keywords, result));
    }
}
