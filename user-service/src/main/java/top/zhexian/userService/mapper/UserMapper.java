package top.zhexian.userService.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.zhexian.common.pojo.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
