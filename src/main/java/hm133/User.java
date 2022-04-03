package hm133;

public class User {
    int userId;
    int id;
    String title;
    boolean completed;

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                '}';
    }
}
