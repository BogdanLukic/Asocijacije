package Servlets;

import Database.IDatabase;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

@WebServlet(name = "Login", urlPatterns = "/login")
public class Login extends HttpServlet {
    IDatabase db;
    @Override
    public void init(ServletConfig config) throws ServletException {
//         db = DbContext.getConnection();
    }
//    Ne koristi se, zakomentarisano da ne bi bacalo gresku

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        ObjectMapper objMapper = new ObjectMapper();
//        JsonNode jsonNode = objMapper.readTree(req.getReader());
//
//        String username = jsonNode.get("username").asText();
//        String password = jsonNode.get("password").asText();
//
//        Account account = new Account();
//        account.setUsername(username);
//        account.setPassword(password);
//
//        String response = db.login(account);
//
//        BufferedWriter bw = new BufferedWriter(resp.getWriter());
//        if(response != null)
//        {
//            ObjectNode response_json = objMapper.createObjectNode();
//            response_json.put("token",response);
//            bw.write(objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(response_json));
//        }
//        else
//            bw.write("null");
//        bw.flush();
//    }
}