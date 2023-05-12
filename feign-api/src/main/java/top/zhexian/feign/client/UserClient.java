package top.zhexian.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import top.zhexian.common.pojo.User;
import top.zhexian.common.tools.R;


@FeignClient("userService")
public interface UserClient {
    @GetMapping("/user/{id}")
    R<User> getById(@PathVariable String id);
}
