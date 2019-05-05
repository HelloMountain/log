package tqs.log.controller;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import tqs.log.entity.GoodsInfo;

@Repository
public interface GoodsRepository extends ElasticsearchRepository<GoodsInfo,Long> {
}
