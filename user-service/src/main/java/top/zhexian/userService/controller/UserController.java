package top.zhexian.userService.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;
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
    @CachePut(cacheNames = "user", key = "'ALL'")
    public R<User> save(@RequestBody User user) {
        log.info(user.toString());
        LambdaQueryWrapper<User> qw = new LambdaQueryWrapper<>();
        qw.eq(User::getUsername, user.getUsername());
        User one = userService.getOne(qw);
        if (one == null) {
            user = userService.saveUser(user);
            timeValueClient.saveByUserId(user.getId());
            return R.success("注册成功", user);
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
    @GetMapping
    @Cacheable(cacheNames = "user", key = "#username")
    public R<User> getByUsernameToRepeat(String username) {
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
    public R<User> getById(@PathVariable String id) {
        return R.success(userService.getById(id));
    }

    @PostMapping("/login")
    @Cacheable(cacheNames = "user", key = "#user.username")
    public R<User> login(@RequestBody User user) {
        log.info(user.toString());
        LambdaQueryWrapper<User> qw = new LambdaQueryWrapper<>();
        qw.eq(User::getUsername, user.getUsername()).eq(User::getPassword, user.getPassword());
        user = userService.getOne(qw);
        if (user == null) {
            return R.error("用户名或密码错误");
        }
        return R.success(user);
    }

    @PostMapping("/logout")
    public R<User> logout() {
        return R.success(null);
    }

    @DeleteMapping("/{id}")
    @CacheEvict(cacheNames = "user", key = "#id")
    public R<User> deleteById(@PathVariable String id) {
        timeValueClient.deleteById(id);
        userService.removeById(id);
        return R.success(null);
    }

    @PutMapping
    @CachePut(cacheNames = "user", key = "#user.id")
    public R<User> update(@RequestBody User user) {
        userService.updateById(user);
        return R.success(user);
    }
}
