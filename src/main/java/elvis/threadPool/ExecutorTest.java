package elvis.threadPool;

import java.util.concurrent.*;

public class ExecutorTest {

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor tpe = new ThreadPoolExecutor(3, 5, 1L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(5), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                System.out.println("creating new Thread");
                return new Thread(r);
            }
        }, new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                System.out.println("rejecting Thread");
            }
        });

        for (int i = 0; i < 20; i++) {
            int finalI = i;
            tpe.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("executing new thread[" + finalI + "]...");
                    try {
                        Thread.sleep(3 * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("finished executing thread[" + finalI + "]");
                }
            });
        }
        while (true) {
            System.out.println("queue[" + tpe.getQueue().size() + "]");
            System.out.println("activeCount[" + tpe.getActiveCount() + "]");
            Thread.sleep(1000);
        }
    }
}
