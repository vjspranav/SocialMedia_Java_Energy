package com.socialmedia.model;

import java.util.ArrayList;

public class Post {
    private User author;
    private String content;
    private long timestamp;
    private ArrayList<User> likes = new ArrayList<>();
    private ArrayList<String> comments = new ArrayList<>();

    public Post(User author, String content) {
        this.author = author;
        this.content = content;
        this.timestamp = System.currentTimeMillis();
    }

    public Post(User author, String content, long timestamp) {
        this.author = author;
        this.content = content;
        this.timestamp = timestamp;
    }

    public Post(User author, String content, long timestamp, ArrayList<User> likes, ArrayList<String> comments) {
        this.author = author;
        this.content = content;
        this.timestamp = timestamp;
        this.likes = likes;
        this.comments = comments;
    }

    public User getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public ArrayList<User> getLikes() {
        return likes;
    }

    public void addLike(User user) {
        likes.add(user);
    }

    public void removeLike(User user) {
        likes.remove(user);
    }

    public ArrayList<String> getComments() {
        return comments;
    }

    public void addComment(User user, String comment) {
        comments.add(user.getUsername() + ": " + comment);
    }

    @Override
    public String toString() {
        return author.getUsername() + ": " + content + " [" + likes.size() + " likes]";
    }
}

