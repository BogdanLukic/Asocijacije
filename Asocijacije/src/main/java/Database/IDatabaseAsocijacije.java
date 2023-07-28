package Database;

import Entities.*;

public interface IDatabaseAsocijacije {
    int numOfRows();
    Column_A getKolonaA(int id_asocijacije);
    Column_B getKolonaB(int id_asocijacije);
    Column_C getKolonaC(int id_asocijacije);
    Column_D getKolonaD(int id_asocijacije);
    FinalAnswer getKonacnoResenje(int id_asocijacije);

}
