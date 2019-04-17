package tqs.log.service;

import org.springframework.stereotype.Component;
import tqs.log.entity.User;
import tqs.log.model.UserModel;
import tqs.log.model.request.UserRequest;

import java.util.List;

@Component
public interface UserService {
    UserModel findUserByUserName(String name);

    int createUser(UserRequest.Create create);

    int deleteUser(int id);

    int updateUser(UserRequest.Update update);

    List<UserModel> findByUserName(String username);

    List<UserModel> findAll();

    UserModel findById(int id);

    int batchDelete(Integer[] ids);
}
