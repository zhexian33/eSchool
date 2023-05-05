package top.zhexian.feign.config;


import feign.Logger;
import org.springframework.context.annotation.Bean;
import top.zhexian.feign.client.fallback.UserClientFallbackFactory;

public class DefaultFeignConfiguration {
    @Bean
    public Logger.Level level() {
        return Logger.Level.BASIC;
    }

    @Bean
    public UserClientFallbackFactory userClientFallbackFactory() {
        return new UserClientFallbackFactory();
    }
}
