package com.com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KibanaStart {
    public static void main(String[] args) {
        SpringApplication.run(KibanaStart.class,args);
        System.out.println("启动完成。。。。");
    }
}
