package ru.kornilov.reha.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Service;
import ru.kornilov.reha.service.interfaces.LoginService;
import ru.kornilov.reha.service.interfaces.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * This class contains methods responsible for authorization
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserService userService;

    /**
     * This method gets User by username from DB
     * @param username String username
     * @return username
     * @throws UsernameNotFoundException user not find in DB
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userService.findByUsername(username);
    }

    /**
     * This method gets error message
     * @param request HttpServletRequest request
     * @return String error message
     */
    @Override
    public String getErrorMessage(HttpServletRequest request){

        HttpSession session = request.getSession(false);
        String errorMessage = null;

        if (session != null) {
            AuthenticationException ex = (AuthenticationException) session
                    .getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
            if (ex != null) {
                errorMessage = ex.getMessage();
            }
        }
        return errorMessage;
    }
}