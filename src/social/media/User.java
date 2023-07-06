package social.media;

import social.media.utlis.Notification;
import social.media.utlis.NotificationType;
import social.media.utlis.Visibility;

public class User {
    private static long lastUserId;
    private final long id;
    private String name;
    private String email;
    private String password;
    private String avatar;

    String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
    public User(String name, String email, String password, String avatar) {
        this.name = name;
        this.id = ++lastUserId;
        this.email = email;
        this.password = encrypt(password);
        this.avatar = avatar;
    }

    void forgetPassword() {
        // Send Reset link to email
        Notification.notify(NotificationType.SUCCESS, "Check email for reset Link");
    }
    void resetPassword(String magicLink) {
//        verify magicLink
        String newPassword = "new Password"; // Take user input
        if (password.length() <= 8) {
            Notification.notify(NotificationType.ERROR, "Password length must be greater than 7 characters");
        }
        this.password = newPassword;
    }
    public void displayInfo() {
        System.out.printf(
                """
                                     %s           \s
                        Name: %s\s
                        Email: %s\s
                        %n""",
                id, name, email);
        displayAvatar();
    }
    void displayAvatar() {
        // some function to display UserAvatar
    }

    private String encrypt(String password) {
        String salt = "";
        return salt + password;
    }

    public long getId() {
        return id;
    }

    public void like(Post post) {
        post.likePost(id);
    }

    void commentPost(Post post) {
        post.commentPost(id);
    }

    public void deletePost(Post post) {
        if (id != post.getCreator()) {
            Notification.notify(NotificationType.WARNING, "Can't Delete post");
            return;
        }
        int response = Posts.deletePost(post);
        if (response != 1) {
            Notification.notify(NotificationType.ERROR, "Post not deleted");
            return;
        }
        Notification.notify(NotificationType.SUCCESS, "Post deleted successfully");
    }

    public Post createPost() {
        // GET User Data Input from Stdin
        String title = "New Post",
                text = "This is a message in the  post",
                media = "https://images.indianexpress.com/2018/02/howrah-bridge-759-pixabay.jpg";
        Post post = new Post(
                id,
                title,
                text,
                media,
                new String[]{"Testing"},
                Visibility.PUBLIC
        );
        int response = Posts.storePost(post);
        if (response != 1) {
            Notification.notify(NotificationType.ERROR, "Post not created");
            return null;
        }
        Notification.notify(NotificationType.SUCCESS, "Post created successfully");
        return post;
    }
}
