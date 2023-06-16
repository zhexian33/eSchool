package top.zhexian.common.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User implements Serializable {
    private String id;
    private String avatar;
    private String nickname;
    private String username;
    private String password;
    private String payPassword;
    private Integer vip;
    private String phoneNumber;
    private String inventCode;
    private String inventUserCode;
}
