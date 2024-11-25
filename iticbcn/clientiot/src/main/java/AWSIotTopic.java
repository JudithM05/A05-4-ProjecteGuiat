package com.amazonaws.services.iot.client;
import com.amazonaws.services.iot.client.core.AwsIotTopicCallback;

public class AWSIotTopic extends AWSIotMqttClient implements AwsIotTopicCallback {

    /**
     * Instantiates a new topic object.
     *
     * @param topic
     *            the topic to be subscribed to
     */
    public AWSIotTopic(String topic) {
        super(topic, AWSIotQos.QOS0);
    }

    /**
     * Instantiates a new topic object.
     *
     * @param topic
     *            the topic to be subscribed to
     * @param qos
     *            the MQTT QoS level for the subscription
     */
    public AWSIotTopic(String topic, AWSIotQos qos) {
        super(topic, qos);
    }

    /**
     * Callback function to be invoked upon the arrival of a subscribed message.
     *
     * @param message
     *            the message received
     */
    @Override
    public void onMessage(AWSIotMqttClient message) {
        // Default callback implementation is no-op
    }

}
