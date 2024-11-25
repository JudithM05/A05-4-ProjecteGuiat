import com.amazonaws.services.iot.client.*;
import java.util.Date;

public class AWSIotTopicHandler extends AWSIotTopic {

    private PostgreSQLHandler dbHandler;

    public AWSIotTopicHandler(String topic, AWSIotQos qos, PostgreSQLHandler dbHandler) {
        super(topic, qos);
        this.dbHandler = dbHandler;
    }

    @Override
    public void onMessage(AWSIotMessage message) {
        String payload = message.getStringPayload();
        System.out.println("Mensaje recibido: " + payload);

        // Guardar el mensaje en la base de datos
        try {
            dbHandler.saveMessage(payload, getTopic(), new Date());
            System.out.println("Mensaje guardado correctamente en la base de datos.");
        } catch (Exception e) {
            System.err.println("Error al guardar el mensaje en la base de datos.");
            e.printStackTrace();
        }
    }
}
