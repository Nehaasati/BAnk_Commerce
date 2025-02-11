import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {


        private static final String URL = "jdbc:mysql://localhost:3306/bank_commerce";
        private static final String USER = "root"; // Change to your MySQL username
        private static final String PASSWORD = "Ashu!234"; // Change to your MySQL password

        public static Connection getConnection() throws SQLException {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        }
    }


