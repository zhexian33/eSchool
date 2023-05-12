package top.zhexian.timeValueService.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;
import top.zhexian.common.pojo.TimeValue;
import top.zhexian.common.tools.R;
import top.zhexian.timeValueService.service.TimeValueService;

@RestController
@RequestMapping("/timeValue")
@Slf4j
public class TimeValueController {

    @Autowired
    private TimeValueService timeValueService;


    @PostMapping("/{id}")
    @Cacheable(cacheNames = "timeValue", key = "#id")
    public R<TimeValue> save(@PathVariable String id) {
        TimeValue timeValue = new TimeValue(id, 0, 0, 0, 0);
        timeValueService.save(timeValue);
        return R.success(timeValue);
    }

    @DeleteMapping("/{id}")
    @CacheEvict(cacheNames = "timeValue", key = "#id")
    public R<TimeValue> deleteById(@PathVariable String id) {
        timeValueService.removeById(id);
        return R.success(null);
    }

    @PutMapping("/value")
    @CachePut(cacheNames = "timeValue", key = "#value.id")
    public R<TimeValue> updateByValue(@RequestBody AddValue value) {
        TimeValue timeValue = timeValueService.updateByValue(value.getId(), value.getValue());
        if (timeValue == null) {
            return R.error("错误的id");
        }
        return R.success(timeValue);
    }

    /**
     * 内部类，用于接收修改光影值的参数并将对应光影值对象存到redis
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    static class AddValue {
        private String id;
        private Integer value;
    }

    @PutMapping("/{id}")
    @CachePut(cacheNames = "timeValue", key = "#id")
    public R<TimeValue> updateById(@PathVariable String id) {
        TimeValue timeValue = timeValueService.getById(id);
        if (timeValue == null) {
            return R.error("错误的id");
        }
        timeValue.setYesterdayValue(timeValue.getTotalValue());
        timeValue.setTodayValue(0);
        timeValueService.updateById(timeValue);
        return R.success(timeValue);
    }

    @GetMapping
    @Cacheable(cacheNames = "timeValue", key = "#id")
    public R<TimeValue> getById(String id) {
        TimeValue timeValue = timeValueService.getById(id);
        return R.success(timeValue);
    }
}
