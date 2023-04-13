package elvis.yield;

import lombok.SneakyThrows;
import lombok.Synchronized;

public class YieldTest2 extends Thread {
    private String threadId;

    public YieldTest2(String threadId) {
        this.threadId = threadId;
    }

    @SneakyThrows
    @Override
    public void run() {
        while (true) {
            synchronized (YieldTest2.class) {
//            Thread.sleep(1000);
                System.out.println(System.currentTimeMillis() + ": thread[" + threadId + "] got CPU");
                Thread.yield();
            }
        }
    }

    @SneakyThrows
    public static void main(String[] args) {
        YieldTest2 t1 = new YieldTest2("t1");
        YieldTest2 t2 = new YieldTest2("t2");
        YieldTest2 t3 = new YieldTest2("t3");
        t1.start();
        t2.start();
        t3.start();

        Thread.sleep(1000);
    }
}
