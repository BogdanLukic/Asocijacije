package Servlets;

import Database.DatabaseAsocijacije;
import Database.IDatabaseAsocijacije;
import Entities.*;
import Models.Association;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.eclipse.persistence.internal.libraries.asm.TypeReference;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "AssociationList", urlPatterns = "/associationlist")
public class AssociationList extends HttpServlet {
    IDatabaseAsocijacije connection;
    ObjectMapper objMapper;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(connection == null)
            connection = DatabaseAsocijacije.getConnection();
        if(objMapper == null)
            objMapper = new ObjectMapper();

        List<Association> associationList = connection.getListOfAssociation();
        BufferedWriter bw = new BufferedWriter(response.getWriter());

        bw.write(objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(associationList));
        bw.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(connection == null)
            connection = DatabaseAsocijacije.getConnection();
        if(objMapper == null)
            objMapper = new ObjectMapper();

        StringBuilder jsonBody = new StringBuilder();
        try (BufferedReader reader = req.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                jsonBody.append(line);
            }
        }

        String json = jsonBody.toString();
        JsonNode jsonNode = objMapper.readTree(json);

        Column_A column_a = objMapper.convertValue(jsonNode.get("column_a"), Column_A.class);
        Column_B column_b = objMapper.convertValue(jsonNode.get("column_b"), Column_B.class);
        Column_C column_c = objMapper.convertValue(jsonNode.get("column_c"), Column_C.class);
        Column_D column_d = objMapper.convertValue(jsonNode.get("column_d"), Column_D.class);
        FinalAnswer finalAnswer = objMapper.convertValue(jsonNode.get("finalAnswer"), FinalAnswer.class);

        Association new_association =  new Association(column_a,column_b,column_c,column_d,finalAnswer);

        IDatabaseAsocijacije databaseAsocijacije = DatabaseAsocijacije.getConnection();
        databaseAsocijacije.addAssociation(new_association);

        BufferedWriter bw = new BufferedWriter(resp.getWriter());
        bw.write(objMapper.writeValueAsString(new_association));
        bw.flush();
    }
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(connection == null)
            connection = DatabaseAsocijacije.getConnection();
        if(objMapper == null)
            objMapper = new ObjectMapper();

        StringBuilder id = new StringBuilder();
        try (BufferedReader reader = req.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                id.append(line);
            }
        }
        int id_int = Integer.parseInt(id.toString());
        BufferedWriter bw = new BufferedWriter(resp.getWriter());

        connection.removeAssociation(id_int);

        bw.write("obrisano");
        bw.flush();

    }
}
