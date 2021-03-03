package com.example.demo;

import com.example.demo.interfaces.CrudUserRepository;
import dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private CrudUserRepository crudUserRepository;

    @PostMapping(value = "/users")
    @ResponseStatus(HttpStatus.CREATED)
    public String createUser(@RequestBody UserDTO userDTO) {
        if (!crudUserRepository.findByNameLike(userDTO.getName()).isEmpty()) {
            return "This username already exists.";
        } else {
            User user = new User();
            user.setName(userDTO.getName());
            user.setPassword(userDTO.getPassword());
            crudUserRepository.save(user);
            return "Registration successful!";
        }
    }

    @GetMapping(value = "/results")
    @ResponseStatus(HttpStatus.CREATED)
    public Object searchPage(@RequestParam("name") String name) {
        List<User> userList;
        if (!name.equals("")) {
            userList = new ArrayList<>(crudUserRepository.findByNameLike(name));
            return (userList.isEmpty() ? "There is no users with name like this!" : userList);
        } else {
            userList = new ArrayList<>();
            for (User user : crudUserRepository.findAll()) {
                userList.add(user);
            }
            return userList;
        }
    }
}