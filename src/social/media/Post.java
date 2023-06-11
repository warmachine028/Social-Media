package social.media;

import social.media.utlis.Notification;
import social.media.utlis.NotificationType;
import social.media.utlis.Visibility;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Post {
    private static long lastPostId;
    private final long id, creator;
    private final Timestamp createdAt;
    private final Set<Long> likes = new HashSet<>();
    private final Set<String> tags = new HashSet<>();
    private String title;
    private String text;
    private String media;
    private Visibility nonPublic;

    public Post(long creator, String title, String text) {
        id = ++lastPostId;
        this.creator = creator;
        this.title = title;
        this.text = text;
        createdAt = new Timestamp(System.currentTimeMillis());
    }

    public Post(long creator, String title, String text, String media) {
        this(creator, title, text);
        this.media = media;
    }

    public Post(long creator, String title, String text, String media, String[] tags) {
        this(creator, title, text, media);
        Collections.addAll(this.tags, tags);
    }

    public Post(long creator, String title, String text, String media, String[] tags, Visibility nonPublic) {
        this(creator, title, text, media, tags);
        this.nonPublic = nonPublic;
    }

    public int getLikes() {
        return likes.size();
    }

    public void showLikes() {
        for (long like : likes)
            System.out.println(like);
    }

    int editPost(String title, String text) {
        // Perform operation
        setTitle(title);
        setText(text);
        return 1;
    }

    private void setTitle(String title) {
        this.title = title;
    }

    private void setText(String text) {
        this.text = text;
    }

    private void setMedia(String media) {
        this.media = media;
    }

    int editPost(String title, String text, String media) {
        // Perform operation
        int result = editPost(title, text);
        setMedia(media);
        return result & 1;
    }

    Visibility getVisibility() {
        return nonPublic;
    }

    long getCreator() {
        return creator;
    }

    long getId() {
        return id;
    }

    Timestamp getCreatedAt() {
        return createdAt;
    }

    void setPrivate() {
        nonPublic = Visibility.PRIVATE;
    }

    void setPublic() {
        nonPublic = Visibility.PUBLIC;
    }

    void likePost(long userId) {
        if (!likes.contains(userId)) { // unLike post
            likes.add(userId);
            return;
        }
        likes.remove(userId);
    }

    void commentPost(long userId) {
        // Get Comment text from user STDIN
        String message = "Nice Post";
        Comment comment = new Comment(id, userId, message);
        int response = Comments.storeComment(comment);
        if (response != 1) {
            Notification.notify(NotificationType.ERROR, "Comment not added");
            return;
        }
        Notification.notify(NotificationType.SUCCESS, "Comment Created");
    }
}

