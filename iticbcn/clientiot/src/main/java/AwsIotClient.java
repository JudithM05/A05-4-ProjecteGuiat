import software.amazon.awssdk.services.iotdataplane.IotDataPlaneClient;
import software.amazon.awssdk.services.iotdataplane.model.PublishRequest;
import software.amazon.awssdk.services.iotdataplane.model.PublishResponse;

import java.nio.charset.StandardCharsets;

public class AwsIotClient {
    private final IotDataPlaneClient client;
    private final String topic;

    public AwsIotClient(String topic) {
        this.client = IotDataPlaneClient.create();
        this.topic = topic;
    }

    public void publishMessage(String message) {
        PublishRequest request = PublishRequest.builder()
                .topic(topic)
                .payload(StandardCharsets.UTF_8.encode(message))
                .build();

        PublishResponse response = client.publish(request);
        System.out.println("Mensaje publicado: " + response.sdkHttpResponse().statusCode());
    }
}
