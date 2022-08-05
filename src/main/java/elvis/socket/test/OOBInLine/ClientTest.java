package elvis.socket.test.OOBInLine;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ClientTest {

    public static Socket socket;
    public static final String LocalHOST = "127.0.0.1";
    public static final int PORT = 1234;

    public static void main(String[] args) {
        Client(LocalHOST, PORT);
    }

    public static void Client(String address, int port) {
        try {
            socket = new Socket(address, port);
        } catch (Exception e) {
            System.out.println("connection reset");
            return;
        }
        if (socket != null && socket.isConnected()) {
            try {
//                socket.setOOBInline(true);
                OutputStream out = socket.getOutputStream();
                OutputStreamWriter outWriter = new OutputStreamWriter(out);
                outWriter.write(67); // 向服务器发送字符"C"
                outWriter.write("hello world\r\n");
                socket.sendUrgentData(65); // 向服务器发送字符"A"
                socket.sendUrgentData(322); // 向服务器发送字符"B"
//                outWriter.flush();
                socket.sendUrgentData(214); // 向服务器发送汉字”中”
                socket.sendUrgentData(208);
                socket.sendUrgentData(185); // 向服务器发送汉字”国”
                socket.sendUrgentData(250);
                socket.close();
            } catch (Exception e) {
                System.out.println("has throw exception");
                e.printStackTrace();
            } finally {
                try {
                    if (socket != null) {
                        socket.close();
                    }
                } catch (IOException e) {
                    System.out.println("socket close fail");
                }
            }
        } else {
            System.out.println("socket is null or socket connect fail");
        }
    }
}