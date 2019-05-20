package tqs.log.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tqs.log.entity.Addr;
import tqs.log.entity.Log;
import tqs.log.model.*;

import java.util.List;

@Repository
public interface  LogMapper extends BaseMapper<Log> {

    /*
    * limit后期加入分页  limit page,size
    * */


    //状态码
    @Select("select status code, count(status) num from log group by  status DESC limit 5")
    List<CodeModel> getCode();

    //
    @Select("select client, count(client) num from log group by client DESC limit 5")
    List<TopIpModel> getTopIp();

    //访问url
    @Select("select url, count(url) num from log group by url DESC limit 5")
    List<TopUrlModel> getTopUrl();

    //访问pv  是对的
    @Select("select * from (select DATE_FORMAT(timestamp,'%Y-%m-%d') day,count(*)num from log group by DAY) as temp where day = #{date}")
    PVModel getPvModel(String date);

    //访问uv   //todo : count client还是homian 还是host
    @Select("select * from (select DATE_FORMAT(timestamp,'%Y-%m-%d') day,count(DISTINCT 'client')num from log group by DAY) as temp where day = #{date}")
    UVModel getUvModel(String date);

    //错误url
    @Select("select COUNT(url)num, url from log where status like '4%' GROUP BY url DESC LIMIT 5")
    List<ErrorUrlModel> getErrorUlrModel();

    //响应时间url
    @Select("select url, responsetime from log  ORDER BY responsetime DESC")
    List<UrlTimeModel> getResponsetimeModel();

    //查询最新时间
    @Select("SELECT MAX(TIMESTAMP) from log")
    String getNewTime();

    //查找大于logId的log
    @Select("select id logId, ip from `log` where id > #{logId}")
    List<Addr> getLogs(int logId);


    //    select COUNT(`ua`),`ua` FROM `access` GROUP BY `ua` ORDER BY COUNT(*) DESC LIMIT 10;
//    @Select("select COUNT(`ua`),`ua` FROM `log` GROUP BY `ua` ORDER BY COUNT(*) DESC LIMIT 10")

    //模糊匹配  浏览器种类
//    select COUNT(*) chrome from log where ua REGEXP 'Safari/[0123456789.]*$'   chrome浏览器



}
