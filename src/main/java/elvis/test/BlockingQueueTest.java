package elvis.test;

import org.junit.Test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BlockingQueueTest {
    @Test
    public void blockingQueue() throws InterruptedException {
        BlockingQueue<Integer> q = new LinkedBlockingQueue<>();
        Consumer c = new Consumer(q);
        Producer p = new Producer(q);
        c.start();
        p.start();
        Thread.sleep(10 * 1000);
    }

    public class Consumer extends Thread {
        private BlockingQueue<Integer> q;

        public Consumer(BlockingQueue<Integer> q) {
            this.q = q;
        }

        @Override
        public void run() {
            System.out.println("start to taking value from queue...");
            try {
                Integer i = q.take();
                System.out.println("integer taken:" + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public class Producer extends Thread {
        private BlockingQueue<Integer> q;

        public Producer(BlockingQueue<Integer> q) {
            this.q = q;
        }

        @Override
        public void run() {
            System.out.println("Provider started...");
            try {
                Thread.sleep(3000);
                q.add(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
