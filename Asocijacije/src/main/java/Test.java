import Database.DatabaseAsocijacije;
import Database.IDatabaseAsocijacije;
import Entities.*;
import Models.Association;
import Services.Timer;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Test {

    public static void main(String[] args) {

        Timer t = new Timer();
        t.startTimer();

    }
}
