package com.socialmedia.controller.posts;

import com.socialmedia.controller.Command;

import com.socialmedia.service.AuthService;
import com.socialmedia.service.PostService;

import java.util.Scanner;

public class CommentPostCommand implements Command {
    
    private final AuthService authService;
    private final PostService postService;
    private final Scanner scanner;

    private final int postIndex;

    public CommentPostCommand(AuthService authService, PostService postService, int postIndex){
        this.authService = authService;
        this.postService = postService;
        this.postIndex = postIndex;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void execute() {
        System.out.println("Enter your comment:");
        String comment = scanner.nextLine();
        postService.commentPost(postIndex, authService.getCurrentUser(), comment);
    }
}
