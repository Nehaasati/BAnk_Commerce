import java.sql.*;
import java.util.*;
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
                System.out.println("\n=== MinBank System ===");
                System.out.println("1. Login");
                System.out.println("2. Exit");
                System.out.print("Choose an option: ");

                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1 -> Login.loginUser(scanner);
                    case 2 -> Login.loginAdmin(scanner);
                    case 3 -> {
                        System.out.println("Exiting...");
                        return;
                    }
                    default -> System.out.println("Invalid choice! Try again.");
                }
            }
        }
    }
