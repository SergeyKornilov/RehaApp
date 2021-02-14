package ru.kornilov.reha.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerExceptionResolver;
import ru.kornilov.reha.entities.User;
import ru.kornilov.reha.service.UserService;

import javax.servlet.MultipartConfigElement;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;


@Controller
public class UserController {
    private static final Logger logger = Logger.getLogger(UserController.class);


    @Autowired
    private UserService userService;

    @PostMapping(path = "/admin", params = {"action=editUser"})
    public String editUser(@ModelAttribute @Valid User newUser,
                           BindingResult bindingResult,
                           @AuthenticationPrincipal User user,
                           Map<String, Object> model) {

//        if (bindingResult.hasErrors()) {
//            Set<String> errors = new HashSet<>();
//
//            for (ObjectError error : bindingResult.getAllErrors()) {
//                String fieldError = ((FieldError) error).getField();
//                errors.add(fieldError);
//            }
//
//
//            model.put("users", userService.allUsers());
//            model.put("errors", errors);
//            model.put("user", user);
//
//            return "admin/admin-panel";
//        }

        Set<String> errors = userService.editUser(bindingResult, newUser);

        userService.updateUser(newUser);
        model.put("user", user);
        model.put("errors", errors);
        model.put("users", userService.allUsers());

        return "admin/admin-panel";

    }

    @PostMapping(path = "/admin", params = {"action=addUser"})
    public String addUser(@ModelAttribute @Valid User newUser,
                          BindingResult bindingResult,
                          @AuthenticationPrincipal User user,
                          Map<String, Object> model) {


//        if (bindingResult.hasErrors()) {

            Set<String> errors = userService.createUser(bindingResult, newUser);

            model.put("users", userService.allUsers());
            model.put("errors", errors);
            model.put("user", user);

            return "admin/admin-panel";
//        }

//        logger.debug("running method addUser, on PostMapping /registration");
//
//        User userFromDb = userService.findByUsername(newUser.getUsername());
//
//        if (userFromDb != null) {
//            model.put("message", "User with the same username exists!");
//            model.put("users", userService.allUsers());
//            model.put("user", user);
//            return "admin/admin-panel";
//        }
//
//        userService.addUser(newUser);
//        model.put("user", user);
//        model.put("users", userService.allUsers());
//
//        return "admin/admin-panel";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        logger.debug("running method accessDenied, on GetMapping /access-denied");
        return "main/access-denied";
    }


    @GetMapping("/profile")
    public String openProfile(@AuthenticationPrincipal User user, Model model) {
        logger.debug("running method openProfile, on GetMapping /profile");
        model.addAttribute("user", user);
        return "main/profile";
    }


    @PostMapping("/profile")
    public String uploadImg(@RequestParam("file") MultipartFile file,
                            @AuthenticationPrincipal User user,
                            Model model) throws IOException{
        model.addAttribute("error", userService.addImg(user, file));
        model.addAttribute("user", user);
        return "main/profile";
    }

    @GetMapping("/user/delete/{username}")
    public String prescribingDelete(@PathVariable("username") String username, Model model, HttpServletRequest request) {
        userService.deleteUser(userService.findByUsername(username));
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }

    @GetMapping("/user/activate/{code}")
    public String activateUser(Model model, @PathVariable String code){
        userService.activateUser(code);
        model.addAttribute("successActivate","Your account has been successfully activated!");
        return "main/login";
    }
}