package tqs.log.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tqs.log.dao.LogMapper;
import tqs.log.model.CodeModel;
import tqs.log.rep.LogRepository;
import tqs.log.service.LogService;

import java.util.Arrays;
import java.util.List;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogMapper logMapper;

    @Override
    public List<CodeModel> code() {
        List<CodeModel> result = logMapper.getCode();
        for (CodeModel s : result) {
            System.out.println(s);
        }
        return result;
    }
}
