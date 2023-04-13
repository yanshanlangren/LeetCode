package elvis.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Tester {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> t = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(3000);
                return "success";
            }
        });
        System.out.println("start...");
        new Thread(t).start();
        System.out.println(t.get());
    }
}
