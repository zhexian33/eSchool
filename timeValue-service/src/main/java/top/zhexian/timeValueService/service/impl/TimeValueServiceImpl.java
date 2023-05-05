package top.zhexian.timeValueService.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import top.zhexian.common.tools.SnowFlakeGenerateIdWorker;
import top.zhexian.timeValueService.mapper.TimeValueMapper;
import org.springframework.stereotype.Service;
import top.zhexian.timeValueService.service.TimeValueService;
import top.zhexian.common.pojo.TimeValue;

@Service
@Slf4j
public class TimeValueServiceImpl extends ServiceImpl<TimeValueMapper, TimeValue> implements TimeValueService {

    static SnowFlakeGenerateIdWorker idWorker = SnowFlakeGenerateIdWorker.getIdWorker();

    @Autowired
    private TimeValueMapper timeValueMapper;


    @Override
    public TimeValue saveByUserId(String userId) {
        TimeValue timeValue = new TimeValue(idWorker.generateNextId(), userId, 0, 0, 0, 0);
        log.info(timeValue.toString());
        timeValueMapper.insert(timeValue);
        return timeValue;
    }

    @Override
    public TimeValue updateValue(String userId, Integer value) {
        return null;
    }
}
