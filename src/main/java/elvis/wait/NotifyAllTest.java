package elvis.wait;

/**
 * notifyAll 一次唤醒所有对象
 */
public class NotifyAllTest extends Thread {
    public NotifyAllTest(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(this.getName() + ": " + i);
            if (i == 50) {
                this.waitSelf();
            }
        }
    }

    private void waitSelf() {
        try {
            synchronized (NotifyAllTest.class) {
                System.out.println(this.getName() + " start to wait");
                NotifyAllTest.class.wait();
//                this.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void notifySelf() {
        synchronized (NotifyAllTest.class) {
            System.out.println(this.getName() + " is notified");
            NotifyAllTest.class.notifyAll();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        NotifyAllTest n1 = new NotifyAllTest("钟煜");
        NotifyAllTest n2 = new NotifyAllTest("程平");
        n1.start();
        n2.start();
        Thread.sleep(1000);
//        synchronized (NotifyAllTest.class) {
//            NotifyAllTest.class.notifyAll();
//        }
        Thread.sleep(1000);
        n1.notifySelf();
//        Thread.sleep(1000);
//        n2.notifySelf();
    }
}
