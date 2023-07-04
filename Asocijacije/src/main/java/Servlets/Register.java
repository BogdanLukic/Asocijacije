package Servlets;

import Database.Database;
import Database.IDatabase;
import Entities.Accounts;
import Models.ERegistrationStatus;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedWriter;
import java.io.IOException;

@WebServlet(name = "register", urlPatterns = "/register")
public class Register extends HttpServlet{
        IDatabase db;
        @Override
        public void init(ServletConfig config) throws ServletException {
            db = Database.getConnection();
        }

        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            ObjectMapper objMapper = new ObjectMapper();
            JsonNode jsonNode = objMapper.readTree(req.getReader());

            String username = jsonNode.get("username").asText();
            String password = jsonNode.get("password").asText();
            String email = jsonNode.get("email").asText();
            String character = jsonNode.get("character").asText();
            int num_character = Integer.parseInt(character);
            Accounts account = new Accounts();
            account.setUsername(username);
            account.setPassword(password);
            account.setEmail(email);
            account.setCharacter(num_character);
            ERegistrationStatus response = db.register(account);
            BufferedWriter bw = new BufferedWriter(resp.getWriter());

            ObjectNode response_json = objMapper.createObjectNode();
            response_json.put("response",response.toString());
            bw.write(objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(response_json));
            bw.flush();
        }
}
