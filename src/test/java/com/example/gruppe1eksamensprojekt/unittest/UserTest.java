package com.example.gruppe1eksamensprojekt.unittest;

import com.example.gruppe1eksamensprojekt.model.User;
import com.example.gruppe1eksamensprojekt.repository.UserRepo;
import com.example.gruppe1eksamensprojekt.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import java.util.List;

@SpringBootTest
public class UserTest {

    @Autowired
    UserService userService;

    @Autowired
    UserRepo userRepo;

    @BeforeEach
    void setup(){}

    @AfterEach
    void teardown(){}


    @Test
    void getAllTest(){

        //Arrange
        List<User> list;

        //Act
        list = userService.getAll();

        //Assert
        assert !list.isEmpty();
    }

    @Test
    void createUserTest(){

        //Arrange
        String name = "John Testman";
        String username = "JTestman";
        String password = "JTest";
        String email = "JTest@mail.test";
        String type = "Dataregistrering";
        Model model = new ConcurrentModel();
        RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

        //Act
        userService.createUser(name, username, password, email, type, model, redirectAttributes);

        //Assert
        assert userService.login(username, password, redirectAttributes)!=null;
    }

    @Test
    void getUserByIdTest(){

        //Arrange
        String name = "John Testman";
        String username = "JTestman";
        String password = "JTest";
        String email = "JTest@mail.test";
        String type = "Dataregistrering";
        Model model = new ConcurrentModel();
        RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();
        int id;
        userService.createUser(name, username, password, email, type, model, redirectAttributes);

        //Act
        id = userRepo.getUserByUsername(username).getId();

        //Assert
        assert userService.getUserById(id)!=null;

        //Cleanup
        userService.deleteUser(id);
    }

    @Test
    void updateUserTest(){

        //Arrange
        String name = "John Testman";
        String username = "JTestman";
        String password = "JTest";
        String email = "JTest@mail.test";
        String type = "Dataregistrering";
        Model model = new ConcurrentModel();
        RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();
        userService.createUser(name, username, password, email, type, model, redirectAttributes);
        User user = userRepo.getUserByUsername("JTestman");

        //Act
        user.setUsername("Jim Testman");
        userService.updateUser(user);

        //Assert
        assert userRepo.getUserByUsername("Jim Testman")!=null;

        //Cleanup
        userService.deleteUser(user.getId());
    }

    @Test
    void deleteUserTest(){

        //Arrange
        String name = "John Testman";
        String username = "JTestman";
        String password = "JTest";
        String email = "JTest@mail.test";
        String type = "Dataregistrering";
        Model model = new ConcurrentModel();
        RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();
        userService.createUser(name, username, password, email, type, model, redirectAttributes);
        User user = userRepo.getUserByUsername("JTestman");

        //Act
        userService.deleteUser(user.getId());

        //Assert
        assert userService.getUserById(user.getId())==null;
    }

    @Test
    void loginTest(){

        //Arrange
        String name = "John Testman";
        String username = "JTestman";
        String password = "JTest";
        String email = "JTest@mail.test";
        String type = "Dataregistrering";
        Model model = new ConcurrentModel();
        RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();
        userService.createUser(name, username, password, email, type, model, redirectAttributes);

        //Act
        User user = userService.login(username, password, redirectAttributes);

        //Assert
        assert user!=null;

        //Cleanup
        userService.deleteUser(user.getId());
        
    }

}
