package client;

import Interfaces.Result;

import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        try {
            String host = "localhost";
            int port = 12345;
            Socket clientSocket = new Socket(host, port);

            ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
            String classFileName = "JobOne.txt";
            out.writeObject(classFileName);
            FileInputStream fis = new FileInputStream(classFileName);
            byte[] classFileBytes = new byte[fis.available()];
            fis.read(classFileBytes);
            out.writeObject(classFileBytes);

            JobOne aJob = new JobOne();
            out.writeObject(aJob);

            ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
            String resultClassFileName = (String) in.readObject();
            byte[] resultFileBytes = (byte[]) in.readObject();
            FileOutputStream fos = new FileOutputStream(resultClassFileName);
            fos.write(resultFileBytes);

            Result r = (Result) in.readObject();

            System.out.println("result = " + r.output() + ", time taken = " + r.scoreTime() + "ns");

            in.close();
            out.close();
            clientSocket.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

