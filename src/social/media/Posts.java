package social.media;

import social.media.utlis.Visibility;

import java.util.ArrayList;
import java.util.HashMap;

public class Posts {
    private static final HashMap<Long, Post> data = new HashMap<>();

    public static int deletePost(Post post) {
        try {
            data.remove(post.getId());
            return 1;
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return 0;
    }

    public static int storePost(Post post) {
        try {
            data.put(post.getId(), post);
            return 1;
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return 0;
    }

    public static ArrayList<Post> getPosts(long userId) {
        ArrayList<Post> posts = new ArrayList<>();
        for (Post post : data.values()) {
            if (post.getVisibility() == Visibility.PUBLIC)
                posts.add(post);
            else if (post.getCreator() == userId) {
                posts.add(post);
            }
        }
        return posts;
    }
}
