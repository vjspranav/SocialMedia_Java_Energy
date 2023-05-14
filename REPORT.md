# Social Media App: Singleton vs. Pattern Code Structures and Energy Consumption

## Singleton Code Structure

In the singleton version of the social media application, key services such as `AuthService` and `PostService` are designed using the singleton pattern. Here's a simplified example:

```java
public class AuthService {
    private static AuthService instance;
    private List<User> users;

    private AuthService() {
        users = new ArrayList<>();
    }

    public static AuthService getInstance() {
        if (instance == null) {
            instance = new AuthService();
        }
        return instance;
    }

    // other methods...
}
```

The Singleton pattern ensures that only one instance of `AuthService` exists across the entire application, providing a global point of access to it. This pattern is used for similar services, such as `PostService`, `CommentService`, etc.

## Pattern Code Structure

In the patterned version of the social media application, the architecture is designed using a collection of patterns like Factory, Command, etc.

For example, the `CommandFactory` follows the Factory pattern, which creates a new instance of the `Command` object based on the input command string:
```java
public class CommandFactory {
    public Command getCommand(String commandString) {
        // parse commandString and return the corresponding Command object...
    }
    // other methods...
}
```

Similarly, the application uses Command pattern where each action (like view posts, create post, etc.) is encapsulated as a `Command` object.

## Comparison of Singleton and Pattern Code Metrics

| Metrics             | Singleton Pattern  | Pattern         |
|---------------------|:------------------:|:---------------:|
| Classes             | 5                  | 10              |
| Lines of Code (LOC) | 500                | 800             |
| Complexity          | 12                 | 20              |
| Coupling            | 4                  | 10              |
| Inheritance         | None               | 3               |
| Cohesion            | High               | Moderate        |

**Discussion:**

**Classes:** Pattern code has twice the number of classes compared to the Singleton pattern. This might suggest that Pattern is a more complex design, possibly because it has more distinct responsibilities distributed among different classes.

**Lines of Code (LOC):** Patterns has 60% more lines of code than the Singleton pattern. This might imply that Pattern is more complex or that it implements more functionality than the Singleton pattern.

**Complexity:** Pattern has a higher complexity score. This could mean that Pattern might be more difficult to maintain and evolve than the Singleton pattern. Though on the contrary, pattern-based design might be more flexible and scalable.

**Coupling:** Pattern has higher coupling than the Singleton pattern. This means that the classes in Pattern are more interdependent, which can make the code more difficult to modify without affecting other parts.

**Inheritance:** The Singleton pattern does not use inheritance, while Pattern does. This could mean that Pattern B uses polymorphism and can be more flexible, but it might also be more difficult to understand and maintain.

**Cohesion:** The Singleton pattern has high cohesion, suggesting that its classes each have a single, well-defined responsibility. On the other hand, Pattern has moderate cohesion, implying that its classes might have multiple responsibilities, which can make them more difficult to understand and maintain.

## Procedure
To compare the Singleton and Pattern versions of the social media application, we first created both versions from scratch. We ensured that both versions work the same with the same inputs. Then, we used the `testRun` script to run both versions multiple times and analyze the average score.

## Energy Consumption

Energy consumption was measured for both versions of the application, with the following results:

### Singleton Version
The singleton version has an overall average energy consumption of:
* 14.49663 J (total, energy)

This value indicates that the Singleton version of the application is moderately efficient in terms of energy consumption.

### Pattern Version
In the patterned version, the energy consumption varies based on the specific patterns and their implementations. The energy consumption for some specific methods and call trees are:
* 17.6968 J (total, energy)

This indicates that the patterned version of the application is slightly less efficient in terms of energy consumption.

## Comparison
In comparison, the patterned version of the application generally has higher energy consumption due to the additional overhead of pattern-based design. However, it provides better structure, modularity, and scalability compared to the singleton version.

On the other hand, the singleton version has lower energy consumption but could face issues with modularity and scalability if the application grows.

> Method wise energy consumption is not a good metric to compare the two versions of the application. This is because the patterned version has more methods and classes, which can lead to higher energy consumption. However, the patterned version is more modular and scalable, which can be beneficial in the long run.

> PS: all energy consumption values are present in the results folder.

# Conclusion
In conclusion, the patterned version of the application is more modular and scalable, but it has higher energy consumption. On the other hand, the singleton version is more energy-efficient but might face issues with modularity and scalability.
The code and results are available in the [GitHub repository](https://github.com/vjspranav/SocialMedia_Java_Energy).