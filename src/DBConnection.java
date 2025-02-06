public class Connection {
    import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

    public class DBConnect {
        private static final String URL = "jdbc:mysql://localhost:3306/MiniBank";
        private static final String USER = "root"; // Change to your MySQL username
        private static final String PASSWORD = ""; // Change to your MySQL password

        public static Connection getConnection() throws SQLException {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        }
    }

}
