package com.socialmedia.service;

import com.socialmedia.model.Post;
import com.socialmedia.model.User;

import java.util.ArrayList;

public class PostService {
    private ArrayList<Post> posts;

    public PostService() {
        this.posts = new ArrayList<>();
    }

    public ArrayList<Post> getAllPosts() {
        return posts;
    }

    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }

    public void createPost(User author, String content) {
        Post newPost = new Post(author, content);
        posts.add(newPost);
    }
}
