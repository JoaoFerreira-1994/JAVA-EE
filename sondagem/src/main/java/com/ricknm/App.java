package com.ricknm;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/censos")
public class App extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        // Os parâmetros offset e limit não podem ser texto.
        
        String offsetParam = req.getParameter("offset");
        String limitParam = req.getParameter("limit");
        int offset = 1;
        int limit = 20;

        if (offsetParam != null) {
            try {
                offset = Integer.parseInt(offsetParam);
                limit = Integer.parseInt(limitParam);

            } catch (NumberFormatException e) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("O parâmetro offset tem de ser um número.");
                return;
            }
        }

        // Retornar um JSON de erro estruturado
        String jsonString = "{\"status\":\"400\",\"error\":\"Bad Request\",\"message\":\"O parâmetro 'limit' deve ser um número inteiro entre 1 e 50.\"}";

        // O parâmetro limit não pode ser negativo nem superior a 50.
        // Definir o código de estado HTTP para 400 Bad Request.
        if (limit < 0 && limit > 50 ) { 
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Erro de validação");
            resp.getWriter().write("O limite deve ser maior que 0 e menos que 50 !!!!");
            resp.getWriter().write(jsonString);
            
        } 

        // O parâmetro showAlerts só pode ser true ou false.
        boolean showAlerta = req.getParameter("showAlerta") == null ?
                     true :
                     Boolean.parseBoolean(req.getParameter("showAlerta"));


        HttpClient client = HttpClient.newHttpClient();
        ObjectMapper mapper = new ObjectMapper();


        for (int i = offset; i <= limit; i++) {

         try {
                HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(
                            "https://rickandmortyapi.com/api/character/" + i))
                    .GET()
                    .build();

                HttpResponse<String> response = client.send(
                        request,
                        HttpResponse.BodyHandlers.ofString()
                );

                String body = response.body();
                
                JsonNode jsonNode = mapper.readTree(body);

                String status = jsonNode.get("status").asText();
                String species = jsonNode.get("species").asText();

                if (showAlerta && status.equals("Dead") && species.equals("Alien")) {
                    resp.getWriter().write("Alerta alien morto e análise forense");   
                    
                };
                
             }

                 catch (Exception e) {
                 System.out.println(
                         "Erro ao processar a personagem com ID " + i
                 );
            }
  
        }
    }
}
       

