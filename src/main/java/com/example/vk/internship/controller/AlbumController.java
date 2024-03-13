package com.example.vk.internship.controller;

import com.example.vk.internship.model.Album;
import com.example.vk.internship.service.AlbumService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/albums")
public class AlbumController {
    private final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping
    public List<Album> getAlbums() {
        return albumService.findAll();
    }

    @PostMapping
    public Album createAlbum(@RequestBody Album album) {
        return albumService.createAlbum(album);
    }

    @GetMapping("/{id}")
    public Album getAlbumById(@PathVariable Long id) {
        return albumService.findById(id);
    }

    @PutMapping("/{id}")
    public void updateAlbum(@PathVariable Long id, @RequestBody Album album) {
        albumService.updateAlbum(id, album);
    }

    @DeleteMapping("/{id}")
    public void deleteAlbum(@PathVariable Long id) {
        albumService.deleteAlbum(id);
    }
}
