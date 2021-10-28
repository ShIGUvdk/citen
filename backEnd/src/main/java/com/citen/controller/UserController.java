package com.citen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.citen.repository.UserRepository;
import com.citen.model.User;

import org.springframework.beans.factory.annotation.Autowired;


@Controller
@RequestMapping(path="/user")
public class UserController {
    @Autowired // This means to get the bean called userRepository
    private UserRepository userRepository;

    @PostMapping(path = "/add")
    public @ResponseBody String addNewUser(@RequestParam String login, @RequestParam String pass, @RequestParam String email){
        User user = new User();
        user.setLogin(login);
        user.setPass(pass);
        user.setEmail(email);
        userRepository.save(user);

        return "saved"+user.getLogin();
    }

    @PostMapping(path="/upd")
    public @ResponseBody String updUser(@RequestParam Integer id, @RequestParam String login, @RequestParam String pass, @RequestParam String email){
        User user = new User();
        user.setId(id);
        user.setLogin(login);
        user.setPass(pass);
        user.setEmail(email);
        userRepository.save(user);

        return "saved user id="+user.getId();
    }

    @PostMapping(path="/all")
    private @ResponseBody Iterable<User> getAllUser(){
        return userRepository.findAll();
    }

    @PostMapping(path="/delete")
    private @ResponseBody String userDelete(@RequestParam Integer id){
        userRepository.deleteById(id);
        
        return "delete id="+id;
    }
}
