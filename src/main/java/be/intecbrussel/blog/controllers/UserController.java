package be.intecbrussel.blog.controllers;

import be.intecbrussel.blog.data.User;
import be.intecbrussel.blog.repositories.UserRepository;
import be.intecbrussel.blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/register")
    public String userRegistrationPage(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "signup";
    }

    @PostMapping("/register")
    public String submitForm(Model model, @ModelAttribute("user") User user) {
        model.addAttribute("user", userService.createUser(user));
        return "redirect:/index";

    }


    @RequestMapping(value = "password", method = {RequestMethod.GET, RequestMethod.POST})
        public String password(Model model, @PathVariable String password) {
           String chekedPassword = password;
           model.addAttribute("password", password);
           return "password";
    }























}








