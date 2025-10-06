import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class sqlConnection {
    private static final String url = "jdbc:mysql://localhost:3306/blog";
    private static final String user = "root";
    private static final String password = "Omer.erg.6565";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}