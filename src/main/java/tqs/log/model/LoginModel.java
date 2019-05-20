package tqs.log.model;

import lombok.Data;

@Data
public class LoginModel {

    private long id;
    //登录时用的名字
    private String username;
    //进入之后显示的名字
    private String name;

    private String password;
    private String role;

    private String avatar;
}
