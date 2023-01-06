import lombok.Data;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.UnsupportedTemporalTypeException;
import java.util.*;

public class InstantTest {

    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Test
    public void getName() throws UnsupportedEncodingException {
        String x = URLEncoder.encode("因子批量导出_" + DateTimeFormatter.ofPattern("yyyyMMddHHmmss").format(Instant.now()) + ".xlsx", "UTF-8");
        System.out.println(x);
    }

    @Test
    public void date() {
        Instant now = Instant.now();
        System.out.println(DateTimeFormatter.ofPattern("yyyy:MM:dd  HH:mm:ss").format(LocalDateTime.now()));
    }

    @Test
    public void SetNull() {
        Set<Integer> s = new HashSet<>();
        s.add(1);
        s.add(2);
        System.out.println(s.contains(null));
    }

    @Test
    public void Validate() {
        String gas = "";
        boolean gasType = false;
        boolean isUpdate = false;

        System.out.println(validate(isUpdate, gasType, gas));
    }

    private boolean validate(boolean isUpdate, boolean gasType, String gas) {
        if (isUpdate) {
            if (gas == null) return false;
            else return !gasType;
        } else {
            if (gas == null) {
                return true;
            } else return !gasType;
        }
    }


    @Test
    public void LocalTimeTest() {
        System.out.println(LocalDate.now().toString());


        List<MyData> l = new ArrayList<>();
        List<String> ll = new ArrayList<>();

        l.sort((a, b) -> (int) (a.count - b.count));

        String[] s = new String[]{"aa", "bbbb", "c"};
//Arrays.sort(a, (String a, String b) ->(a.length() - b.length())); is also OK
        Arrays.sort(s, (a, b) -> (a.length() - b.length()));
    }


    public class MyData {
        public Long count;
        public String tableName;
    }
}
