import java.sql.*;
public class Admin {
    private final Connection conn;
    private static final String URL = "jdbc:mysql://localhost:3306/bank_commerce";
    private static final String USER = "root"; // Change to your MySQL username
    private static final String PASSWORD = "Ashu!234";
        public Admin() throws SQLException {
            this.conn = DriverManager.getConnection(URL,USER,PASSWORD);
        }

        public void viewAllUsers() {
            try {
                String query = "SELECT personal_number, email, account_type, balance FROM users";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                System.out.println("\n=== All Users ===");
                while (rs.next()) {
                    System.out.println("PN: " + rs.getString("personal_number") +
                            " | Email: " + rs.getString("email") +
                            " | Type: " + rs.getString("account_type") +
                            " | Balance: $" + rs.getDouble("balance"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


