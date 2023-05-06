package top.zhexian.timeValueService.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.zhexian.common.pojo.TimeValue;
import top.zhexian.timeValueService.mapper.TimeValueMapper;
import top.zhexian.timeValueService.service.TimeValueService;

@Service
@Slf4j
public class TimeValueServiceImpl extends ServiceImpl<TimeValueMapper, TimeValue> implements TimeValueService {

    @Autowired
    private TimeValueMapper timeValueMapper;

    @Override
    public TimeValue updateByValue(String id, Integer value) {
        TimeValue timeValue = timeValueMapper.selectById(id);
        if (timeValue == null) {
            return null;
        }
        timeValue.setTotalValue(timeValue.getTotalValue() + value);
        timeValue.setTodayValue(timeValue.getTodayValue() + value);
        log.info(timeValue.toString());
        timeValueMapper.updateById(timeValue);
        return timeValue;
    }
}
