package elvis.blockingQueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class TestLinkedBlockingQueue extends Thread {
    BlockingQueue<String> queue = new LinkedBlockingQueue<>();

    @Override
    public void run() {
        while (true) {
            try {
                String msg = queue.take();
                System.out.println("start to deal message[" + msg + "]");
                Thread.sleep(3000);
                System.out.println("message[" + msg + "] dealt success");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void feed(String msg) {
        queue.add(msg);
    }


    public static void main(String[] args) {
        TestLinkedBlockingQueue t = new TestLinkedBlockingQueue();
        t.start();
        t.feed("hello");
        t.feed("my name is elvis");
        t.feed("see you around");
    }
}
