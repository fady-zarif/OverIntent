package com.resideo.mqttoverintentlib.sender;

public interface IMQTTReceiver {
    void messageArrived(String topic, String message) throws Exception;
}
