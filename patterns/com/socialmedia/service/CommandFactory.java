package com.socialmedia.service;

import com.socialmedia.controller.Command;
import com.socialmedia.controller.CreatePostCommand;
import com.socialmedia.controller.LoginCommand;
import com.socialmedia.controller.SignUpCommand;
import com.socialmedia.model.User;

import java.util.Scanner;

public class CommandFactory {
    private final AuthService authService;
    private final PostService postService;
    private final Scanner scanner;

    public CommandFactory(AuthService authService, PostService postService) {
        this.authService = authService;
        this.postService = postService;
        this.scanner = new Scanner(System.in);
    }

    public Command getCommand() {
        String input = scanner.nextLine();
        User currentUser = authService.getCurrentUser();

        switch (input) {
            case "1":
                return new SignUpCommand(scanner, authService);
            case "2":
                return new LoginCommand(scanner, authService);
            case "3":
                if (currentUser != null) {
                    return new CreatePostCommand(scanner, postService, currentUser);
                } else {
                    System.out.println("Please log in first.");
                }
                break;
            case "4":
                return null; // exit
            default:
                System.out.println("Invalid option. Please try again.");
        }
        return () -> {}; // empty command
    }
}
