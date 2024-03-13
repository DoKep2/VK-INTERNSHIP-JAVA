package com.example.vk_internship;

import com.example.vk.internship.Application;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class RolesTests {

    private final static String BASE_URL = "/api";

    @Autowired
    public MockMvc mockMvc;

    @Test
    @DisplayName("Unauthorized for unauthenticated users")
    public void accessDeniedForUnauthenticatedUsers() throws Exception {
        mockMvc.perform(get(BASE_URL + "/users "))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("Access allowed to /users for authenticated users with role USERS")
    @WithMockUser(username = "user", roles = {"USERS"})
    public void successAuth() throws Exception {
        mockMvc.perform(get(BASE_URL + "/users"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Access denied to /users for authenticated users with role POSTS")
    @WithMockUser(username = "user", roles = {"POSTS"})
    public void accessDeniedForPosts() throws Exception {
        mockMvc.perform(get(BASE_URL + "/users"))
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("Access denied to /users for authenticated users with role ALBUMS")
    @WithMockUser(username = "user", roles = {"ALBUMS"})
    public void accessDeniedForAlbums() throws Exception {
        mockMvc.perform(get(BASE_URL + "/users"))
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("Access allowed to /** for authenticated users with role ADMIN")
    @WithMockUser(username = "user", roles = {"ADMIN"})
    public void accessAllowedForAdmin() throws Exception {
        mockMvc.perform(get(BASE_URL + "/users"))
                .andExpect(status().isOk());
        mockMvc.perform(get(BASE_URL + "/posts"))
                .andExpect(status().isOk());
        mockMvc.perform(get(BASE_URL + "/albums"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Access denied to /posts for authenticated users with role USERS")
    @WithMockUser(username = "user", roles = {"USERS"})
    public void accessDeniedForUsers() throws Exception {
        mockMvc.perform(get(BASE_URL + "/posts"))
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("Access allowed to /posts for authenticated users with role POSTS")
    @WithMockUser(username = "user", roles = {"POSTS"})
    public void accessAllowedForPosts() throws Exception {
        mockMvc.perform(get(BASE_URL + "/posts"))
                .andExpect(status().isOk());
    }
}
