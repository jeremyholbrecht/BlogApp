package be.intecbrussel.blog.data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class BlogPost {

    @Id
    @GeneratedValue
    private long id;
    private String title;
    @ManyToOne
    private User user;
    private String blogBody;
    @OneToMany
    private List<Comment> comments;
    LocalDateTime timeOfPost;

    public BlogPost() {
    }

    public BlogPost(String title, User author, LocalDateTime timeOfPost) {
        this.title = title;
        this.user = author;
        this.timeOfPost = timeOfPost;
    }

    public BlogPost(long id, String title, User author, String blogBody, List<Comment> comments, LocalDateTime timeOfPost) {
        this.id = id;
        this.title = title;
        this.user = author;
        this.blogBody = blogBody;
        this.comments = comments;
        this.timeOfPost = timeOfPost;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getBlogBody() {
        return blogBody;
    }

    public void setBlogBody(String blogBody) {
        this.blogBody = blogBody;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public LocalDateTime getTimeOfPost() {
        return timeOfPost;
    }

    public void setTimeOfPost(LocalDateTime timeOfPost) {
        this.timeOfPost = timeOfPost;
    }

    @Override
    public String toString() {
        return "BlogPost{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author=" + user +
                ", blogBody='" + blogBody + '\'' +
                ", comments=" + comments +
                ", timeOfPost=" + timeOfPost +
                '}';
    }
}
