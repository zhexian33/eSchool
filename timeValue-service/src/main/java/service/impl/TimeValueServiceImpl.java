package service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import mapper.TimeValueMapper;
import org.springframework.stereotype.Service;
import service.TimeValueService;
import top.zhexian.common.pojo.TimeValue;

@Service
public class TimeValueServiceImpl extends ServiceImpl<TimeValueMapper, TimeValue> implements TimeValueService {
}
