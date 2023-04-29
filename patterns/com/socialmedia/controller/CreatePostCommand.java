package com.socialmedia.controller;

import com.socialmedia.model.User;
import com.socialmedia.service.PostService;

import java.util.Scanner;

public class CreatePostCommand implements Command {
    private final Scanner scanner;
    private final PostService postService;
    private final User currentUser;

    public CreatePostCommand(Scanner scanner, PostService postService, User currentUser) {
        this.scanner = scanner;
        this.postService = postService;
        this.currentUser = currentUser;
    }

    @Override
    public void execute() {
        System.out.println("Enter your post:");
        String content = scanner.nextLine();
        postService.createPost(currentUser, content);
    }
}
