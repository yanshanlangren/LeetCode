import org.junit.Test;

public class SigmodTest {

    @Test
    public void sigmodTest() {
        double a = -1.0;
        double b = 1.0 / (1 + Math.pow(Math.E, -a));
        System.out.println(Math.round(b));
    }
}
