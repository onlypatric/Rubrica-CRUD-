import java.util.Scanner;

import fsvec.FSVector;
import datastruct.*;

/**
 * MainApp
 * 
 * This class represents the main application for managing contacts.
 * It provides a user interface for interacting with the contacts.
 * 
 * @author Patric Pintescul
 * @see fsvec.FSVector
 * @see datastruct.Contact
 * @see datastruct.PhoneNumber
 * 
 */
public class MainApp {
    private static String clearLn = "\033[F"; // Escape sequence for clearing the current line
    private static String upLn = "\033[2K"; // Escape sequence for moving the cursor up and clearing the line

    /**
     * Displays a menu and prompts the user to select an option.
     * 
     * @param sc         Scanner object for user input
     * @param clear      Specifies whether to clear the console before displaying
     *                   the menu
     * @param selections The available menu options
     * @return The selected menu option
     */
    private static int menuSelection(Scanner sc, boolean clear, String... selections) {
        int selection = 0;
        int min = 1;
        int max = selections.length;
        int index;
        if (clear)
            System.out.print("\033c"); // Clear console screen
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
                System.out.print((clearLn + upLn).repeat(max + 2)); // Clear and move cursor up
                System.out.println("ERRORE! Seleziona di nuovo:");
                continue;
            }
            if (selection < min || selection > max) {
                System.out.print((clearLn + upLn).repeat(max + 2)); // Clear and move cursor up
                System.out.println("INVALIDO! Seleziona di nuovo:");
            }
        } while (selection < min || selection > max);
        System.out.print((clearLn + upLn).repeat(max + 2)); // Clear and move cursor up
        return selection;
    }

    private static void printPhoneNumber(FSVector<Contact> contacts, Scanner sc) {
        System.out.print("Cognome: ");
        String surname = sc.nextLine();
        System.out.print("Nome: ");
        String name = sc.nextLine();

        for (Contact contact : contacts) {
            if (contact.getSurname().equalsIgnoreCase(surname) && contact.getName().equalsIgnoreCase(name)) {
                System.out.println("Telefono: " + contact.getPhone().toString());
                return;
            }
        }

        System.out.println("Contatto non trovato.");
    }

    /**
     * Given a phone number, prints the surname and name.
     *
     * @param contacts The vector of contacts
     * @param sc       Scanner object for user input
     */
    private static void printNameAndSurname(FSVector<Contact> contacts, Scanner sc) {
        System.out.print("Numero di telefono: ");
        String phoneNumber = sc.nextLine();

        for (Contact contact : contacts) {
            if (contact.getPhone().getNumber().equals(phoneNumber)) {
                System.out.println("Cognome: " + contact.getSurname());
                System.out.println("Nome: " + contact.getName());
                return;
            }
        }

        System.out.println("Contatto non trovato.");
    }

    /**
     * Prompts the user to enter an integer within a specified range.
     * 
     * @param sc  Scanner object for user input
     * @param min The minimum allowed value
     * @param max The maximum allowed value
     * @return The validated input value
     */
    private static int safeNextInt(Scanner sc, int min, int max) {
        int x = min - 1;
        do {
            try {
                System.out.print(" >>> ");
                x = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.print((clearLn + upLn).repeat(2)); // Clear and move cursor up
                continue;
            }
            if (x < min || x > max) {
                System.out.print((clearLn + upLn).repeat(2)); // Clear and move cursor up
            }
        } while (x < min || x > max);
        System.out.print((clearLn + upLn).repeat(2)); // Clear and move cursor up
        return x;
    }

    /**
     * The entry point of the application.
     * 
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        FSVector<Contact> contacts = new FSVector<Contact>(); // Create a new vector to store contacts
        Scanner sc = new Scanner(System.in); // Create a scanner object to read user input
        int mode = menuSelection(sc, true, "Standard user mode", "CLI Dev mode"); // Prompt the user to select a mode
        switch (mode) {
            case 1:
                userMode(contacts, sc); // Run user mode
                break;
            case 2:
                devMode(contacts, sc); // Run developer mode
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
                    "Aggiorna un contatto", "Elimina un contatto", "Stampa telefono da cognome e nome",
                    "Stampa cognome e nome da telefono", "Esci dal programma");
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
                    String date;
                    DateTime dt = new DateTime(0, 0, 0);
                    System.out.print("Data di nascita (gg/mm/aaaa):");
                    date = sc.nextLine();
                    try {
                        dt.setDate(date);
                    } catch (Exception e) {
                        System.out.println("Formato data non riconosciuto...");
                        break;
                    }

                    contacts.add(new Contact(new PhoneNumber(prefix, phone), name, surname, dt));
                    System.out.println("\033c"); // Clear console screen
                    System.out.println("Contatto aggiunto con successo!");
                    break;
                case 3:
                    System.out.printf("Seleziona la posizione del contatto da aggiornare (%s-%s) ", 1,
                            contacts.length());
                    int updatePos = safeNextInt(sc, 1, contacts.length());
                    Contact contactToUpdate = contacts.get(updatePos - 1);

                    int attributeToUpdate = menuSelection(sc, true, "Nome", "Cognome", "Prefisso", "Telefono","Data di nascita");
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
                        case 5:
                            DateTime dt2 = new DateTime(0, 0, 0);
                            do {
                                System.out.print("Nuova data di nascita (gg/mm/aaaa):");
                                date = sc.nextLine();
                                try {
                                    dt2.setDate(date);
                                    break;
                                } catch (Exception e) {
                                    System.out.println("Formato non riconosciuto...");
                                }
                            } while (true);
                            contactToUpdate.setDateOfBirth(dt2);
                            break;
                        default:
                            break;
                    }

                    System.out.println("\033c"); // Clear console screen
                    System.out.println("Contatto aggiornato con successo!");
                    break;
                case 4:
                    System.out.printf("Seleziona la posizione del contatto da eliminare (%s-%s) ", 1,
                            contacts.length());
                    int deletePos = safeNextInt(sc, 1, contacts.length());
                    contacts.pop(deletePos - 1);
                    System.out.println("\033c"); // Clear console screen
                    System.out.println("Contatto eliminato con successo!");
                    break;
                case 5:
                    printPhoneNumber(contacts, sc);
                    break;
                case 6:
                    printNameAndSurname(contacts, sc);
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
        String name, surname, prefix, phone, phoneNumber;
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
                        try {
                            if (commandParts.length < 6) {
                                System.out.println(
                                        "Invalid command. Usage: ADD <name> <surname> <prefix> <phone> <gg/mm/aaaa>");
                                break;
                            }
                            name = commandParts[1];
                            surname = commandParts[2];
                            prefix = commandParts[3];
                            phone = commandParts[4];
                            DateTime date = new DateTime(0, 0, 0);
                            date.setDate(commandParts[5]);

                            contacts.add(new Contact(new PhoneNumber(prefix, phone), name, surname, date));
                            System.out.println("Contatto aggiunto con successo!");
                        } catch (Exception e) {
                            System.out.println("Error while executing command");
                        }
                        break;
                    case "update":
                        try {
                            if (commandParts.length < 3) {
                                System.out.println(
                                        "Invalid command. Usage: UPDATE <position> <name> <surname> <prefix> <phone> <gg/mm/aaaa>");
                                break;
                            }
                            int updatePos = Integer.parseInt(commandParts[1]);
                            if (updatePos < 1 || updatePos > contacts.length()) {
                                System.out.println("Invalid position.");
                                break;
                            }
                            Contact contactToUpdate = contacts.get(updatePos - 1);
                            contactToUpdate.setName(commandParts[2]);
                            contactToUpdate.setSurname(commandParts[3]);
                            contactToUpdate.setPhone(new PhoneNumber(commandParts[4], commandParts[5]));
                            DateTime dt = new DateTime(0, 0, 0);
                            dt.setDate(commandParts[6]);
                            contactToUpdate.setDateOfBirth(dt);
                            System.out.println("Contatto aggiornato con successo!");
                        } catch (Exception e) {
                            System.out.println("Error while executing command");
                        }
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
                    case "searchphone":
                        if (commandParts.length < 3) {
                            System.out.println("Invalid command. Usage: SEARCHPHONE <surname> <name>");
                            break;
                        }
                        surname = commandParts[1];
                        name = commandParts[2];

                        for (Contact contact : contacts) {
                            if (contact.getSurname().equalsIgnoreCase(surname)
                                    && contact.getName().equalsIgnoreCase(name)) {
                                System.out.println("Telefono: " + contact.getPhone().toString());
                            }
                        }

                        System.out.println("Contatto non trovato.");
                        break;
                    case "searchname":
                        if (commandParts.length < 2) {
                            System.out.println("Invalid command. Usage: SEARCHNAME <phone>");
                            break;
                        }
                        phoneNumber = commandParts[1];

                        for (Contact contact : contacts) {
                            if (contact.getPhone().getNumber().equals(phoneNumber)) {
                                System.out.println("Cognome: " + contact.getSurname());
                                System.out.println("Nome: " + contact.getName());
                            }
                        }

                        System.out.println("Contatto non trovato.");
                        break;

                    case "length":
                        System.out.println("Number of contacts: " + contacts.length());
                        break;
                    case "help":
                        System.out.println("Available commands:");
                        System.out.println("\tREAD <position> -> read a contact");
                        System.out.println("\tADD <name> <surname> <prefix> <phone> <birth [gg/mm/aaaa]> -> add a contact");
                        System.out.println("\tUPDATE <position> <newData> -> update a contact");
                        System.out.println("\tSEARCHPHONE <surname> <name> -> Search by name & surname");
                        System.out.println("\tSEARCHNAME <phone> -> Search by phone number");
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