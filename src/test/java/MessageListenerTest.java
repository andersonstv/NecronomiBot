import io.github.andersonstv.DiceController;
import io.github.andersonstv.MessageListener;
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
    @Test
    public void test01(){
        System.out.println(messageListener.respondDiceRoll("2d6","simple"));
        System.out.println(messageListener.respondDiceRoll("2d12","simple"));
        System.out.println(messageListener.respondDiceRoll("2d6+1d6","simple"));
        System.out.println(messageListener.respondDiceRoll("2d6+2","simple"));
        System.out.println(messageListener.respondDiceRoll("1d6+6","simple"));
        System.out.println(messageListener.respondDiceRoll("$roll 2d6+5+3d10","simple"));
    }
    @Test
    public void testWod(){
        keywords = new String[]{"**Total Successes:** ", "**Successes:** ", "**Failures:** "};
        String result = messageListener.respondDiceRoll("$wod 3d10", "wod");
        boolean containsKeyword = false;
        for (String keyword : keywords) {
            containsKeyword = result.contains(keyword);
        }
        Assert.assertTrue(containsKeyword);
    }
    @Test
    public void testCoc(){
        keywords = new String[]{"Critical", "Extreme", "Hard", "Regular", "Fumble!", "Failure!"};
        boolean containsKeyword = false;
        String result = messageListener.respondDiceRoll("$coc 50", "coc");
        for (String keyword : keywords) {
            containsKeyword = result.contains(keyword);
        }
        Assert.assertTrue(containsKeyword);
    }
}
