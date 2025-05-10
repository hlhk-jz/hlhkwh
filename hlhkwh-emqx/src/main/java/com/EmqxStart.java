package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmqxStart {
    public static void main(String[] args) {
        SpringApplication.run(EmqxStart.class,args);
        System.out.println("启动完成。。。。。。");
    }
}
