package com.socialmedia.controller;

import com.socialmedia.service.AuthService;

import java.util.Scanner;

public class LoginCommand implements Command {
    private final Scanner scanner;
    private final AuthService authService;

    public LoginCommand(Scanner scanner, AuthService authService) {
        this.scanner = scanner;
        this.authService = authService;
    }

    @Override
    public void execute() {
        System.out.println("Enter your username:");
        String username = scanner.nextLine();
        System.out.println("Enter your password:");
        String password = scanner.nextLine();

        if (authService.login(username, password)) {
            System.out.println("Login successful!");
        } else {
            System.out.println("Login failed. Please try again.");
        }
    }
}
