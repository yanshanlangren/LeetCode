package elvis.concurrent.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLock {
    public static void main(String[] args) throws InterruptedException {
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

        lock.writeLock().lock();
        lock.writeLock().lock();

        new Thread(){
            @Override
            public void run(){
                System.out.println("trying to get the lock...");
                lock.writeLock().lock();
                System.out.println("Got write lock, gonna write to the file");
                lock.writeLock().unlock();
            }
        }.start();


        Thread.sleep(200);

        System.out.println("realse one once");
        lock.writeLock().unlock();
        System.out.println("realse one twice");
        lock.writeLock().unlock();
    }
}

