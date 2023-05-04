package top.zhexian.userService.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.zhexian.common.pojo.User;
import top.zhexian.common.tools.R;
import top.zhexian.common.tools.SnowFlakeGenerateIdWorker;
import top.zhexian.userService.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    static SnowFlakeGenerateIdWorker idWorker = SnowFlakeGenerateIdWorker.getIdWorker();
    @Autowired
    private UserService userService;

    /***
     * 保存user
     * @param user 用户
     * @return 返回成功与否
     */
    @PostMapping("/save")
    public R<User> save(@RequestBody User user) {
        LambdaQueryWrapper<User> qw = new LambdaQueryWrapper<>();
        qw.eq(User::getUsername, user.getUsername());
        User one = userService.getOne(qw);
        if (one == null) {
            userService.saveUser(user);
            return R.success(user);
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
    public R<User> getById(@PathVariable String id) {
        return R.success(userService.getById(id));
    }

    @PostMapping("/login")
    public R<User> login(HttpServletResponse response, @RequestBody User user) {
        LambdaQueryWrapper<User> qw = new LambdaQueryWrapper<>();
        qw.eq(User::getUsername, user.getUsername()).eq(User::getPassword, user.getPassword());
        user = userService.getOne(qw);
        if (user == null) {
            return R.error("用户名或密码错误");
        }
        Cookie cookie = new Cookie("user", user.getId());
        cookie.setMaxAge(60 * 60 * 60);
        response.addCookie(cookie);
        return R.success(user);
    }

    @PostMapping("/logout")
    public R<User> logout(HttpServletRequest request) {
        for (Cookie cookie : request.getCookies()) {
            System.out.println(cookie);
        }
        return R.success(null);
    }
}
