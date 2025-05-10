/*
package com.config;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
//测试连接
@SpringBootApplication
public class MqttTestApplication {

    @Value("${spring.mqtt.url}")
    private String broker;

    @Value("${spring.mqtt.username}")
    private String username;

    @Value("${spring.mqtt.password}")
    private String password;

    @Value("${spring.mqtt.client.id}")
    private String clientId;

    public static void main(String[] args) {
        SpringApplication.run(MqttTestApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner() {
        return args -> {
            try {
                System.out.println("尝试连接到: " + broker);

                MqttClient client = new MqttClient(broker, clientId);
                MqttConnectOptions options = new MqttConnectOptions();
                options.setUserName(username);
                options.setPassword(password.toCharArray());
                options.setAutomaticReconnect(true);

                client.connect(options);
                System.out.println("连接成功: " + client.isConnected());

                client.disconnect();
                System.out.println("已断开连接");
            } catch (Exception e) {
                System.err.println("连接失败: " + e.getMessage());
                e.printStackTrace();
            }
        };
    }
}*/
