package top.zhexian.feign.client.fallback;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import top.zhexian.common.pojo.TimeValue;
import top.zhexian.feign.client.TimeValueClient;

@Slf4j
public class UserClientFallbackFactory implements FallbackFactory<TimeValueClient> {

    @Override
    public TimeValueClient create(Throwable throwable) {
        return id -> {
            log.error("查询用户异常", throwable);
            return new TimeValue();
        };
    }
}
