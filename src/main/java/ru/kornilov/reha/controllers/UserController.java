package ru.kornilov.reha.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kornilov.reha.entities.User;
import ru.kornilov.reha.service.UserService;

import java.util.Map;

@Controller
public class UserController {
//    private static final Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;


    @PostMapping("/registration")
    public String addUser(@ModelAttribute User user, Map<String, Object> model) {

 //       logger.debug("running method addUser, on PostMapping /registration");
        User userFromDb = userService.findByUsername(user.getUsername());

        if(userFromDb != null){
            model.put("message", "User exists!");
            return "admin/admin-panel";
        }


        userService.addUser(user);

        return "admin/admin-panel";
    }

    @GetMapping("/access-denied")
    public String accessDenied(){
 //       logger.debug("running method accessDenied, on GetMapping /access-denied");

        return "main/access-denied";
    }



    @GetMapping("/profile")
    public String openProfile(@AuthenticationPrincipal User user, Model model){
//        logger.debug("running method openProfile, on GetMapping /profile");
        model.addAttribute("user", user);
        return "main/profile";
    }
}