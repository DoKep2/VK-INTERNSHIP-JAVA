package com.example.vk.internship.clients;

import com.example.vk.internship.model.Album;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "albumClient")
public interface AlbumClient {
    @GetMapping("/")
    List<Album> findAll();

    @GetMapping("/{id}")
    Album findById(@PathVariable(name = "id") long id);
}
