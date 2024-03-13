package com.example.vk.internship.clients;

import com.example.vk.internship.model.MyUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "userClient")
public interface UserClient {
    @GetMapping("/")
    List<MyUser> findAll();

    @GetMapping("/{id}")
    MyUser findById(@PathVariable(name = "id") long id);
}
