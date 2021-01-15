package com.resideo.mqttoverintentlib.receiver;

public interface IMQTTExternalRequest {
    void onExternalRequestSubscribe(String topic, String owner, String qos);

    void onExternalRequestUnSubscribe(String topic, String owner);

    void onExternalRequestPublish(String topic, String message, String qos);
}
