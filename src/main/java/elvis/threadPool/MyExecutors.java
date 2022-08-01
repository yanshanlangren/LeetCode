package elvis.threadPool;

import java.util.concurrent.*;

public class MyExecutors {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //创建固定大小的线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        //创建只有一个线程的线程池
        ExecutorService executorService1 = Executors.newSingleThreadExecutor();
        //创建一个不限线程数上限的线程池，任何提交的任务都将立即执行
        ExecutorService executorService2 = Executors.newCachedThreadPool();
        /**
         * 小程序使用这些快捷方法没什么问题，
         * 对于服务端需要长期运行的程序，
         * 创建线程池应该直接使用ThreadPoolExecutor的构造方法。
         * 没错，上述Executors方法创建的线程池就是ThreadPoolExecutor。
         * Executors中创建线程池的快捷方法，
         * 实际上是调用了ThreadPoolExecutor的构造方法（定时任务使用的是ScheduledThreadPoolExecutor），
         * 该类构造方法参数列表如下：
         *   int corePoolSize, // 线程池长期维持的线程数，即使线程处于Idle状态，也不会回收。
         *   int maximumPoolSize, // 线程数的上限
         *   long keepAliveTime, TimeUnit unit, // 超过corePoolSize的线程的idle时长，
         *                                      // 超过这个时间，多余的线程会被回收。
         *   BlockingQueue<Runnable> workQueue, // 任务的排队队列
         *   ThreadFactory threadFactory, // 新线程的产生方式
         *   RejectedExecutionHandler handler) // 拒绝策略
         */
        ThreadPoolExecutor tpe = new ThreadPoolExecutor(3, 5, 3600L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                System.out.println("creating new Thread");
                return new Thread(r);
            }
        }, new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                System.out.println("rejecting Thread");
            }
        });
        /**
         * 提交方式
         * Future<T> submit(Callable<T> task)   关心返回结果
         * void execute(Runnable command)       不关心返回结果
         * Future<?> submit(Runnable task)      不关心返回结果，虽然返回Future，但是其get()方法总是返回null
         */
        //关心返回结果
        Future<Object> f = tpe.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                System.out.println("calling anonymous inner Callable");
                return "finished";
            }
        });
        System.out.println(f.get());
        //不关心返回结果
        tpe.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("void execute(Runnable command) executed");
            }
        });
        //不关心返回结果，虽然返回Future，但是其get()方法总是返回null
        tpe.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("calling anonymous inner Runnable");
            }
        });
    }
}
