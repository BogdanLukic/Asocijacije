package Database;

import Models.Account;
import Models.ERegistrationStatus;
import Services.TokenJJWT;

import java.sql.*;
import java.util.List;

public class DbContext implements IDatabase{

    private static IDatabase db;
    private Connection connection;
    private DbContext(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/asocijacije", "root", "");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    private static Object lock = new Object();
    public static IDatabase getConnection(){
        if(db == null){
            synchronized (lock){
                if(db == null){
                    db = new DbContext();
                }
            }
        }
        return db;
    }

//    =======================================================
    @Override
    public String login(Account account) {
        String query = "SELECT * FROM accounts WHERE username=? AND password=?;";
        try{
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1,account.getUsername());
            pst.setString(2,account.getPassword());
            ResultSet haveRes = pst.executeQuery();
            if(haveRes.next())
            {
                account.setEmail(haveRes.getString("email"));
                account.setUsername(haveRes.getString("username"));
                account.setRole_id(haveRes.getInt("role_id"));
                account.setPassword(null);
                return TokenJJWT.generateToken(account);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private boolean existingEmail(String email){
        String query = "SELECT * FROM accounts WHERE email=?";
        try{
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1,email);
            ResultSet rs = pst.executeQuery();
            if(rs.next())
                return true;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    private boolean existingUsername(String username){
        String query = "SELECT * FROM accounts WHERE username=?";
        try{
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1,username);
            ResultSet rs = pst.executeQuery();
            if(rs.next())
                return true;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    private int getUserId(String email){
        String query = "SELECT * FROM accounts WHERE email=?";
        try{
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1,email);
            ResultSet rs = pst.executeQuery();
            if(rs.next())
                return rs.getInt("id");
        }
        catch (Exception e){}
        return -1;
    }

    @Override
    public ERegistrationStatus register(Account account){
        if(existingEmail(account.getEmail()))
            return ERegistrationStatus.Email_is_existing;
        else if(existingUsername(account.getUsername()))
            return ERegistrationStatus.Username_is_existing;
        else {
            String query = "INSERT INTO accounts (username,email,password,role_id) VALUES(?,?,?,2)";
            try{
                PreparedStatement pst = connection.prepareStatement(query);
                pst.setString(1,account.getUsername());
                pst.setString(2,account.getEmail());
                pst.setString(3,account.getPassword());
                pst.execute();
            }
            catch (Exception e){}

            query = "INSERT INTO accounts (username,email,password,role_id) VALUES(?,?,?,2)";

            int user_id = getUserId(account.getEmail());

            query = "INSERT INTO characters (account_id,character_id) values (?,?)";
            try{
                PreparedStatement pst = connection.prepareStatement(query);
                pst.setInt(1,user_id);
                pst.setInt(2,account.getCharacter());
                pst.execute();
            }catch (Exception e){}
            return ERegistrationStatus.Success_registration;
        }
    }
}
