package tqs.log.utils;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import tqs.log.base.ApiResponse;
import tqs.log.dao.AddrMapper;
import tqs.log.dao.BrowserMapper;
import tqs.log.dao.LogMapper;
import tqs.log.entity.Addr;
import tqs.log.entity.Browser;
import tqs.log.entity.Log;
import tqs.log.model.AddrModel;
import tqs.log.rep.LogRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class Scheduler {

    @Autowired
    private LogMapper logMapper;

    @Autowired
    private LogRepository logRepository;

    @Autowired
    private BrowserMapper browserMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AddrMapper addrMapper;

    //每隔一秒运行一次
//    @Scheduled(fixedRate = 1000)
    public void esToSqlTask(){

        String newTime = logMapper.getNewTime();
        if (newTime == null){
            newTime = "2018-05-18T13:00:19.847+08:00";
        }else {
            newTime = newTime.replace(" ", "T") + "+08:00";
        }
        BoolQueryBuilder builders = QueryBuilders.boolQuery().must(QueryBuilders.rangeQuery("timestamp").gt(newTime));
        Iterable<Log> search = logRepository.search(builders);
        search.forEach(log -> {
            System.out.println(log);
            String string = log.getUa();
            addBrowser(string);
            logMapper.insert(log);
        });
    }

    //每隔一秒运行一次,取出log中的数据，创建对应的addr
//    @Scheduled(fixedRate = 1000)
    public void addAddr(){
        int addrMaxId = addrMapper.getMaxLogId();
        List<Addr> addrs = logMapper.getLogs(addrMaxId);
        for (Addr addr:addrs) {
            ArrayList<String> temp = getAddr(addr.getIp());
            if ("中国".equals(temp.get(0))){
                addr.setAddr(temp.get(1));
                addrMapper.insert(addr);
            }
        }
    }

    public void addBrowser(String string){
        String[] split = string.split(" ");
        System.out.println(split[split.length - 1].split("/")[0]);

        String browser = split[split.length - 1].split("/")[0];
        if ("Safari".equals(browser)){
            System.out.println("Chrome");
            browser = "Chrome";
        }
        Browser b = new Browser();
        b.setBrowser(browser);
        browserMapper.insert(b);
    }

    public ArrayList<String> getAddr(String ip){
        if (ip == null){
            return null;
        }
        String url="http://freeapi.ipip.net/"+ip;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ArrayList<String> strbody = restTemplate.exchange(url, HttpMethod.GET, entity,ArrayList.class).getBody();
//        Addr addr = new Addr();
//        addr.setAddr(strbody.get(1));
        for (int i = 0; i < strbody.size(); i++) {
            System.out.println(strbody.get(i));
        }
        return strbody;
    }

}
