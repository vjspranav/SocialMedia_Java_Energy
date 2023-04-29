package com.socialmedia.utils;

import com.socialmedia.model.User;

public class ConsoleUtils {
    public static void printMenu(User currentUser) {
        System.out.println("========== MENU ==========");
        System.out.println("1. Sign up");
        System.out.println("2. Login");
        if (currentUser != null) {
            System.out.println("3. Create a post");
            System.out.println("Logged in as: " + currentUser.getUsername());
        }
        System.out.println("4. Exit");
        System.out.println("===========================");
        System.out.print("Enter your choice: ");
    }
}
