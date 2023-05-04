package top.zhexian.common.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    private String id;
    private String avatar;
    private String nickname;
    private String username;
    private String password;
    private String phoneNumber;
    private String inventCode;
    private String inventUserCode;
}
