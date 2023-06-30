package Database;

import Models.Account;

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
    public Account login(Account account) {
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
                account.setId(-1);
                return account;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
