package be.intecbrussel.blog.controllers;

import be.intecbrussel.blog.data.User;
import be.intecbrussel.blog.repositories.UserRepository;
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

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }



    @GetMapping("/register")
    public String userRegistrationPage(Model model ) {
         User user = new User();
        model.addAttribute("user", user);
        return "signup";
    }

    @PostMapping("/register")
    public String submitForm(Model model, @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "signup";

            model.addAttribute("user", userService.createUser(user));
            return "redirect:/index";


    }




}




































