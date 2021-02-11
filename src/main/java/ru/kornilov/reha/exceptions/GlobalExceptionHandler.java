package ru.kornilov.reha.exceptions;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public String handleMaxUploadException(MaxUploadSizeExceededException e, HttpServletRequest request, HttpServletResponse response){
//        ModelAndView mav = new ModelAndView();
//        boolean isJson = request.getRequestURL().toString().contains(".json");
//        if (isJson) {
//            mav.setView(new MappingJacksonJsonView());
//            mav.addObject("result", "nok");
//        }
//        else mav.setViewName("uploadError");
        return "parts/footer";
    }

}
