package tqs.log.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tqs.log.entity.Log;
import tqs.log.model.*;

import java.util.List;

@Repository
public interface LogMapper extends BaseMapper<Log> {

    @Select("select status code, count(status) num from log group by  status DESC limit 5")
    List<CodeModel> getCode();

    @Select("select client, count(client) num from log group by client DESC limit 5")
    List<TopIpModel> getTopIp();

    @Select("select url, count(url) num from log group by  url DESC limit 5")
    List<TopUrlModel> getTopUrl();

    @Select("select DATE_FORMAT(timestamp,'%Y%m%d') day,count('timestamp')num from log group by DAY")
    List<PVModel> getPvModel();

    @Select("select COUNT(url), url from log where status like '4%' GROUP BY url DESC LIMIT 5")
    List<ErrorUlrModel> getErrorUlrModel();


    //    select COUNT(`ua`),`ua` FROM `access` GROUP BY `ua` ORDER BY COUNT(*) DESC LIMIT 10;
//    @Select("select COUNT(`ua`),`ua` FROM `log` GROUP BY `ua` ORDER BY COUNT(*) DESC LIMIT 10")

    //模糊匹配  浏览器种类

}
