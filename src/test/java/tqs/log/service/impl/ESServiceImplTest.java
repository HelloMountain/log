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
import tqs.log.entity.GoodsInfo;
import tqs.log.service.ESService;

import static org.junit.Assert.*;

public class ESServiceImplTest extends LogApplicationTests {

    @Autowired
    private ESServiceImpl esService;

    @Autowired
    private GoodsRepository goodsRepository;
    @Test
    public void topIP() {
        esService.topIP();
    }

    @Test
    public void goodInfo(){
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder().withSort(SortBuilders.fieldSort("id").order(SortOrder.ASC));
        Iterable<GoodsInfo> search = goodsRepository.search(builder.build());
        search.forEach(goodsInfo -> {
            System.out.println(goodsInfo);
        });
    }
}
