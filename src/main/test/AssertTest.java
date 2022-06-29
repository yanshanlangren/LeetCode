import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
public class AssertTest {
    @Test
    public void assetTest(){

        Assert.isTrue(true, "yes");
        Assert.isTrue(false, "hello");
    }

}
