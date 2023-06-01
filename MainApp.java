import fsvec.FSVector;

import java.util.Scanner;

import datastruct.*;

/**
 * @onlypatric
 * MainApp
 * 
 * This class represents the main application for managing contacts. It provides a user interface for interacting with the contacts.
 * @author Patric Pintescul
 * @see README.MD
 */
public class MainApp {
    private static String clearLn = "\033[F";  // Escape sequence for clearing the current line
    private static String upLn = "\033[2K";    // Escape sequence for moving the cursor up and clearing the line

    /**
     * Displays a menu and prompts the user to select an option.
     * 
     * @param sc          Scanner object for user input
     * @param clear       Specifies whether to clear the console before displaying the menu
     * @param selections  The available menu options
     * @return The selected menu option
     */
    private static int menuSelection(Scanner sc, boolean clear, String... selections) {
        int selection = 0;
        int min = 1;
        int max = selections.length;
        int index;
        if (clear)
            System.out.print("\033c");  // Clear console screen
        System.out.println("Seleziona:");
        do {
            try {
                index = min;
                for (String string : selections) {
                    System.out.printf("%2d) %s\n", index++, string);
                }
                System.out.print(" >>> ");
                selection = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.print((clearLn + upLn).repeat(max + 2));  // Clear and move cursor up
                System.out.println("ERRORE! Seleziona di nuovo:");
                continue;
            }
            if (selection < min || selection > max) {
                System.out.print((clearLn + upLn).repeat(max + 2));  // Clear and move cursor up
                System.out.println("INVALIDO! Seleziona di nuovo:");
            }
        } while (selection < min || selection > max);
        System.out.print((clearLn + upLn).repeat(max + 2));  // Clear and move cursor up
        return selection;
    }

    /**
     * Prompts the user to enter an integer within a specified range.
     * 
     * @param sc   Scanner object for user input
     * @param min  The minimum allowed value
     * @param max  The maximum allowed value
     * @return The validated input value
     */
    private static int safeNextInt(Scanner sc, int min, int max) {
        int x = min - 1;
        do {
            try {
                System.out.print(" >>> ");
                x = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.print((clearLn + upLn).repeat(2));  // Clear and move cursor up
                continue;
            }
            if (x < min || x > max) {
                System.out.print((clearLn + upLn).repeat(2));  // Clear and move cursor up
            }
        } while (x < min || x > max);
        System.out.print((clearLn + upLn).repeat(2));  // Clear and move cursor up
        return x;
    }

    /**
     * The entry point of the application.
     * 
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        FSVector<Contact> contacts = new FSVector<Contact>();  // Create a new vector to store contacts
        Scanner sc = new Scanner(System.in);  // Create a scanner object to read user input
        int mode = menuSelection(sc, true, "Standard user mode", "CLI Dev mode");  // Prompt the user to select a mode
        switch (mode) {
            case 1:
                userMode(contacts, sc);  // Run user mode
                break;
            case 2:
                devMode(contacts, sc);  // Run developer mode
                break;

            default:
                break;
        }
    }

    /**
     * Runs the application in user mode.
     * 
     * @param contacts The vector of contacts
     * @param sc       Scanner object for user input
     */
    private static void userMode(FSVector<Contact> contacts, Scanner sc) {
        boolean nextClear = true;
        while (true) {
            int action = menuSelection(sc, nextClear, "Leggi un contatto", "Aggiungi un contatto",
                    "Aggiorna un contatto", "Elimina un contatto", "Esci dal programma");
            nextClear = true;
            switch (action) {
                case 1:
                    System.out.printf("Seleziona la posizione a cui leggere (%s-%s) ", 1, contacts.length());
                    int pos = safeNextInt(sc, 1, contacts.length());
                    System.out.println(contacts.get(pos - 1).detailedToString());
                    nextClear = false;
                    break;
                case 2:
                    System.out.print("Nome:    ");
                    String name = sc.nextLine();
                    if (name == "")
                        break;
                    System.out.print("Cognome: ");
                    String surname = sc.nextLine();
                    if (surname == "")
                        break;
                    System.out.print("Prefisso:");
                    String prefix = sc.nextLine();
                    if (prefix == "")
                        break;
                    System.out.print("Telefono:");
                    String phone = sc.nextLine();
                    if (phone == "")
                        break;
                    contacts.add(new Contact(new PhoneNumber(prefix, phone), name, surname));
                    System.out.println("\033c");  // Clear console screen
                    System.out.println("Contatto aggiunto con successo!");
                    break;
                case 3:
                    System.out.printf("Seleziona la posizione del contatto da aggiornare (%s-%s) ", 1,
                            contacts.length());
                    int updatePos = safeNextInt(sc, 1, contacts.length());
                    Contact contactToUpdate = contacts.get(updatePos - 1);

                    int attributeToUpdate = menuSelection(sc, true, "Nome", "Cognome", "Prefisso", "Telefono");
                    String updatedValue = "";

                    switch (attributeToUpdate) {
                        case 1:
                            System.out.print("Nuovo nome: ");
                            updatedValue = sc.nextLine();
                            contactToUpdate.setName(updatedValue);
                            break;
                        case 2:
                            System.out.print("Nuovo cognome: ");
                            updatedValue = sc.nextLine();
                            contactToUpdate.setSurname(updatedValue);
                            break;
                        case 3:
                            System.out.print("Nuovo prefisso: ");
                            updatedValue = sc.nextLine();
                            contactToUpdate.getPhone().setPrefix(updatedValue);
                            break;
                        case 4:
                            System.out.print("Nuovo telefono: ");
                            updatedValue = sc.nextLine();
                            contactToUpdate.getPhone().setNumber(updatedValue);
                            break;
                        default:
                            break;
                    }

                    System.out.println("\033c");  // Clear console screen
                    System.out.println("Contatto aggiornato con successo!");
                    break;
                case 4:
                    System.out.printf("Seleziona la posizione del contatto da eliminare (%s-%s) ", 1,
                            contacts.length());
                    int deletePos = safeNextInt(sc, 1, contacts.length());
                    contacts.pop(deletePos - 1);
                    System.out.println("\033c");  // Clear console screen
                    System.out.println("Contatto eliminato con successo!");
                    break;
                default:
                    return;
            }
        }
    }

