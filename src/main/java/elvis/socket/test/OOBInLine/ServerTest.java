package elvis.socket.test.OOBInLine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTest {

    public static ServerSocket serverSocket;
    public static Socket socket;

    public static void main(String[] args) throws IOException {

        try {
            serverSocket = new ServerSocket(1234);
        } catch (IOException e1) {
            System.out.println("serverSocket is fail");
            return;
        }

        System.out.println("服务器已经启动，端口号：1234");

        while (true) {
            try {
                socket = serverSocket.accept();
                socket.setOOBInline(true);
                InputStream in = socket.getInputStream();
                InputStreamReader inReader = new InputStreamReader(in);
                BufferedReader bReader = new BufferedReader(inReader);
                String result;
                while ((result = bReader.readLine()) != null) {
                    System.out.println(result);
                }
//        char [] cha = new char[1024];
//        int len = inReader.read(cha);
//        System.out.println(new String(cha,0,len));
                socket.close();
            } catch (Exception e) {
                System.out.println("read data fail");
            } finally {
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        System.out.println("socket close fail");
                    }
                }
            }
        }
    }
}

