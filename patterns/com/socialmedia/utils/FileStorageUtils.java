package com.socialmedia.utils;

import com.socialmedia.model.Post;
import com.socialmedia.model.User;

import java.io.*;
import java.util.ArrayList;

public class FileStorageUtils {
    private static final String USER_FILE = "users.txt";
    private static final String POST_FILE = "posts.txt";

    public static ArrayList<User> readUserData() {
        ArrayList<User> users = new ArrayList<>();
        try {
            BufferedReader userReader = new BufferedReader(new FileReader(USER_FILE));
            String line;
            while ((line = userReader.readLine()) != null) {
                String[] parts = line.split(",");
                User user = new User(parts[0], parts[1]);
                users.add(user);
            }
            userReader.close();
        } catch (IOException e) {
            System.out.println("Error reading user data from file");
        }
        return users;
    }

    public static ArrayList<Post> readPostData() {
        ArrayList<Post> posts = new ArrayList<>();
        try {
            BufferedReader postReader = new BufferedReader(new FileReader(POST_FILE));
            String line;
            while ((line = postReader.readLine()) != null) {
                String[] parts = line.split(",");
                // Read likes
                ArrayList<User> likes = new ArrayList<>();
                ArrayList<String> comments = new ArrayList<>();
                // parts: author,timestamp,content,likes,like1,like2,like3
                if (parts.length > 3) {
                    for (int i = 4; i < parts.length; i++) {
                        likes.add(new User(parts[i], ""));
                    }

                }
                Post post = new Post(new User(parts[0], ""), parts[2], Long.parseLong(parts[1]), likes, comments);
                posts.add(post);
            }
            postReader.close();
        } catch (IOException e) {
            System.out.println("Error reading post data from file");
        }
        return posts;
    }

    public static void writeUserData(ArrayList<User> users) {
        try {
            BufferedWriter userWriter = new BufferedWriter(new FileWriter(USER_FILE));
            for (User user : users) {
                userWriter.write(user.getUsername() + "," + user.getPassword());
                userWriter.newLine();
            }
            userWriter.close();
        } catch (IOException e) {
            System.out.println("Error writing user data to file");
        }
    }

    public static void writePostData(ArrayList<Post> posts) {
        try {
            BufferedWriter postWriter = new BufferedWriter(new FileWriter(POST_FILE));
            for (Post post : posts) {
                // write with likes
                postWriter.write(post.getAuthor().getUsername() + "," + post.getTimestamp() + "," + post.getContent());
                postWriter.write(",likes");
                for (User user : post.getLikes()) {
                    postWriter.write("," + user.getUsername());
                }
                postWriter.newLine();
            }
            postWriter.close();
        } catch (IOException e) {
            System.out.println("Error writing post data to file");
        }
    }

}