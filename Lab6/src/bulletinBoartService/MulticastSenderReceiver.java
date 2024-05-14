package bulletinBoartService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Scanner;

public class MulticastSenderReceiver {
    private String name;
    private InetAddress addr;
    private int port = 12345;
    private MulticastSocket group;
    public MulticastSenderReceiver(String name) {
        this.name = name;
        try {
            addr = InetAddress.getByName("224.0.0.2");
            group = new MulticastSocket(port);
            new Receiver().start();
            new Sender().start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private class Sender extends Thread {
        public void run() {
            try {
                System.out.println("Enter the message:");
                BufferedReader fromUser = new BufferedReader(new InputStreamReader(System.in));
                while (true) {
                    String msg = name + ":" + fromUser.readLine();
                    byte[] out = msg.getBytes();
                    DatagramPacket pkt = new DatagramPacket(out, out.length, addr, port);
                    group.send(pkt);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    private class Receiver extends Thread {
        public void run() {
            try {
                byte[] in = new byte[256];
                DatagramPacket pkt = new DatagramPacket(in, in.length);
                group.joinGroup(addr);
                while (true) {
                    group.receive(pkt);
                    System.out.println(new String(pkt.getData(), 0,
                            pkt.getLength()));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        System.out.println("Enter the name:");
        Scanner input = new Scanner(System.in);
        new MulticastSenderReceiver(input.nextLine());
    }
}


