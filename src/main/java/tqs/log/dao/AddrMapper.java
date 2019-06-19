package tqs.log.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tqs.log.entity.Addr;
import tqs.log.model.MapModel;

import java.util.List;

@Repository
public interface AddrMapper extends BaseMapper<Addr> {

    //获取addr最大id
    @Select("select IFNull(MAX(log_id), 0) logId from addr")
    int getMaxLogId();

    @Select("select COUNT(addr)num, addr from addr GROUP BY addr")
    List<MapModel> getMap();
}
