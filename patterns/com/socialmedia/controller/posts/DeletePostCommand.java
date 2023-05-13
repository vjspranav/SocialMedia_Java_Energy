package com.socialmedia.controller.posts;

import com.socialmedia.service.AuthService;
import com.socialmedia.service.PostService;

import com.socialmedia.controller.Command;

public class DeletePostCommand implements Command {

    private final AuthService authService;
    private final PostService postService;

    private final int postIndex;

    public DeletePostCommand(AuthService authService, PostService postService, int postIndex) {
        this.authService = authService;
        this.postService = postService;
        this.postIndex = postIndex;
    }

    @Override
    public void execute() {
        boolean status = postService.deletePost(postIndex, authService.getCurrentUser());
        if (status) {
            System.out.println("Post deleted successfully!");
        } else {
            System.out.println("Post deletion failed. Please try again.");
        }
    }    
}
