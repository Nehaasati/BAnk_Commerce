import java.sql.*;
import java.util.Scanner;

public class User {
    private final String personalNumber;
    private final Connection conn;
    private static final String URL = "jdbc:mysql://localhost:3306/bank_commerce";
    private static final String USER = "root"; // Change to your MySQL username
    private static final String PASSWORD = "Ashu!234";
    public User(String personalNumber) throws SQLException {
        this.personalNumber = personalNumber;
        this.conn = DriverManager.getConnection(URL,USER,PASSWORD);
    }

    public void showMenu(Scanner scanner) {
        while (true) {
            System.out.println("\n=== User Menu ===");
            System.out.println("1. View Balance");
            System.out.println("2. Withdraw Money");
            System.out.println("3. Deposit Money");
            System.out.println("4. Logout");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> viewBalance();
                case 2 -> withdraw(scanner);
                case 3 -> deposit(scanner);
                case 4 -> {
                    System.out.println("Logging out...");
                    return;
                }
                default -> System.out.println("Invalid choice! Try again.");
            }
        }
    }

    private void viewBalance() {
        try {
            String query = "SELECT balance FROM users WHERE personal_number = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, personalNumber);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                System.out.println("Your Balance: $" + rs.getDouble("balance"));
            } else {
                System.out.println("Account not found!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void withdraw(Scanner scanner) {
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();

        try {
            String query = "UPDATE users SET balance = balance - ? WHERE personal_number = ? AND balance >= ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setDouble(1, amount);
            pstmt.setString(2, personalNumber);
            pstmt.setDouble(3, amount);
            int result = pstmt.executeUpdate();

            if (result > 0) System.out.println("Withdrawal successful!");
            else System.out.println("Insufficient funds.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deposit(Scanner scanner) {
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();

        try {
            String query = "UPDATE users SET balance = balance + ? WHERE personal_number = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setDouble(1, amount);
            pstmt.setString(2, personalNumber);
            int result = pstmt.executeUpdate();

            if (result > 0) System.out.println("Deposit successful!");
            else System.out.println("Error in deposit.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
