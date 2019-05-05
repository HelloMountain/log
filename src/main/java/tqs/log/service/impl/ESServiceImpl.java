package tqs.log.service.impl;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import tqs.log.dao.LogMapper;
import tqs.log.entity.Log;
import tqs.log.rep.LogRepository;
import tqs.log.service.ESService;

@Service
public class ESServiceImpl implements ESService {

    @Autowired
    private LogRepository logRepository;

    @Autowired
    private LogMapper logMapper;

    @Override
    public void topIP() {
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        Iterable<Log> search = logRepository.search(builder.build());
        search.forEach(log -> {
            System.out.println(log);
            logMapper.insert(log);
        });
    }
}
