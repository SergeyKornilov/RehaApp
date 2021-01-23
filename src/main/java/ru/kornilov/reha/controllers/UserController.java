package ru.kornilov.reha.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kornilov.reha.entities.User;
import ru.kornilov.reha.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;

@Controller
public class UserController {
//    private static final Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping("/admin")
    public String addUser(@ModelAttribute @Valid User newUser,
                        BindingResult bindingResult,
                          @AuthenticationPrincipal User user,
                          Map<String, Object> model) {


        if (bindingResult.hasErrors() ) {
            Set<String> errors = new HashSet<>();

            for (ObjectError error : bindingResult.getAllErrors()) { // 1.
                String fieldError = ((FieldError) error).getField(); // 2.
                errors.add(fieldError);
            }
            model.put("users", userService.allUsers());
            model.put("errors", errors);
            model.put("user", user);
            return "admin/admin-panel";
        }


 //     logger.debug("running method addUser, on PostMapping /registration");
        User userFromDb = userService.findByUsername(newUser.getUsername());

        if(userFromDb != null){
            model.put("message", "User exists!");
            return "admin/admin-panel";
        }


        userService.addUser(newUser);
        model.put("user", user);
        model.put("users", userService.allUsers());

        return "admin/admin-panel";
    }

    @GetMapping("/access-denied")
    public String accessDenied(){
 //       logger.debug("running method accessDenied, on GetMapping /access-denied")
        return "main/access-denied";
    }



    @GetMapping("/profile")
    public String openProfile(@AuthenticationPrincipal User user, Model model){
//        logger.debug("running method openProfile, on GetMapping /profile");
        model.addAttribute("user", user);
        return "main/profile";
    }

    @GetMapping("/user/delete/{username}")
    public String prescribingDelete(@PathVariable("username") String username, Model model, HttpServletRequest request) {
        userService.deleteUser(userService.findByUsername(username));
        String referer = request.getHeader("Referer");
        return "redirect:"+ referer;
    }
}