import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class TenToBinary {
    public String tenToB(int a) {
        StringBuilder sb = new StringBuilder();
        while (a > 0) {
            sb.append(a % 2);
            a /= 2;
        }
        return sb.reverse().toString();
    }

    protected void finalize() {
        System.out.println("对象销毁...");
    }

    public static void main(String[] args) {
        TenToBinary t = new TenToBinary();
        System.out.println(t.tenToB(4));
        System.out.println((byte) 4);

        HashMap<String, String> m = new HashMap<>();
        m.put(null, "a");
        System.out.println(m.get(null));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.HOUR, 4);
        System.out.println(sdf.format(c.getTime()));

        ConcurrentHashMap<String, String> chm = new ConcurrentHashMap<>();
        chm.put(null, "123");
        System.out.println(chm.get(null));
    }
}
