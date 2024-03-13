package com.example.vk.internship.controller;

import com.example.vk.internship.model.MyUser;
import com.example.vk.internship.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<MyUser> getUsers() {
        return userService.findAll();
    }

    @PostMapping
    public MyUser createUser(@RequestBody MyUser myUser) {
        return userService.createUser(myUser);
    }

    @GetMapping("/{id}")
    public MyUser getUserById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PutMapping("/{id}")
    public void updateUser(@PathVariable Long id, @RequestBody MyUser myUser) {
        userService.updateUser(id, myUser);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
