package tqs.log.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import tqs.log.rep.LogRepository;
import tqs.log.service.LogService;

public class LogServiceImpl implements LogService {

    @Autowired
    private LogRepository logRepository;
}
