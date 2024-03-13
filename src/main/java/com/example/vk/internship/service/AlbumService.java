package com.example.vk.internship.service;

import com.example.vk.internship.clients.AlbumClient;
import com.example.vk.internship.model.Album;
import com.example.vk.internship.repository.AlbumRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumService {
    private final AlbumClient albumClient;
    private final AlbumRepository albumRepository;

    public AlbumService(AlbumRepository albumRepository, AlbumClient albumClient) {
        this.albumRepository = albumRepository;
        this.albumClient = albumClient;
    }

    public List<Album> findAll() {
        List<Album> albums = albumRepository.findAll();
        return !albums.isEmpty() ? albums : albumClient.findAll();
    }

    public Album findById(Long id) {
        return albumRepository.findById(id).orElseGet(() -> albumClient.findById(id));
    }

    public void updateAlbum(Long id, Album album) {
        albumRepository.findById(id).ifPresent(a -> {
            a.setTitle(album.getTitle());
            a.setUserId(album.getUserId());
            albumRepository.save(a);
        });
    }

    public void deleteAlbum(Long id) {
        albumRepository.deleteById(id);
    }

    public Album createAlbum(Album album) {
        return albumRepository.save(album);
    }
}
