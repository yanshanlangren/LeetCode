package elvis.socket;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentMap;

public class MyTimer {
    public static void main(String[] args) {
        Timer t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println("executing TimeTask");
            }
        }, 1000L, 1000L);
    }
}
