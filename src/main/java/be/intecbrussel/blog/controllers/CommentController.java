package be.intecbrussel.blog.controllers;

import be.intecbrussel.blog.data.BlogPost;
import be.intecbrussel.blog.data.Comment;
import be.intecbrussel.blog.data.User;
import be.intecbrussel.blog.services.BlogPostService;
import be.intecbrussel.blog.services.CommentService;
import be.intecbrussel.blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class CommentController {

    private CommentService commentService;
    private BlogPostService blogPostService;
    private UserService userService;

    @Autowired
    public CommentController(CommentService commentService, BlogPostService blogPostService, UserService userService) {
        this.commentService = commentService;
        this.blogPostService = blogPostService;
        this.userService = userService;
    }

//    @GetMapping("index/{userName}/{blogPost}/{comments}")
//    public String showAllCommentsOnBlogPost(Model model, @PathVariable("userName") String blogAuthor, @PathVariable("blogPost") String blogTitle, Long commentAuthorId, String commentAuthorName){
//        User author = userService.getCurrentUser(blogAuthor);
//        BlogPost blogPost = blogPostService.getOneByTitle(blogTitle);
//       // model.addAttribute("commentAuthor", commenter);
//        //model.addAttribute("blogPost", blogTitle);
//        model.addAttribute("comment", new Comment());
//        model.addAttribute("comments", commentService.getCommentsForBlogPost(blogPost));
//        //DateTimeFormatter frontEndDT = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
//        //model.addAttribute("timeOfPost", new BlogPost().getTimeOfPost());
//        return "/blogPost";
//    }

    @GetMapping(value = "comment/{id}")
    public @ResponseBody
    Comment get(@PathVariable("id") Long id) {
        return commentService.getCommentById(id);
    }
    // create  submit request
    // @PostMapping("/{postURL}/ comments")


    @PostMapping("/addComment")
    public String addComment(Comment comment, String commentBody, String userName, @PathVariable String blogPostTitle) {
        User user = userService.getCurrentUser(userName);
        BlogPost blogPost = blogPostService.getOneByTitle(blogPostTitle);
        comment = new Comment(commentBody, LocalDateTime.now(), blogPost, user);
        LocalDateTime commentTime = comment.getCommentMade();
        commentService.addComment(comment);
        return "redirect:/blogPost";
    }


}