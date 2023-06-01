# Dynamic Vector - FSVector

The Dynamic Vector (FSVector) is a Java library that provides a dynamic array-like data structure with automatic resizing capabilities. It allows you to work with collections of elements that can grow or shrink as needed.

## Table of Contents

- [Dynamic Vector - FSVector](#dynamic-vector---fsvector)
  - [Table of Contents](#table-of-contents)
  - [Introduction](#introduction)
  - [Features](#features)
    - [Installation](#installation)
    - [Usage](#usage)
- [API Documentation](#api-documentation)
- [Contributing](#contributing)
- [License](#license)
- [Author](#author)

## Introduction

The FSVector library implements a dynamic vector using primitive arrays. It provides methods to add, remove, and access elements in the vector. The vector automatically resizes itself when necessary to accommodate new elements.

The library also includes utility methods for finding elements within the vector and iterating over its contents. It offers thread-safe search capabilities for improved performance in multi-threaded environments.

## Features

- Dynamic array-like data structure
- Automatic resizing
- Thread-safe search functionality
- Support for iterating over vector elements
- Utility methods for finding elements within the vector

### Installation

To use the FSVector library in your Java project, you can follow these steps:

1. Download the FSVector JAR file from the [releases](https://github.com/onlypatric/VettoreDinamico) page.
2. Compile all the classes and use it

### Usage

To start using the FSVector library in your Java code, you need to import the `fsvec.FSVector` class:

```java
import fsvec.FSVector;
public class MainApp {
    public static void main(String[] args) {
        // Create a new FSVector
        FSVector<Integer> vector = new FSVector<>();

        // Add elements to the vector
        vector.add(1);
        vector.add(2);
        vector.add(3);

        // Access elements in the vector
        int element = vector.get(1);
        System.out.println("Element at index 1: " + element);

        // Remove elements from the vector
        vector.pop(2);

        // Iterate over the vector
        for (Integer value : vector) {
            System.out.println(value);
        }
    }
}
```

# API Documentation
The API documentation for the FSVector library can be generated using a documentation tool such as Javadoc. Run the following command in the project's root directory to generate the documentation:

`javadoc -d docs -sourcepath src -subpackages fsvec`

This will generate the documentation in the docs directory. You can then open the index.html file in a web browser to access the documentation.

# Contributing
Contributions to the FSVector library are welcome! If you find any issues or have suggestions for improvements, please open an issue or submit a pull request on the project's GitHub repository.

When contributing, please ensure that your code follows the existing code style and that you include appropriate tests for any new features or bug fixes.

# License
The FSVector library is open source and available under the MIT License. You are free to use, modify, and distribute the library in your projects.

# Author
This project was developed by Patric Pintescul.

Email: patric.personal99@gmail.com
GitHub: https://www.github.com/onlypatric
