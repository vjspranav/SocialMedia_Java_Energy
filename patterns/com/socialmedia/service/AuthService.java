package com.socialmedia.service;

import com.socialmedia.model.User;

import java.util.ArrayList;
import java.util.Optional;

public class AuthService {
    private ArrayList<User> users;
    private User currentUser;

    public AuthService() {
        this.users = new ArrayList<>();
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public boolean signUp(String username, String password) {
        if (isUsernameTaken(username)) {
            return false;
        }

        User newUser = new User(username, password);
        users.add(newUser);
        currentUser = newUser;
        return true;
    }

    public boolean login(String username, String password) {
        Optional<User> user = findUser(username, password);
        if (user.isPresent()) {
            currentUser = user.get();
            return true;
        }
        return false;
    }

    public void logout() {
        currentUser = null;
    }

    private boolean isUsernameTaken(String username) {
        return users.stream().anyMatch(user -> user.getUsername().equals(username));
    }

    private Optional<User> findUser(String username, String password) {
        return users.stream().filter(user -> user.getUsername().equals(username) && user.getPassword().equals(password)).findFirst();
    }
}
