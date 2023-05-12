package top.zhexian.userService.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;
import top.zhexian.common.dto.UserDto;
import top.zhexian.common.pojo.User;
import top.zhexian.common.tools.R;
import top.zhexian.feign.client.TimeValueClient;
import top.zhexian.userService.service.UserService;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private TimeValueClient timeValueClient;

    /***
     * 保存user
     * @param user 用户
     * @return 返回成功与否
     */
    @PostMapping("/save")
    public R<UserDto> save(@RequestBody User user) {
        log.info(user.toString());
        LambdaQueryWrapper<User> qw = new LambdaQueryWrapper<>();
        qw.eq(User::getUsername, user.getUsername());
        User one = userService.getOne(qw);
        if (one == null) {
            user = userService.saveUser(user);
            timeValueClient.saveByUserId(user.getId());
            return R.success("注册成功", new UserDto(user));
        } else {
            return R.error("用户名重复");
        }
    }

    /**
     * 根据username验证是否重复
     *
     * @param username 用户名
     * @return code 1 不重复， code 0 重复
     */
    @GetMapping("/repeat")
    @Cacheable(cacheNames = "user", key = "#username")
    public R<String> getByUsernameToRepeat(String username) {
        log.info("username : " + username);
        LambdaQueryWrapper<User> qw = new LambdaQueryWrapper<>();
        User user = userService.getOne(qw.eq(User::getUsername, username));
        if (user == null) {
            return R.success(null);
        } else {
            return R.error("用户名重复");
        }
    }

    /**
     * 根据用户id获取id
     *
     * @param id 用户id
     * @return id对应用户
     */
    @GetMapping("/{id}")
    @Cacheable(cacheNames = "user", key = "#id")
    public R<UserDto> getById(@PathVariable String id) {
        return R.success(new UserDto(userService.getById(id)));
    }

    @PostMapping("/login")
    public R<SaTokenInfo> login(@RequestBody User user) {
        LambdaQueryWrapper<User> qw = new LambdaQueryWrapper<>();
        qw.eq(User::getUsername, user.getUsername()).eq(User::getPassword, user.getPassword());
        user = userService.getOne(qw);
        if (user == null) {
            return R.error("用户名或密码错误");
        }
        StpUtil.login(user.getId());
        StpUtil.getSession().set("user", user);
        return R.success(StpUtil.getTokenInfo());
    }

    @GetMapping("/login")
    public R<UserDto> getCurrentUser() {
        return R.success(new UserDto((User) StpUtil.getSession().get("user")));
    }

    @GetMapping("/login/isLogin")
    public boolean isLogin() {
        return StpUtil.isLogin();
    }

    @DeleteMapping("/logout")
    public R<String> logout() {
        StpUtil.getSession().delete("user");
        StpUtil.logout();
        return R.success("退出登录成功");
    }

    @DeleteMapping("/{id}")
    @CacheEvict(cacheNames = "user", key = "#id")
    public R<UserDto> deleteById(@PathVariable String id) {
        User user = userService.getById(id);
        if (user == null) {
            return R.error("用户不存在");
        }
        timeValueClient.deleteById(id);
        userService.removeById(id);
        return R.success("删除用户成功", new UserDto(user));
    }

    @PutMapping
    @CachePut(cacheNames = "user", key = "#user.id")
    public R<UserDto> update(@RequestBody User user) {
        userService.updateById(user);
        return R.success(new UserDto(user));
    }
}
//    docker search minio
//
//        docker pull minio/minio
//
//        docker run  -p 9000:9000 -p 9090:9090 --name minio \
//        -d --restart=always \
//        -e MINIO_ACCESS_KEY=admin \
//        -e MINIO_SECRET_KEY=2022@minio \
//        -v /usr/dockersys/minio/data:/data \
//        -v /usr/dockersys/minio/config:/root/.minio \
//        minio/minio server /data  --console-address ":9000" --address ":9090"