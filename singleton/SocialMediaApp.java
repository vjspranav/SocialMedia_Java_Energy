import java.nio.file.*;
import java.util.*;

public class SocialMediaApp {
    private static final Scanner input = new Scanner(System.in);

    private static ArrayList<User> users = new ArrayList<>();
    private static ArrayList<Post> posts = new ArrayList<>();
    private static User currentUser;



    public static void main(String[] args) {
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
    }

    // View all posts
    private static void viewPosts() {
        if (posts.size() == 0) {
            System.out.println("No posts found.");
            return;
        }

        for (int i = 0; i < posts.size(); i++) {
            Post post = posts.get(i);
            System.out.println((i+1) + ". " + post.getAuthor().getUsername() + ": " + post.getContent() + " [" + post.getLikes() + " likes]");
            
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
                System.out.println("Post options:");
                System.out.println("1. Delete post");
                System.out.println("2. Like post");
                System.out.println("3. Unlike post");
                System.out.println("4. Comment on post");
                System.out.println("5. Back to posts");
    
                int choice = input.nextInt();
                input.nextLine();
    
                switch (choice) {
                    case 1:
                        if (post.getAuthor() == currentUser) {
                            posts.remove(post);
                            System.out.println("Post deleted successfully.");
                            return;
                        } else {
                            System.out.println("You can only delete your own posts.");
                            continue;
                        }
                    case 2:
                        post.addLike(currentUser);
                        System.out.println("Post liked successfully.");
                        break;
                    case 3:
                        post.removeLike(currentUser);
                        System.out.println("Like removed successfully.");
                        break;
                    case 4:
                        System.out.println("Enter your comment:");
                        String comment = input.nextLine();
                        post.addComment(currentUser, comment);
                        System.out.println("Comment added successfully.");
                        break;
                    case 5:
                        return;
                    default:
                        System.out.println("Invalid choice.");
                        break;
                }
            }
        }
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
        return this.username;
    }
    
    public String getPassword() {
        return this.password;
    }
}

class Post {
    private User author;
    private String content;
    private long timestamp;
    private ArrayList<User> likes;
    private ArrayList<String> comments;
    
    public Post(User author, String content) {
        this.author = author;
        this.content = content;
        this.timestamp = System.currentTimeMillis();
        this.likes = new ArrayList<>();
        this.comments = new ArrayList<>();
    }
    
    public User getAuthor() {
        return this.author;
    }
    
    public String getContent() {
        return this.content;
    }
    
    public long getTimestamp() {
        return this.timestamp;
    }
    
    public int getLikes() {
        return this.likes.size();
    }
    
    public void addLike(User user) {
        if (!likes.contains(user)) {
            likes.add(user);
        }
    }
    
    public void removeLike(User user) {
        likes.remove(user);
    }
    
    public ArrayList<String> getComments() {
        return this.comments;
    }
    
    public void addComment(User user, String comment) {
        String commentString = user.getUsername() + ": " + comment;
        comments.add(commentString);
    }
}