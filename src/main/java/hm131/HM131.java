package hm131;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HM131 {

    private String url = "https://jsonplaceholder.typicode.com/users";


    public String toJsonUser(Users user) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(user);
    }


    public void createUser(Users user) throws IOException, InterruptedException {

        String json = toJsonUser(user);

        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create(url))
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpClient client = HttpClient.newBuilder().build();

        HttpResponse<String> send = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(send);
        System.out.println(send.body());

    }

    public void updateUser(Users user, int id) throws IOException, InterruptedException {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(user);

        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/users/" + id))
                .PUT(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpClient client = HttpClient.newBuilder().build();

        HttpResponse<String> send = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(send);
        System.out.println(send.body());

    }

    public void deleteUser(int id) throws IOException, InterruptedException {

        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/users/" + id))
                .DELETE()
                .build();

        HttpClient client = HttpClient.newBuilder().build();

        HttpResponse<String> send = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(send);
        System.out.println(send.body());

    }

    public void infoAllUser() throws IOException, InterruptedException {

        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/users"))
                .GET()
                .build();

        HttpClient client = HttpClient.newBuilder().build();

        HttpResponse<String> send = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(send);
        System.out.println(send.body());

    }

    public void infoIdUser(int id) throws IOException, InterruptedException {

        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/users/" + id))
                .GET()
                .build();

        HttpClient client = HttpClient.newBuilder().build();

        HttpResponse<String> send = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(send);
        System.out.println(send.body());

    }

    public void infoUserName(String userName) throws IOException, InterruptedException {

        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/users?username=" + userName))
                .GET()
                .build();

        HttpClient client = HttpClient.newBuilder().build();

        HttpResponse<String> send = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(send);
        System.out.println(send.body());

    }

    public static void main(String[] args) throws IOException, InterruptedException {
        Users users = new Users("User", "user@test.com", "vinnitsa", "0974445566", "test.com","goit");
        Users users2 = new Users("Ervin Howell", "user@test.com", "vinnitsa", "0974445566", "test.com","goit");

        HM131 hm131 = new HM131();

        //hm131.createUser(users);

        //hm131.updateUser(users2, 3);
        //hm131.deleteUser(2);
        //hm131.infoAllUser();
        //hm131.infoIdUser(4);
        hm131.infoUserName("Antonette");
    }
}
