package com.ricknm;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Hello world!
 *
 */
public class App 
{ 
    public static void main(String[] args) throws Exception {

        HttpClient client = HttpClient.newHttpClient();
        ObjectMapper mapper = new ObjectMapper();

        for (int i = 1; i <= 20; i++) {

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

            try {
                JsonNode jsonNode = mapper.readTree(body);

                String status = jsonNode.get("status").asText();
                String species = jsonNode.get("species").asText();

                if (status.equals("Dead") && species.equals("Alien")) {

                    System.out.println(
                            "Um Alien morto foi encontrado com o ID " + i
                    );

                    JsonNode episodes = jsonNode.get("episode");

                    String ultimoEpisodio = episodes
                            .get(episodes.size() - 1)
                            .asText();

                    HttpRequest request2 = HttpRequest.newBuilder()
                        .uri(URI.create(ultimoEpisodio))
                        .GET()
                        .build();
                    
                    HttpResponse<String> response2 = client.send(
                        request2,
                        HttpResponse.BodyHandlers.ofString()
                    );

                    String body2 = response2.body();

                    JsonNode episodioNode = mapper.readTree(body2);

                    String nome_episodio = episodioNode.get("name").asText();

                    System.out.println("Nome:" + nome_episodio);

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

