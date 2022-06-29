package elvis.concurrent.deadlock;

/**
 * 死锁
 * 4个必要条件
 * 1.互斥
 * 2.占有且等待
 * 3.不可强占
 * 4.循环等待
 */
public class DeadLock {

    public static void main(String[] args){
        final String a = "a";
        final String b = "b";
        new Thread(){
            @Override
            public void run(){
                synchronized (a){
                    System.out.println("I am thread 1, I got a.");

                    //这里要sleep目的是避免,在线程2启动之前, 线程1已经结束释放掉了a和b的锁.
                    //线程2同
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (b){
                        System.out.println("I am thread 1, I got b.");
                    }
                }
            }
        }.start();

        new Thread(){
            public void run(){
                synchronized(b){
                    System.out.println("I am thread 2, I got b.");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (a){
                        System.out.println("I am thread 2, I got a.");
                    }

                }
            }
        }.start();

    }
}
