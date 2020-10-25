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

}
