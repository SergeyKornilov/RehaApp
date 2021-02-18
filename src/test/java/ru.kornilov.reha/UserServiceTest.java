package ru.kornilov.reha;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.multipart.MultipartFile;
import ru.kornilov.reha.DAO.UserDAO;
import ru.kornilov.reha.entities.User;
import ru.kornilov.reha.exceptions.UploadImgException;
import ru.kornilov.reha.service.UserServiceImpl;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    UserDAO userDAO;
    @Mock
    MultipartFile file;
    @Mock
    File uploadDir;


    private static final String name = "Name";

    @Test
    public void whenFindUserByUsernameIsNullThenException() {

        User user = new User();
        user.setUsername(name);
        when(userDAO.findByUsername(name)).thenReturn(user);
        String username = userService.findByUsername(name).getUsername();
        assertEquals(name, username);
    }

    @Test
    public void uploadEmptyFileTest() throws UploadImgException {
        assertEquals("You need to select file first!", userService.addImg(new User(), file));
    }

    @Test
    public void createUserDuplicateUsername(){
        String username = "Username";
        User user = new User();
        user.setUsername(username);
        User duplicateUsernameUser = new User();
        duplicateUsernameUser.setUsername(username);
        BindingResult errors = new BeanPropertyBindingResult(duplicateUsernameUser, "errors");
        when(userService.findByUsername(username)).thenReturn(user);
        assertEquals("[duplicate username]", userService.createUser(errors, duplicateUsernameUser).toString());
    }

}