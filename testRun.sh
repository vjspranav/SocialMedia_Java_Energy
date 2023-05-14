#!/bin/bash

# Function to perform actions
perform_actions() {
    echo "1"       # Sign up
    sleep 1
    echo "user$1"  # Username
    sleep 1
    echo "pass$1"  # Password
    sleep 1
    echo "1"       # Create post
    sleep 1
    echo "This is a post from user$1"
    sleep 1
    echo "2"       # View all posts
    sleep 1
    echo "1"       # Select post
    sleep 1
    echo "1"       # Like post
    sleep 1
    echo "2"       # View all posts
    sleep 1
    echo "1"       # Select post
    sleep 1
    echo "3"       # Comment on post
    sleep 1
    echo "Nice post!"
    sleep 3
    echo "3"       # Logout
    sleep 1
    echo "9"       # Exit
}

# Function to run the application
run_app() {
    # cd to the application directory
    cd $2

    # Remove users.txt and posts.txt
    rm -f users.txt posts.txt

    # Run Java application with Joularjx
    for i in {1..5}
    do
        # Run the application with input from perform_actions
        perform_actions $i | java -javaagent:joularjx-2.0.jar $1 | tee -a "app_output_$1_$2.txt"

        # Move the result
        mv joularjx-result/* ../results/$2/run_$1_$i
        rm -rf joularjx-result
    done

    # cd back to the root directory
    cd ..
}

# Run the application 5 times in singleton and pattern modes
run_app "SocialMediaApp" "singleton"
run_app "com.socialmedia.Main" "patterns"
