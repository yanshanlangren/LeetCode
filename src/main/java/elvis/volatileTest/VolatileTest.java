package elvis.volatileTest;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * volatile字段有两个特性:
 * 1. 多线程环境的原子操作对内存有可见性
 * 2. 防止指令重排
 */

public class VolatileTest {
    public boolean ready = false;
    public int a = 0;

    public void write() {
        a = 2;
        ready = true;
    }

    public int multiply() {
        while (!ready) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return a;
    }

    public static AtomicInteger count = new AtomicInteger(0);
    public static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        while (true) {
            VolatileTest v = new VolatileTest();
            new Thread() {
                @Override
                public void run() {
                    v.write();
                }
            }.start();
            new Thread() {
                @Override
                public void run() {
                    if (v.multiply() != 2) {
                        System.out.println("False");
                    }
                }
            }.start();
        }

//        long t1 = System.currentTimeMillis();
//        for (int i = 0; i < 10; i++) {
//            new Thread() {
//                @Override
//                public void run() {
//                    try {
//                        Thread.sleep(1);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    for (int j = 0; j < 100; j++)
////                        synchronized (lock) {
//                        count.incrementAndGet();
////                        }
//                }
//            }.start();
//        }
//        Thread.sleep(2000);
//        System.out.println("count=" + count);
//        System.out.println("total time:" + (System.currentTimeMillis() - t1));
    }
}
