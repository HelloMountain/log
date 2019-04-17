package tqs.log.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tqs.log.dao.UserMapper;
import tqs.log.entity.User;
import tqs.log.model.UserModel;
import tqs.log.model.request.UserRequest;
import tqs.log.service.UserService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserModel findUserByUserName(String username) {
        User user = userMapper.getUserByName(username);
        UserModel userModel = modelMapper.map(user, UserModel.class);
        return userModel;
    }

    @Override
    public int createUser(UserRequest.Create create) {

        User user = modelMapper.map(create, User.class);
        user.setCreatedAt(new Date());
        user.setUpdatedAt(new Date());
        int n = userMapper.insert(user);
        return n;
    }

    @Override
    public int deleteUser(int id) {
        int n = userMapper.deleteById(id);
        return n;
    }

    @Override
    public int updateUser(UserRequest.Update update) {
        User user = modelMapper.map(update, User.class);
        user.setUpdatedAt(new Date());
        int n = userMapper.updateById(user);
        return n;
    }

    @Override
    public List<UserModel> findAll() {
        List<User> users = userMapper.selectList(null);
        List<UserModel> result = new ArrayList<>();

        if (users != null && users.size() > 0){
            users.forEach(user -> {
                result.add(modelMapper.map(user, UserModel.class));
            });
        }
        return result;
    }

    @Override
    public List<UserModel> findByUserName(String username) {
        List<User> users = userMapper.findUserByUserName(username);
        List<UserModel> result = new ArrayList<>();

        if (users != null && users.size() > 0){
            users.forEach(user -> {
                result.add(modelMapper.map(user, UserModel.class));
            });
        }
        return result;
    }

    @Override
    public UserModel findById(int id) {
        User user = userMapper.selectById(id);
        UserModel userModel = modelMapper.map(user, UserModel.class);
        return userModel;
    }

    @Override
    public int batchDelete(Integer[] ids) {
        int n = userMapper.deleteBatchIds(Arrays.asList(ids));
        return n;
    }
}
