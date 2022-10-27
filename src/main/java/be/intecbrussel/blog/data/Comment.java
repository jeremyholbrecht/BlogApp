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
    private User commentAuthor;
    LocalDateTime commentMade;

    protected void getCommentCreatedTime(){
        this.commentCreatedTime = LocalDateTime.now();
    }
    public Comment() {
    }

    public Comment(String comment, LocalDateTime commentCreatedTime, User commentAuthor) {
        this.comment = comment;
        this.commentCreatedTime = commentCreatedTime;
        this.commentAuthor = commentAuthor;
    }

    public Comment(String comment, LocalDateTime commentCreatedTime, BlogPost post, User commentAuthor) {
        this.comment = comment;
        this.commentCreatedTime = commentCreatedTime;
        this.post = post;
        this.commentAuthor = commentAuthor;
    }

    public Comment(long id, String comment, LocalDateTime commentCreatedTime, BlogPost post, User commentAuthor) {
        this.id = id;
        this.comment = comment;
        this.commentCreatedTime = commentCreatedTime;
        this.post = post;
        this.commentAuthor = commentAuthor;
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

    public User getCommentAuthor() {
        return commentAuthor;
    }

    public void setCommentAuthor(User commentAuthor) {
        this.commentAuthor = commentAuthor;
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
                ", commentAuthor=" + commentAuthor +
                ", commentMade=" + commentMade +
                '}';
    }


}
