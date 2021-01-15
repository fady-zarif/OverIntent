package com.resideo.mqttoverintentlib;

public class MQTTIntentConstants {

    public static final String TAG_MQTT = "MqttIntentReceiver";

    /** Mqtt Extras */
    public static final String EXTRA_MQTT_TOPIC = "Topic";
    public static final String EXTRA_MQTT_MESSAGE = "Message";
    public static final String EXTRA_MQTT_QOS = "Qos";
    public static final String EXTRA_MQTT_RECEIVER = "Receiver";

    /** Mqtt Actions */
    public static final String ACTION_MQTT_SUBSCRIBE = "android.intent.action.mqtt.Subscribe";
    public static final String ACTION_MQTT_PUBLISH = "android.intent.action.mqtt.Publish";
    public static final String ACTION_MQTT_UNSUBSCRIBE = "android.intent.action.mqtt.Unsubscribe";
}
