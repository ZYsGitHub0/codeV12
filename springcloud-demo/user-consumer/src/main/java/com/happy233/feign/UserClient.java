package com.happy233.feign;

import com.happy233.config.FeignConfig;
import com.happy233.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zy136
 * @date 2021/12/25 17:42
 */
//feign会帮我们生成此接口的代理类，在代理类中完成远程调用
@FeignClient(value = "user-service", configuration = FeignConfig.class)//声明为 feign 的 client 并指定远程调用的 服务名 configuration=指定配置类(配置了日志等级)
@Component
public interface UserClient {
    //与服务提供者入口方法保持一致(controller)
    @RequestMapping("/user/{id}")
    User findById(@PathVariable Long id);
}
