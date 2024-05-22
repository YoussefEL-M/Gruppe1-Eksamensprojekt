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
            redirectAttributes.addFlashAttribute("username", username);
            redirectAttributes.addFlashAttribute("password", password);

            User newUser;

            switch (type){
                case("Dataregistrering") -> newUser = new DataUser(name, username, password, email, type);
                case("SkadeOgUdbedring") -> newUser = new DamageUser(name, username, password, email, type);
                case("Forretningsudvikler") -> newUser = new BusinessUser(name, username, password, email, type);
                default -> {
                    redirectAttributes.addFlashAttribute("typeError", true); // Todo: implementer i frontend?
                    return "redirect:/register";
                }
            }

            userRepo.create(newUser);
            redirectAttributes.addFlashAttribute("username", username);
            redirectAttributes.addFlashAttribute("password", password);

            return "redirect:/";
        }

            redirectAttributes.addFlashAttribute("name", name);
            redirectAttributes.addFlashAttribute("email", email);
            redirectAttributes.addFlashAttribute("password", password);
            redirectAttributes.addFlashAttribute("confirmPassword", password);
            redirectAttributes.addFlashAttribute("type", type);
            redirectAttributes.addFlashAttribute("usernameExists", true);
            return "redirect:/createUser";
    }

    //Opdater i klassediagram
    public User getUserById(int id){

        User user;
        try {
            user = userRepo.getUserById(id);
        } catch (EmptyResultDataAccessException ERDA) {
            return null;
        }

        if (user.getId() == id) {

            return user;
        }
        return null;
    }


    public void updateUser(User user){

        userRepo.update(user);
    }

    public void deleteUser(int id){
        userRepo.delete(id);
    }

    public User login(String username, String password, RedirectAttributes redirectAttributes){
        // Todo: tilføj getUserByUsername() til repo.
        // Todo: håndter login i frontend med Model attributter.
        User user;
        try {
            user = userRepo.getUserByUsername(username);
        } catch (EmptyResultDataAccessException E){
            redirectAttributes.addFlashAttribute("userNotFound", true);
            return null;
        }
        if(user.getPassword().equals(password))
            return user;

        redirectAttributes.addFlashAttribute("passwordMismatch", true);
        redirectAttributes.addFlashAttribute("username", username);
        return null;

    }


    public User getUserByUsername(String username){
        return userRepo.getUserByUsername(username);
    }

}