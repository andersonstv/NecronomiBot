import io.github.andersonstv.listener.MessageListener;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;



public class MessageListenerTest {
    public MessageListener messageListener;
    public String[] keywords;
    @Before
    public void setUp(){
        messageListener = new MessageListener();
    }
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
        String result = messageListener.respondDiceRoll("2d12","simple");
        Assert.assertTrue(contains(keywords, result));
    }
    @Test
    public void testSimpleWithSum(){
        keywords = new String[]{"2d6"};
        String result = messageListener.respondDiceRoll("2d6+2","simple");
        Assert.assertTrue(contains(keywords, result));
    }
    @Test
    public void testSimpleMultiroll(){
        keywords = new String[]{"2d6", "1d6"};
        String result =  messageListener.respondDiceRoll("2d6+1d6","simple");
        Assert.assertTrue(contains(keywords, result));
    }
    @Test
    public void testSimpleMultirollWithSum(){
        keywords = new String[]{"2d6", "3d10"};
        String result = messageListener.respondDiceRoll("$roll 2d6+5+3d10","simple");
        Assert.assertTrue(contains(keywords, result));
    }
    @Test
    public void testWod(){
        keywords = new String[]{"**Total Successes:** ", "**Successes:** ", "**Failures:** "};
        String result = messageListener.respondDiceRoll("$wod 3 8", "wod");
        Assert.assertTrue(contains(keywords, result));
    }
    @Test
    public void testCocSuccess(){
        keywords = new String[]{"**Results:** ","Critical", "Extreme", "Hard", "Regular", "Fumble!", "Failure!", " Success!"};
        String result = messageListener.respondDiceRoll("$coc 100", "coc");

        System.out.println(result);
        Assert.assertTrue(contains(keywords, result));
    }
}
