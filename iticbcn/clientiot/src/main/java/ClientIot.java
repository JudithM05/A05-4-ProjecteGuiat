import software.amazon.awssdk.services.iotdataplane.IotDataPlaneClient;
import software.amazon.awssdk.services.iotdataplane.model.GetThingShadowRequest;
import software.amazon.awssdk.services.iotdataplane.model.GetThingShadowResponse;

public class ClientIot {
    private final IotDataPlaneClient iotClient;

    public ClientIot() {
        this.iotClient = IotDataPlaneClient.create();
    }

    public String getShadowData(String thingName) {
        GetThingShadowRequest request = GetThingShadowRequest.builder()
                .thingName(thingName)
                .build();

        GetThingShadowResponse response = iotClient.getThingShadow(request);
        return response.payload().asUtf8String();
    }
}
