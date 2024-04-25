package tcpWork;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MetroServer extends Thread{
    MetroCardBank bank;
    private ServerSocket servSock = null;
    private final int serverPort;

    public MetroServer(int port) {
        this.bank = new MetroCardBank();
        this.serverPort = port;
    }

    @Override
    public void run() {
        try {
            this.servSock = new ServerSocket(serverPort);
            System.out.println("Metro Server started");
            while (true) {
                System.out.println("New Client Waiting...");
                Socket sock = servSock.accept();
                System.out.println("New client: " + sock);
                ClientHandler ch = new ClientHandler(this.getBank(), sock);
                ch.start();
            }
        } catch (IOException e) {
            System.out.println("Error: " + e);
        } finally {
            try {
                servSock.close();
                System.out.println("Metro Server stopped");
            } catch (IOException ex) {
                System.out.println("Error: " + ex);
            }
        }
    }

    private MetroCardBank getBank() {
        return bank;
    }

    public static void main(String[] args) {
        MetroServer srv = new MetroServer(7891);
        srv.start();
    }
}
