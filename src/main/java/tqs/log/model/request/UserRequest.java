package tqs.log.model.request;

import lombok.Data;

import java.util.Date;

public class UserRequest {

    @Data
    public static class Create{

        //登录时用的名字
        private String username;
        //进入之后显示的名字
        private String name;

        private String password;
        private String role;
        //头像
        private String avatar;
        private Boolean published;
    }

    @Data
    public static class Update{

        private int id;
        //登录时用的名字
        private String username;
        //进入之后显示的名字
        private String name;

//        private String password;
//        private String role;
        //头像
//        private String avatar;
        private Boolean published;
    }
}
