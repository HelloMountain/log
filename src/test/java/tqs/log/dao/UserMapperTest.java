package tqs.log.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tqs.log.LogApplicationTests;
import tqs.log.entity.User;

import static org.junit.Assert.*;

public class UserMapperTest extends LogApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void getUserByName(){
        User user = userMapper.getUserByName("tqs");
//        User user = new User(1, "user", "password", "admin");
        System.out.println(user.getUsername());
    }

}