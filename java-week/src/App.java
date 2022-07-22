import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {

    public static void main(String[] args) throws Exception {

        // fazer cnx http e buscar top 10 filmes mock
        String url = "https://api.mocki.io/v2/549a5d8b";
        URI link = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(link).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();
        // System.out.println(body);

        // filtrar apenas dados relevantes (titulo, poster, classificação)
        var parser = new JsonParse();
        List<Map<String, String>> movieList = parser.parse(body);

        // exibir e manipular dados na app

        String title = "Título do filme: ";
        String poster = "Poster do filme: ";
        String rating = "Avaliação do público: ";

        for (Map<String, String> movie : movieList) {
            System.out.println(title.concat(movie.get("title")));
            System.out.println(poster.concat(movie.get("image")));
            System.out.println(rating.concat(movie.get("imDbRating")));

        }
    }
}
