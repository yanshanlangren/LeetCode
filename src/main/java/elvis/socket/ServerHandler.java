package elvis.socket;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class ServerHandler extends Thread {
    private Socket socket;
    private SocketAddress host;

    public ServerHandler(Socket socket, SocketAddress host) {
        this.socket = socket;
        this.host = host;
    }

    @Override
    public void run() {
        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String mess = br.readLine();
            while (mess != null && !"".equals(mess)) {
                System.out.println("客户端[" + host + "]：" + mess);
                bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                bw.write("message[" + mess + "] received\n");
                bw.flush();
                mess = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) bw.close();
                if (br != null) br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("客户端[" + host + "]断开连接");
        }
    }
}
