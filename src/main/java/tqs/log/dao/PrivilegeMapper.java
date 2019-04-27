package tqs.log.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tqs.log.entity.Privilege;
import tqs.log.entity.User;
import tqs.log.model.PrivilegeModel;

import java.util.List;

@Repository
public interface PrivilegeMapper extends BaseMapper<Privilege> {

    @Select("SELECT * FROM privilege WHERE user_id = #{userId}")
    List<Privilege> findByUserId(int userId);

    @Select("SELECT * FROM privilege WHERE server_id = #{serverId}")
    List<Privilege> findByServerId(int serverId);

    @Select("SELECT a.* , b.username, c.name servername FROM privilege as a left join user as b on a.user_id = b.id left join server as c on a.nginx_id = c.id")
    List<PrivilegeModel> findAll();
}
