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
import java.util.Locale;

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
    public String userLogin(@ModelAttribute User user, Model model, BlogPost blogPost) {
        User registeredUser = userService.autenticate(user.getUserName(), user.getPassword());
        String title = blogPost.getTitle();
        model.addAttribute("title", blogPostService.getOneByTitle(title));
        if (registeredUser == null) {
            return "/login";
        } else {
            return "/author";
        }
    }

    @GetMapping("/index/{userName}/edit")
    public String editAuthor(Model model,
                             @PathVariable("userName") String name) {
        User forUpdate = userService.getCurrentUser(name);
        model.addAttribute("user", forUpdate);
        return "edit_author";
    }

    @PostMapping("/index/{userId}")
    public String updateAuthor(@PathVariable("userId") Long userId,
                               @Valid @ModelAttribute("user") User user,
                               BindingResult bindingResult,
                               Model model) {
        if(bindingResult.hasErrors()){
            model.addAttribute("user", user);
            return "edit_author";
        }
        user.setId(userId);
        userService.update(user);
        System.out.println("проверка" + user);
        return "redirect:/index";
    }






}

















































