package tcpWork;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private Socket socket;
    private ObjectInputStream is;
    private ObjectOutputStream os;
    private static final Scanner input = new Scanner(System.in);
    private static final Scanner strInput = new Scanner(System.in);

    public Client(String server, int port) {
        try {
            socket = new Socket();
            socket.connect(new InetSocketAddress(server, port), 1000);
            os = new ObjectOutputStream(socket.getOutputStream());
            is = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }

    public void finish() {
        try {
            os.writeObject(new StopOperation());
            os.flush();
            System.out.println(is.readObject());
            socket.close();
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("Error: " + ex);
        }
    }

    public void applyOperation(CardOperation op) {
        try {
            os.writeObject(op);
            os.flush();
            System.out.println(is.readObject());
        } catch (IOException ex) {
            System.out.println("Error:" + ex);
        } catch (ClassNotFoundException ex) {
            System.out.println("Error: " + ex);
        }
    }

    public static void main(String[] args){
        int choice;
        Client cl = new Client("localhost", 7891);
        int serNum, money;
        do {
            System.out.println("==== Bank Menu ====");
            System.out.println("1. Add Metro Card");
            System.out.println("2. Add Money");
            System.out.println("3. Pay Money");
            System.out.println("4. Remove Metro Card");
            System.out.println("5. Show Balance");
            System.out.println("0. Exit");
            choice = input.nextInt();
            switch (choice) {
                case 1: {
                    AddMetroCardOperation op = new AddMetroCardOperation();
                    System.out.println("Enter serial number card: ");
                    serNum = input.nextInt();
                    System.out.println("Enter holder name: ");
                    String name = strInput.nextLine();
                    System.out.println("Enter holder surname: ");
                    String surname = strInput.nextLine();
                    System.out.println("Enter holder sex: ");
                    String sex = strInput.nextLine();
                    System.out.println("Enter holder college: ");
                    String college = strInput.nextLine();
                    System.out.println("Enter holder birthday example(28/04/2023): ");
                    String dateString = strInput.nextLine();
                    op.getCrd().setUsr(new User(name, surname, sex, dateString));
                    op.getCrd().setSerNum(Integer.toString(serNum));
                    op.getCrd().setCollege(college);
                    op.getCrd().setBalance(0);
                    cl.applyOperation(op);
                    break;
                }
                case 2: {
                    System.out.println("Enter serial number card: ");
                    serNum = input.nextInt();
                    System.out.println("Enter money to add: ");
                    money = input.nextInt();
                    cl.applyOperation(new AddMoneyOperation(Integer.toString(serNum), money));
                    break;
                }
                case 3: {
                    System.out.println("Enter serial number card: ");
                    serNum = input.nextInt();
                    System.out.println("Enter money to pay: ");
                    money = input.nextInt();
                    cl.applyOperation(new PayMoneyOperation(Integer.toString(serNum), money));
                    break;
                }
                case 4: {
                    System.out.println("Enter serial number card: ");
                    serNum = input.nextInt();
                    cl.applyOperation(new RemoveCardOperation(Integer.toString(serNum)));
                    break;
                }
                case 5: {
                    System.out.println("Enter serial number card: ");
                    serNum = input.nextInt();
                    cl.applyOperation(new ShowBalanceOperation(Integer.toString(serNum)));
                    break;
                }
            }
        } while (choice != 0);
        cl.finish();
    }
}
