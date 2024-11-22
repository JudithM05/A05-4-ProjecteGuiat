import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DatabaseClient {
    private final String dbUrl;
    private final String user;
    private final String password;

    public DatabaseClient(String dbUrl, String user, String password) {
        this.dbUrl = dbUrl;
        this.user = user;
        this.password = password;
    }

    public void saveMessage(String topic, String message) {
        String query = "INSERT INTO iot_messages (topic, message) VALUES (?, ?)";

        try (Connection connection = DriverManager.getConnection(dbUrl, user, password);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, topic);
            statement.setString(2, message);
            statement.executeUpdate();

            System.out.println("Datos guardados correctamente en la base de datos.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
