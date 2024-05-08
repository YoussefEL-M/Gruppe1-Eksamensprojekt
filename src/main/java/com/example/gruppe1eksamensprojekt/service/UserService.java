package com.example.gruppe1eksamensprojekt.service;

import com.example.gruppe1eksamensprojekt.model.User;
import com.example.gruppe1eksamensprojekt.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

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

    public void createUser(User user){
        userRepo.create(user);
    }

    public User getUserById(int id){
        return userRepo.getUserById(id);
    }

    public updateUser(int id){
        // Skal den ikke have et User objekt som parameter?
        // Indtil videre gør jeg, som der står i klassediagrammet - Severin.
        userRepo.update(id);
    }

    public deleteUser(int id){
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

}