package Database;

import Entities.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class DatabaseAsocijacije implements IDatabaseAsocijacije{
    private static IDatabaseAsocijacije db;
    private EntityManagerFactory emf;
    private EntityManager em;

    private DatabaseAsocijacije(){
        try{
            emf = Persistence.createEntityManagerFactory("asocijacije_rmi");
            em = emf.createEntityManager();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Object lock = new Object();

    public static IDatabaseAsocijacije getConnection(){
        if(db == null){
            synchronized (lock){
                if(db == null){
                    db = new DatabaseAsocijacije();
                }
            }
        }
        return db;
    }

    @Override
    public int numOfRows() {
        String jpql = "SELECT MAX(kr.id) from FinalAnswer kr";
        TypedQuery<Integer> query = em.createQuery(jpql,Integer.class);
        List<Integer> response = query.getResultList();
        return response.get(0);
    }

    @Override
    public Column_A getKolonaA(int id_asocijacije) {
        String jpql = "SELECT k_a FROM Column_A k_a WHERE k_a.id_konacno_resenje = :id_asocijacije";
        TypedQuery<Column_A> query = em.createQuery(jpql, Column_A.class);
        query.setParameter("id_asocijacije",id_asocijacije);
        List<Column_A> response = query.getResultList();
        if(!response.isEmpty())
            return response.get(0);
        return null;
    }

    @Override
    public Column_B getKolonaB(int id_asocijacije) {
        String jpql = "SELECT k_b FROM Column_B k_b WHERE k_b.id_konacno_resenje = :id_asocijacije";
        TypedQuery<Column_B> query = em.createQuery(jpql, Column_B.class);
        query.setParameter("id_asocijacije",id_asocijacije);
        List<Column_B> response = query.getResultList();
        if(!response.isEmpty())
            return response.get(0);
        return null;
    }

    @Override
    public Column_C getKolonaC(int id_asocijacije) {
        String jpql = "SELECT k_c FROM Column_C k_c WHERE k_c.id_konacno_resenje = :id_asocijacije";
        TypedQuery<Column_C> query = em.createQuery(jpql, Column_C.class);
        query.setParameter("id_asocijacije",id_asocijacije);
        List<Column_C> response = query.getResultList();
        if(!response.isEmpty())
            return response.get(0);
        return null;
    }

    @Override
    public Column_D getKolonaD(int id_asocijacije) {
        String jpql = "SELECT k_d FROM Column_D k_d WHERE k_d.id_konacno_resenje = :id_asocijacije";
        TypedQuery<Column_D> query = em.createQuery(jpql, Column_D.class);
        query.setParameter("id_asocijacije",id_asocijacije);
        List<Column_D> response = query.getResultList();
        if(!response.isEmpty())
            return response.get(0);
        return null;
    }

    @Override
    public FinalAnswer getKonacnoResenje(int id_asocijacije) {
        String jpql = "SELECT k_r FROM FinalAnswer k_r WHERE k_r.id = :id_asocijacije";
        TypedQuery<FinalAnswer> query = em.createQuery(jpql, FinalAnswer.class);
        query.setParameter("id_asocijacije",id_asocijacije);
        List<FinalAnswer> response = query.getResultList();
        if(!response.isEmpty())
            return response.get(0);
        return null;
    }
}
