package Database;

import Entities.*;
import Models.Association;
import jakarta.persistence.*;

import java.util.ArrayList;
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
    public FinalAnswer getKonacnoResenje(int max_row) {

        String jpql = "SELECT k_r FROM FinalAnswer k_r WHERE k_r.id = :id_asocijacije";
        TypedQuery<FinalAnswer> query = em.createQuery(jpql, FinalAnswer.class);
        query.setParameter("id_asocijacije",max_row);
        List<FinalAnswer> response = query.getResultList();
        if(!response.isEmpty())
            return response.get(0);
        return null;
    }

    @Override
    public List<Association> getListOfAssociation() {
        List<Association> list_of_associations = new ArrayList<>();

        String jpql = "SELECT fa, a, b, c, d from FinalAnswer fa join Column_A a on fa.id = a.id_konacno_resenje " +
                "join Column_B b on fa.id = b.id_konacno_resenje join Column_C c on fa.id = c.id_konacno_resenje " +
                "join Column_D d on fa.id = d.id_konacno_resenje";
        Query query = em.createQuery(jpql);
        List<Object[]> results = query.getResultList();
        for(Object[] o : results){
            FinalAnswer fa = (FinalAnswer) o[0];
            Column_A ca = (Column_A) o[1];
            Column_B cb = (Column_B) o[2];
            Column_C cc = (Column_C) o[3];
            Column_D cd = (Column_D) o[4];
            Association association = new Association(ca,cb,cc,cd,fa);
            list_of_associations.add(association);
        }
        return list_of_associations;
    }

    @Override
    public void addAssociation(Association association) {
        em.getTransaction().begin();
        em.merge(association.getColumn_a());
        em.merge(association.getColumn_b());
        em.merge(association.getColumn_c());
        em.merge(association.getColumn_d());
        em.merge(association.getFinalAnswer());
        em.getTransaction().commit();
    }
    @Override
    public void removeAssociation(int association_id) {
        em.clear();
        Column_A ca = em.find(Column_A.class, association_id);
        Column_B cb = em.find(Column_B.class, association_id);
        Column_C cc = em.find(Column_C.class, association_id);
        Column_D cd = em.find(Column_D.class, association_id);
        FinalAnswer fa = em.find(FinalAnswer.class, association_id);

        try{
            em.getTransaction().begin();
            em.remove(ca);
            em.remove(cb);
            em.remove(cc);
            em.remove(cd);
            em.remove(fa);
            em.getTransaction().commit();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
