package tqs.log.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tqs.log.dao.ServerMapper;
import tqs.log.entity.Server;
import tqs.log.model.request.NginxRequest;
import tqs.log.model.request.NginxRequestCreate;
import tqs.log.model.request.NginxRequestUpdate;
import tqs.log.service.ServerService;
import tqs.log.utils.HttpResult;

import java.util.Date;
import java.util.UUID;

@Service
public class ServerServiceImpl implements ServerService {

    @Autowired
    private ServerMapper serverMapper;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public int createServer(NginxRequest.Create nginx, String host) {
        Server server = modelMapper.map(nginx, Server.class);
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");

        //todo: 通过uuid创建对应的文件
        server.setUuid(uuid);
        server.setHost(host);
        server.setCreatedAt(new Date());
        server.setUpdatedAt(new Date());

        //todo ; 返回的是插入成功的数量，不是server的id
        int id = serverMapper.insert(server);
        return id;
    }

    @Override
    public HttpResult<Boolean> updateServer(NginxRequest.Update nginx) {
        Server server = modelMapper.map(nginx, Server.class);
        server.setUpdatedAt(new Date());
        int n = serverMapper.updateById(server);
        return n > 0 ? HttpResult.success("更新成功", true): HttpResult.fail("更新失败", false);
    }

    @Override
    public HttpResult<Server> findById(int id) {
        Server server = serverMapper.selectById(id);
        return new HttpResult<>(server);
    }

    @Override
    public HttpResult<Boolean> deleteById(int id) {
        Server server = serverMapper.selectById(id);
        if (server == null){
            return new HttpResult().fail("不存在此服务器", false);
        }
        int n = serverMapper.deleteById(id);
        return n > 0 ? HttpResult.success("删除成功", true): HttpResult.fail("删除失败", false);
    }
}
