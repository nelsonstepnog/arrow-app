package com.arrowapp.api.v1.controllers;

import com.arrowapp.api.v1.domain.User;
import com.arrowapp.api.v1.domain.ViewsData;
import com.arrowapp.api.v1.repositories.UserRepository;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
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
    @JsonView(ViewsData.DataName.class)
    public User updateUser(
            @PathVariable("id") User userFromDB,
            @RequestBody User user
    ) {
        BeanUtils.copyProperties(user, userFromDB, "id");
        return userRepo.save(userFromDB);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") User userFromDB) {
        userRepo.delete(userFromDB);
    }

    @MessageMapping("/changeUsers")
    @SendTo("/topic/users")
    public User change(User user) {
        return userRepo.save(user);
    }
}