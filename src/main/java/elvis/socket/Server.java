package elvis.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.SocketHandler;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(8888);
            System.out.println("启动服务器....");
            while (true) {
                Socket s = ss.accept();
                System.out.println("客户端:" + s.getInetAddress().getLocalHost() + "已连接到服务器");

                ServerHandler socketHandler = new ServerHandler(s);
                BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
                //读取客户端发送来的消息
                String mess = br.readLine();
                while (mess != null && !"".equals(mess)) {
                    System.out.println("客户端：" + mess);
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
                    bw.write("message[" + mess + "] received\n");
                    bw.flush();
                    mess = br.readLine();
                }
//                s.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}