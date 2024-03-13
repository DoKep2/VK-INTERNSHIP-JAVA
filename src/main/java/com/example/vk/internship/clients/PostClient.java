package com.example.vk.internship.clients;

import com.example.vk.internship.model.Post;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "postClient")
public interface PostClient {
    @GetMapping("/")
    List<Post> findAll();

    @GetMapping("/{id}")
    Post findById(@PathVariable(name = "id") long id);
}
