package com.socialmedia.service;

import com.socialmedia.controller.Command;
import com.socialmedia.controller.CreatePostCommand;
import com.socialmedia.controller.LoginCommand;
import com.socialmedia.controller.LogoutCommand;
import com.socialmedia.controller.SignUpCommand;
import com.socialmedia.controller.ViewPostsCommand;

import com.socialmedia.controller.posts.CommentPostCommand;
import com.socialmedia.controller.posts.LikePostCommand;
import com.socialmedia.controller.posts.UnlikePostCommand;
import com.socialmedia.controller.posts.DeletePostCommand;

import com.socialmedia.model.User;

import java.util.Scanner;

public class CommandFactory {
    private final AuthService authService;
    private final PostService postService;
    private final Scanner scanner;

    private int postIndex;

    public CommandFactory(AuthService authService, PostService postService) {
        this.authService = authService;
        this.postService = postService;
        this.scanner = new Scanner(System.in);
    }

    public CommandFactory(AuthService authService, PostService postService, int postIndex) {
        this(authService, postService);
        this.postIndex = postIndex;
    };

    public Command getCommand() {
        String input = scanner.nextLine();
        User currentUser = authService.getCurrentUser();

        if (postIndex > 0) {
            return handlePostCommands(input, currentUser);
        }

        if (currentUser == null) {
            return handleNotLoggedInCommands(input);
        } else {
            return handleLoggedInCommands(input, currentUser);
        }
    }

    private Command handleNotLoggedInCommands(String input) {
        switch (input) {
            case "1":
                return new SignUpCommand(scanner, authService);
            case "2":
                return new LoginCommand(scanner, authService);
            case "9":
                return null; // exit
            default:
                System.out.println("Invalid option. Please try again.");
                return () -> {
                }; // empty command
        }
    }


    private Command handleLoggedInCommands(String input, User currentUser) {
        System.out.println("Welcome, " + currentUser.getUsername() + "!");
        switch (input) {
            case "1":
                return new CreatePostCommand(scanner, postService, currentUser);
            case "2":
                return new ViewPostsCommand(authService, postService);
            case "3":
                return new LogoutCommand(authService);
            case "9":
                return null; // exit
            default:
                System.out.println("Invalid option. Please try again.");
                return () -> {
                }; // empty command
        }
    }

    private Command handlePostCommands(String input, User currentUser) {
        switch (input) {
            case "1":
                return new LikePostCommand(authService, postService, postIndex);
            case "2":
                return new UnlikePostCommand(authService, postService, postIndex);
            case "3":
                return new CommentPostCommand(authService, postService, postIndex);
            case "4":
                return new DeletePostCommand(authService, postService, postIndex);
            case "5":
                return null; // exit
            default:
                System.out.println("Invalid option. Please try again.");
                return () -> {
                }; // empty command
        }
    }
}
