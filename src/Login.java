import java.sql.*;
import java.util.Scanner;

    public class Login {
        private static final String URL = "jdbc:mysql://localhost:3306/bank_commerce";
        private static final String USER = "root"; // Change to your MySQL username
        private static final String PASSWORD = "Ashu!234";
        public static void loginUser(Scanner scanner) {
            System.out.print("Enter Personal Number: ");
            String personalNumber = scanner.nextLine();

            int attempts = 0;
            while (attempts < 3) {
                System.out.print("Enter PIN: ");
                String pin = scanner.nextLine();

                try (Connection conn = DriverManager.getConnection(URL,USER,PASSWORD)) {
                    String query = "SELECT * FROM users WHERE personal_number = ?";
                    PreparedStatement pstmt = conn.prepareStatement(query);
                    pstmt.setString(1, personalNumber);
                    ResultSet rs = pstmt.executeQuery();

                    if (rs.next()) {
                        if (pin.equals(rs.getString("pin"))) {
                            System.out.println("Login successful!");

                            if (rs.getString("role").equals("Admin")) {
                                Admin admin = new Admin();
                                admin.viewAllUsers();
                            } else {
                                User user = new User(personalNumber);
                                user.showMenu(scanner);
                            }
                            return;
                        } else {
                            attempts++;
                            System.out.println("Incorrect PIN. Attempts left: " + (3 - attempts));
                        }
                    } else {
                        System.out.println("User not found!");
                        return;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Maximum login attempts reached. Account locked.");
        }

        public static void loginAdmin(Scanner scanner) {
            System.out.print("Enter Personal Number: ");
            String personalNumber = scanner.nextLine();

            int attempts = 0;
            while (attempts < 3) {
                System.out.print("Enter PIN: ");
                String pin = scanner.nextLine();

                try (Connection conn = DriverManager.getConnection(URL,USER,PASSWORD)) {
                    String query = "SELECT * FROM admin WHERE personal_number = ?";
                    PreparedStatement pstmt = conn.prepareStatement(query);
                    pstmt.setString(1, personalNumber);
                    ResultSet rs = pstmt.executeQuery();

                    if (rs.next()) {
                        if (pin.equals(rs.getString("pin"))) {
                            System.out.println("Login successful!");

                            if (rs.getString("role").equals("Admin")) {
                                Admin admin = new Admin();
                                admin.viewAllUsers();
                            } else {
                                User user = new User(personalNumber);
                                user.showMenu(scanner);
                            }
                            return;
                        } else {
                            attempts++;
                            System.out.println("Incorrect PIN. Attempts left: " + (3 - attempts));
                        }
                    } else {
                        System.out.println("User not found!");
                        return;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Maximum login attempts reached. Account locked.");
        }
    }



