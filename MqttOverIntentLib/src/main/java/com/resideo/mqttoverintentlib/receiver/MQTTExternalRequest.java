package com.resideo.mqttoverintentlib.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import static com.resideo.mqttoverintentlib.MQTTIntentConstants.ACTION_MQTT_PUBLISH;
import static com.resideo.mqttoverintentlib.MQTTIntentConstants.ACTION_MQTT_SUBSCRIBE;
import static com.resideo.mqttoverintentlib.MQTTIntentConstants.ACTION_MQTT_UNSUBSCRIBE;
import static com.resideo.mqttoverintentlib.MQTTIntentConstants.EXTRA_MQTT_MESSAGE;
import static com.resideo.mqttoverintentlib.MQTTIntentConstants.EXTRA_MQTT_QOS;
import static com.resideo.mqttoverintentlib.MQTTIntentConstants.EXTRA_MQTT_RECEIVER;
import static com.resideo.mqttoverintentlib.MQTTIntentConstants.EXTRA_MQTT_TOPIC;
import static com.resideo.mqttoverintentlib.MQTTIntentConstants.TAG_MQTT;

public class MQTTExternalRequest {
    private IMQTTExternalRequest imqttExternalRequest;
    private Context context;

    public MQTTExternalRequest(Context context, IMQTTExternalRequest imqttExternalRequest) {
        this.imqttExternalRequest = imqttExternalRequest;
        this.context = context;
        initReceiver(context);
    }

    private void initReceiver(Context context) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_MQTT_SUBSCRIBE);
        intentFilter.addAction(ACTION_MQTT_PUBLISH);
        intentFilter.addAction(ACTION_MQTT_UNSUBSCRIBE);
        context.registerReceiver(broadcastReceiver, intentFilter);
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String topic = intent.getStringExtra(EXTRA_MQTT_TOPIC);
            if (intent.getAction().equalsIgnoreCase(ACTION_MQTT_PUBLISH)) {
                String message = intent.getStringExtra(EXTRA_MQTT_MESSAGE);
                String qos = intent.getStringExtra(EXTRA_MQTT_QOS);
                imqttExternalRequest.onExternalRequestPublish(topic, message, qos);
            } else if (intent.getAction().equalsIgnoreCase(ACTION_MQTT_SUBSCRIBE)) {
                String owner = intent.getStringExtra(EXTRA_MQTT_RECEIVER);
                String qos = intent.getStringExtra(EXTRA_MQTT_QOS);
                imqttExternalRequest.onExternalRequestSubscribe(topic, owner, qos);
            } else if (intent.getAction().equalsIgnoreCase(ACTION_MQTT_UNSUBSCRIBE)) {
                String owner = intent.getStringExtra(EXTRA_MQTT_RECEIVER);
                imqttExternalRequest.onExternalRequestUnSubscribe(topic, owner);
            }
        }
    };

    public void publishToExternalOwners(String topic, String message, String owner) {
        Intent intent = new Intent(owner);
        intent.putExtra(EXTRA_MQTT_TOPIC, topic);
        intent.putExtra(EXTRA_MQTT_MESSAGE, message);
        context.sendBroadcast(intent);
        Log.e(TAG_MQTT, "PublishToExternalOwners Invoked");
    }
}
