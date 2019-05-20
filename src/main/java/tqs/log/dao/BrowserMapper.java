package tqs.log.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tqs.log.entity.Browser;
import tqs.log.model.BrowserModel;

import java.util.List;

@Repository
public interface BrowserMapper extends BaseMapper<Browser> {

    @Select("SELECT browser, COUNT(*)num from browser GROUP BY browser")
    List<BrowserModel> getBrowser();
}
