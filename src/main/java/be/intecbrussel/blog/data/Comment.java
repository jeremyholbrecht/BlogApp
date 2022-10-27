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
    @Lob
    //this is to make the column big object.
    private String comment;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setCommentCreatedTime(LocalDateTime commentCreatedTime) {
        this.commentCreatedTime = commentCreatedTime;
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

    public LocalDateTime getCommentMade() {
        return commentMade;
    }

    public void setCommentMade(LocalDateTime commentMade) {
        this.commentMade = commentMade;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                ", commentCreatedTime=" + commentCreatedTime +
                ", post=" + post +
                ", author=" + author +
                ", commentMade=" + commentMade +
                '}';
    }
}
