package tqs.log.controller;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tqs.log.entity.GoodsInfo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
public class GoodsController {

    @Autowired
    private GoodsRepository goodsRepository;

    @GetMapping(value = "/findby")
    public void findBy(@RequestParam(value = "something") String something){
        //属性集合中是否包含指定值查询
        BoolQueryBuilder builders = QueryBuilders.boolQuery().must(QueryBuilders.rangeQuery("id").from(10).to(20));
        Iterable<GoodsInfo> search = goodsRepository.search(builders);
        search.forEach(goodsInfo->{
            System.out.println(goodsInfo);
        });
    }

    @GetMapping(value = "/findsort")
    public void findSort(){
        //复杂查询自定义排序规则

        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder().withSort(SortBuilders.fieldSort("id").order(SortOrder.ASC));
        Iterable<GoodsInfo> search = goodsRepository.search(builder.build());
        search.forEach(goodsInfo -> {
            System.out.println(goodsInfo);
        });
    }


    //http://localhost:8081/save
    @GetMapping("save")
    public String save(){
        GoodsInfo goodsInfo = new GoodsInfo(System.currentTimeMillis(),
                "商品"+System.currentTimeMillis(),"这是一个测试商品");
        goodsRepository.save(goodsInfo);
        return "success";
    }

    //http://localhost:8888/delete?id=1525415333329
    @GetMapping("delete")
    public String delete(long id){
        goodsRepository.deleteById(id);
        return "success";
    }

    //http://localhost:8888/update?id=1525417362754&name=修改&description=修改
    @GetMapping("update")
    public String update(long id,String name,String description){
        GoodsInfo goodsInfo = new GoodsInfo(id,
                name,description);
        goodsRepository.save(goodsInfo);
        return "success";
    }

    //http://localhost:8081/getOne?id=1555478837041
    @GetMapping("getOne")
    public GoodsInfo getOne(long id){
        GoodsInfo goodsInfo = goodsRepository.findById(id).get();
        return goodsInfo;
    }

    /**
     * 2、查  ++:全文检索（根据整个实体的所有属性，可能结果为0个）
     * @param q
     * @return
     */
    @GetMapping("/select/{q}")
    public List<GoodsInfo> testSearch(@PathVariable String q) {
        QueryStringQueryBuilder builder = new QueryStringQueryBuilder(q);
        Iterable<GoodsInfo> searchResult = goodsRepository.search(builder);
        Iterator<GoodsInfo> iterator = searchResult.iterator();
        List<GoodsInfo> list = new ArrayList<>();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        return list;
    }

//    /**
//     * 3、查   +++：分页、分数、分域（结果一个也不少）
//     * @param page
//     * @param size
//     * @param q
//     * @return
//     * @return
//     */
//    @GetMapping("/{page}/{size}/{q}")
//    public List<GoodsInfo> searchCity(@PathVariable Integer page, @PathVariable Integer size, @PathVariable String q) {
//
//        // 分页参数
//        Pageable pageable = new PageRequest(page, size);
//
//        // 分数，并自动按分排序
//        FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery()
//                .add(QueryBuilders.boolQuery().should(QueryBuilders.matchQuery("name", q)),
//                        ScoreFunctionBuilders.weightFactorFunction(1000)) // 权重：name 1000分
//                .add(QueryBuilders.boolQuery().should(QueryBuilders.matchQuery("message", q)),
//                        ScoreFunctionBuilders.weightFactorFunction(100)); // 权重：message 100分
//
//        // 分数、分页
//        SearchQuery searchQuery = new NativeSearchQueryBuilder().withPageable(pageable)
//                .withQuery(functionScoreQueryBuilder).build();
//
//        Page<GoodsInfo> searchPageResults = goodsRepository.search(searchQuery);
//        return searchPageResults.getContent();
//
//    }




//    //每页数量
//    private Integer PAGESIZE=10;
//
//    //http://localhost:8081/getGoodsList?query=商品
//    //http://localhost:8888/getGoodsList?query=商品&pageNumber=1
//    //根据关键字"商品"去查询列表，name或者description包含的都查询
//    @GetMapping("getGoodsList")
//    public List<GoodsInfo> getList(Integer pageNumber, String query){
//        if(pageNumber==null) pageNumber = 0;
//        //es搜索默认第一页页码是0
//        SearchQuery searchQuery=getEntitySearchQuery(pageNumber,PAGESIZE,query);
//        Page<GoodsInfo> goodsPage = goodsRepository.search(searchQuery);
//        return goodsPage.getContent();
//    }


//    private SearchQuery getEntitySearchQuery(int pageNumber, int pageSize, String searchContent) {
////        FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery()
//        FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery()
//                .add(QueryBuilders.matchPhraseQuery("name", searchContent),
//                        ScoreFunctionBuilders.weightFactorFunction(100))
//                .add(QueryBuilders.matchPhraseQuery("description", searchContent),
//                        ScoreFunctionBuilders.weightFactorFunction(100))
//                //设置权重分 求和模式
//                .scoreMode("sum")
//                //设置权重分最低分
//                .setMinScore(10);
//
//        // 设置分页
//        Pageable pageable = new PageRequest(pageNumber, pageSize);
//        return new NativeSearchQueryBuilder()
//                .withPageable(pageable)
//                .withQuery(functionScoreQueryBuilder).build();
//    }

}

