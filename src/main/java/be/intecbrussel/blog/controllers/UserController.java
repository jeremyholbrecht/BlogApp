package be.intecbrussel.blog.controllers;

import be.intecbrussel.blog.data.BlogPost;
import be.intecbrussel.blog.data.User;
import be.intecbrussel.blog.repositories.UserRepository;
import be.intecbrussel.blog.services.BlogPostService;
import be.intecbrussel.blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

// import javax.validation.Valid;

@Controller
public class UserController {

    private UserService userService;
    private BlogPostService blogPostService;

    @Autowired
    public UserController(UserService userService, BlogPostService blogPostService) {
        this.userService = userService;
        this.blogPostService = blogPostService;
    }


    @GetMapping("/register")
    public String userRegistrationPage(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("passwordsEqual", user.isPasswordsEqual());
        return "signup";
    }

    @PostMapping("/register")
    public String submitForm(Model model, @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "signup";

        model.addAttribute("user", userService.createUser(user));

        return "redirect:/index";
    }

    @GetMapping("/login")
    public String getlogin() {
        return "login";
    }


    @PostMapping("/login")
    public String userLogin(@ModelAttribute User user) {
        User registeredUser = userService.autenticate(user.getUserName(), user.getPassword());
        if (registeredUser != null) {
            return "/author";
        } else {
            return "/login";
        }
    }

    @GetMapping("/author")
    public String getAuthor(Model model, BlogPost blogPost) {
        String title = blogPost.getTitle();
        model.addAttribute("title", blogPostService.getOneByTitle(title));
        return "author";
    }



}

















































