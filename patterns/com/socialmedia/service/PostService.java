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

    public void likePost(int postIndex, User user) {
        // post index is 1-based, but array index is 0-based
        int postArrayIndex = postIndex - 1;
        if (postArrayIndex < 0 || postArrayIndex >= posts.size()) {
            System.out.println("Invalid post number. Please try again.");
            return;
        }

        Post post = posts.get(postArrayIndex);
        post.addLike(user);
    }

    public void unlikePost(int postIndex, User user) {
        // post index is 1-based, but array index is 0-based
        int postArrayIndex = postIndex - 1;
        if (postArrayIndex < 0 || postArrayIndex >= posts.size()) {
            System.out.println("Invalid post number. Please try again.");
            return;
        }

        Post post = posts.get(postArrayIndex);
        post.removeLike(user);
    }

    public void commentPost(int postIndex, User user, String comment) {
        // post index is 1-based, but array index is 0-based
        int postArrayIndex = postIndex - 1;
        if (postArrayIndex < 0 || postArrayIndex >= posts.size()) {
            System.out.println("Invalid post number. Please try again.");
            return;
        }

        Post post = posts.get(postArrayIndex);
        post.addComment(user, comment);
    }

    public boolean deletePost(int postId, User currentUser) {
        // post id is 1-based, but array index is 0-based
        int postIndex = postId - 1;
        if (postIndex < 0 || postIndex >= posts.size()) {
            System.out.println("Invalid post number. Please try again.");
            return false;
        }

        Post post = posts.get(postIndex);
        if (post.getAuthor().equals(currentUser)) {
            posts.remove(postIndex);
            return true;
        }else{
            // print both the users to show that the post author is different from the current user
            System.out.println(post.getAuthor().equals(currentUser));
        }

        System.out.println("You can only delete your own posts.");
        return false;
    }
}
