package elvis.threadLocal;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class ThreadLocalTest extends Thread{

    ThreadLocal<String> local = new ThreadLocal<>();
    @Override
    public void run(){
        System.out.println("xxx");
    }
    public static void main(String[] args) {
        ThreadLocalTest t = new ThreadLocalTest();
//        ThreadLocal<String> local = new ThreadLocal<>();
//        for (int i = 0; i < 5; i++) {
//            new Thread() {
//                local.set("asd");
//            }.start();
//        }

//        //新建一个ThreadLocal
//        ThreadLocal<String> local = new ThreadLocal<>();
//        //新建一个随机数类
//        Random random = new Random();
//        //使用java8的Stream新建5个线程
//        IntStream.range(0, 5).forEach(a-> new Thread(()-> {
//            //为每一个线程设置相应的local值
//            local.set(a+"  "+random.nextInt(10));
//            System.out.println("线程和local值分别是  "+ local.get());
//            try {
//                TimeUnit.SECONDS.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }).start());


//        IntStream.range(0, 5).forEach(a -> new Thread(() -> {
//            System.out.println(a);
//            try {
//                Thread.sleep(10000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }).start());
    }
}
