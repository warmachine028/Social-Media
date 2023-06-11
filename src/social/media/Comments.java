package social.media;

import java.util.ArrayList;
import java.util.HashMap;

public class Comments {
    private static final HashMap<Long, Comment> data = new HashMap<>();
    public static int deleteComment(Comment comment) {
        try {
            data.remove(comment.getId());
            return 1;
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return 0;
    }
    public static int storeComment(Comment comment) {
        try {
            data.put(comment.getId(), comment);
            return 1;
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return 0;
    }
    ArrayList<Comment> getComments(long postId){
        ArrayList<Comment> comments = new ArrayList<>();
        for (Comment comment : data.values()) {
            if (comment.getPostId() == postId)
                comments.add(comment);
        }
        return comments;
    }
}
