package elvis.socket.test;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class SoLingerTest {

    public static void main(String[] args) throws Exception {
        new Thread() {
            public void run() {
                try {
                    server();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
        startClient();
    }


    public static void startClient() throws Exception {

        Socket s = new Socket();
        InetSocketAddress i = new InetSocketAddress(7777);
        try {
            s.connect(i);
            s.setSoLinger(true, 0);
//            s.setSoLinger(true, 1);
//            s.setSoLinger(false, -1);
            Thread.sleep(1000);
            s.getOutputStream().write(new byte[1000000]);
            Thread.sleep(1000);
            System.out.println("closing client");
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void server() throws Exception {
        ServerSocket ss = new ServerSocket(7777, 5);
        Socket s = ss.accept();
        try {
            InputStream in = s.getInputStream();
            BufferedInputStream bi = new BufferedInputStream(in);
            byte[] buf = new byte[1024];
            int read = -1;
            long total = 0;
            while ((read = bi.read(buf)) != -1) {
                System.out.println("读取字节数：" + read);
                total += read;
            }
            System.out.println("共读取字节数为：" + total);
            System.out.println("closing server");
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
