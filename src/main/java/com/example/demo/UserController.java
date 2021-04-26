package com.example.demo;

import com.example.demo.error_handling.UserNotFoundException;
import com.example.demo.interfaces.CrudUserRepository;
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
    public String createUser(@RequestBody User user) {
        crudUserRepository.save(user);
        return "Registration successful!";
    }

    @GetMapping(value = "/users")
    public List<User> searchPage(@RequestParam(value = "name", required = false) String name) {
        List<User> userList;
        if (name != null && !name.equals("")) {
            userList = new ArrayList<>(crudUserRepository.findByNameLike(name));
            if (userList.isEmpty()){
                throw new UserNotFoundException();
            }
        } else {
            userList = new ArrayList<>();
            for (User user : crudUserRepository.findAll()) {
                userList.add(user);
            }
        }
        return userList;
    }
}