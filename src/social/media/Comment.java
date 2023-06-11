package social.media;

public class Comment {
    private long id, post, creator;
    private String message;
    private static long lastCommentId;
    Comment(long postId, long creatorId, String message) {
        id = ++lastCommentId;
        this.post = postId;
        this.creator = creatorId;
        this.message = message;
    }
    long getId() {
        return id;
    }
    long getCreatorId() {
        return creator;
    }
    long getPostId() {
        return post;
    }
    String getMessage() {
        return message;
    }
}
