package hm131;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HM131 {

    private final String url = "https://jsonplaceholder.typicode.com/users/";


    public String toJsonUser(Users user) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(user);
    }


    public String createUser(Users user) throws IOException, InterruptedException {

        String json = toJsonUser(user);

        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create(url))
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpClient client = HttpClient.newBuilder().build();

        HttpResponse<String> send = client.send(request, HttpResponse.BodyHandlers.ofString());

        return send.body();

    }

    public String updateUser(Users user, int id) throws IOException, InterruptedException {

        String json = toJsonUser(user);

        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create(url + id))
                .PUT(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpClient client = HttpClient.newBuilder().build();

        HttpResponse<String> send = client.send(request, HttpResponse.BodyHandlers.ofString());

        return send.body();

    }

    public int deleteUser(int id) throws IOException, InterruptedException {

        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create(url + id))
                .DELETE()
                .build();

        HttpClient client = HttpClient.newBuilder().build();

        HttpResponse<String> send = client.send(request, HttpResponse.BodyHandlers.ofString());

        return send.statusCode();

    }

    public String infoAllUser() throws IOException, InterruptedException {

        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpClient client = HttpClient.newBuilder().build();

        HttpResponse<String> send = client.send(request, HttpResponse.BodyHandlers.ofString());

        return send.body();

    }

    public String infoIdUser(int id) throws IOException, InterruptedException {

        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create(url + id))
                .GET()
                .build();

        HttpClient client = HttpClient.newBuilder().build();

        HttpResponse<String> send = client.send(request, HttpResponse.BodyHandlers.ofString());

        return send.body();

    }

    public String infoUserName(String userName) throws IOException, InterruptedException {

        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create(url + "?username=" + userName))
                .GET()
                .build();

        HttpClient client = HttpClient.newBuilder().build();

        HttpResponse<String> send = client.send(request, HttpResponse.BodyHandlers.ofString());
        return send.body();

    }

    public static void main(String[] args) throws IOException, InterruptedException {
        Users users = new Users("User", "user@test.com", "vinnitsa", "0974445566", "test.com","goit");
        Users users2 = new Users("Ervin Howell", "user@test.com", "vinnitsa", "0974445566", "test.com","goit");

        HM131 hm131 = new HM131();

        //System.out.println(hm131.createUser(users));
        //System.out.println(hm131.updateUser(users2, 3));
        //System.out.println("hm131.deleteUser(2) = " + hm131.deleteUser(2));
        //System.out.println(hm131.infoAllUser());
        System.out.println(hm131.infoIdUser(4));
        //System.out.println(hm131.infoUserName("Antonette"));
    }
}
