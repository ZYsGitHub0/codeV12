package com.happy233;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author zy136
 * @date 2021/12/25 15:39
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrix//开启熔断器
@EnableFeignClients //开启Feign支持
public class ConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }

    @Bean
    @LoadBalanced//负载运河 默认为 轮询
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
