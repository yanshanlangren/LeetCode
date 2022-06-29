package elvis.concurrent;

public class YieldTest {
    private int n;
    volatile boolean s = false;

    public YieldTest(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            while(s);
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            s = true;
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            while(!s);
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            s = false;
        }
    }
}
