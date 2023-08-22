package Database;

import Entities.*;
import Models.Association;
import Models.ERegistrationStatus;
import Models.EndGame;
import Models.GameStatus;
import jakarta.persistence.*;

import java.util.ArrayList;
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
//                response_account.setPassword("");
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

            String jpql = "SELECT a FROM Accounts a where a.username = :username ";
            TypedQuery<Accounts> query = em_temp.createQuery(jpql,Accounts.class);
            query.setParameter("username", account.getUsername());
            List<Accounts> response = query.getResultList();
            if(!response.isEmpty()){
                account.setId(response.get(0).getId());
            }

            Score score = new Score();
            score.setScore(0);
            score.setAccount_id(account.getId());

            em_temp.getTransaction().begin();
            em_temp.merge(score);
            em_temp.getTransaction().commit();

            em_temp.close();
            emf_temp.close();

            return ERegistrationStatus.Success_registration;
        }
    }

    @Override
    public Score getScore(Accounts account) {
        String jpql = "SELECT s FROM Score s where s.account_id = :account_id ";
        TypedQuery<Score> query = em.createQuery(jpql,Score.class)
                .setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS)
                .setHint("javax.persistence.cache.storeMode", CacheStoreMode.REFRESH);
        query.setParameter("account_id", account.getId());
        List<Score> response = query.getResultList();
        if(!response.isEmpty()){
            Score s = response.get(0);
            em.refresh(s);
            return s;
        }
        return null;
    }

    @Override
    public void setPoints(GameStatus gameStatus, EndGame points_challanger, EndGame points_enemy) {
        String jpql = "SELECT s FROM Score s where s.account_id = :account_id ";
        TypedQuery<Score> query = em.createQuery(jpql,Score.class)
                .setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS)
                .setHint("javax.persistence.cache.storeMode", CacheStoreMode.REFRESH);
        query.setParameter("account_id", gameStatus.getChallenge().getChallenger().getId());
        List<Score> challanger = query.getResultList();

        Score score_challanger = null;
        Score score_enemy = null;

        if(!challanger.isEmpty()) {
            score_challanger = challanger.get(0);
            score_challanger.setScore(points_challanger.getPoints());
        }

        String jpql_2 = "SELECT s FROM Score s where s.account_id = :account_id ";
        TypedQuery<Score> query2 = em.createQuery(jpql_2,Score.class)
                .setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS)
                .setHint("javax.persistence.cache.storeMode", CacheStoreMode.REFRESH);
        query2.setParameter("account_id", gameStatus.getChallenge().getEnemy().getId());
        List<Score> enemy = query2.getResultList();

        if(!enemy.isEmpty()){
            score_enemy = enemy.get(0);
            score_enemy.setScore(points_enemy.getPoints());
        }
        em.getTransaction().begin();
        if(score_challanger!=null)
            em.merge(score_challanger);
        if(score_enemy!=null)
            em.merge(score_enemy);
        em.getTransaction().commit();
    }

    @Override
    public List<Score> getTopThree() {
        String jpql = "SELECT s FROM Score s ORDER BY s.score DESC";
        TypedQuery<Score> query = em.createQuery(jpql, Score.class);
        query.setMaxResults(3);

        List<Score> response = query.getResultList();

        String jpql1 = "SELECT a FROM Accounts a WHERE a.id = :account_id";
        TypedQuery<Accounts> query1 = em.createQuery(jpql1, Accounts.class);
        for (Score entity : response) {
            query1.setParameter("account_id",entity.getAccount_id());
            em.refresh(entity);
            List<Accounts> response1 = query1.getResultList();
            if(!response1.isEmpty()){
                entity.setAccount(response1.get(0));
            }
        }
        return response;
    }

    @Override
    public List<Score> getAllAccounts() {
        String jpql = "SELECT s, a FROM Score s JOIN Accounts a ON s.account_id = a.id";
        Query query = em.createQuery(jpql);
        List<Object[]> resultList = query.getResultList();

        List<Score> response = new ArrayList<>();

        for (Object[] result : resultList) {
            Score score = (Score) result[0];
            Accounts account = (Accounts) result[1];
            score.setAccount(account);
            response.add(score);
        }
        return response;
    }

    @Override
    public void removeAccount(int account_id) {
        Score score = em.find(Score.class, account_id);
        Accounts accounts = em.find(Accounts.class, account_id);

        em.getTransaction().begin();
        em.remove(score);
        em.remove(accounts);
        em.getTransaction().commit();

    }
}
