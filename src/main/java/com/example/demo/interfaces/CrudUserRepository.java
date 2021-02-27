package com.example.demo.interfaces;

import com.example.demo.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CrudUserRepository extends CrudRepository<User, Long> {
    List<User> findByNameLike(String name);
}
