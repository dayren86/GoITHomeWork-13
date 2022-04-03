package hm132;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Comments {
    public void findLastPost(int id) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/users/" + id + "/posts/"))
                .GET()
                .build();

        HttpClient client = HttpClient.newBuilder().build();

        HttpResponse<String> send = client.send(request, HttpResponse.BodyHandlers.ofString());

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        UserPosts[] userPosts = gson.fromJson(send.body(), UserPosts[].class);

        HttpRequest requestComments = HttpRequest
                .newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/posts/" + userPosts[userPosts.length - 1].id + "/comments"))
                .GET()
                .build();

        HttpResponse<String> sendComments = client.send(requestComments, HttpResponse.BodyHandlers.ofString());
        System.out.println("sendComments.body() = " + sendComments.body());

        File file = new File("src/main/java/hm132/user-" + id + "-post-" + userPosts[userPosts.length - 1].id + "-comments.json");
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(sendComments.body());
        fileWriter.flush();

    }

    public static void main(String[] args) throws IOException, InterruptedException {
        Comments comments = new Comments();
        comments.findLastPost(5);
    }
}
