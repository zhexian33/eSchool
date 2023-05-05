package top.zhexian.timeValueService.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.zhexian.common.pojo.TimeValue;

public interface TimeValueService extends IService<TimeValue> {
    TimeValue saveByUserId(String userId);

    TimeValue updateValue(String userId, Integer value);
}
