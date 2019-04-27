package tqs.log.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tqs.log.LogApplicationTests;
import tqs.log.dao.UserMapper;
import tqs.log.entity.User;
import tqs.log.model.UserModel;
import tqs.log.service.UserService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class UserServiceImplTest extends LogApplicationTests {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Test
    public void likeUserByUserName() {
        String username = "qs";
        List<UserModel> list = userService.likeUserByUserName(username);
        list.forEach(userModel -> System.out.println(userModel));
    }

    @Test
    public void createUser() {
    }

    @Test
    public void deleteUser() {
    }

    @Test
    public void updateUser() {
    }

    @Test
    public void findAll() {
    }

    @Test
    public void findByUserName() {

    }

    @Test
    public void findById() {
    }

    @Test
    public void batchDelete() {

        Integer[] ids = {4, 5};
//        List<Integer> ids = new ArrayList<>();
//        ids.add(5);
//        ids.add(4);

        int n = userMapper.deleteBatchIds(Arrays.asList(ids));
    }
}
