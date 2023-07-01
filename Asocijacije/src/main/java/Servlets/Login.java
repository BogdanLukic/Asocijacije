package Servlets;

import Database.DbContext;
import Database.IDatabase;
import Models.Account;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedWriter;
import java.io.IOException;

@WebServlet(name = "Login", urlPatterns = "/login")
public class Login extends HttpServlet {
    IDatabase db;
    @Override
    public void init(ServletConfig config) throws ServletException {
         db = DbContext.getConnection();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ObjectMapper objMapper = new ObjectMapper();
        JsonNode jsonNode = objMapper.readTree(req.getReader());

        String username = jsonNode.get("username").asText();
        String password = jsonNode.get("password").asText();

        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);

        String response = db.login(account);

        BufferedWriter bw = new BufferedWriter(resp.getWriter());
        if(response != null)
            bw.write(response);
        else
            bw.write("null");
        bw.flush();
    }
}