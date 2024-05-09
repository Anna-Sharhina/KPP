package server;

import Interfaces.Executable;

import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try {
            int port = 12345;
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server started...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket);

                // Handling client request in a separate thread
                Thread clientHandler = new Thread(new ClientHandler(clientSocket));
                clientHandler.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class ClientHandler implements Runnable {
        private final Socket clientSocket;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try {
                // Receive class file name
                ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
                String classFileName = (String) in.readObject();
                classFileName = classFileName.replaceFirst("client", "server");

                // Receive and save class file
                byte[] classFileBytes = (byte[]) in.readObject();
                FileOutputStream fos = new FileOutputStream(classFileName);
                fos.write(classFileBytes);

                // Receive and execute job
                Executable job = (Executable) in.readObject();
                double startTime = System.nanoTime();
                Object result = job.execute();
                double endTime = System.nanoTime();
                double completionTime = endTime - startTime;

                // Send result back to client
                ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
                out.writeObject(classFileName);
                FileInputStream fis = new FileInputStream(classFileName);
                byte[] resultFileBytes = new byte[fis.available()];
                fis.read(resultFileBytes);
                out.writeObject(resultFileBytes);
                out.writeObject(new ResultImpl(result, completionTime));

                // Close streams and socket
                in.close();
                out.close();
                clientSocket.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}

