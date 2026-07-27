import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


// O Censo Demográfico (Lógica de Contagem) 
// Queremos estatísticas reais. O programa deve analisar o texto de cada resposta (JSON) e contar quantos cidadãos estão vivos e quantos estão mortos.
// No final do programa (fora do loop), imprimam o relatório final na consola:
// => CENSO: Detetados X personagens VIVOS e Y personagens MORTOS nos primeiros 20 registos.


public class ApiTest2{

  static int alive = 0;
  static int dead = 0;
  static int na = 0;

    public static void main(String[] args) throws Exception{
      HttpClient client = HttpClient.newHttpClient();

      for (int i = 1; i <= 20; i++){
        HttpRequest request = HttpRequest.newBuilder()
          .uri(URI.create("https://rickandmortyapi.com/api/character/" + i ))
          .GET()
          .build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        String body = response.body();

        if (body.contains("\"status\":Alive")){alive++;}

        else if(body.contains("\"status\":\"Dead\"")){dead++;}

        else{na++;}

      }

      System.out.println("Alive:" + alive);
      System.out.println("Death:" + dead);
      System.out.println("Unknown:" + na);
    }
    
}
