public class Main {
    public static void main(String[] args) {
        // Leer argumentos desde la línea de comandos
        CommandArguments arguments = new CommandArguments(args);
        String topic = arguments.get("topic", "default/topic");
        String message = arguments.get("message", "Hello, IoT!");
        String dbUrl = arguments.get("dbUrl", "jdbc:mysql://localhost:3306/iot_db");
        String dbUser = arguments.get("dbUser", "root");
        String dbPassword = arguments.get("dbPassword", "password");

        // Conectar a AWS IoT y enviar mensaje
        AwsIotClient awsIotClient = new AwsIotClient(topic);
        awsIotClient.publishMessage(message);

        // Guardar mensaje en la base de datos
        DatabaseClient dbClient = new DatabaseClient(dbUrl, dbUser, dbPassword);
        dbClient.saveMessage(topic, message);

        System.out.println("Proceso completado.");
    }
}
