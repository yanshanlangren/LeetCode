package elvis.wait;

/**
 * wait 和 notify都需要对调用的对象加锁, 否则会报错
 * 如果使用非静态的 syncrhonized 字段修饰方法, 则方法对该对象(this)加锁
 */

public class WaitTest extends Thread {
    public WaitTest(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(this.getName() + ": " + i);
            if (i == 30) {
                this.waitSelf();
            }
        }
    }

    public synchronized void waitSelf() {
        try {
            this.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void notifySelf() {
        this.notify();
    }

    public static void main(String[] args) throws InterruptedException {
        WaitTest wt1 = new WaitTest("钟煜");
        wt1.start();
        Thread.sleep(1000);
        wt1.notifySelf();
    }
}
