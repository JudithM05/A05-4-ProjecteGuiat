import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DatabaseClient {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/mydatabase";
    private static final String USER = "root";
    private static final String PASSWORD = "password";

    public void saveData(String thingName, String data) {
        String query = "INSERT INTO iot_data (thing_name, data) VALUES (?, ?)";

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, thingName);
            statement.setString(2, data);
            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

