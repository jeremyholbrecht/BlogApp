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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    //if i just leave it like this JPA will give automatically  filed name so i give it will make the table not null
    @Column(nullable = false)
    private String name;
    @Lob
    //this is to make the column big object.
    private String comment;
    // Helps us when you search to display according to modification data
    @CreationTimestamp
    private LocalDateTime commentCreatedTime;
    @UpdateTimestamp
    private LocalDateTime CommentUpdatedTime;
    @ManyToOne
    @JoinColumn(name = "posted" ,nullable = false)
    private BlogPost post;
    @ManyToOne
    private User author;

    public Comment() {
    }

    public Comment(String name, String comment, LocalDateTime commentCreatedTime, LocalDateTime commentUpdatedTime, BlogPost post) {
        this.name = name;
        this.comment = comment;
        this.commentCreatedTime = commentCreatedTime;
        CommentUpdatedTime = commentUpdatedTime;
        this.post = post;
    }

    public Comment(long id, String name, String comment, LocalDateTime commentCreatedTime, LocalDateTime commentUpdatedTime, BlogPost post, User author) {
        this.id = id;
        this.name = name;
        this.comment = comment;
        this.commentCreatedTime = commentCreatedTime;
        CommentUpdatedTime = commentUpdatedTime;
        this.post = post;
        this.author = author;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getCommentCreatedTime() {
        return commentCreatedTime;
    }

    public void setCommentCreatedTime(LocalDateTime commentCreatedTime) {
        this.commentCreatedTime = commentCreatedTime;
    }

    public LocalDateTime getCommentUpdatedTime() {
        return CommentUpdatedTime;
    }

    public void setCommentUpdatedTime(LocalDateTime commentUpdatedTime) {
        CommentUpdatedTime = commentUpdatedTime;
    }

    public BlogPost getPost() {
        return post;
    }

    public void setPost(BlogPost post) {
        this.post = post;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}


