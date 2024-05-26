package Example.Client;

import Example.compute.Task;
import Example.compute.Compute;
import java.math.BigDecimal;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ComputePi {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 12345);
            Compute compute = (Compute) registry.lookup("Compute");
            Task<BigDecimal> task = new Pi();
            BigDecimal result = compute.executeTask(task);
            System.out.println("Result: " + result);
        } catch (Exception e) {
            System.err.println("Client exception:");
            e.printStackTrace();
        }
    }
}

