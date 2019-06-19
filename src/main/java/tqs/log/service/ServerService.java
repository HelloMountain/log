package tqs.log.service;

import org.springframework.stereotype.Service;
import tqs.log.base.ApiResponse;
import tqs.log.entity.Server;
import tqs.log.model.request.NginxRequest;
import tqs.log.model.request.NginxRequestCreate;
import tqs.log.model.request.NginxRequestUpdate;
import tqs.log.utils.HttpResult;

import java.util.List;

@Service
public interface ServerService {

    int createServer(NginxRequest.Create nginx);

    int updateServer(NginxRequest.Update nginx);

    Server findById(int id);

    List<Server> findByLikeName(String name);

    Server findByName(String name);

    int deleteById(int id);

    List<Server> findAll();

    int batchDelete(Integer[] ids);
}
