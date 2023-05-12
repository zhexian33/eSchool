package top.zhexian.common.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.zhexian.common.tools.R;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/common")
@Slf4j
public class CommonController {

    @Autowired
    private ServletContext servletContext;

    /**
     * 获取验证码并存session
     *
     * @throws IOException
     */
    @GetMapping("/verify/*")
    public void Verify(HttpServletResponse response) throws IOException {
        //定义图形验证码的长、宽、验证码字符数、干扰线宽度
        ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(150, 40, 5, 4);
        //图形验证码写出，可以写出到文件，也可以写出到流
        captcha.write(response.getOutputStream());
        //获取验证码中的文字内容
        String verifyCode = captcha.getCode();
        log.info("verifyCode:" + verifyCode);
        servletContext.setAttribute("verifyCode", verifyCode);
    }

    /**
     * 验证验证码是否正确
     *
     * @param verify 用户输入的验证码
     * @return 是否正确
     */
    @GetMapping("/inVerify")
    public R<String> inVerify(HttpServletRequest request, String verify) {
        if (verify == null) {
            log.info("验证码为空");
            return R.error("验证码为空");
        }
        String code = (String) servletContext.getAttribute("verifyCode");
        log.info("verify," + verify + "," + code);
        if (verify.equals(code)) {
            return R.success("验证码正确");
        } else {
            return R.error("验证码错误");
        }
    }
}