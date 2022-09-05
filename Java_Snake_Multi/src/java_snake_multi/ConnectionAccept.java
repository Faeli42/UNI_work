package java_snake_multi;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author mahdal
 */
public class ConnectionAccept extends Thread {

    ServerSocket serverSocket;
    Snake hadovec;
    Socket client;
    int port;
    
    public ConnectionAccept(int port){
        this.port = port;
    }
    
    @Override
    public void run() {
            if (serverSocket != null) {
                try {
                    System.out.println("Waiting for client on port: "+port);
                    client = serverSocket.accept();
                } catch (IOException ex) {
                }
                if (client != null && hadovec != null)
                {
                    hadovec.startServerGame(client);
                }
            }
    }

    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void setHadovec(Snake hadovec) {
        this.hadovec = hadovec;
    }
}