package com.socialmedia.controller;

import com.socialmedia.service.AuthService;

import java.util.Scanner;

public class SignUpCommand implements Command {
    private final Scanner scanner;
    private final AuthService authService;

    public SignUpCommand(Scanner scanner, AuthService authService) {
        this.scanner = scanner;
        this.authService = authService;
    }

    @Override
    public void execute() {
        System.out.println("Enter your username:");
        String username = scanner.nextLine();
        System.out.println("Enter your password:");
        String password = scanner.nextLine();

        boolean success = authService.signUp(username, password);
        if (success) {
            System.out.println("Sign up successful!");
        } else {
            System.out.println("Username already taken.");
        }
    }
}
