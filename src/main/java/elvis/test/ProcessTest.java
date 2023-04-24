package elvis.test;

import java.lang.management.ManagementFactory;

public class ProcessTest {

    public static void main(String[] args) throws InterruptedException {
        System.out.println(ManagementFactory.getRuntimeMXBean().getName());

        Thread.sleep(300000);
    }
}
