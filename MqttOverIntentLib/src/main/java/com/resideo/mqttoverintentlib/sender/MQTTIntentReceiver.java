package com.resideo.mqttoverintentlib.sender;

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


public class MQTTIntentReceiver implements IMQTTIntentReceiver {
    private IMQTTReceiver mReceiver;
    private Context mContext;
    private String mAction;
    private String action2;

    public MQTTIntentReceiver(Context context, String action, IMQTTReceiver callBack) {
        mContext = context;
        mAction = action;
        mReceiver = callBack;
        IntentFilter filter = new IntentFilter(action);
        context.registerReceiver(intentReceiver, filter);
    }

    @Override
    public void setReceiver(IMQTTReceiver receiver) {
        this.mReceiver = receiver;
    }

    public void publish(String topic, String message, int qos) {
        Intent intent = new Intent();
        intent.setAction(ACTION_MQTT_PUBLISH);
        intent.putExtra(EXTRA_MQTT_TOPIC, topic);
        intent.putExtra(EXTRA_MQTT_MESSAGE, message);
        intent.putExtra(EXTRA_MQTT_QOS, qos);
        mContext.sendBroadcast(intent);
        Log.d(TAG_MQTT, "MQTT_Publish Invoked");
    }

    public void subscribe(String topic, int qos) {
        Intent intent = new Intent();
        intent.setAction(ACTION_MQTT_SUBSCRIBE);
        intent.putExtra(EXTRA_MQTT_RECEIVER, mAction);
        intent.putExtra(EXTRA_MQTT_TOPIC, topic);
        intent.putExtra(EXTRA_MQTT_QOS, qos);
        mContext.sendBroadcast(intent);
        Log.d(TAG_MQTT, "MQTT_Subscribe Invoked");
    }

    public void unsubscribe(String topic) {
        Intent intent = new Intent();
        intent.setAction(ACTION_MQTT_UNSUBSCRIBE);
        intent.putExtra(EXTRA_MQTT_RECEIVER, mAction);
        intent.putExtra(EXTRA_MQTT_TOPIC, topic);
        mContext.sendBroadcast(intent);
        Log.d(TAG_MQTT, "MQTT_UnSubscribe Invoked");
    }

    private BroadcastReceiver intentReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG_MQTT, "MQTT_IntentReceiver_OnReceive Invoked");
            try {
                String topic = intent.getStringExtra(EXTRA_MQTT_TOPIC);
                String message = intent.getStringExtra(EXTRA_MQTT_MESSAGE);
                mReceiver.messageArrived(topic, message);
            } catch (Exception e) {
                Log.e(TAG_MQTT, "MQTT_Intent_Receiver_OnReceive_Error " + e.getMessage());
            }
        }
    };
}
