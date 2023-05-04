package top.zhexian.common.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.zhexian.common.tools.R;

@RestController
@RequestMapping("/common")
public class CommonController {
    @GetMapping
    public R<String> getTest() {
        return R.success("success");
    }
}
