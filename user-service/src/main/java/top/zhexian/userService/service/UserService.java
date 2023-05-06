package top.zhexian.userService.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.zhexian.common.pojo.User;

public interface UserService extends IService<User> {
    User saveUser(User user);
}
