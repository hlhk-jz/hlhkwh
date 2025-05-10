package com;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MqttTest {
    private static final String BROKER = "tcp://192.168.194.131:1883";  // EMQ X服务器地址
    private static final String TOPIC = "test/topic";
    private static final String CLIENT_ID = "JavaTestClient";
    private static final String USERNAME = "whemqx";  // 如果需要认证
    private static final String PASSWORD = "123456";  // 如果需要认证

    public static void main(String[] args) {
        try {
            // 创建MQTT客户端
            MqttClient mqttClient = new MqttClient(BROKER, CLIENT_ID, new MemoryPersistence());

            // 设置回调
            mqttClient.setCallback(new MqttCallbackExtended() {
                @Override
                public void connectComplete(boolean reconnect, String serverURI) {
                    System.out.println("连接成功: " + serverURI + ", Reconnect: " + reconnect);
                    try {
                        // 连接成功后订阅主题
                        mqttClient.subscribe(TOPIC, 1);
                        System.out.println("已订阅主题: " + TOPIC);

                        // 发布测试消息
                        String payload = "Hello, EMQ X!";
                        MqttMessage message = new MqttMessage(payload.getBytes());
                        message.setQos(1);
                        mqttClient.publish(TOPIC, message);
                        System.out.println("消息已发布: " + payload);
                    } catch (MqttException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void connectionLost(Throwable cause) {
                    System.err.println("连接丢失: " + cause.getMessage());
                }

                @Override
                public void messageArrived(String topic, MqttMessage message) {
                    System.out.println("收到消息: " + new String(message.getPayload()) + " from topic: " + topic);
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {
                    System.out.println("消息发送完成");
                }
            });

            // 配置连接选项
            MqttConnectOptions options = new MqttConnectOptions();
            options.setCleanSession(true);
            options.setAutomaticReconnect(true);
            options.setConnectionTimeout(30);
            options.setKeepAliveInterval(60);

            // 如果需要认证，设置用户名和密码
            if (USERNAME != null && !USERNAME.isEmpty()) {
                options.setUserName(USERNAME);
                options.setPassword(PASSWORD.toCharArray());
            }

            // 连接服务器
            System.out.println("正在连接到: " + BROKER);
            mqttClient.connect(options);
            System.out.println("连接状态: " + mqttClient.isConnected());

            // 保持主线程运行一段时间，以便接收消息
            Thread.sleep(5000);

            // 断开连接
            if (mqttClient.isConnected()) {
                mqttClient.disconnect();
                System.out.println("已断开连接");
            }
        } catch (MqttException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}