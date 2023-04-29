import java.io.*;
import java.util.*;

public class SocialMediaApp {
    private static final Scanner input = new Scanner(System.in);
    private static final String USER_FILE = "users.txt";
    private static final String POST_FILE = "posts.txt";

    private static ArrayList<User> users = new ArrayList<>();
    private static ArrayList<Post> posts = new ArrayList<>();
    private static User currentUser;

    public static void main(String[] args) {
        // Load user and post data from file
        try {
            BufferedReader userReader = new BufferedReader(new FileReader(USER_FILE));
            String line;
            while ((line = userReader.readLine()) != null) {
                String[] parts = line.split(",");
                String username = parts[0];
                String password = parts[1];
                User user = new User(username, password);
                users.add(user);
            }
            userReader.close();

            BufferedReader postReader = new BufferedReader(new FileReader(POST_FILE));
            while ((line = postReader.readLine()) != null) {
                String[] parts = line.split(",");
                String authorUsername = parts[0];
                User author = null;
                for (User user : users) {
                    if (user.getUsername().equals(authorUsername)) {
                        author = user;
                        break;
                    }
                }
                String content = parts[1];
                long timestamp = Long.parseLong(parts[2]);
                Post post = new Post(author, content, timestamp);
                for (int i = 3; i < parts.length; i++) {
                    String[] likeParts = parts[i].split(":");
                    String likerUsername = likeParts[0];
                    User liker = null;
                    for (User user : users) {
                        if (user.getUsername().equals(likerUsername)) {
                            liker = user;
                            break;
                        }
                    }
                    post.addLike(liker);
                }
                posts.add(post);
            }
            postReader.close();
        } catch (Exception e) {
            // If there is an error loading the data, just start with empty lists
        }

        // Main menu loop
        while (true) {
            System.out.println("Welcome to the Social Media App!");

            if (currentUser == null) {
                System.out.println("1. Sign up");
                System.out.println("2. Login");
                System.out.println("3. Exit");
            } else {
                System.out.println("1. Add a post");
                System.out.println("2. View all posts");
                System.out.println("3. Logout");
            }

            int choice = input.nextInt();
            input.nextLine();

            if (currentUser == null) {
                switch (choice) {
                    case 1:
                        signUp();
                        break;
                    case 2:
                        login();
                        break;
                    case 3:
                        // Save user and post data to file before exiting
                        saveUserData();
                        savePostData();
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice.");
                        break;
                }
            } else {
                switch (choice) {
                    case 1:
                        addPost();
                        break;
                    case 2:
                        viewPosts();
                        break;
                    case 3:
                        currentUser = null;
                        System.out.println("Logged out successfully.");
                        break;
                    default:
                        System.out.println("Invalid choice.");
                        break;
                }
            }
        }
    }

    // Create a new user

    private static void signUp() {
        System.out.println("Enter a username:");
        String username = input.nextLine();
        System.out.println("Enter a password:");
        String password = input.nextLine();

        // Check if the username already exists
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                System.out.println("Username already exists.");
                return;
            }
        }

        // Add the new user
        User newUser = new User(username, password);
        users.add(newUser);
        currentUser = newUser;
        System.out.println("Signed up successfully.");

        // Save user data to file
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(USER_FILE, true));
            writer.write(username + "," + password);
            writer.newLine();
            writer.close();
        } catch (Exception e) {
            System.out.println("Error saving user data to file.");
        }
    }

    // Log in to an existing account
    private static void login() {
        System.out.println("Enter your username:");
        String username = input.nextLine();
        System.out.println("Enter your password:");
        String password = input.nextLine();

        // Check if the username and password match
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                currentUser = user;
                System.out.println("Logged in successfully.");
                return;
            }
        }

        System.out.println("Invalid username or password.");
    }

    // Add a new post
    private static void addPost() {
        System.out.println("Enter your post:");
        String postContent = input.nextLine();

        // Create a new post object
        Post newPost = new Post(currentUser, postContent);
        posts.add(newPost);
        System.out.println("Post added successfully.");

        // Save post data to file
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(POST_FILE, true));
            writer.write(currentUser.getUsername() + "," + postContent + "," + newPost.getTimestamp());
            for (User liker : newPost.getLikes()) {
                writer.write("," + liker.getUsername() + ":1");
            }
            writer.newLine();
            writer.close();
        } catch (Exception e) {
            System.out.println("Error saving post data to file.");
        }
    }

    // View all posts
    private static void viewPosts() {
        if (posts.size() == 0) {
            System.out.println("No posts found.");
            return;
        }

        for (int i = 0; i < posts.size(); i++) {
            Post post = posts.get(i);
            System.out.println((i + 1) + ". " + post.getAuthor().getUsername() + ": " + post.getContent() + " ["
                    + Integer.toString(
                            post.getLikes().size())
                    + " likes]");

            // Print comments
            ArrayList<String> comments = post.getComments();
            if (comments.size() > 0) {
                System.out.println("  Comments:");
                for (String comment : comments) {
                    System.out.println("    " + comment);
                }
            }
        }

        // Post options loop
        while (true) {
            System.out.println("Enter a post number to see more options, or enter 0 to go back.");
            int postNumber = input.nextInt();
            input.nextLine();

            if (postNumber == 0) {
                return;
            }

            // Check if the post number is valid
            if (postNumber < 1 || postNumber > posts.size()) {
                System.out.println("Invalid post number.");
                continue;
            }

            Post post = posts.get(postNumber - 1);

            // Post options menu
            while (true) {
                System.out.println("Options for post" + postNumber + ":");
                System.out.println("1. Like");
                System.out.println("2. Unlike");
                System.out.println("3. Comment");
                System.out.println("4. Delete");
                System.out.println("5. Go back");

                int choice = input.nextInt();
                input.nextLine();

                switch (choice) {
                    case 1:
                        post.addLike(currentUser);
                        System.out.println("Liked post.");
                        break;
                    case 2:
                        post.removeLike(currentUser);
                        System.out.println("Unliked post.");
                        break;
                    case 3:
                        System.out.println("Enter your comment:");
                        String comment = input.nextLine();
                        post.addComment(currentUser, comment);
                        System.out.println("Comment added.");
                        break;
                    case 4:
                        if (post.getAuthor().equals(currentUser)) {
                            posts.remove(post);
                            System.out.println("Post deleted.");
                            return;
                        } else {
                            System.out.println("You can only delete your own posts.");
                            break;
                        }
                    case 5:
                        return;
                    default:
                        System.out.println("Invalid choice.");
                        break;
                }
            }
        }
    }

    // Save user data to file
    private static void saveUserData() {
        try {
            BufferedWriter userWriter = new BufferedWriter(new FileWriter(USER_FILE));
            for (User user : users) {
                userWriter.write(user.getUsername() + "," + user.getPassword());
                userWriter.newLine();
            }
            userWriter.close();
        } catch (Exception e) {
            System.out.println("Error saving user data to file.");
        }
    }

    // Save post data to file
    private static void savePostData() {
        try {
            BufferedWriter postWriter = new BufferedWriter(new FileWriter(POST_FILE));
            for (Post post : posts) {
                postWriter.write(post.getAuthor().getUsername() + "," + post.getContent() + "," + post.getTimestamp());
                for (User liker : post.getLikes()) {
                    postWriter.write("," + liker.getUsername() + ":1");
                }
                postWriter.newLine();
            }
            postWriter.close();
        } catch (Exception e) {
            System.out.println("Error saving post data to file.");
        }
    }
}

class Post {
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

class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}