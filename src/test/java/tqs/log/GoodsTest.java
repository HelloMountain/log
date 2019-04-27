package tqs.log;

import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import tqs.log.controller.GoodsRepository;
import tqs.log.entity.GoodsInfo;

public class GoodsTest extends LogApplicationTests {

    @Autowired
    private GoodsRepository goodsRepository;
    @Test
    public void findSort(){
        //复杂查询自定义排序规则

        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder().withSort(SortBuilders.fieldSort("id").order(SortOrder.ASC));
        Iterable<GoodsInfo> search = goodsRepository.search(builder.build());
        search.forEach(goodsInfo -> {
            System.out.println(goodsInfo);
        });
    }
}
