package elvis.socket;

import java.net.Socket;

public class ServerHandler extends Thread {
    private Socket socket;

    public ServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
//        socket
    }
}
