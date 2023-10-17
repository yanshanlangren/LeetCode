import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class VectorTest {

    public static class _implements{

    }

    public static void main(String[] args) {
        _implements x = new _implements();

        List<Integer> v = new Vector<>();
        List<Integer> l = new ArrayList<>();
        int num = 5000000;
        long t0 = System.currentTimeMillis();
        for (int i = 0; i < num; i++) {
            v.add(i);
        }
        long t1 = System.currentTimeMillis();
        System.out.println((t1 - t0) + "ms");
        for (int i = 0; i < num; i++) {
            l.add(i);
        }
        long t2 = System.currentTimeMillis();
        System.out.println((t2 - t1) + "ms");
        int sum = 0;
        for (int i = 0; i < num; i++) {
            sum += l.get(i);
        }
        long t3 = System.currentTimeMillis();
        System.out.println((t3 - t2) + "ms");
        for (int i = 0; i < num; i++) {
            sum += v.get(i);
        }
        long t4 = System.currentTimeMillis();
        System.out.println((t4 - t3) + "ms");
        System.out.println(sum);
    }
}
