package elvis.concurrent.ZeroEvenOdd;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

public class ZeroEvenOdd {
    private final int n;
    private final Semaphore sz = new Semaphore(1);
    private final Semaphore se = new Semaphore(0);
    private final Semaphore so = new Semaphore(0);
    private volatile int c = 0;

    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        while (c < n) {
            sz.acquire();
            c++;
            // if(c<n)
            printNumber.accept(0);
            if ((c & 1) == 1) so.release();
            else se.release();
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        while (c < n) {
            se.acquire();
            printNumber.accept(c);
            sz.release();
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        while (c < n) {
            so.acquire();
            printNumber.accept(c);
            sz.release();
        }
    }

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(3);
//        service.execute(() -> {
//            while (c < n) {
//                so.acquire();
//                printNumber.accept(c);
//                sz.release();
//            }
//        });
//        service.shutdownNow();
    }
}
