package top.zhexian.userService.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.zhexian.common.pojo.User;
import top.zhexian.common.tools.InventCode;
import top.zhexian.common.tools.SnowFlakeGenerateIdWorker;
import top.zhexian.userService.mapper.UserMapper;
import top.zhexian.userService.service.UserService;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    static SnowFlakeGenerateIdWorker idWorker = SnowFlakeGenerateIdWorker.getIdWorker();
    @Autowired
    private UserMapper userMapper;

    public User saveUser(User user) {
        user.setId(idWorker.generateNextId());
        String code = InventCode.getCode();
        LambdaQueryWrapper<User> qw = new LambdaQueryWrapper<>();
        qw.eq(User::getInventCode, code);
        User one = userMapper.selectOne(qw);
        while (one != null) {
            qw = new LambdaQueryWrapper<>();
            qw.eq(User::getInventCode, code);
            code = InventCode.getCode();
            one = userMapper.selectOne(qw);
        }

        user.setInventCode(code);
        userMapper.insert(user);
        return user;
    }
}
