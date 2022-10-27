package be.intecbrussel.blog.services;

import be.intecbrussel.blog.data.Comment;

public interface CommentInter {

    void createComment(String postURL, Comment comment);

}
