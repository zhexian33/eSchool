package top.zhexian.timeValueService.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.zhexian.common.pojo.TimeValue;
import top.zhexian.common.tools.R;
import top.zhexian.timeValueService.service.TimeValueService;

import java.util.Map;

@RestController
@RequestMapping("/timeValue")
@Slf4j
public class TimeValueController {

    @Autowired
    private TimeValueService timeValueService;


    @PostMapping("/{userId}")
    public R<TimeValue> save(@PathVariable String userId) {
        TimeValue timeValue = timeValueService.saveByUserId(userId);
        return R.success(timeValue);
    }

    @DeleteMapping("/{id}")
    public R<TimeValue> delete(@PathVariable String id) {
        timeValueService.removeById(id);
        return R.success(null);
    }

    @PutMapping
    public R<String> update(@RequestBody Map<String, String> map) {
        String id = map.get("id");
        Integer value = Integer.valueOf(map.get("value"));
        log.info(id + "," + value);


        return R.success(id + "," + value);
    }

    @GetMapping
    public R<String> get(String id, Integer value) {
        log.info(id + "," + value);
        return R.success(id + "," + value);
    }
}
