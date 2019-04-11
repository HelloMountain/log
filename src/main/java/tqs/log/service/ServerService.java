package tqs.log.service;

import org.springframework.stereotype.Service;
import tqs.log.entity.Server;
import tqs.log.model.request.NginxRequest;
import tqs.log.model.request.NginxRequestCreate;
import tqs.log.model.request.NginxRequestUpdate;
import tqs.log.utils.HttpResult;

@Service
public interface ServerService {

    int createServer(NginxRequest.Create nginx, String host);

    HttpResult<Boolean> updateServer(NginxRequest.Update nginx);

    HttpResult<Server> findById(int id);

    HttpResult<Boolean> deleteById(int id);
}
