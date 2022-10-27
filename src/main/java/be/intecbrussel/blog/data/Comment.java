package be.intecbrussel.blog.data;

//import org.apache.catalina.User;

//import com.fasterxml.jackson.annotation.JsonAnySetter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name =  "comments")
public class Comment {
    @Id
    @GeneratedValue
    private long id;
    //if i just leave it like this JPA will give automatically  filed name so i give it will make the table not null
    @Lob
    //this is to make the column big object.
    private String comment;
    // Helps us when you search to display according to modification data
    @CreationTimestamp
    private LocalDateTime commentCreatedTime;
    @ManyToOne
    @JoinColumn
    private BlogPost post;
    @ManyToOne
    private User author;
    LocalDateTime commentMade;

    protected void getCommentCreatedTime(){
        this.commentCreatedTime = LocalDateTime.now();
    }
    public Comment() {
    }

    public Comment(String comment, LocalDateTime commentCreatedTime) {
        this.comment = comment;
        this.commentCreatedTime = commentCreatedTime;
    }

    public Comment(String comment, LocalDateTime commentCreatedTime, BlogPost post, User author) {
        this.comment = comment;
        this.commentCreatedTime = commentCreatedTime;
        this.post = post;
        this.author = author;
    }

    public Comment(long id, String comment, LocalDateTime commentCreatedTime, BlogPost post, User author) {
        this.id = id;
        this.comment = comment;
        this.commentCreatedTime = commentCreatedTime;
        this.post = post;
        this.author = author;
    }
}
