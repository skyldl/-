package com.itxiaofeng.spzx.manager;


import com.itxiaofeng.spzx.manager.properties.MinioProperties;
import com.itxiaofeng.spzx.manager.properties.UserAuthProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableConfigurationProperties(value = {UserAuthProperties.class, MinioProperties.class})
@ComponentScan(basePackages = {"com.itxiaofeng.spzx"})
@SpringBootApplication
public class ManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManagerApplication.class,args);
    }
}
