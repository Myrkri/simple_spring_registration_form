package com.example.demo;

import com.example.demo.interfaces.CrudUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MainController {

    @Autowired
    private CrudUserRepository crudUserRepository;

    @PostMapping(value = {"/", "/home"})
    public String homePage(@RequestParam("name") String name, @RequestParam("password") String password) {
        User users = new User();
        boolean temp = crudUserRepository.findByNameLike(name).isEmpty();
        String text;
        if (!temp) {
            text = "This username already exists.";
        } else {
            users.setName(name);
            users.setPassword(password);
            crudUserRepository.save(users);
            text = "Registration successful!";
        }
        return text;
    }

    @GetMapping(value = "/users")
    public List<User> searchPage(@RequestParam("name") String name){
        return crudUserRepository.findByNameLike(name);
    }
}
