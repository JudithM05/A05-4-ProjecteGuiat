import com.amazonaws.services.iot.client.*;

public class AWSIotMqttClientHandler {

    private AWSIotMqttClient mqttClient;
    private PostgreSQLHandler dbHandler;

    public AWSIotMqttClientHandler(String endpoint, String clientId, String certificateFile, String privateKeyFile, PostgreSQLHandler dbHandler) {
        // Inicializamos el cliente MQTT con los parámetros de conexión
        this.mqttClient = new AWSIotMqttClient(endpoint, clientId, certificateFile, privateKeyFile);
        this.dbHandler = dbHandler;
    }

    public void connect() throws AWSIotException {
        try {
            mqttClient.connect();
            System.out.println("Conectado a AWS IoT Core.");
        } catch (AWSIotException e) {
            System.err.println("Error al conectar al AWS IoT Core.");
            throw e;
        }
    }

    public void subscribeToTopic(String topic) throws AWSIotException {
        try {
            // Creamos y registramos el manejador de tópicos
            AWSIotTopicHandler topicHandler = new AWSIotTopicHandler(topic, AWSIotQos.QOS0, dbHandler);
            mqttClient.subscribe(topicHandler);
            System.out.println("Suscrito al tópico: " + topic);
        } catch (AWSIotException e) {
            System.err.println("Error al suscribirse al tópico: " + topic);
            throw e;
        }
    }

    public void disconnect() {
        try {
            if (mqttClient != null) {
                mqttClient.disconnect();
                System.out.println("Desconectado de AWS IoT Core.");
            }
        } catch (AWSIotException e) {
            System.err.println("Error al desconectar de AWS IoT Core.");
            e.printStackTrace();
        }
    }
}
