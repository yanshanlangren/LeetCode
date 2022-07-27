package elvis.blockingQueue;

import java.util.concurrent.*;
import java.util.function.Predicate;

public class TestLinkedBlockingQueue extends Thread {
    BlockingQueue<Message> queue = new LinkedBlockingQueue<>();

    @Override
    public void run() {
        while (true) {
            try {
                Message message = queue.take();
                System.out.println("start to deal message[" + message.msg + "]...");
                Thread.sleep(3000);
//                System.out.println("message[" + message.msg + "] dealt success");
//                message.p.test("message[" + message.msg + "] dealt");
                message.c.call();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void feed(Message msg) {
        queue.add(msg);
    }

    public static class Message {
        String msg;
        Predicate<String> p;
        Future<String> f;
        Callable<String> c;

        public Message(String msg, Predicate<String> p) {
            this.msg = msg;
            this.p = p;
        }

        public Message(String msg, Future<String> f) {
            this.msg = msg;
            this.f = f;
        }

        public Message(String msg, Callable<String> c) {
            this.msg = msg;
            this.c = c;
        }
    }


    public static void main(String[] args) {
        TestLinkedBlockingQueue t = new TestLinkedBlockingQueue();
        t.start();
        t.feed(new Message("hello", () -> {
            System.out.println();
            return "";
        }));
//        t.feed(new Message("my name is elvis", x -> {
//            System.out.println(x);
//            return true;
//        }));
//        t.feed(new Message("see you around", x -> {
//            System.out.println(x);
//            return true;
//        }));
    }
}
