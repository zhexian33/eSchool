package top.zhexian.timeValueService.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.zhexian.common.pojo.TimeValue;

public interface TimeValueService extends IService<TimeValue> {
    TimeValue updateByValue(String id, Integer value);
}
