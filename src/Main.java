import social.media.Post;
import social.media.Posts;
import social.media.User;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        long randomUserId = 1222121;
        Post randomPost = new Post(randomUserId, "Title", "text");
        Posts.storePost(randomPost);

        User user = new User(
                "Pritam Kundu",
                "pritamkundu771@gmail.com",
                "someRandomPassword",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/c/cb/Howrah_bridge_at_night.jpg/1200px-Howrah_bridge_at_night.jpg"
        );

        user.displayInfo();

        long userId = user.getId();
        Post post = user.createPost();
        Post post1 = user.createPost();
        ArrayList<Post> feed = Posts.getPosts(userId);
//
        user.like(randomPost);
        user.like(post);
        user.like(post1);

        System.out.println(post.getLikes());
        System.out.println(post1.getLikes());

        user.deletePost(randomPost); // Throws error
        user.deletePost(post); // Success

        Post feedPost = feed.get(1);
        System.out.println(feedPost);
        user.deletePost(feedPost);
    }
}
