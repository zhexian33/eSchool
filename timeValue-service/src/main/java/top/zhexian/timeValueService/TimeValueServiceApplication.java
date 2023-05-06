package top.zhexian.timeValueService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableCaching
@ComponentScan({
        "top.zhexian.timeValueService",
        "top.zhexian.common.config"
})
public class TimeValueServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(TimeValueServiceApplication.class, args);
    }
}