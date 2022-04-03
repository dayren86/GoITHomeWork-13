package hm133;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Todo {
    public void findTodo(int id) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/users/" + id + "/todos"))
                .GET()
                .build();

        HttpClient client = HttpClient.newBuilder().build();

        HttpResponse<String> send = client.send(request, HttpResponse.BodyHandlers.ofString());

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        User[] user = gson.fromJson(send.body(), User[].class);

        for (int i = 0; i < user.length - 1; i++) {
            if (!user[i].completed) {
                System.out.println(user[i]);
            }

        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        Todo todo = new Todo();
        todo.findTodo(4);
    }
}
