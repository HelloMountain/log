package tqs.log.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@Data
@TableName(value = "user")
public class User{

    private long id;
    private String username;
    private String  password;
    private String role;

    public User() {
    }

    public User(long id, String username, String password, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
