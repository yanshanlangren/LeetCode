package elvis.concurrent.lock;

/**
 * 读锁不能升级为写锁(读写锁已经读锁, 不能再写锁)
 */

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class UpgradeLock {
    public static void main(String[] args){
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        lock.readLock().lock();
        System.out.println("got read lock.");
        lock.writeLock().lock();
        System.out.println("got write lock.");
    }
}
