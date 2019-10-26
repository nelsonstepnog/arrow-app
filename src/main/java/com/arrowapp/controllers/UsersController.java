package com.arrowapp.controllers;

import com.arrowapp.domain.User;
import com.arrowapp.domain.ViewsData;
import com.arrowapp.repositories.UserRepository;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("users")
public class UsersController {

    @Autowired
    private final UserRepository userRepo;

    public UsersController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping
    @JsonView(ViewsData.DataName.class)
    public List<User> listUsers() {
        return userRepo.findAll();
    }

    @GetMapping("{id}")
    @JsonView(ViewsData.DataName.class)
    public User getOneUser(@PathVariable("id") User user) {
        return user;
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        user.setCreationDate(LocalDateTime.now());
        return userRepo.save(user);
    }

    @PutMapping("{id}")
    public User updateUser(
            @PathVariable("id") User userFromDB,
            @RequestBody User user
    ) {
        BeanUtils.copyProperties(user, userFromDB, "id");
        return userRepo.save(userFromDB);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") User user) {
        userRepo.delete(user);
    }
}