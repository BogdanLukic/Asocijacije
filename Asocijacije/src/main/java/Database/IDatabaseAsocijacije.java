package Database;

import Entities.*;
import Models.Association;

import java.util.List;

public interface IDatabaseAsocijacije {
    int numOfRows();
    Column_A getKolonaA(int id_asocijacije);
    Column_B getKolonaB(int id_asocijacije);
    Column_C getKolonaC(int id_asocijacije);
    Column_D getKolonaD(int id_asocijacije);
    FinalAnswer getKonacnoResenje(int max_row);
    List<Association> getListOfAssociation();
    void addAssociation (Association association);
    void removeAssociation(int association_id);
}
