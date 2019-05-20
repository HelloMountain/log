package tqs.log.service.impl;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import tqs.log.LogApplicationTests;
import tqs.log.controller.GoodsRepository;
import tqs.log.dao.LogMapper;
import tqs.log.entity.GoodsInfo;
import tqs.log.service.ESService;

import static org.junit.Assert.*;

public class ESServiceImplTest extends LogApplicationTests {

    @Autowired
    private ESServiceImpl esService;

    @Autowired
    private LogMapper logMapper;

    @Autowired
    private GoodsRepository goodsRepository;
    @Test
    public void topIP() {
        esService.topIP();
    }

    @Test
    public void getTime(){

    }

    @Test
    public void goodInfo(){
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder().withSort(SortBuilders.fieldSort("id").order(SortOrder.ASC));
        Iterable<GoodsInfo> search = goodsRepository.search(builder.build());
        search.forEach(goodsInfo -> {
            System.out.println(goodsInfo);
        });
    }

    @Test
    public void removeString(){
        String temp = "/zh-CN/events/updates?baby_id=542931&ts_etag=d41d8cd98f00b204e9800998ecf8427e&include_ts=false&include_rt=true&include_ge=true&since=1515683981.79874&skip_invisible=true";
//        temp = temp.split("[?]")[0];
//        System.out.println(temp);

    }
}
