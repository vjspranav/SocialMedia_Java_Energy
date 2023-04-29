package com.socialmedia;

import com.socialmedia.controller.Command;
import com.socialmedia.service.AuthService;
import com.socialmedia.service.CommandFactory;
import com.socialmedia.service.PostService;
import com.socialmedia.utils.ConsoleUtils;
import com.socialmedia.utils.FileStorageUtils;

public class Main {
    public static void main(String[] args) {
        AuthService authService = new AuthService();
        PostService postService = new PostService();
        CommandFactory commandFactory = new CommandFactory(authService, postService);

        // read data from files
        authService.setUsers(FileStorageUtils.readUserData());
        postService.setPosts(FileStorageUtils.readPostData());

        while (true) {
            ConsoleUtils.printMenu(authService.getCurrentUser());
            Command command = commandFactory.getCommand();
            if (command == null) {
                break;
            }
            command.execute();

            // write data to files
            FileStorageUtils.writeUserData(authService.getUsers());
            FileStorageUtils.writePostData(postService.getAllPosts());
        }
    }
}
