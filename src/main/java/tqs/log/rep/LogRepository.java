package tqs.log.rep;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import tqs.log.entity.Log;

@Repository
public interface LogRepository extends ElasticsearchRepository<Log, String> {
}
