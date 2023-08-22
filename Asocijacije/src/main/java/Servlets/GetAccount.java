package Servlets;

import Database.Database;
import Database.IDatabase;
import Entities.Accounts;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

@WebServlet(name = "GetAccount", urlPatterns = "/getAccount")
public class GetAccount extends HttpServlet {
    IDatabase connection;
    ObjectMapper objMapper;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper objMapper = new ObjectMapper();

        HttpSession session = req.getSession();
        Accounts account = (Accounts) session.getAttribute("token");
        String account_string = objMapper.writeValueAsString(account);

        BufferedWriter bw = new BufferedWriter(resp.getWriter());

        ObjectNode response_json = objMapper.createObjectNode();
        response_json.put("token",account_string);
        bw.write(objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(response_json));
        bw.flush();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(connection == null)
            connection = Database.getConnection();
        if(objMapper ==null)
            objMapper = new ObjectMapper();

        StringBuilder id = new StringBuilder();
        try (BufferedReader reader = req.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                id.append(line);
            }
        }
        int id_int = Integer.parseInt(id.toString());
        connection.removeAccount(id_int);
        BufferedWriter bw = new BufferedWriter(resp.getWriter());
        bw.write("obrisano");
        bw.flush();
    }
}
