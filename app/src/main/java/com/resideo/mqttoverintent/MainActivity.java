package com.resideo.mqttoverintent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.resideo.mqttoverintentlib.receiver.IMQTTExternalRequest;
import com.resideo.mqttoverintentlib.receiver.MQTTExternalRequest;

public class MainActivity extends AppCompatActivity implements IMQTTExternalRequest {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onExternalRequestSubscribe(String topic, String owner, String qos) {

    }

    @Override
    public void onExternalRequestUnSubscribe(String topic, String owner) {

    }

    @Override
    public void onExternalRequestPublish(String topic, String message, String qos) {

    }
}