package elvis.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;
import java.util.logging.SocketHandler;

public class Server {
    public static void main(String[] args) {
        try {
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
            ServerSocket ss = new ServerSocket(8888);
            System.out.println("启动服务器....");
            while (true) {
                Socket s = ss.accept();
                System.out.println("客户端:" + s.getRemoteSocketAddress() + "已连接到服务器");
                ServerHandler socketHandler = new ServerHandler(s, s.getRemoteSocketAddress());
                tpe.submit(socketHandler);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}