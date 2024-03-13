package com.example.vk.internship.service;

import com.example.vk.internship.clients.PostClient;
import com.example.vk.internship.model.Post;
import com.example.vk.internship.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private final PostClient postClient;
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository, PostClient postClient) {
        this.postRepository = postRepository;
        this.postClient = postClient;
    }

    public List<Post> findAll() {
        List<Post> posts = postRepository.findAll();
        return !posts.isEmpty() ? posts : postClient.findAll();
    }

    public Post findById(Long id) {
        return postRepository.findById(id).orElseGet(() -> postClient.findById(id));
    }

    public void updatePost(Long id, Post post) {
        postRepository.findById(id).ifPresent(p -> {
            p.setTitle(post.getTitle());
            p.setBody(post.getBody());
            p.setUserId(post.getUserId());
            postRepository.save(p);
        });
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    public Post createPost(Post post) {
        return postRepository.save(post);
    }
}
