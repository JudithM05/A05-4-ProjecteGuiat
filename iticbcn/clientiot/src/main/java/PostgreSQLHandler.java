import java.sql.*;

public class PostgreSQLHandler {

    private Connection connection;

    public PostgreSQLHandler(String dbUrl, String dbUser, String dbPassword) throws SQLException {
        this.connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        System.out.println("Conectado a PostgreSQL.");
    }

    public void saveMessage(String payload, String topic, java.util.Date timestamp) throws SQLException {
        String query = "INSERT INTO iot_messages (payload, topic, timestamp) VALUES (?, ?, ?);";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, payload);
            statement.setString(2, topic);
            statement.setTimestamp(3, new Timestamp(timestamp.getTime()));

            statement.executeUpdate();
            System.out.println("Mensaje guardado en la base de datos.");
        }
    }

    public void close() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
            System.out.println("Conexión a PostgreSQL cerrada.");
        }
    }
}
