package top.zhexian.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import top.zhexian.common.pojo.TimeValue;

@FeignClient("timeValueService")
public interface TimeValueClient {

    @PostMapping("/timeValue/{userId}")
    TimeValue saveByUserId(@PathVariable String userId);
}
