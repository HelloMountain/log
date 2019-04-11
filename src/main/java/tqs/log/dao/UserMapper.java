package tqs.log.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tqs.log.entity.User;

@Repository
public interface UserMapper extends BaseMapper<User> {

    @Select("SELECT username, password FROM user WHERE username = #{username}")
    User getUserByName(String username);

    @Select("SELECT password FROM user WHERE username = #{username}")
    String getPasswordByName(String username);

    @Select("SELECT role FROM user WHERE username = #{username}")
    String getRoleByName(String username);
}
