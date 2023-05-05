package top.zhexian.userService.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.zhexian.common.pojo.User;
import top.zhexian.common.tools.R;

public interface UserService extends IService<User> {
    User saveUser(User user);
}
