package com.itxiaofeng.spzx.order;

import com.itxiaofeng.spzx.common.anno.EnableUserTokenFeignInterceptor;
import com.itxiaofeng.spzx.common.anno.EnableUserWebMvcConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableUserWebMvcConfiguration
@SpringBootApplication
@EnableFeignClients(basePackages = {
        "com.itxiaofeng.spzx.feign.cart",
        "com.itxiaofeng.spzx.feign.user",
        "com.itxiaofeng.spzx.feign.product"
})
@EnableUserTokenFeignInterceptor
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class , args) ;
    }

}
