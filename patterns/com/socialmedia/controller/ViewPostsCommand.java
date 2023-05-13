package com.socialmedia.controller;

import com.socialmedia.model.Post;
import com.socialmedia.service.AuthService;
import com.socialmedia.service.CommandFactory;
import com.socialmedia.service.PostService;
import com.socialmedia.utils.ConsoleUtils;

import java.util.Scanner;

public class ViewPostsCommand implements Command {
    private final Scanner scanner;
    private final AuthService authService;
    private final PostService postService;

    private CommandFactory commandFactory;

    public ViewPostsCommand(AuthService authService, PostService postService) {
        this.scanner = new Scanner(System.in);
        this.authService = authService;
        this.postService = postService;
    }

    @Override
    public void execute() {
        // Print all posts
        // If there are no posts, print "No posts yet"
        if (postService.getAllPosts().isEmpty()) {
            System.out.println("No posts yet");
        } else {
            // post_index. author: post_content
            for (int i = 0; i < postService.getAllPosts().size(); i++) {
                Post post = postService.getAllPosts().get(i);
                System.out.printf("%d. %s: %s [%d likes]\n", i + 1, post.getAuthor().getUsername(), post.getContent(), post.getLikes().size());
                for (int j = 0; j < post.getComments().size(); j++) {
                    System.out.printf("\t%d. %s\n", j + 1, post.getComments().get(j));
                }
            }
        }

        // Select a post
        System.out.println("Enter a post number to see more options, or enter 0 to go back.");
        System.out.print("Enter your choice: ");
        String choice = scanner.nextLine();
        int postId = Integer.parseInt(choice);
        if (postId == 0) {
            return;
        }else if (postId < 0 || postId > postService.getAllPosts().size()) {
            System.out.println("Invalid post number. Please try again.");
            return;
        }

        commandFactory = new CommandFactory(authService, postService, postId);

        ConsoleUtils.printPostMenu();
        Command command = commandFactory.getCommand();
        if (command == null) {
            return;
        }
        command.execute();
    }
}
