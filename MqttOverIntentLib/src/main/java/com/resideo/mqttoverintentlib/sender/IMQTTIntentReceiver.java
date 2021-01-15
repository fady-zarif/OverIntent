package com.resideo.mqttoverintentlib.sender;

public interface IMQTTIntentReceiver {

    void setReceiver(IMQTTReceiver receiver);
    void publish(String topic, String message, int qos);
    void subscribe(String topic, int qos);
    void unsubscribe(String topic);
}
