package top.zhexian.userService.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.zhexian.common.pojo.User;
import top.zhexian.common.tools.InventCode;
import top.zhexian.common.tools.R;
import top.zhexian.common.tools.SnowFlakeGenerateIdWorker;
import top.zhexian.userService.mapper.UserMapper;
import top.zhexian.userService.service.UserService;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    static SnowFlakeGenerateIdWorker idWorker = SnowFlakeGenerateIdWorker.getIdWorker();

    public R<User> saveUser(User user) {
        user.setId(idWorker.generateNextId());
        String code = InventCode.getCode();
        LambdaQueryWrapper<User> qw = new LambdaQueryWrapper<>();
        qw.eq(User::getInventCode, code);
        User one = getOne(qw);
        while (one != null) {
            qw = new LambdaQueryWrapper<>();
            qw.eq(User::getInventCode, code);
            code = InventCode.getCode();
            one = getOne(qw);
        }

        user.setInventCode(code);
        save(user);
        return R.success(user);
    }
}
