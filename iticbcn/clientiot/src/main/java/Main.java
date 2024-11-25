public class Main {

    public static void main(String[] args) {
        // Configuración AWS IoT MQTT
        String clientId = "mqtt-client-id";
        String endpoint = "http://au609fvdk4tdm-ats.iot.us-east-1.amazonaws.com/";  // Reemplaza con tu endpoint AWS IoT
        String topic = "your/topic";

        // Archivos de certificados para conectarse a AWS IoT
        String certificateFile = "path/to/certificate.pem.crt";
        String privateKeyFile = "path/to/private.pem.key";

        // Configuración de PostgreSQL
        String dbUrl = "jdbc:postgresql://localhost:5432/your_database";
        String dbUser = "your_username";
        String dbPassword = "your_password";

        PostgreSQLHandler dbHandler = null;
        AWSIotMqttClientHandler mqttClientHandler = null;

        try {
            // Conectar a PostgreSQL
            dbHandler = new PostgreSQLHandler(dbUrl, dbUser, dbPassword);

            // Iniciar el cliente MQTT
            mqttClientHandler = new AWSIotMqttClientHandler(
                    endpoint, clientId, certificateFile, privateKeyFile, dbHandler
            );

            // Conectar al cliente MQTT y suscribirse al tópico
            mqttClientHandler.connect();
            mqttClientHandler.subscribeToTopic(topic);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Cerrar conexiones al terminar
            if (mqttClientHandler != null) {
                mqttClientHandler.disconnect();
            }
            if (dbHandler != null) {
                try {
                    dbHandler.close();
                } catch (Exception e) {
                    System.err.println("Error al cerrar la conexión con PostgreSQL.");
                    e.printStackTrace();
                }
            }
        }
    }
}
