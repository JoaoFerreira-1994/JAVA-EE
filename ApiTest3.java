import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


// Alerta de Segurança: Ameaça Alienígena 
// A Citadela precisa de monitorizar riscos biológicos. Se o vosso programa detetar um cidadão que seja da espécie Alien e que esteja Morto, deve imprimir um alerta imediato na consola:
// [PERIGO] Um Alien foi encontrado morto com o ID X!


public class ApiTest3{

    public static void main(String[] args) throws Exception{
      HttpClient client = HttpClient.newHttpClient();

      for (int i = 1; i <= 20; i++){
        HttpRequest request = HttpRequest.newBuilder()
          .uri(URI.create("https://rickandmortyapi.com/api/character/" + i ))
          .GET()
          .build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        String body = response.body();

        if (body.contains("\"status\":\"Dead\"") && body.contains("\"species\":\"Alien\"")){
          System.out.println("Um Alien foi encontrado morto com o ID " + i);;
        }

      }
    }   
}
