import Database.DatabaseAsocijacije;
import Database.IDatabaseAsocijacije;
import Entities.*;
import Models.Association;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Test {

    public static void main(String[] args) {


        IDatabaseAsocijacije db = DatabaseAsocijacije.getConnection();
        db.removeAssociation(6);

    }
}
