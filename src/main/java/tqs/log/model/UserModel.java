package tqs.log.model;

import lombok.Data;

import java.util.Date;


@Data
public class UserModel {

    private long id;
    //登录时用的名字
    private String username;
    //进入之后显示的名字
    private String name;

    private String password;
    private String role;
    //头像
    private String avatar;
    private Date createdAt;
    private Date updatedAt;
    private Boolean published;

    public UserModel() {
    }

    public UserModel(long id, String username, String name, String password, String role, String avatar, Date createdAt, Date updatedAt, Boolean published) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.password = password;
        this.role = role;
        this.avatar = avatar;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.published = published;
    }

    public UserModel(String username, String name, String password, String role, String avatar, Date createdAt, Date updatedAt, Boolean published) {
        this.username = username;
        this.name = name;
        this.password = password;
        this.role = role;
        this.avatar = avatar;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.published = published;
    }
}
