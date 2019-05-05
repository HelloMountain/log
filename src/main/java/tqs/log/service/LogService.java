package tqs.log.service;

import org.springframework.stereotype.Service;
import tqs.log.model.CodeModel;

import java.util.List;

@Service
public interface LogService {

    //统计所有接口的调用次数并显示出现次数最多的前二十的URL

    //统计报错的接口

    //统计HTTP响应状态码
    List<CodeModel> code();


    //统计服务器并发量

    //请求IP

    //User-Agent  获取访问频率最高的前10个User-Agent:

    //流量观察
    //对于一般网站来说流量监控也是比较重要的，所以根据访问时间，可以统计出每天的访问量和一天中平均各个时间段的访问量：


}
