package ru.kornilov.reha.exceptions;


import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;
import ru.kornilov.reha.entities.User;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public String handleMaxUploadException(Model model, @AuthenticationPrincipal User user){
        model.addAttribute("user", user);
        model.addAttribute("error", "Max upload file size 300Kb!");
        return "main/profile";
    }

    @ExceptionHandler(SecurityConfigureException.class)
    public String handlerSecurityConfigureException(Model model){
        model.addAttribute("error", "Security configure error");
        return "errors/error-page";
    }

    @ExceptionHandler(UploadImgException.class)
    public String handleUploadImgException(Model model){
        model.addAttribute("error", "Upload image exception");
        return "errors/error-page";
    }

    @ExceptionHandler(SQLException.class)
    public String handleSQLException(Model model) {
//        logger.error("SQL Exception!");
        model.addAttribute("error", "SQL exception");

        return "errors/error-page";
    }

    @ExceptionHandler(NullPointerException.class)
    public String handleNullPointerException(Model model) {
//        logger.error("Null-pointer Exception!");
        model.addAttribute("error", "Null point exception");

        return "errors/error-page";
    }

    @ExceptionHandler(value = Exception.class)
    public String defaultErrorHandler(Exception e, Model model) {
        model.addAttribute("error", e);
        return "errors/error-page";
    }
}