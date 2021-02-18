package ru.kornilov.reha.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.kornilov.reha.entities.User;
import ru.kornilov.reha.exceptions.UploadImgException;
import ru.kornilov.reha.service.interfaces.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * This controller is responsible for
 * requests related with users
 */
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
            Set<String> errors = userService.createUser(bindingResult, newUser);
            model.put("users", userService.allUsers());
            model.put("errors", errors);
            model.put("user", user);
            return "admin/admin-panel";
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
                            Model model) throws UploadImgException {
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