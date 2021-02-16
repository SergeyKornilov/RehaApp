package ru.kornilov.reha.service.interfaces;

import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;
import ru.kornilov.reha.entities.User;
import ru.kornilov.reha.exceptions.UploadImgException;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface UserService {

    User findByUsername(String name);


    void addUser(User user);


    void updateUser(User user);


    void deleteUser(User user);


    List<User> allUsers();


    List<User> findAllUsersRolDoctor();


    void activateUser(String code);


    String addImg(User user, MultipartFile file) throws UploadImgException;

    String getAuthorizedRedirect();


    Set<String> editUser(BindingResult bindingResult, User newUser);


    Set<String> createUser(BindingResult bindingResult, User newUser);

    Set<String> validateUser(BindingResult bindingResult, User newUser);
}
