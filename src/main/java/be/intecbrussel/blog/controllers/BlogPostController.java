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
        //BlogPost blogPost = blogPostService.getOneById(id);
        //userId = user.getId();
        model.addAttribute("userName", author);
        model.addAttribute("blogPosts", blogPostService.getAllByAuthorByNewest(user));
        model.addAttribute("blogPost", new BlogPost());
       // model.addAttribute("timeOfPost", new BlogPost().getTimeOfPost());
        return "author";
    }

    @GetMapping
    @PostMapping("/addBlogPost")
    public String addBlogPost(BlogPost blogPost, String userName){
        User user = userService.getCurrentUser(userName);
        blogPost = new BlogPost("", user, blogPost.getTimeOfPost());
        blogPostService.createBlogPost(blogPost);
        return "redirect:/author";
    }
}
