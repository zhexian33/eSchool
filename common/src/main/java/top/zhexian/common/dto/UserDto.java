package top.zhexian.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import top.zhexian.common.pojo.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDto {
    private String id;
    private String avatar;
    private String nickname;
    private String username;
    private Integer vip;
    private String phoneNumber;
    private String inventCode;
    private String inventUserCode;

    public UserDto(User user) {
        this.id = user.getId();
        this.avatar = user.getAvatar();
        this.nickname = user.getNickname();
        this.username = user.getUsername();
        this.vip = user.getVip();
        this.phoneNumber = user.getPhoneNumber();
        this.inventCode = user.getInventCode();
        this.inventUserCode = user.getInventUserCode();
    }
}
