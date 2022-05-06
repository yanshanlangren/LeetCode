package elvis.failfast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class FailFastTest {

    public static void main(String[] args) {
        HashMap<Integer, Integer> map = new HashMap();
        for (int i = 10; i < 100; i++) {
            map.put(i, i);
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(i);
        }
        Iterator<Integer> it = map.keySet().iterator();
        int temp = 0;
        while (it.hasNext()) {
            if (temp == 3) {
                temp++;
                map.remove(13);
            } else {
                temp++;
                System.out.println(it.next());
            }
        }
    }
}
