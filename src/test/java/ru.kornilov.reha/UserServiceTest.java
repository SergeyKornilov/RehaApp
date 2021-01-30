package ru.kornilov.reha;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ru.kornilov.reha.DAO.UserDAO;
import ru.kornilov.reha.entities.User;
import ru.kornilov.reha.service.UserService;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;
    @Mock
    UserDAO userDAO;

    private static final String name = "Name";


    @Test
    public void whenFindUserByUsernameIsNullThenException() {

        User user = new User();
        user.setUsername(name);

        when(userDAO.findByUsername(name)).thenReturn(user);

        String username = userService.findByUsername(name).getUsername();

        assertEquals(name, username);
    }

}