package be.intecbrussel.blog.controllers;

import be.intecbrussel.blog.data.Comment;
import be.intecbrussel.blog.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CommentController {

    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping(value = "comment/{id}")
    public @ResponseBody Comment  get(@PathVariable("id") Long id) {
        return commentService.getComment(id);
    }


}