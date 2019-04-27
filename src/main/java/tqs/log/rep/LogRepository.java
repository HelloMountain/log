package tqs.log.rep;

import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;
import tqs.log.entity.Log;

public interface LogRepository extends ElasticsearchCrudRepository<Log, Integer> {
}
