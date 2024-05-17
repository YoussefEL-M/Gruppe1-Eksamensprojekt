package com.example.gruppe1eksamensprojekt.service;

import com.example.gruppe1eksamensprojekt.model.*;
import com.example.gruppe1eksamensprojekt.model.User;
import com.example.gruppe1eksamensprojekt.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Service
public class UserService { // Severin
    // Metoder oprettet inden Repo er færdig.
    // Todo: test at alle metoderne virker efter Repo er pushet.

    @Autowired
    UserRepo userRepo;

    public List<User> getAll(){
        return userRepo.getAll();
    }

    public String createUser(String name, String username, String password, String email, String type, Model model, RedirectAttributes redirectAttributes){
        // Opdateret for at rykke logikken fra Controlleren ned i Service.
        // Simplificeret 16/05/24;

        try {
            userRepo.getUserByUsername(username);
        } catch (EmptyResultDataAccessException E){
            redirectAttributes.addAttribute("username", username);
            redirectAttributes.addAttribute("password", password);

            User newUser;

            switch (type){
                case("Dataregistrering") -> newUser = new DataUser(name, username, password, email, type);
                case("SkadeOgUdbedring") -> newUser = new DamageUser(name, username, password, email, type);
                case("Forretningsudvikler") -> newUser = new BusinessUser(name, username, password, email, type);
                default -> {
                    redirectAttributes.addAttribute("typeError", true); // Todo: implementer i frontend?
                    return "redirect:/register";
                }
            }

            userRepo.create(newUser);

            return "redirect:/";
        }
            model.addAttribute("usernameExists", true);
            return "register";
    }

    public User getUserById(int id){
        return userRepo.getUserById(id);
    }


    public void updateUser(User user){

        userRepo.update(user);
    }

    public void deleteUser(int id){
        userRepo.delete(id);
    }

    public User login(String username, String password, Model model){
        // Todo: tilføj getUserByUsername() til repo.
        // Todo: håndter login i frontend med Model attributter.
        User user;
        try {
            user = userRepo.getUserByUsername(username);
        } catch (EmptyResultDataAccessException E){
            model.addAttribute("userNotFound", true);
            return null;
        }
        if(user.getPassword().equals(password))
            return user;

        model.addAttribute("passwordMismatch", true);
        return null;

    }


    public User getUserByUsername(String username){
        return userRepo.getUserByUsername(username);
    }

}