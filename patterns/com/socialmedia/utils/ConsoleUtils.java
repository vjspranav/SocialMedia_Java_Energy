package com.socialmedia.utils;

import com.socialmedia.model.User;

public class ConsoleUtils {
    public static void printMenu(User currentUser) {
        System.out.println("========== MENU ==========");
        if (currentUser == null) {
            System.out.println("1. Sign up");
            System.out.println("2. Login");
            System.out.println("9. Exit");
        } else {
            System.out.println("Logged in as: " + currentUser.getUsername());
            System.out.println("1. Create a post");
            System.out.println("2. View posts");
            System.out.println("3. Logout");
            System.out.println("9. Exit");
        }
        System.out.println("===========================");
        System.out.print("Enter your choice: ");
    }


    public static void printPostMenu() {
        System.out.println("========== POST MENU ==========");
        System.out.println("1. Like post");
        System.out.println("2. Unlike post");
        System.out.println("3. Comment on post");
        System.out.println("4. Delete post");
        System.out.println("5. Back to main menu");
        System.out.println("===============================");
        System.out.print("Enter your choice: ");
    }
}
