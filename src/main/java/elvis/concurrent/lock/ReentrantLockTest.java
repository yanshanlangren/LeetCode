package elvis.concurrent.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入是线程独占的锁, 可以重复加锁, 需要对应次数的解锁才可以完全释放掉锁
 */

public class ReentrantLockTest {

    public static void main(String[] args) throws InterruptedException {

        ReentrantLock lock = new ReentrantLock();

        new Thread() {
            @Override
            public void run() {
                lock.lock();
                System.out.println("I get the lock");
                lock.unlock();
            }
        }.start();
        Thread.sleep(10);

        for (int i = 1; i <= 3; i++) {
            lock.lock();
            System.out.println("locked: " + i);
            Thread.sleep(1000);
        }


        for (int i = 1; i <= 3; i++) {
            try {

                Thread.sleep(1000);

            } finally {
                lock.unlock();
                System.out.println("unlocked: " + i);
            }
        }
    }
}
