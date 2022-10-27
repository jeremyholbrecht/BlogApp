package be.intecbrussel.blog.controllers;

import be.intecbrussel.blog.data.BlogPost;
import be.intecbrussel.blog.data.User;
import be.intecbrussel.blog.services.BlogPostService;
import be.intecbrussel.blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Controller
public class BlogPostController {
    private BlogPostService blogPostService;
    private UserService userService;

    @Autowired
    public BlogPostController(BlogPostService blogPostService, UserService userService) {
        this.blogPostService = blogPostService;
        this.userService = userService;
    }

    @GetMapping("/index")
    public String showAllBlogs(Model model){
        model.addAttribute("blogs", blogPostService.getAllByNewest());
        return "index";
    }

    @GetMapping("/index/{userName}")
    public String showAllBlogsFromAuthor(Model model, @PathVariable("userName") String author, Long id){
        User user = userService.getCurrentUser(author);
        model.addAttribute("userName", author);
        model.addAttribute("blogPosts", blogPostService.getAllByAuthorByNewest(user));
        model.addAttribute("blogPost", new BlogPost());
        //DateTimeFormatter frontEndDT = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        model.addAttribute("timeOfPost", new BlogPost().getTimeOfPost());
        return "author";
    }


    @PostMapping("/addBlogPost")
    public String addBlogPost(BlogPost blogPost, String userName){
        User user = userService.getCurrentUser(userName);
        blogPost = new BlogPost(0, "", user, "");
        LocalDateTime timeOfPost = blogPost.getTimeOfPost();
        blogPostService.createBlogPost(blogPost);
        return "redirect:/author";
    }

    @GetMapping("index/{userName}/{title}")
    public String showBlogPost(Model model, Long id,  @PathVariable ("title") String title, @PathVariable ("userName") String author){
        BlogPost blogPost= new BlogPost();
        User user = userService.getCurrentUser(author);
        model.addAttribute("userName", author);
        model.addAttribute("blogPost", blogPostService.getOneByTitle(title));
        model.addAttribute("title", blogPost.getTitle());
        model.addAttribute("timeOfPost", new BlogPost().getTimeOfPost());
        return "/blogPost";
    }

}
