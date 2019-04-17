package tqs.log.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tqs.log.dao.ServerMapper;
import tqs.log.entity.Server;
import tqs.log.model.request.NginxRequest;
import tqs.log.service.ServerService;
import tqs.log.utils.HttpResult;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ServerServiceImpl implements ServerService {

    @Autowired
    private ServerMapper serverMapper;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public int createServer(NginxRequest.Create nginx, String host) {
        List<Server> temp = this.findByName(nginx.getName());
        int n = 0;
        if (temp != null && temp.size() != 0){
            return n;
        }
        Server server = modelMapper.map(nginx, Server.class);
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");

        //todo: 通过uuid创建对应的文件
        server.setUuid(uuid);
        server.setHost(host);
        server.setCreatedAt(new Date());
        server.setUpdatedAt(new Date());

        //todo ; 返回的是插入成功的数量，不是server的id
        n = serverMapper.insert(server);
        return n;
    }

    @Override
    public int updateServer(NginxRequest.Update nginx) {
        Server server = modelMapper.map(nginx, Server.class);
        server.setUpdatedAt(new Date());
        int n = serverMapper.updateById(server);
        return n;
    }

    @Override
    public HttpResult<Server> findById(int id) {
        Server server = serverMapper.selectById(id);
        return new HttpResult<>(server);
    }

    @Override
    public List<Server> findByName(String name) {
        QueryWrapper<Server> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", name);
        List<Server> serverList = serverMapper.selectList(queryWrapper);
        return serverList;
    }

    @Override
    public int deleteById(int id) {
        Server server = serverMapper.selectById(id);
        if (server == null) {
            return 0;
        }
        int n = serverMapper.deleteById(id);
        return n;
    }

    @Override
    public List<Server> findAll() {
        List<Server> list = serverMapper.selectList(null);
        return list;
    }
}
