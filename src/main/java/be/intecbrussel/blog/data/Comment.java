package be.intecbrussel.blog.data;

//import org.apache.catalina.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Comment {
    @Id
    @GeneratedValue
    private long id;
    private String comment;
    @ManyToOne
    private BlogPost post;
    @ManyToOne
    private User author;
    private String commentBody;
    LocalDateTime commentDate;

    public Comment() {
    }

    public Comment(String comment, BlogPost post, User author) {
        this.comment = comment;
        this.post = post;
        this.author = author;
    }

    public Comment(long id, String comment, BlogPost post, User author, String commentBody, LocalDateTime commentDate) {
        this.id = id;
        this.comment = comment;
        this.post = post;
        this.author = author;
        this.commentBody = commentBody;
        this.commentDate = commentDate;
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

    public String getCommentBody() {
        return commentBody;
    }

    public void setCommentBody(String commentBody) {
        this.commentBody = commentBody;
    }

    public LocalDateTime getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(LocalDateTime commentDate) {
        this.commentDate = commentDate;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                ", post=" + post +
                ", author=" + author +
                ", commentBody='" + commentBody + '\'' +
                ", commentDate=" + commentDate +
                '}';
    }
}

