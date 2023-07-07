package Database;

import Entities.Accounts;
import Entities.Role;
import Models.ERegistrationStatus;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jdk.jfr.Percentage;

import javax.xml.crypto.Data;
import java.util.List;

public class Database implements IDatabase{

    private static IDatabase db;
    private EntityManagerFactory emf;
    private EntityManager em;

    private Database(){
        try{
            emf = Persistence.createEntityManagerFactory("asocijacije");
            em = emf.createEntityManager();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Object lock = new Object();

    public static IDatabase getConnection(){
        if(db == null){
            synchronized (lock){
                if(db == null){
                    db = new Database();
                }
            }
        }
        return db;
    }

    @Override
    public Accounts login(Accounts account) {
        String jpql = "SELECT a FROM Accounts a WHERE a.username = :username AND a.password = :password";

        TypedQuery<Accounts> query =  em.createQuery(jpql, Accounts.class);
        if(account.getUsername() != null && account.getPassword() != null){
            query.setParameter("username", account.getUsername());
            query.setParameter("password", account.getPassword());

            List<Accounts> response = query.getResultList();
            if(!response.isEmpty()){
                Accounts response_account = response.get(0);
                response_account.setPassword("");
                return response_account;
            }
        }
        return null;
    }

    private boolean existingEmail(String email){
        String jpql = "SELECT a From Accounts a WHERE a.email = :email";
        TypedQuery<Accounts> query = em.createQuery(jpql,Accounts.class);
        query.setParameter("email", email);
        List<Accounts> response = query.getResultList();
        if(!response.isEmpty()){
            return true;
        }
        return false;
    }

    private boolean existingUsername(String username){
        String jpql = "SELECT a FROM Accounts a WHERE a.username = :username";
        TypedQuery<Accounts> query = em.createQuery(jpql,Accounts.class);
        query.setParameter("username", username);
        List<Accounts> response = query.getResultList();
        if(!response.isEmpty()){
            return true;
        }
        return false;
    }

    private int getUserId(String email){
        String jpql = "SELECT a FROM Accounts a where a.email = :email ";
        TypedQuery<Accounts> query = em.createQuery(jpql,Accounts.class);
        query.setParameter("email", email);
        List<Accounts> response = query.getResultList();
        if(!response.isEmpty()){
            Accounts response_account = response.get(0);
            return response_account.getId();
        }
        return -1;
    }

    @Override
    public ERegistrationStatus register(Accounts account) {
        if(existingEmail(account.getEmail()))
            return ERegistrationStatus.Email_is_existing;
        else if (existingUsername(account.getUsername()))
            return ERegistrationStatus.Username_is_existing;
        else {
            Role role = em.find(Role.class,2);
            account.setRole(role);

            EntityManagerFactory emf_temp = Persistence.createEntityManagerFactory("asocijacije");
            EntityManager em_temp = emf_temp.createEntityManager();

            em_temp.getTransaction().begin();
            em_temp.merge(account);
            em_temp.getTransaction().commit();

            em_temp.close();
            emf_temp.close();
            return ERegistrationStatus.Success_registration;
        }
    }
}
