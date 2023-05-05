package top.zhexian.userService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import top.zhexian.feign.client.TimeValueClient;
import top.zhexian.feign.config.DefaultFeignConfiguration;

@SpringBootApplication
@EnableFeignClients(defaultConfiguration = DefaultFeignConfiguration.class, clients = TimeValueClient.class)
@EnableTransactionManagement
public class UserServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }
}