package com.socialmedia.controller.posts;

import com.socialmedia.service.AuthService;
import com.socialmedia.service.PostService;

import com.socialmedia.controller.Command;

public class LikePostCommand implements Command {

    private final AuthService authService;
    private final PostService postService;

    private final int postIndex;

    public LikePostCommand(AuthService authService, PostService postService, int postIndex) {
        this.authService = authService;
        this.postService = postService;
        this.postIndex = postIndex;
    }

    @Override
    public void execute() {
        postService.likePost(postIndex, authService.getCurrentUser());
    }
}