    /**
     * Runs the application in developer mode.
     * 
     * @param contacts The vector of contacts
     * @param sc       Scanner object for user input
     */
    private static void devMode(FSVector<Contact> contacts, Scanner sc) {
        System.out.println("Scrivi HELP per aiuto");
        while (true) {
            try {
                System.out.print("Enter a command: ");
                String command = sc.nextLine();

                String[] commandParts = command.split(" ");
                String action = commandParts[0].toUpperCase();

                switch (action.toLowerCase()) {
                    case "read":
                        if (commandParts.length < 2) {
                            System.out.println("Invalid command. Usage: READ <position>");
                            break;
                        }
                        int readPos = Integer.parseInt(commandParts[1]);
                        if (readPos < 1 || readPos > contacts.length()) {
                            System.out.println("Invalid position.");
                            break;
                        }
                        System.out.println(contacts.get(readPos - 1).detailedToString());
                        break;
                    case "add":
                        if (commandParts.length < 5) {
                            System.out.println("Invalid command. Usage: ADD <name> <surname> <prefix> <phone>");
                            break;
                        }
                        String name = commandParts[1];
                        String surname = commandParts[2];
                        String prefix = commandParts[3];
                        String phone = commandParts[4];
                        contacts.add(new Contact(new PhoneNumber(prefix, phone), name, surname));
                        System.out.println("Contatto aggiunto con successo!");
                        break;
                    case "update":
                        if (commandParts.length < 3) {
                            System.out.println("Invalid command. Usage: UPDATE <position> <newData>");
                            break;
                        }
                        int updatePos = Integer.parseInt(commandParts[1]);
                        if (updatePos < 1 || updatePos > contacts.length()) {
                            System.out.println("Invalid position.");
                            break;
                        }
                        Contact contactToUpdate = contacts.get(updatePos - 1);
                        String newData = commandParts[2];
                        contactToUpdate.setName(newData);
                        System.out.println("Contatto aggiornato con successo!");
                        break;
                    case "remove":
                        if (commandParts.length < 2) {
                            System.out.println("Invalid command. Usage: REMOVE <position>");
                            break;
                        }
                        int removePos = Integer.parseInt(commandParts[1]);
                        if (removePos < 1 || removePos > contacts.length()) {
                            System.out.println("Invalid position.");
                            break;
                        }
                        contacts.pop(removePos - 1);
                        System.out.println("Contatto eliminato con successo!");
                        break;
                    case "length":
                        System.out.println("Number of contacts: " + contacts.length());
                        break;
                    case "help":
                        System.out.println("Available commands:");
                        System.out.println("\tREAD <position> -> read a contact");
                        System.out.println("\tADD <name> <surname> <prefix> <phone> -> add a contact");
                        System.out.println("\tUPDATE <position> <newData> -> update a contact");
                        System.out.println("\tREMOVE <position> -> remove a contact");
                        System.out.println("\tLENGTH -> number of contacts");
                        System.out.println("\tHELP -> this command");
                        break;
                    default:
                        System.out.println("Invalid command.");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Error...");
            }
        }
    }

}