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

    private final String url = "https://jsonplaceholder.typicode.com/users/";
    private final String urlComments = "https://jsonplaceholder.typicode.com/posts/";
    private int id;
    int lastPost;

    public void setId(int id) {
        this.id = id;
    }

    public void setLastPost(int lastPost) {
        this.lastPost = lastPost;
    }

    public UserPosts[] fromJsonUser(String string) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.fromJson(string, UserPosts[].class);
    }

    public void findUserPosts() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create(url + id + "/posts/"))
                .GET()
                .build();

        HttpClient client = HttpClient.newBuilder().build();

        HttpResponse<String> send = client.send(request, HttpResponse.BodyHandlers.ofString());

        UserPosts[] userPosts = fromJsonUser(send.body());

        setLastPost(userPosts[userPosts.length - 1].id);

    }

    public String findComments() throws IOException, InterruptedException {
        HttpRequest requestComments = HttpRequest
                .newBuilder()
                .uri(URI.create(urlComments + lastPost + "/comments"))
                .GET()
                .build();

        HttpClient client = HttpClient.newBuilder().build();

        HttpResponse<String> sendComments = client.send(requestComments, HttpResponse.BodyHandlers.ofString());

        return sendComments.body();
    }

    public void writeComments(String comment) throws IOException {
        File file = new File("src/main/java/hm132/user-" + id + "-post-" + lastPost + "-comments.json");
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(comment);
        fileWriter.flush();

    }

    public static void main(String[] args) throws IOException, InterruptedException {
        Comments comments = new Comments();

        comments.setId(5);
        comments.findUserPosts();
        comments.writeComments(comments.findComments());
    }
}
