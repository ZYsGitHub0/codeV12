package com.happy233.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import reactor.core.publisher.Mono;

/**
 * 配置全局过滤器
 *
 * @author zy136
 * @date 2021/12/25 21:49
 */

@Slf4j
@Configuration
public class GlobalFilterConfig {

    @Bean
    @Order(-1)
//@Order 控制过滤器链顺序 越小优先级越高
    GlobalFilter globalFilter1() {
        return ((exchange, chain) -> {
            log.info("Filter1.pre.....");
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                log.info("Filter1.post...");
            }));
        });
    }

    @Bean
    @Order(0)
    GlobalFilter globalFilter2() {
        return ((exchange, chain) -> {
            log.info("Filter2.pre.....");
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                log.info("Filter2.post...");
            }));
        });
    }


}
