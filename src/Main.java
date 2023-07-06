import social.media.Post;
import social.media.Posts;
import social.media.User;
import social.media.Users;

import java.io.Console;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    static void displayPosts(User user) {
        ArrayList<Post> posts = user != null ?
                Posts.getPosts(user.getId())
                : Posts.getPosts();
        posts.forEach(post -> {
            System.out.println("_--------------------_");
            System.out.printf("_     %s    _\n", post.getTitle());
            System.out.printf("_     %s    _\n", post.getMedia());
            System.out.printf("_     %s    _\n", post.getLikes());
        });
    }

    static User logIn() {
        String message = """
                :     LOGGING IN     :
                1. Enter Credentials
                2. Forgot Password
                """;
        System.out.println(message);
        int choice = scanner.nextInt();
        String emailId, password;
        User user = null;
        switch (choice) {
            case 1 -> {
                System.out.print("Enter emailID: ");
                emailId = scanner.next();
                System.out.print("Enter password: ");
                password = scanner.next();
                user = Users.authenticate(emailId, password);
                if (user != null)
                    System.out.println("Authenticated Successfully");
                else
                    System.out.println("Authentication Failed");

            }
            case 2 -> {
                System.out.print("Enter emailID: ");
                emailId = scanner.next();
                // process Reset Password
            }
        }
        return user;

    }

    static void signUp() {

    }

    static void createPost(User user) {

    }

    public static void main(String[] args) {
        String message = ":WELCOME TO SOCIAL MEDIA:";
        System.out.println(message);
        int choice = -1;
        User user = null;
        while (choice != 9) {
            if (user == null)
                message = """
                        Please SELECT YOUR CHOICE:
                        1. Browse Posts
                        2. Login
                        3. Register
                        9. Exit
                        """;
            else
                message = """
                        Please SELECT YOUR CHOICES:
                        1. Browse Posts
                        4. CreatePost
                        5. Logout
                        9. Exit
                        """;

            System.out.println(message);
            choice = scanner.nextInt();
            switch (choice) {
                case 1 -> displayPosts(user);
                case 2 -> user = logIn();
                case 3 -> signUp();
                case 4 -> createPost(user);
                case 5 -> user = null;
            }
        }
    }
}
