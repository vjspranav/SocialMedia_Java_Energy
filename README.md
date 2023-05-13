# Design Patterns and Power Consumption Study

This repository is part of an independent study project. The primary goal is to investigate the relationship between software design patterns, code smells, and power consumption.

## Singleton

The `singleton` directory contains an implementation of a simple social media application using the Singleton design pattern. Singleton is a creational design pattern that ensures that a class has only one instance, while providing a global access point to this instance.

In this application, Singleton pattern is used for services like `AuthService` and `PostService` to ensure only one instance of these services exist throughout the application lifecycle.

## Patterns

The `patterns` directory contains the same social media application, but implemented using various other design patterns such as Factory, Command, etc. The Factory pattern is used to create objects without exposing the creation logic to the client and refer to newly created object using a common interface. The Command pattern is used to encapsulate a request as an object, thereby allowing users to parameterize clients with queues, requests, and operations.

These patterns provide a more flexible architecture, allowing for easier modification and expansion of the application's functionality.

## Power Consumption Measurement

To measure the power consumption of the different implementations, we're using [JoularJX](https://github.com/joular/joularjx), a Java-based power measurement library. This will help us compare the energy efficiency of different design patterns and detect any potential power-related code smells.

Note: The power consumption can vary significantly depending on the hardware and operating environment, so it's recommended to run the tests in a controlled and consistent environment for accurate comparisons.

## Running the Tests

**TODO**

Please refer to the official JoularJX documentation for more information on how to set up and use the library for power measurements.

## Contributing

This is an independent study and not currently accepting contributions. However, constructive feedback and suggestions are always welcome.

## License

This project is open source and available under the [MIT License](LICENSE).

