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

    public boolean deletePost(int postId, User currentUser) {
        // post id is 1-based, but array index is 0-based
        int postIndex = postId - 1;
        if (postIndex < 0 || postIndex >= posts.size()) {
            return false;
        }

        Post post = posts.get(postIndex);
        if (post.getAuthor().equals(currentUser)) {
            posts.remove(postIndex);
            return true;
        }
        return false;
    }
}
