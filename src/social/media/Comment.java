package social.media;

public class Comment {
    private final long id, post, creator;
    private String message;
    private static long lastCommentId;
    public Comment(long postId, long creatorId, String message) {
        id = ++lastCommentId;
        this.post = postId;
        this.creator = creatorId;
        this.message = message;
    }
    public int editComment(String message) {
        setMessage(message);
        return 1;
    }
    private void setMessage(String message) {
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
    public String getMessage() {
        return message;
    }

}
