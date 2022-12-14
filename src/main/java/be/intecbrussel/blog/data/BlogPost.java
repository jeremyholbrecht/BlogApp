package be.intecbrussel.blog.data;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Entity
public class BlogPost {

    @Id
    @GeneratedValue
    private long id;
    private String title;
    @ManyToOne//(cascade = {CascadeType.PERSIST})
    private User user;
    private String blogBody;
    private static int hitCount = CounterServlet.getHitCounter();
    @OneToMany(mappedBy = "post")
    private List<Comment> comments;

    LocalDateTime timeOfPost;

    @PrePersist
    protected void oneCreate() {

        this.timeOfPost = LocalDateTime.now();
    }

    public BlogPost() {
    }

    public BlogPost(String title, User author, LocalDateTime timeOfPost) {
        this.title = title;
        this.user = author;
        this.timeOfPost = timeOfPost;
    }


    public BlogPost(long id, String title, User author, String blogBody) {
        this.id = id;
        this.title = title;
        this.user = author;
        this.blogBody=blogBody;

    }

    public BlogPost(long id, String title, User user, String blogBody, List<Comment> comments, LocalDateTime timeOfPost) {
        this.id = id;
        this.title = title;
        this.user = user;
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

    public int getHitCounter(){
        return hitCount;
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

class CounterServlet extends HttpServlet{
    private static int hitCounter;

    public void init() throws ServletException{
        hitCounter = 0;
    }
    public void countHits(){
        hitCounter++;
    }
    public static int getHitCounter(){
        return hitCounter;
    }
}