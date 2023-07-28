package RMI;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class StartRMI {
    public static void main(String[] args) {
        try{
            Engine engine = new Engine();
            LocateRegistry.createRegistry(5353);
            Naming.rebind("//localhost:5353/engine", engine);
            System.out.println("RMI - Engine za asocijacije startovan!");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
