package com.example.vk.internship.service;

import com.example.vk.internship.clients.UserClient;
import com.example.vk.internship.model.MyUser;
import com.example.vk.internship.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserClient userClient;
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository, UserClient userClient) {
        this.userRepository = userRepository;
        this.userClient = userClient;
    }

    public List<MyUser> findAll() {
        List<MyUser> myUsers = userRepository.findAll();
        return !myUsers.isEmpty() ? myUsers : userClient.findAll();
    }

    public MyUser findById(Long id) {
        return userRepository.findById(id).orElseGet(() -> userClient.findById(id));
    }

    public void updateUser(Long id, MyUser myUser) {
        userRepository.findById(id).ifPresent(u -> {
            u.setName(myUser.getName());
            u.setUsername(myUser.getUsername());
            u.setEmail(myUser.getEmail());
            userRepository.save(u);
        });
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public MyUser createUser(MyUser myUser) {
        return userRepository.save(myUser);
    }
}
