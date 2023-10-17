package elvis.apisix;

import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

public class ApigatewayTester {

    private ApigatewayTester() {
        for (int i = 0; i < 100; i++) {
            System.out.println();
        }
    }

    public static String test() {
        return "";
    }


    public static final int thread = 500;
    public static final int count = 100;

    public static void main(String[] args) throws InterruptedException {
        final String url = "http://139.198.21.67:9080/test";
//        final String url = "http://192.168.0.60:9999/test";
//        final String url = "http://192.168.0.6:9080/test1";
//        final String url = "http://139.198.9.241:9080/test";
        ExecutorService pool = Executors.newCachedThreadPool();
        long t0 = System.currentTimeMillis();
        AtomicInteger succ = new AtomicInteger(0);
        for (int i = 0; i < thread; i++) {
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    HttpClient httpClient = new HttpClient();
                    GetMethod getMethod = new GetMethod(url);
                    getMethod.addRequestHeader("Connection", "keep-alive");
                    getMethod.addRequestHeader("apikey", "test_key");
                    try {
                        for (int i = 0; i < count; i++) {
                            if (httpClient.executeMethod(getMethod) == 200)
                                succ.incrementAndGet();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        System.out.println("all threads created successfully");
        while (((ThreadPoolExecutor) pool).getActiveCount() > 0) {
            Thread.sleep(100);
        }
        System.out.println("thruput:" + 1.0 * thread * count * 1000 / (System.currentTimeMillis() - t0));
        System.out.println("total time:" + (System.currentTimeMillis() - t0) * 1.0 / 1000 + "s");
        System.out.println("rate:" + (100.0 * succ.get()) / (thread * count));
    }
}
