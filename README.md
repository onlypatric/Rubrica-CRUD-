# Java Contact Management Application

This is a Java application for managing contacts. It provides a user interface for interacting with the contacts, allowing users to perform various operations such as reading, adding, updating, and removing contacts.

## Features

- User Mode: Provides a menu-driven interface for managing contacts.
- Developer Mode: Allows developers to interact with the application using command-line commands.
- Contact Operations: Supports operations such as reading, adding, updating, and removing contacts.
- Input Validation: Validates user input to ensure data integrity.

## Prerequisites

- Java Development Kit (JDK) installed
- Command-line interface or an integrated development environment (IDE)

## Usage

1. Compile the Java source file:
   `javac *.java`
   `javac fsvec/*.java -d fsvec`
   `javac datastruct/*.java -d datastruct`
2. Run the application:
   `java MainApp`

3. Follow the instructions provided by the application to manage contacts.

## User Mode
In User Mode, the application presents a menu with options to perform various contact operations. The available options include:

1. Read a contact: Displays the details of a specific contact.
2. Add a contact: Prompts for contact information and adds it to the contact list.
3. Update a contact: Allows updating the details of an existing contact.
4. Remove a contact: Deletes a contact from the contact list.
5. Exit the program: Terminates the application.

## Developer Mode
In Developer Mode, the application allows interaction via command-line commands. Available commands include:

 - `READ <int position>`: Reads the details of a contact at the specified position.
 - `ADD <str name> <str surname> <str prefix> <str phone>`: Adds a contact with the provided details.
 - `UPDATE <position> <newData>`: Updates the name of a contact at the specified position.
 - `REMOVE <position>`: Removes a contact at the specified position.
 - `LENGTH`: Displays the number of contacts.
 - `HELP`: Provides a list of available commands.
## Contributing
Contributions are welcome! If you find any issues or have suggestions for improvements, please create an issue or submit a pull request.

## License
This project is licensed under the MIT License.

## Author
This project was developed by Patric Pintescul.

Email: patric.personal99@gmail.com
GitHub: https://www.github.com/onlypatric
