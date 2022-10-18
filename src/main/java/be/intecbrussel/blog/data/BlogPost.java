package be.intecbrussel.blog.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class BlogPost {

    @Id
    @GeneratedValue
    private long id;
    private String title;
    private User author;
    private List<Comment> comments;
    LocalDateTime timeOfPost;

    public BlogPost() {
    }
}
